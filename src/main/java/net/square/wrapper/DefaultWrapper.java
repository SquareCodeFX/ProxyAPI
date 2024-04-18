package net.square.wrapper;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

/**
 * The DefaultWrapper class is a utility class that provides methods for retrieving JSON values from a JsonObject.
 * It uses the Gson library for JSON serialization and deserialization.
 */
public class DefaultWrapper {

    /**
     * The Gson object used for JSON serialization and deserialization.
     * It is used in the class DefaultWrapper to convert JSON data to Java objects and vice versa.
     * Gson provides efficient, flexible, and easy-to-use methods for handling JSON data.
     */
    private final       Gson   gson  = new Gson();

    /**
     * The error message indicating that the field is not set.
     * This constant is used in the class DefaultWrapper when a required field is not set.
     * <p>
     * It is recommended to check for this error message when handling unassigned fields.
     */
    public static final String ERROR = "FIELD_IS_NOT_SET";

    /**
     * Retrieves the value associated with the specified key from a JsonObject.
     * If the JsonObject is null or does not contain the key, the defaultValue is returned.
     *
     * @param jsonObject the JsonObject to retrieve the value from
     * @param key        the key of the value to retrieve
     * @param valueType  the class object representing the type of the value
     * @param defaultValue the default value to return if the JsonObject is null or does not contain the key
     * @param <T> the type of the value to retrieve and return
     * @return the value associated with the key, or the defaultValue if the key is not found or the JsonObject is null
     */
    public <T> T getJsonValue(JsonObject jsonObject, String key, Class<T> valueType, T defaultValue) {
        return jsonObject != null && jsonObject.has(key) ? gson.fromJson(jsonObject.get(key), valueType) : defaultValue;
    }
}
