package net.square.wrapper.impl;

import com.google.gson.JsonObject;
import lombok.Getter;
import net.square.wrapper.DefaultWrapper;

@Getter
public class DeniedWrapper extends DefaultWrapper {

    private final String status;
    private final String message;

    private final JsonObject rawObject;

    public DeniedWrapper(JsonObject jsonObject) {

        this.status = this.getJsonValue(jsonObject, "status", String.class, ERROR);
        this.message = this.getJsonValue(jsonObject, "message", String.class, ERROR);

        this.rawObject = jsonObject;
    }
}
