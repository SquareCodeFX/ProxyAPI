package net.square.wrapper.impl;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import lombok.Getter;
import net.square.wrapper.DefaultWrapper;

import java.util.ArrayList;
import java.util.List;

/**
 * The OperatorWrapper class represents a wrapper for an operator object.
 * It provides methods for retrieving operator-related information from a JSON object.
 */
@Getter
public class OperatorWrapper extends DefaultWrapper {

    /**
     * The name variable represents the name of an operator.
     * It is a private final String variable.
     * The value of this variable is set in the constructor of the OperatorWrapper class.
     */
    private final String                  name;

    /**
     * The url variable stores the URL associated with an OperatorWrapper object.
     * It represents the URL of the operator.
     */
    private final String                  url;

    /**
     * The anonymity variable represents the level of anonymity associated with an OperatorWrapper object.
     * It is a private final String variable.
     * The value of this variable is set in the constructor of the OperatorWrapper class.
     */
    private final String                  anonymity;

    /**
     * The Popularity class represents the popularity of an operator.
     */
    private final String                  popularity;

    /**
     * The protocols variable represents a list of protocols associated with an OperatorWrapper object.
     * It is a private final List<String> field.
     * The value of this variable is set in the constructor of the OperatorWrapper class.
     */
    private final List<String>            protocols = new ArrayList<>();

    /**
     * The operatorPoliciesWrapper variable is an instance of the OperatorPoliciesWrapper class.
     * It represents the policies of an operator and provides methods for accessing the operator policies and the raw JSON object.
     */
    private final OperatorPoliciesWrapper operatorPoliciesWrapper;

    /**
     * Represents a JSON object that holds raw data.
     */
    private final JsonObject rawObject;

    /**
     * Constructs an OperatorWrapper object using the provided JsonObject.
     *
     * @param jsonObject the JsonObject containing the operator information
     */
    public OperatorWrapper(JsonObject jsonObject) {

        this.name = getJsonValue(jsonObject, "name", String.class, ERROR);
        this.url = getJsonValue(jsonObject, "url", String.class, ERROR);
        this.anonymity = getJsonValue(jsonObject, "anonymity", String.class, ERROR);
        this.popularity = getJsonValue(jsonObject, "popularity", String.class, ERROR);

        if (jsonObject.has("protocols")) {
            for (JsonElement jsonElement : jsonObject.get("protocols").getAsJsonArray().asList()) {
                protocols.add(jsonElement.getAsString());
            }
        }

        this.operatorPoliciesWrapper = new OperatorPoliciesWrapper(
            getJsonValue(jsonObject, "policies", JsonObject.class, new JsonObject()));

        this.rawObject = jsonObject;
    }
}
