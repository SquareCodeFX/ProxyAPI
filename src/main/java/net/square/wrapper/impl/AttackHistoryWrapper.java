package net.square.wrapper.impl;

import com.google.gson.JsonObject;
import lombok.Getter;
import net.square.wrapper.DefaultWrapper;

/**
 * AttackHistoryWrapper is a class that represents the attack history data retrieved from a JsonObject.
 * It extends the DefaultWrapper class, which provides methods for retrieving JSON values from a JsonObject.
 * <p>
 * The AttackHistoryWrapper class has the following properties:
 * - total: a String representing the total number of attacks
 * - vulnerabilityProbing: a String representing the number of attacks related to vulnerability probing
 * - forumSpam: a String representing the number of attacks related to forum spam
 * - loginAttempt: a String representing the number of login attempts
 * - registrationAttempt: a String representing the number of registration attempts
 * - rawObject: a JsonObject containing the raw attack history data
 * <p>
 * The AttackHistoryWrapper class has a constructor that takes a JsonObject as a parameter and initializes its properties.
 * The constructor also calls the getJsonValue method from the DefaultWrapper class to parse the values from the JsonObject.
 * <p>
 * Example usage:
 * JsonObject jsonObject = ...;
 * AttackHistoryWrapper attackHistoryWrapper = new AttackHistoryWrapper(jsonObject);
 * String totalAttacks = attackHistoryWrapper.getTotal();
 * <p>
 * Note: The AttackHistoryWrapper class assumes that the JsonObject follows a specific structure and contains the required fields.
 * If a field is not present in the JsonObject or the field value cannot be parsed to the expected type, the ERROR constant value is used as the default value.
 * It is recommended to handle the ERROR constant value when accessing the properties of the AttackHistoryWrapper object.
 */
@Getter
public class AttackHistoryWrapper extends DefaultWrapper {

    /**
     * The total variable is a private final String that stores the total value. It belongs to the AttackHistoryWrapper class, which is a wrapper class that stores information about
     *  attack history.
     * <p>
     * The total variable is initialized and assigned a value when an AttackHistoryWrapper object is created. Once assigned, the value of total cannot be changed.
     * <p>
     * It is not recommended to directly access or modify the total variable from outside the AttackHistoryWrapper class. Instead, use appropriate getter methods provided by the class
     *  to access the value of total.
     */
    private final String total;

    /**
     * The vulnerabilityProbing variable represents a string value that stores information about vulnerability probing in the system.
     * It is a private final field, meaning that it cannot be modified after initialization.
     * The type of the variable is String.
     * <p>
     * This variable is part of the AttackHistoryWrapper class, which is a wrapper class that stores information about attack history.
     * The AttackHistoryWrapper class extends the DefaultWrapper class, which provides methods for retrieving JSON values from a JsonObject.
     * The vulnerabilityProbing variable is used to track vulnerability probing information related to attacks.
     * <p>
     * Usage Example:
     * AttackHistoryWrapper attackHistoryWrapper = new AttackHistoryWrapper(jsonObject);
     * String vulnerabilityProbingValue = attackHistoryWrapper.getVulner*/
    private final String vulnerabilityProbing;

    /**
     * Gets the number of attacks related to forum spam.
     */
    private final String forumSpam;

    /**
     * The loginAttempt variable represents the number of login attempts.
     * It is a String value retrieved from a JsonObject in the AttackHistoryWrapper class.
     * If the JsonObject is null or does not contain the loginAttempt key, the value is set to the ERROR constant value.
     * <p>
     * Example usage:
     * JsonObject jsonObject = ...; // initialize the JsonObject
     * AttackHistoryWrapper attackHistoryWrapper = new AttackHistoryWrapper(jsonObject);
     * String loginAttempts = attackHistoryWrapper.getLoginAttempt();
     */
    private final String loginAttempt;

    /**
     * The registrationAttempt variable represents the registration attempt value.
     *
     * <p>
     * This variable is a string that stores information about a registration attempt.
     * It is used in the AttackHistoryWrapper class, which is a wrapper class that stores information about attack history.
     * </p>
     *
     * <p>
     * The registrationAttempt variable is a private final variable, which means its value cannot be changed after it is assigned.
     * </p>
     *
     * <p>
     * Example usage:
     * </p>
     * <pre>
     * String attempt = registrationAttempt;
     * </pre>
     */
    private final String registrationAttempt;

    /**
     * The rawObject variable is a private final field of type JsonObject.
     * It is used in the AttackHistoryWrapper class to store information about attack history.
     * <p>
     * JsonObject is a class from the JSON library that represents a JSON object.
     * It provides methods for retrieving, manipulating, and converting JSON data.
     * <p>
     * The rawObject variable is declared as private and final, indicating that it is immutable and can only be set once.
     * This helps ensure the integrity of the data stored in rawObject.
     * <p>
     * Example usage:
     * JsonObject rawObject = new JsonObject();
     * rawObject.addProperty("key", "value");
     * System.out.println(rawObject.get("key")); // Output: "value"
     */
    private final JsonObject rawObject;

    /**
     * The AttackHistoryWrapper class is a wrapper class that stores information about attack history.
     */
    public AttackHistoryWrapper(JsonObject jsonObject) {

        this.total = getJsonValue(jsonObject, "total", String.class, ERROR);
        this.vulnerabilityProbing = getJsonValue(jsonObject, "Vulnerability Probing", String.class, ERROR);
        this.forumSpam = getJsonValue(jsonObject, "Forum Spam", String.class, ERROR);
        this.loginAttempt = getJsonValue(jsonObject, "Login Attempt", String.class, ERROR);
        this.registrationAttempt = getJsonValue(jsonObject, "Registration Attempt", String.class, ERROR);

        this.rawObject = jsonObject;
    }
}
