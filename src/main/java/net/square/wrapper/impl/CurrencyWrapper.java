package net.square.wrapper.impl;

import com.google.gson.JsonObject;
import lombok.Getter;
import net.square.wrapper.DefaultWrapper;

@Getter
public class CurrencyWrapper extends DefaultWrapper {

    private final String code;
    private final String name;
    private final String symbol;

    private final JsonObject rawObject;

    public CurrencyWrapper(JsonObject jsonObject) {

        this.code = this.getJsonValue(jsonObject, "code", String.class, ERROR);
        this.name = this.getJsonValue(jsonObject, "name", String.class, ERROR);
        this.symbol = this.getJsonValue(jsonObject, "symbol", String.class, ERROR);

        this.rawObject = jsonObject;
    }
}
