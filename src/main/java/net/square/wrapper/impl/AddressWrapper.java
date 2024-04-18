package net.square.wrapper.impl;

import com.google.gson.JsonObject;
import lombok.Getter;
import net.square.wrapper.DefaultWrapper;

/**
 * The AddressWrapper class is used to wrap the values of an address retrieved from a JSON object.
 * It extends the DefaultWrapper class and provides additional functionality for retrieving specific address data.
 */
@Getter
public class AddressWrapper extends DefaultWrapper {

    /**
     * The AddressWrapper class is used to wrap the values of an address retrieved from a JSON object.
     * It extends the DefaultWrapper class and provides additional functionality for retrieving specific address data.
     */
    private final String asn;

    /**
     * This private final String variable represents the provider of an address.
     * <p>
     * It is a property of the AddressWrapper class, which is used to wrap the values of an address retrieved from a JSON object.
     * The AddressWrapper class extends the DefaultWrapper class and provides additional functionality for retrieving specific address data.
     * <p>
     * The provider variable stores the name of the provider associated with the address.
     */
    private final String provider;

    /**
     * The continent variable represents the continent associated with an address.
     */
    private final String continent;

    /**
     * The continentCode variable represents the code of a continent.
     */
    private final String continentCode;

    /**
     * The country associated with the address.
     */
    private final String country;

    /**
     * The isoCode variable represents an ISO code string.
     * It is a private final variable, meaning once it is assigned a value, it cannot be changed.
     * ISO codes are internationally recognized codes used to identify countries, currencies, languages, and more.
     * ISO codes consist of two or three uppercase letters.
     */
    private final String isoCode;

    /**
     * The time zone associated with an address.
     */
    private final String timezone;

    /**
     * The latitude variable represents the latitude coordinate of a location.
     * It is a private final String variable.
     */
    private final String latitude;

    /**
     * Represents the longitude of an address.
     */
    private final String longitude;

    /**
     * The CurrencyWrapper class is a wrapper class that represents currency information obtained from a JSON object.
     * It provides getters for accessing the currency code, name, symbol, and the raw JSON object.
     */
    private final CurrencyWrapper currencyWrapper;

    /**
     * Retrieves the value associated with the specified key from a JsonObject.
     * If the JsonObject is null or does not contain the key, the defaultValue is returned.
     */
    private final String proxy;

    /**
     * Represents a variable of type String.
     * This variable is private and final, indicating that it cannot be modified after instantiation.
     * It is used to store the type of an address.
     */
    private final String type;

    /**
     * The risk associated with an address.
     *
     * <p>
     * This variable represents the level of risk associated with an address. It is a string value that indicates the
     * likelihood or severity of potential risks.
     *
     * <p>
     * Example values for the risk variable can include:
     * - "High Risk"
     * - "Medium Risk"
     * - "Low Risk"
     * - "No Risk"
     *
     * <p>
     * This field is constant and cannot be modified after initialization.
     */
    private final String risk;

    /**
     * Represents the last seen human in an address wrapper.
     * The last seen human provides information about the most recent human activity associated with the address.
     */
    private final String lastSeenHuman;

    /**
     * The lastSeenUnix variable stores the timestamp of the last seen activity in Unix format.
     * It represents the number of seconds that have elapsed since January 1, 1970.
     * <p>
     * The variable is a private final field, which means it cannot be modified once it is assigned a value.
     * It is of type String, which represents the Unix timestamp as a string.
     * The variable is used to track and display the last seen activity of an object or entity.
     */
    private final String lastSeenUnix;

    /**
     * The OperatorWrapper class represents an operator in the system.
     * It stores information about the operator's name, URL, anonymity, popularity, protocols supported, and operator policies.
     * It also provides a raw JSON object representing the operator.
     * <p>
     * This class extends the DefaultWrapper class, which provides methods for retrieving JSON values from a JsonObject.
     */
    private final OperatorWrapper operatorWrapper;

