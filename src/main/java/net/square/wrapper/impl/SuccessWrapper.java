package net.square.wrapper.impl;

import com.google.gson.JsonObject;
import lombok.Getter;
import net.square.wrapper.DefaultWrapper;

@Getter
public class SuccessWrapper extends DefaultWrapper {

    private final String         status;
    private final String         node;
    private final String         ipAddress;
    private final AddressWrapper addressWrapper;
    private final String         queryTime;

    private final JsonObject rawObject;

    public SuccessWrapper(JsonObject jsonObject, String ipAddress) {

        this.ipAddress = ipAddress;

        this.status = this.getJsonValue(jsonObject, "status", String.class, ERROR);
        this.node = this.getJsonValue(jsonObject, "node", String.class, ERROR);
        this.addressWrapper = new AddressWrapper(
            this.getJsonValue(jsonObject, ipAddress, JsonObject.class, new JsonObject()));
        this.queryTime = this.getJsonValue(jsonObject, "query time", String.class, ERROR);

        this.rawObject = jsonObject;
    }
}
