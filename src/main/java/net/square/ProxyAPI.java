package net.square;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.SneakyThrows;
import net.square.exceptions.impl.AddressDataFetchingException;
import net.square.exceptions.impl.ProxyCheckBlockingException;
import net.square.settings.ProxyCheckSettings;
import net.square.wrapper.impl.SuccessWrapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.time.Duration;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;
import java.util.concurrent.ExecutionException;

@SuppressWarnings({"unused", "UnusedAssignment"})
@Builder
public class ProxyAPI {

    /**
     * The URL for the API endpoint.
     */
    private static final String API_URL = "https://proxycheck.io/v2/%s?key=%s";

    /**
     * The license key used for accessing the proxy check API.
     */
    @Getter
    private String proxyKey = "license_key";

    /**
     * Represents the settings for proxy checking.
     */
    private ProxyCheckSettings proxyCheckSettings = ProxyCheckSettings.builder().build();

    /**
     * The duration for which a cached entry will remain valid.
     */
    @Getter
    private Duration cacheDuration = Duration.ofMinutes(60);

    /**
     * The cacheCat variable is an instance of the Guava LoadingCache class. This cache is used to store IP addresses
     * and their corresponding JsonObject data retrieved from a website
     * .
     * The cacheCat instance is built using the CacheBuilder class, which allows configuring various cache properties
     * and behaviors.
     * The cache is configured to expire entries after a certain duration specified by the cacheDuration variable.
     * When an entry expires, it will be removed from the cache and must be fetched again from the website when
     * accessed again.
     * The cacheCat instance uses a CacheLoader implementation to fetch the data for a given IP address by calling
     * the fetchData method.
     * The result is then stored in the cache. If the fetchData method returns null, an exception will be thrown.
     * It is recommended to install a separate cache alongside this cache for better performance and flexibility.
     */
    private final LoadingCache<String, SuccessWrapper> cacheCat = CacheBuilder.newBuilder()
        .expireAfterWrite(cacheDuration)
        .build(CacheLoader.from(this::fetchData));

    /**
     * Fetches address data for the given IP address.
     *
     * @param ipAddress The IP address for which to fetch the data.
     * @return The {@link SuccessWrapper} object containing the fetched address data.
     * @throws ExecutionException If an error occurs during the execution of the method.
     * @throws NullPointerException If the ipAddress argument is null.
     */
    public SuccessWrapper fetchAddressDataForIP(@NonNull String ipAddress) throws ExecutionException {
        // Checks if the passed argument is null. There are some jokers :P
        Validation.checkNotNull(ipAddress, "Field ipAddress cannot be null");
        return cacheCat.get(ipAddress);
    }

    /**
     * Fetches address data for the given IP address asynchronously.
     *
     * @param ipAddress The IP address for which to fetch the data.
     * @return A CompletableFuture that resolves to the {@link SuccessWrapper} object containing the fetched address data.
     * @throws NullPointerException If the ipAddress argument is null.
     */
    public CompletableFuture<SuccessWrapper> fetchAddressDataForIPAsync(@NonNull String ipAddress) {
        // Checks if the passed argument is null. There are some jokers :P
        Validation.checkNotNull(ipAddress, "Field ipAddress cannot be null");
        return CompletableFuture.supplyAsync(() -> {
            try {
                return fetchAddressDataForIP(ipAddress);
            } catch (ExecutionException e) {
                throw new CompletionException(e);
            }
        });
    }

    /**
     * Fetches data for the given IP address.
     *
     * @param ipAddress The IP address for which to fetch the data.
     * @return The SuccessWrapper object containing the fetched address data.
     * @throws AddressDataFetchingException If an error occurs during the fetching of the data.
     * @throws NullPointerException If the ipAddress argument is null.
     */
    @SneakyThrows
    private SuccessWrapper fetchData(@NonNull String ipAddress) {
        JsonObject jsonObject;
        try {
            jsonObject = parseJsonObjectFromURL(formatURL(ipAddress));
        } catch (IOException e) {
            throw new AddressDataFetchingException("Failed to fetch data for address %s".formatted(ipAddress), e);
        }

        // Validation.checkNotNull(jsonObject, "Object is null. No internet connection?");
        // Validation.checkNotNull(jsonObject.get("status"), "Invalid object. Maybe timeout?");

        // Processing of reports from https://proxycheck.io
        if (isBlockingResponse(jsonObject)) {

            var status  = jsonObject.get("status").getAsString().toUpperCase();
            var message = jsonObject.get("message").getAsString();

            throw new ProxyCheckBlockingException("%s: %s".formatted(status, message));
        }
        return new SuccessWrapper(jsonObject, ipAddress);
    }

    /**
     * Checks if the response is blocking based on the status field of the provided JsonObject.
     *
     * @param jsonObject The JsonObject to check.
     * @return true if the response is blocking, false otherwise.
     */
    @SneakyThrows
    private boolean isBlockingResponse(@NonNull JsonObject jsonObject) {
        final String status = jsonObject.get("status").getAsString();
        return status.equalsIgnoreCase("error") || status.equalsIgnoreCase("denied");
    }

    /**
     * Parses a JsonObject from a given URL.
     *
     * @param url The URL to fetch the JsonObject from.
     * @return The parsed JsonObject.
     * @throws IOException If an error occurs during the fetching of the JsonObject.
     * @throws NullPointerException If the url argument is null.
     */
    private JsonObject parseJsonObjectFromURL(@NonNull String url) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new URL(url).openStream()))) {
            return JsonParser.parseReader(reader).getAsJsonObject();
        }
    }

    /**
     * Formats the URL for fetching data related to the given address.
     *
     * @param address The address for which to format the URL.
     * @return The formatted URL as a string.
     */
    private String formatURL(@NonNull String address) {

        // Use StringBuilder for efficient string concatenation
        StringBuilder formatted = new StringBuilder(String.format(API_URL, address, proxyKey));

        // Append query parameters
        appendQueryParam(formatted, "vpn", this.proxyCheckSettings.getVpn());
        appendQueryParam(formatted, "asn", this.proxyCheckSettings.isAsn() ? 1 : 0);
        appendQueryParam(formatted, "node", this.proxyCheckSettings.isNode() ? 1 : 0);
        appendQueryParam(formatted, "time", this.proxyCheckSettings.isTime() ? 1 : 0);
        appendQueryParam(formatted, "port", this.proxyCheckSettings.isPort() ? 1 : 0);
        appendQueryParam(formatted, "seen", this.proxyCheckSettings.isSeen() ? 1 : 0);
        appendQueryParam(formatted, "short", this.proxyCheckSettings.isShrt() ? 1 : 0);
        appendQueryParam(formatted, "risk", String.valueOf(this.proxyCheckSettings.getRisk()));
        appendQueryParam(formatted, "days", String.valueOf(this.proxyCheckSettings.getDays()));

        // Convert StringBuilder to String before returning
        return formatted.toString();
    }

    /**
     * Appends a query parameter to a StringBuilder.
     *
     * @param builder The StringBuilder to append the query parameter to.
     * @param key The key of the query parameter.
     * @param value The value of the query parameter.
     */
    private void appendQueryParam(StringBuilder builder, String key, Object value) {
        // Append "&key=value" to the StringBuilder
        builder.append("&").append(key).append("=").append(value);
    }
}