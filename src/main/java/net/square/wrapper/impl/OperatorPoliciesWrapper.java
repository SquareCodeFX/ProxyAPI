package net.square.wrapper.impl;

import com.google.gson.JsonObject;
import lombok.Getter;
import net.square.wrapper.DefaultWrapper;

/**
 * The OperatorPoliciesWrapper class is a wrapper class that represents the policies of an operator.
 * It extends the DefaultWrapper class for JSON serialization and deserialization.
 * It provides getters for accessing the operator policies and the raw JSON object.
 */
@Getter
public class OperatorPoliciesWrapper extends DefaultWrapper {

    /**
     * The adFiltering variable represents the ad filtering policy of an operator.
     * It is a final String variable that is initialized in the constructor of the OperatorPoliciesWrapper class.
     * The value of the adFiltering variable is retrieved from a JsonObject by calling the getJsonValue() method.
     * If the JsonObject is null or does not contain the "ad_filtering" key, the adFiltering variable will be set to the default value.
     */
    private final String adFiltering;

    /**
     * The freeAccess variable represents the free access policy of an operator.
     * It is a final String variable that is initialized in the constructor of the OperatorPoliciesWrapper class.
     * The value of the freeAccess variable is retrieved from a JsonObject by calling the getJsonValue() method.
     * If the JsonObject is null or does not contain the "free_access" key, the freeAccess variable will be set to the default value.
     */
    private final String freeAccess;

    /**
     *
     * The paidAccess variable represents the paid access policy of an operator.
     * It is a final String variable that is initialized in the constructor of the OperatorPoliciesWrapper class.
     * The value of the paidAccess variable is retrieved from a JsonObject by calling the getJsonValue() method.
     * If the JsonObject is null or does not contain the "paid_access" key, the paidAccess variable will be set to the default value.
     *
     */
    private final String paidAccess;

    /**
     * The portForwarding variable represents the port forwarding policy of an operator.
     * It is a final String variable that is initialized in the constructor of the OperatorPoliciesWrapper class.
     * The value of the portForwarding variable is retrieved from a JsonObject by calling the getJsonValue() method.
     * If the JsonObject is null or does not contain the "port_forwarding" key, the portForwarding variable will be set to the default value.
     */
    private final String portForwarding;

    /**
     * The logging variable represents the logging configuration of an operator.
     * It is a String that contains the logging configuration information.
     * This variable is marked as private and final, which means it cannot be modified and can only be accessed within the class.
     * <p>
     * The logging configuration determines how logging events are handled in the system.
     * It can specify options such as log levels, log file paths, log formats, etc.
     * <p>
     * It is recommended to access the logging variable using the appropriate getter method provided by the OperatorPoliciesWrapper class.
     * <p>
     * Example usage:
     * <p>
     * OperatorPoliciesWrapper policiesWrapper = new OperatorPoliciesWrapper(json);
     * String loggingConfig = policiesWrapper.getLogging();
     */
    private final String logging;

    /**
     * Represents anonymous payments in the operator's policies.
     */
    private final String anonymousPayments;

    /**
     * The cryptoPayments variable represents the crypto payments field in the OperatorPoliciesWrapper class.
     * It is a private final variable of type String.
     * <p>
     * The cryptoPayments field is used to store the value of the crypto payments policy of an operator.
     * It is retrieved from a JsonObject using the getJsonValue method in the DefaultWrapper class.
     * If the JsonObject is null or does not contain the cryptoPayments key, the value will be set to null.
     * <p>
     * Example usage:
     * JsonObject jsonObject = ...
     * OperatorPoliciesWrapper wrapper = new OperatorPoliciesWrapper(jsonObject);
     * String cryptoPayments = wrapper.getCryptoPayments();
     */
    private final String cryptoPayments;

    /**
     * The traceableOwnership variable represents the ownership status of a traceable item.
     * It is a private final field of type String.
     * The value of the traceableOwnership field can be accessed through getter methods of the containing class.
     *
     * <p>
     * The class that contains the traceableOwnership field is OperatorPoliciesWrapper.
     * OperatorPoliciesWrapper is a wrapper class that represents the policies of an operator.
     * It extends the DefaultWrapper class for JSON serialization and deserialization.
     * It provides getters for accessing the operator policies and the raw JSON object.
     *
     * <p>
     * The traceableOwnership field is initialized in the constructor of OperatorPoliciesWrapper.
     *
     * <p>
     * The traceableOwnership field is a member of OperatorPoliciesWrapper, along with other fields such as adFiltering,
     * freeAccess, paidAccess, portForwarding, logging, anonymousPayments, and cryptoPayments.
     *
     * <p>
     * The traceableOwnership field is final, which means its value cannot be changed once initialized.
     * It is marked as private, so it can only be accessed within the OperatorPoliciesWrapper class.
     *
     * <p>
     * Example usage:
     * OperatorPoliciesWrapper policiesWrapper = new OperatorPoliciesWrapper(jsonObject);
     * String ownership = policiesWrapper.getTraceableOwnership();
     */
    private final String traceableOwnership;

    /**
     * Represents a JSON object that holds raw data.
     */
    private final JsonObject rawObject;

    /**
     * The OperatorPoliciesWrapper class is a wrapper class that represents the policies of an operator.
     * It extends the DefaultWrapper class for JSON serialization and deserialization.
     * It provides getters for accessing the operator policies and the raw JSON object.
     */
    public OperatorPoliciesWrapper(JsonObject jsonObject) {

        this.adFiltering = getJsonValue(jsonObject, "ad_filtering", String.class, ERROR);
        this.freeAccess = getJsonValue(jsonObject, "free_access", String.class, ERROR);
        this.paidAccess = getJsonValue(jsonObject, "paid_access", String.class, ERROR);
        this.portForwarding = getJsonValue(jsonObject, "port_forwarding", String.class, ERROR);
        this.logging = getJsonValue(jsonObject, "logging", String.class, ERROR);
        this.anonymousPayments = getJsonValue(jsonObject, "anonymous_payments", String.class, ERROR);
        this.cryptoPayments = getJsonValue(jsonObject, "crypto_payments", String.class, ERROR);
        this.traceableOwnership = getJsonValue(jsonObject, "traceable_ownership", String.class, ERROR);

        this.rawObject = jsonObject;
    }
}
