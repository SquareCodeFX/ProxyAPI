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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.time.Duration;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;
import java.util.concurrent.ExecutionException;
import java.util.function.BiConsumer;
import java.util.function.Function;

@SuppressWarnings({"unused", "UnusedAssignment"})
@Builder
public class ProxyAPI {

    // https://proxycheck.io is an online security service offering Proxy
    // and VPN detection and generalised IP location information.
    private static final String API_URL = "https://proxycheck.io/v2/%s?key=%s";

    // If you have a plan on https://proxycheck.io, you should enter your key here. If you don't have one, you are
    // free to make 1000 requests per day for your own server/computer IP. I recommend buying a plan though.
    @Getter
    private String proxyKey = "license_key";

    // Settings for the response of the URL
    private ProxyCheckSettings proxyCheckSettings = ProxyCheckSettings.builder().build();

    // In which time unit should the duration time last?
    @Getter
    private Duration cacheDuration = Duration.ofMinutes(60);

    // To avoid unnecessary requests, I have implemented the Guava LoadingCache. This keeps an IP in the cache for
    // 60 minutes, after which it is removed and must be fetched again from the website when it is accessed again.
    // I recommend installing an own cache beside this one.
    private final LoadingCache<String, JsonObject> cacheCat = CacheBuilder.newBuilder()
        .expireAfterWrite(cacheDuration)
        .build(CacheLoader.from(this::fetchData));

    /**
     * Looks up an {@link JsonObject} object for the passed IPv4 address inside the cache. If not cached, the cache
     * attempts to fetch this object via {@link ProxyAPI#fetchData(String)}.
     * <br>
     * The cached entry will expire after every access after the given time
     * (see {@link #cacheDuration}).
     * <br>
     * Will throw if the cache fails during the fetch or when the fetched data returns null.
     *
     * @param ipAddress A IPv4 address in string representation.
     * @return An {@link JsonObject} object for the given ipAddress
     * @throws ExecutionException Thrown when cache returned null or when an exception was thrown inside the cache.
     */
    public JsonObject fetchAddressDataForIP(@NonNull String ipAddress) throws ExecutionException {
        // Checks if the passed argument is null. There are some jokers :P
        Validation.checkNotNull(ipAddress, "Field ipAddress cannot be null");
        return cacheCat.get(ipAddress);
    }

    /**
     * Wraps the execution of {@link #fetchAddressDataForIP(String)} inside a {@link CompletableFuture} to retrieve
     * its result asynchronously.
     * <br>
     * <p>
     * Thrown {@link Exception}s, like {@link AddressDataFetchingException}, have to be handled by using
     * {@link CompletableFuture#exceptionally(Function)} or {@link CompletableFuture#whenComplete(BiConsumer)}.
     *
     * @param ipAddress A IPv4 address in string representation.
     * @return The {@link JsonObject} object
     */
    public CompletableFuture<JsonObject> fetchAddressDataForIPAsync(@NonNull String ipAddress) {
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
     * Converts the response body received by the HTTP GET request to the
     * <a href="https://proxycheck.io">https://proxycheck.io</a> API
     * to an {@link JsonObject} object.
     *
     * @param ipAddress A IPv4 address represented as a string.
     * @return An {@link JsonObject} object containing the information of the response body.
     */
    @SneakyThrows
    private JsonObject fetchData(@NonNull String ipAddress) {
        JsonObject jsonObject;
        try {
            jsonObject = parseJsonObjectFromURL(formatURL(ipAddress));
        } catch (IOException e) {
            throw new AddressDataFetchingException("Failed to fetch data for address %s".formatted(ipAddress), e);
        }

        Validation.checkNotNull(jsonObject, "Object is null. No internet connection?");
        Validation.checkNotNull(jsonObject.get("status"), "Invalid object. Maybe timeout?");

        // Processing of reports from https://proxycheck.io
        if (isBlockingResponse(jsonObject)) {

            var status  = jsonObject.get("status").getAsString().toUpperCase();
            var message = jsonObject.get("message").getAsString();

            throw new ProxyCheckBlockingException("%s: %s".formatted(status, message));
        }
        return jsonObject;
    }

    /**
     * This method processes the status as well as the messages from
     * <a href="https://proxycheck.io">https://proxycheck.io</a>
     * and passes them to the consumer in the form of an exception.
     * <br>
     * <p>
     * In this method, not all messages from <a href="https://proxycheck.io">https://proxycheck.io</a>
     * are processed. Only the problems that have an
     * impact on
     * the functionality of the API are caught here. If you want to have a full list of all possible errors you can
     * have a look at it here:
     * <a href="https://proxycheck.io/api/#test_console">https://proxycheck.io/api/#test_console</a>
     *
     * @param jsonObject The object from the <a href="https://proxycheck.io">https://proxycheck.io</a> website
     *                   <p>
     *                   It will throw an {@link ProxyCheckBlockingException} is thrown if a message is present
     *                   in the return value. Since these are always negative in nature, the exception was named
     *                   MalfunctionException.
     */
    @SneakyThrows
    private boolean isBlockingResponse(@NonNull JsonObject jsonObject) {
        final String status = jsonObject.get("status").getAsString();
        return status.equalsIgnoreCase("error") || status.equalsIgnoreCase("denied");
    }

    /**
     * Converts the response {@link URL#openStream()} of a URL to a {@link JsonObject}.
     *
     * @param url The URL string from which to read and convert the response of.
     * @return The object if it was successfully converted.
     * @throws IOException Thrown when an error occurred while reading from the {@link java.io.InputStream} of the URL.
     */
    private JsonObject parseJsonObjectFromURL(@NonNull String url) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new URL(url).openStream()))) {
            return JsonParser.parseReader(reader).getAsJsonObject();
        }
    }

    /**
     * Formats a URL with the provided address and proxy key, along with additional query parameters based on proxy check settings.
     *
     * @param address The address to include in the URL.
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
     * Appends a query parameter to the StringBuilder with the specified key and value.
     *
     * @param builder The StringBuilder to which the parameter will be appended.
     * @param key The key of the query parameter.
     * @param value The value of the query parameter.
     */
    private void appendQueryParam(StringBuilder builder, String key, Object value) {
        // Append "&key=value" to the StringBuilder
        builder.append("&").append(key).append("=").append(value);
    }
}