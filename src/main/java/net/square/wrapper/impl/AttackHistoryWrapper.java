package net.square.wrapper.impl;

import com.google.gson.JsonObject;
import lombok.Getter;
import net.square.wrapper.DefaultWrapper;

@Getter
public class AttackHistoryWrapper extends DefaultWrapper {

    private final String total;
    private final String vulnerabilityProbing;
    private final String forumSpam;
    private final String loginAttempt;
    private final String registrationAttempt;

    private final JsonObject rawObject;

    public AttackHistoryWrapper(JsonObject jsonObject) {

        this.total = getJsonValue(jsonObject, "total", String.class, ERROR);
        this.vulnerabilityProbing = getJsonValue(jsonObject, "Vulnerability Probing", String.class, ERROR);
        this.forumSpam = getJsonValue(jsonObject, "Forum Spam", String.class, ERROR);
        this.loginAttempt = getJsonValue(jsonObject, "Login Attempt", String.class, ERROR);
        this.registrationAttempt = getJsonValue(jsonObject, "Registration Attempt", String.class, ERROR);

        this.rawObject = jsonObject;
    }
}
