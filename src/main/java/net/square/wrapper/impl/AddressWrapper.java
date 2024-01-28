package net.square.wrapper.impl;

import com.google.gson.JsonObject;
import lombok.Getter;
import net.square.wrapper.DefaultWrapper;

@Getter
public class AddressWrapper extends DefaultWrapper {

    private final String asn;
    private final String provider;
    private final String continent;
    private final String continentCode;
    private final String country;
    private final String isoCode;
    private final String timezone;
    private final String latitude;
    private final String longitude;

    private final CurrencyWrapper currencyWrapper;

    private final String proxy;
    private final String type;
    private final String risk;

    private final String lastSeenHuman;
    private final String lastSeenUnix;

    private final OperatorWrapper operatorWrapper;

    private final JsonObject rawObject;

    public AddressWrapper(JsonObject jsonObject) {

        this.asn = getJsonValue(jsonObject, "asn", String.class, ERROR);
        this.provider = getJsonValue(jsonObject, "provider", String.class, ERROR);
        this.continent = getJsonValue(jsonObject, "continent", String.class, ERROR);
        this.continentCode = getJsonValue(jsonObject, "continentcode", String.class, ERROR);
        this.country = getJsonValue(jsonObject, "country", String.class, ERROR);
        this.isoCode = getJsonValue(jsonObject, "isocode", String.class, ERROR);
        this.timezone = getJsonValue(jsonObject, "timezone", String.class, ERROR);
        this.latitude = getJsonValue(jsonObject, "latitude", String.class, ERROR);
        this.longitude = getJsonValue(jsonObject, "longitude", String.class, ERROR);

        this.currencyWrapper = new CurrencyWrapper(
            getJsonValue(jsonObject, "currency", JsonObject.class, new JsonObject()));

        this.proxy = getJsonValue(jsonObject, "proxy", String.class, ERROR);
        this.type = getJsonValue(jsonObject, "type", String.class, ERROR);
        this.risk = getJsonValue(jsonObject, "risk", String.class, ERROR);

        this.operatorWrapper = new OperatorWrapper(
            getJsonValue(jsonObject, "operator", JsonObject.class, new JsonObject()));

        this.lastSeenHuman = getJsonValue(jsonObject, "last seen human", String.class, ERROR);
        this.lastSeenUnix = getJsonValue(jsonObject, "last seen unix", String.class, ERROR);

        this.rawObject = jsonObject;
    }
}
