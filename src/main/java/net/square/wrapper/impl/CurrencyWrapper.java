package net.square.wrapper.impl;

import com.google.gson.JsonObject;
import lombok.Getter;
import net.square.wrapper.DefaultWrapper;

/**
 * The CurrencyWrapper class is a wrapper class that represents a currency.
 * It provides methods for retrieving currency information from a JSON object.
 * <p>
 * The CurrencyWrapper class extends the DefaultWrapper class, which provides utility methods
 * for retrieving JSON values from a JsonObject.
 */
@Getter
public class CurrencyWrapper extends DefaultWrapper {

    /**
     * The code variable stores a string representing the code of a currency.
     * It is a private final variable, meaning it cannot be changed once initialized and it can only be accessed within the class it is declared in.
     * The code variable is used to store the code of a currency retrieved from a JSON object.
     * The JSON object is passed as a parameter to the constructor of the CurrencyWrapper class.
     * The code is retrieved from the JSON object using the getJsonValue method of the DefaultWrapper class.
     * If the JSON object is null or does not contain the key "code", the error constant ERROR is returned as the default value.
     */
    private final String code;

    /**
     * The name variable is a private final String representing the name of a currency.
     * It is a constant variable, meaning its value cannot be changed once initialized.
     * The name variable is used to store the name of a currency retrieved from a JSON object.
     * The JSON object is passed as a parameter to the constructor of the CurrencyWrapper class.
     * The name is retrieved from the JSON object using the getJsonValue method of the DefaultWrapper class.
     * If the JSON object is null or does not contain the key "name", the error constant ERROR is returned as the default value.
     */
    private final String name;

    /**
     * The symbol of a currency.
     */
    private final String symbol;

    /**
     * Represents a JSON object that holds raw data.
     */
    private final JsonObject rawObject;

    /**
     * The CurrencyWrapper class is a wrapper class that represents a currency.
     * It provides methods for retrieving currency information from a JSON object.
     */
    public CurrencyWrapper(JsonObject jsonObject) {

        this.code = this.getJsonValue(jsonObject, "code", String.class, ERROR);
        this.name = this.getJsonValue(jsonObject, "name", String.class, ERROR);
        this.symbol = this.getJsonValue(jsonObject, "symbol", String.class, ERROR);

        this.rawObject = jsonObject;
    }
}
