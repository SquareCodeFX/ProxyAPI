package net.square.wrapper;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class DefaultWrapper {

    private final       Gson   gson  = new Gson();
    public static final String ERROR = "FIELD_IS_NOT_SET";

    public <T> T getJsonValue(JsonObject jsonObject, String key, Class<T> valueType, T defaultValue) {
        return jsonObject != null && jsonObject.has(key) ? gson.fromJson(jsonObject.get(key), valueType) : defaultValue;
    }
}