    /**
     * The rawObject variable represents a JsonObject that holds raw data.
     * It is a private final variable that is of type JsonObject.
     * <p>
     * This variable is used in multiple classes as a parameter in their constructors.
     * It is used to parse and retrieve values from JSON data.
     * <p>
     * The rawObject variable is initialized when an instance of the containing class is created.
     * It is typically constructed by passing a JsonObject as a parameter to the constructor.
     * <p>
     * Example usage:
     * <p>
     * // Create a JsonObject
     * JsonObject jsonObject = new JsonObject();
     * <p>
     * // Set values in the JsonObject
     * jsonObject.addProperty("key1", "value1");
     * jsonObject.addProperty("key2", "value2");
     * <p>
     * // Create an instance of the containing class and pass the JsonObject as a parameter
     * AddressWrapper addressWrapper = new AddressWrapper(jsonObject);
     * <p>
     * // Access the rawObject variable
     * JsonObject rawObject = addressWrapper.getRawObject();
     * <p>
     * // Retrieve values from the rawObject variable
     * String value1 = rawObject.get("key1").getAsString();
     * String value2 = rawObject.get("key2").getAsString();
     * <p>
     * // Use the retrieved values
     * System.out.println("Value1: " + value1);
     * System.out.println("Value2: " + value2);
     * <p>
     * The above example demonstrates how to use the rawObject variable to retrieve values from a JsonObject.
     * The values can be accessed using the get() method of the rawObject variable and converted to the desired types using the appropriate methods.
     */
    private final JsonObject           rawObject;

    /**
     * The attackHistory variable is an object of the AttackHistoryWrapper class, which provides information about attack history.
     * It contains the following fields:
     * - total: the total number of attacks
     * - vulnerabilityProbing: the number of attacks related to vulnerability probing
     * - forumSpam: the number of attacks related to forum spam
     * - loginAttempt: the number of login attempts
     * - registrationAttempt: the number of registration attempts
     * <p>
     * The attack history information is retrieved from a JSON object and stored in the corresponding fields of the AttackHistoryWrapper object.
     * The rawObject field contains the original JSON object for reference or further processing, if needed.
     * <p>
     * Example usage:
     * AttackHistoryWrapper attackHistory = new AttackHistoryWrapper(jsonObject);
     * String totalAttacks = attackHistory.getTotal();
     * String probingAttacks = attackHistory.getVulnerabilityProbing();
     * String spamAttacks = attackHistory.getForumSpam();
     * String loginAttempts = attackHistory.getLoginAttempt();
     * String registrationAttempts = attackHistory.getRegistrationAttempt();
     * <p>
     * Note: The example code is provided to demonstrate how the attack history information can be accessed.
     * It should not be used as reference code in actual implementations.
     */
    private final AttackHistoryWrapper attackHistory;

    /**
     * Constructs an AddressWrapper object by parsing the values from a JsonObject.
     *
     * @param jsonObject the JsonObject to parse the values from
     */
    public AddressWrapper(JsonObject jsonObject) {

        this.asn = getJsonValue(jsonObject, "asn", String.class, ERROR);
        this.provider = getJsonValue(jsonObject, "provider", String.class, ERROR);
        this.continent = getJsonValue(jsonObject, "continent", String.class, ERROR);
        this.continentCode = getJsonValue(jsonObject, "continentcode", String.class, ERROR);
        this.country = getJsonValue(jsonObject, "country", String.class, ERROR);
        this.isoCode = getJsonValue(jsonObject, "isocode", String.class, ERROR);
        this.timezone = getJsonValue(jsonObject, "timezone", String.class, ERROR);
        this.latitude = getJsonValue(jsonObject, "latitude", String.class, ERROR);
        this.longitude = getJsonValue(jsonObject, "longitude", String.class, ERROR);

        this.currencyWrapper = new CurrencyWrapper(
            getJsonValue(jsonObject, "currency", JsonObject.class, new JsonObject()));

        this.proxy = getJsonValue(jsonObject, "proxy", String.class, ERROR);
        this.type = getJsonValue(jsonObject, "type", String.class, ERROR);
        this.risk = getJsonValue(jsonObject, "risk", String.class, ERROR);

        this.operatorWrapper = new OperatorWrapper(
            getJsonValue(jsonObject, "operator", JsonObject.class, new JsonObject()));

        this.attackHistory = new AttackHistoryWrapper(
            getJsonValue(jsonObject, "attack history", JsonObject.class, new JsonObject()));

        this.lastSeenHuman = getJsonValue(jsonObject, "last seen human", String.class, ERROR);
        this.lastSeenUnix = getJsonValue(jsonObject, "last seen unix", String.class, ERROR);

        this.rawObject = jsonObject;
    }
}
