package de.shurablack.jwsa.api.entities.worldstate.relay;

import de.shurablack.jwsa.api.requests.Paths;
import de.shurablack.jwsa.api.requests.Requests;
import de.shurablack.jwsa.api.utils.ServerOffsetTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.json.JSONObject;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a Vault Trader in the worldstate, containing details such as activation and expiry times,
 * character information, location, inventory, and other related attributes.
 */
@AllArgsConstructor
@Getter
public class VaultTrader {

    /** The unique identifier of the Vault Trader. */
    private final String id;

    /** The activation time of the Vault Trader. */
    private final LocalDateTime activation;

    /** The expiry time of the Vault Trader. */
    private final LocalDateTime expiry;

    /** The string representation of the start time for the Vault Trader. */
    private final String startString;

    /** Indicates whether the Vault Trader is currently active. */
    private final boolean active;

    /** The name of the character representing the Vault Trader. */
    private final String character;

    /** The location of the Vault Trader. */
    private final String location;

    /** The inventory of items available with the Vault Trader. */
    private final List<InventoryItem> inventory;

    /** The unique PlayStation identifier of the Vault Trader. */
    private final String psId;

    /** The string representation of the end time for the Vault Trader. */
    private final String endString;

    /**
     * Creates a VaultTrader instance from a JSON object.
     *
     * @param object The JSON object containing the Vault Trader details.
     * @return A new VaultTrader instance with the parsed details.
     */
    public static VaultTrader fromJSON(JSONObject object) {
        String id = object.optString("id", null);
        LocalDateTime activation = ServerOffsetTime.of(object.optString("activation", null));
        LocalDateTime expiry = ServerOffsetTime.of(object.optString("expiry", null));
        String startString = object.optString("startString", null);
        boolean active = object.optBoolean("active", false);
        String character = object.optString("character", null);
        String location = object.optString("location", null);

        List<InventoryItem> inventory = new ArrayList<>();
        if (object.has("inventory")) {
            for (Object itemObj : object.getJSONArray("inventory")) {
                inventory.add(InventoryItem.fromJSON((JSONObject) itemObj));
            }
        }

        String psId = object.optString("psId", null);
        String endString = object.optString("endString", null);

        return new VaultTrader(id, activation, expiry, startString, active, character, location, inventory, psId, endString);
    }

    /**
     * Requests the VaultTrader details from the server.
     *
     * @return A VaultTrader instance retrieved from the server.
     */
    public static VaultTrader request() {
        return Requests.withDirectMapping(VaultTrader.class, Paths.VAULT_TRADER);
    }

    /**
     * Represents an inventory item available with the Vault Trader, containing details such as
     * the item name, ducat value, and credit cost.
     */
    @AllArgsConstructor
    @Getter
    public static class InventoryItem {

        /** The name of the inventory item. */
        private final String item;

        /** The ducat value of the inventory item. */
        private final Number ducats;

        /** The credit cost of the inventory item. */
        private final Number credits;

        /**
         * Creates an InventoryItem instance from a JSON object.
         *
         * @param object The JSON object containing the inventory item details.
         * @return A new InventoryItem instance with the parsed details.
         */
        public static InventoryItem fromJSON(JSONObject object) {
            String item = object.optString("item", null);
            Number ducats = object.optNumber("ducats", -1);
            Number credits = object.optNumber("credits", -1);
            return new InventoryItem(item, ducats, credits);
        }
    }
}