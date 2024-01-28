package net.square.wrapper.impl;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import lombok.Getter;
import net.square.wrapper.DefaultWrapper;

import java.util.ArrayList;
import java.util.List;

@Getter
public class OperatorWrapper extends DefaultWrapper {

    private final String                  name;
    private final String                  url;
    private final String                  anonymity;
    private final String                  popularity;
    private final List<String>            protocols = new ArrayList<>();
    private final OperatorPoliciesWrapper operatorPoliciesWrapper;

    private final JsonObject rawObject;

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
