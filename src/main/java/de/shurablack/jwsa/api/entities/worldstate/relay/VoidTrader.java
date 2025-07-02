package de.shurablack.jwsa.api.entities.worldstate.relay;

import de.shurablack.jwsa.api.entities.IJsonMapping;
import de.shurablack.jwsa.api.requests.Paths;
import de.shurablack.jwsa.api.requests.Requests;
import de.shurablack.jwsa.api.utils.ServerOffsetTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.json.JSONObject;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a Void Trader in the worldstate, containing details such as activation and expiry times,
 * character information, location, inventory, and other related attributes.
 */
@AllArgsConstructor
@Getter
public class VoidTrader implements Serializable, IJsonMapping {

    private static final long serialVersionUID = -9006742180568720263L;

    /** The unique identifier of the Void Trader. */
    private final String id;

    /** The activation time of the Void Trader. */
    private final LocalDateTime activation;

    /** The expiry time of the Void Trader. */
    private final LocalDateTime expiry;

    /** The string representation of the start time for the Void Trader. */
    private final String startString;

    /** Indicates whether the Void Trader is currently active. */
    private final boolean active;

    /** The name of the character representing the Void Trader. */
    private final String character;

    /** The location of the Void Trader. */
    private final String location;

    /** The inventory of items available with the Void Trader. */
    private final List<VaultTrader.InventoryItem> inventory;

    /** The unique PlayStation identifier of the Void Trader. */
    private final String psId;

    /** The string representation of the end time for the Void Trader. */
    private final String endString;

    /**
     * Creates a VoidTrader instance from a JSON object.
     *
     * @param object The JSON object containing the Void Trader details.
     * @return A new VoidTrader instance with the parsed details.
     */
    public static VoidTrader deserialize(JSONObject object) {
        String id = object.optString("id", null);
        LocalDateTime activation = ServerOffsetTime.of(object.optString("activation", null));
        LocalDateTime expiry = ServerOffsetTime.of(object.optString("expiry", null));
        String startString = object.optString("startString", null);
        boolean active = object.optBoolean("active", false);
        String character = object.optString("character", null);
        String location = object.optString("location", null);

        List<VaultTrader.InventoryItem> inventory = new ArrayList<>();
        if (object.has("inventory")) {
            for (Object itemObj : object.getJSONArray("inventory")) {
                inventory.add(VaultTrader.InventoryItem.deserialize((JSONObject) itemObj));
            }
        }

        String psId = object.optString("psId", null);
        String endString = object.optString("endString", null);

        return new VoidTrader(id, activation, expiry, startString, active, character, location, inventory, psId, endString);
    }

    @Override
    public JSONObject serialize() {
        JSONObject json = new JSONObject();
        json.put("id", id != null ? id : JSONObject.NULL);
        json.put("activation", activation != null ? activation.toString() : JSONObject.NULL);
        json.put("expiry", expiry != null ? expiry.toString() : JSONObject.NULL);
        json.put("startString", startString != null ? startString : JSONObject.NULL);
        json.put("active", active);
        json.put("character", character != null ? character : JSONObject.NULL);
        json.put("location", location != null ? location : JSONObject.NULL);

        if (inventory != null) {
            json.put("inventory", inventory.stream()
                    .map(VaultTrader.InventoryItem::serialize)
                    .collect(java.util.stream.Collectors.toList()));
        } else {
            json.put("inventory", new ArrayList<>());
        }

        json.put("psId", psId != null ? psId : JSONObject.NULL);
        json.put("endString", endString != null ? endString : JSONObject.NULL);

        return json;
    }

    /**
     * Requests the VoidTrader details from the server.
     *
     * @return A VoidTrader instance retrieved from the server.
     */
    public static VoidTrader request() {
        return Requests.withDirectMapping(VoidTrader.class, Paths.VOID_TRADER);
    }

    /**
     * Represents an inventory item available with the Void Trader, containing details such as
     * the item name, ducat value, and credit cost.
     */
    @AllArgsConstructor
    @Getter
    public static class InventoryItem implements Serializable, IJsonMapping {

        private static final long serialVersionUID = 3405923625104715502L;

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
        public static VaultTrader.InventoryItem deserialize(JSONObject object) {
            String item = object.optString("item", null);
            Number ducats = object.optNumber("ducats", -1);
            Number credits = object.optNumber("credits", -1);
            return new VaultTrader.InventoryItem(item, ducats, credits);
        }

        @Override
        public JSONObject serialize() {
            JSONObject json = new JSONObject();
            json.put("item", item != null ? item : JSONObject.NULL);
            json.put("ducats", ducats != null ? ducats : JSONObject.NULL);
            json.put("credits", credits != null ? credits : JSONObject.NULL);
            return json;
        }
    }

}