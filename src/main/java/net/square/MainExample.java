package net.square;

import net.square.settings.ProxyCheckSettings;
import net.square.wrapper.impl.SuccessWrapper;

import java.time.Duration;
import java.util.concurrent.ExecutionException;

/**
 * This class provides an example of how to use the ProxyAPI class to fetch information about a given IP address.
 * It demonstrates both synchronous and asynchronous methods of accessing the information.
 */
@SuppressWarnings("unused")
public class MainExample {

    /**
     * The main method for executing the program. It demonstrates the usage of the ProxyAPI class.
     *
     * @param args The command line arguments.
     */
    public static void main(String[] args) {

        // Init with {@link lombok.Builder}
        final var proxyAPI = ProxyAPI.builder()
            // If changes need to be made to the license key, this method can be called.
            // However, if you do not have a plan, you do not have to
            // do this, otherwise it will take the Default parameter.
            .proxyKey("license_here")
            // How long should the objects stay in the cache?
            .cacheDuration(Duration.ofMinutes(60))
            // Set up your own net.square.settings to limit the response and to get less information.
            .proxyCheckSettings(ProxyCheckSettings.builder()
                                    // More information here: https://proxycheck.io/api/
                                    .vpn(3)      // Should VPNChecks run?
                                    .asn(true)   // Should ASN be queried?
                                    .node(true)  // Should node be queried?
                                    .time(true)  // Get if the API should tell you the time it took to query the address
                                    // Should the time flag be enabled?
                                    .port(true)  // Get if the API should tell you the active port the proxy is using
                                    // Should the port flag be enabled?
                                    .seen(true)  // Most recent time we saw this IP Address operating as a proxy server.
                                    .risk(2)     // Risk score
                                    .shrt(false) // Compress response
                                    .days(2)     // Check our database for Proxies that we saw within the past 48 hours
                                    .build())
            // Build class
            .build();

        // For testing, the IP address of the provider https://prohosting24.de was used here.
        final var address = "45.142.115.247";

        // Thus, it is possible to access the information of an IP synchronously.
        try {
            SuccessWrapper successWrapper = proxyAPI.fetchAddressDataForIP(address);

            System.out.println(successWrapper.getAddressWrapper().getProxy());
            System.out.println(successWrapper.getAddressWrapper().getOperatorWrapper().getAnonymity());

        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }

        // Thus, it is possible to access the information of an IP asynchronously.
        proxyAPI.fetchAddressDataForIPAsync(address).whenComplete((successWrapper, throwable) -> {

            // The exception is always null if everything worked properly.
            // If an error occurred during the process, this exception will
            // not be null and will be thrown. The process will also be terminated.
            if (throwable != null) {
                throwable.printStackTrace();
            }

            System.out.println(successWrapper.getAddressWrapper().getProxy());
            System.out.println(successWrapper.getAddressWrapper().getOperatorWrapper().getAnonymity());
        });
    }
}
