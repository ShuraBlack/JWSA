package de.shurablack.jwsa.api.entities.worldstate.relay;

import de.shurablack.jwsa.api.entities.IJsonMapping;
import de.shurablack.jwsa.api.requests.Paths;
import de.shurablack.jwsa.api.requests.Requests;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.List;

/**
 * Represents a Flash Sale in the worldstate, containing details such as the item being sold,
 * its discount, whether it is expired, and other related attributes.
 */
@AllArgsConstructor
@Getter
public class FlashSale implements Serializable, IJsonMapping {

    private static final long serialVersionUID = 4469454051579649584L;

    /** The name of the item being sold in the Flash Sale. */
    private final String item;

    /** Indicates whether the Flash Sale has expired. */
    private final boolean expired;

    /** The estimated time remaining for the Flash Sale. */
    private final String eta;

    /** The discount percentage applied to the item in the Flash Sale. */
    private final Number discount;

    /** The premium override price for the item in the Flash Sale. */
    private final Number premiumOverride;

    /** Indicates whether the item in the Flash Sale is popular. */
    private final boolean popular;

    /** Indicates whether the item in the Flash Sale is featured. */
    private final boolean featured;

    /**
     * Creates a FlashSale instance from a JSON object.
     *
     * @param object The JSON object containing the Flash Sale details.
     * @return A new FlashSale instance with the parsed details.
     */
    public static FlashSale deserialize(JSONObject object) {
        String item = object.optString("item", null);
        boolean expired = object.optBoolean("expired", false);
        String eta = object.optString("eta", null);
        Number discount = object.optNumber("discount", -1);
        Number premiumOverride = object.optNumber("premiumOverride", -1);
        boolean popular = object.optBoolean("popular", false);
        boolean featured = object.optBoolean("featured", false);

        return new FlashSale(item, expired, eta, discount, premiumOverride, popular, featured);
    }

    @Override
    public JSONObject serialize() {
        JSONObject json = new JSONObject();
        json.put("item", item);
        json.put("expired", expired);
        json.put("eta", eta);
        json.put("discount", discount);
        json.put("premiumOverride", premiumOverride);
        json.put("popular", popular);
        json.put("featured", featured);
        return json;
    }

    /**
     * Requests the list of Flash Sales from the server.
     *
     * @return A list of FlashSale instances retrieved from the server.
     */
    public static List<FlashSale> request() {
        return Requests.withListMapping(FlashSale.class, Paths.FLASH_SALES);
    }
}