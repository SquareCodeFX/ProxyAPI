package net.square.wrapper.impl;

import com.google.gson.JsonObject;
import lombok.Getter;
import net.square.wrapper.DefaultWrapper;

/**
 * The SuccessWrapper class represents a wrapper for a successful JSON object response.
 * It extends the DefaultWrapper class, which provides utility methods for retrieving JSON values.
 */
@Getter
public class SuccessWrapper extends DefaultWrapper {

    /**
     * The status variable represents the status of a response.
     * It is a private final field of type String.
     * <p>
     * The value of the status variable is retrieved from a JsonObject using the getJsonValue() method of the DefaultWrapper class.
     * If the JsonObject is null or does not contain the key "status", the value of the ERROR constant is assigned to the status variable.
     * <p>
     * The status variable is used in the SuccessWrapper class to represent the status of a successful JSON object response.
     * <p>
     * Example usage:
     * SuccessWrapper successWrapper = new SuccessWrapper(jsonObject, ipAddress);
     * String status = successWrapper.getStatus();
     */
    private final String         status;

    /**
     * The node variable represents a node in a response JSON object.
     * It is a private final String variable.
     * <p>
     * This variable is a part of the SuccessWrapper class, which represents a wrapper for a successful JSON object response.
     * It extends the DefaultWrapper class, which provides utility methods for retrieving JSON values.
     * <p>
     * The SuccessWrapper class has the following fields:
     * - status: The status of the response (String)
     * - node: The node in the response JSON object (String)
     * - ipAddress: The IP address associated with the response (String)
     * - addressWrapper: An instance of the AddressWrapper class (AddressWrapper)
     * - queryTime: The query time of the response (String)
     * - rawObject: The raw JSON object (JsonObject)
     * <p>
     * The SuccessWrapper class provides a constructor that takes a JsonObject and an IP address as parameters.
     * It initializes the fields based on the values in the JsonObject.
     * <p>
     * This variable is only accessible within the SuccessWrapper class and cannot be modified once initialized.
     */
    private final String         node;

    /**
     * The ipAddress variable represents the IP address associated with a successful JSON object response.
     * It is a private final field of type String.
     * <p>
     * The ipAddress variable is used in the SuccessWrapper class to store and retrieve the IP address from a JSON object.
     * <p>
     * Example usage:
     * SuccessWrapper successWrapper = new SuccessWrapper(jsonObject, ipAddress);
     * String ipAddress = successWrapper.getIpAddress();
     */
    private final String         ipAddress;

    /**
     * Represents a wrapper class for an address.
     */
    private final AddressWrapper addressWrapper;

    /**
     * The queryTime variable holds the value of the query time retrieved from a JSON object.
     */
    private final String         queryTime;

    /**
     * Represents a JSON object that holds raw data.
     */
    private final JsonObject rawObject;

    /**
     * Represents a wrapper for a successful response.
     * This class contains information retrieved from a JsonObject and provides getters for accessing that information.
     */
    public SuccessWrapper(JsonObject jsonObject, String ipAddress) {

        this.ipAddress = ipAddress;

        this.status = this.getJsonValue(jsonObject, "status", String.class, ERROR);
        this.node = this.getJsonValue(jsonObject, "node", String.class, ERROR);
        this.addressWrapper = new AddressWrapper(
            this.getJsonValue(jsonObject, ipAddress, JsonObject.class, new JsonObject()));
        this.queryTime = this.getJsonValue(jsonObject, "query time", String.class, ERROR);

        this.rawObject = jsonObject;
    }
}
