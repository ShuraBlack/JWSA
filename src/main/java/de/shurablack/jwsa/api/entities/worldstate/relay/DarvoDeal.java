package de.shurablack.jwsa.api.entities.worldstate.relay;

import de.shurablack.jwsa.api.requests.Paths;
import de.shurablack.jwsa.api.requests.Requests;
import de.shurablack.jwsa.api.utils.ServerOffsetTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.json.JSONObject;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Represents a Darvo Deal in the worldstate, containing details such as the item being sold,
 * the total number of items available, the discount, and the expiry time of the deal.
 */
@AllArgsConstructor
@Getter
public class DarvoDeal {

    /** The unique identifier of the Darvo Deal. */
    private final String id;

    /** The number of items sold in the Darvo Deal. */
    private final Number sold;

    /** The name of the item being sold in the Darvo Deal. */
    private final String item;

    /** The unique name of the item being sold in the Darvo Deal. */
    private final String uniqueName;

    /** The total number of items available in the Darvo Deal. */
    private final Number total;

    /** The estimated time remaining for the Darvo Deal. */
    private final String eta;

    /** The original price of the item before the discount. */
    private final Number originalPrice;

    /** The sale price of the item after the discount. */
    private final Number salePrice;

    /** The discount percentage applied to the item. */
    private final Number discount;

    /** The expiry time of the Darvo Deal. */
    private final LocalDateTime expiry;

    /**
     * Creates a DarvoDeal instance from a JSON object.
     *
     * @param json The JSON object containing the Darvo Deal details.
     * @return A new DarvoDeal instance with the parsed details.
     */
    public static DarvoDeal fromJSON(JSONObject json) {
        String id = json.optString("id", null);
        Number sold = json.optNumber("sold", -1);
        String item = json.optString("item", null);
        String uniqueName = json.optString("uniqueName", null);
        Number total = json.optNumber("total", -1);
        String eta = json.optString("eta", null);
        Number originalPrice = json.optNumber("originalPrice", -1);
        Number salePrice = json.optNumber("salePrice", -1);
        Number discount = json.optNumber("discount", -1);
        LocalDateTime expiry = ServerOffsetTime.of(json.optString("expiry", null));

        return new DarvoDeal(id, sold, item, uniqueName, total, eta, originalPrice, salePrice, discount, expiry);
    }

    /**
     * Requests the list of Darvo Deals from the server.
     *
     * @return A list of DarvoDeal instances retrieved from the server.
     */
    public static List<DarvoDeal> request() {
        return Requests.withListMapping(DarvoDeal.class, Paths.DAILY_DARVO_DEALS);
    }
}