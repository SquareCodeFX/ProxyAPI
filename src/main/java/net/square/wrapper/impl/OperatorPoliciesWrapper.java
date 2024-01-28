package net.square.wrapper.impl;

import com.google.gson.JsonObject;
import lombok.Getter;
import net.square.wrapper.DefaultWrapper;

@Getter
public class OperatorPoliciesWrapper extends DefaultWrapper {

    private final String adFiltering;
    private final String freeAccess;
    private final String paidAccess;
    private final String portForwarding;
    private final String logging;
    private final String anonymousPayments;
    private final String cryptoPayments;
    private final String traceableOwnership;

    private final JsonObject rawObject;

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
