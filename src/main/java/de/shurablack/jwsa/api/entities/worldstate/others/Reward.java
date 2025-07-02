package de.shurablack.jwsa.api.entities.worldstate.others;

import de.shurablack.jwsa.api.entities.IJsonMapping;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Represents a reward in the worldstate, containing details such as items, credits, and other attributes.
 */
@AllArgsConstructor
@Getter
public class Reward implements Serializable, IJsonMapping {

    private static final long serialVersionUID = 7529932030040067370L;

    /** The list of counted items in the reward. */
    private final List<Item> countedItems;

    /** The thumbnail URL associated with the reward. */
    private final String thumbnail;

    /** The color associated with the reward. */
    private final Number color;

    /** The amount of credits included in the reward. */
    private final int credits;

    /** The string representation of the reward. */
    private final String asString;

    /** The list of item names included in the reward. */
    private final List<String> items;

    /** The string representation of the items in the reward. */
    private final String itemString;

    /**
     * Creates a Reward instance from a JSON object.
     *
     * @param json The JSON object containing the reward details.
     * @return A new Reward instance with the parsed details, or null if the JSON object is null.
     */
    public static Reward deserialize(JSONObject json) {
        if (json == null) {
            return null;
        }

        List<Item> countedItems = new ArrayList<>();

        JSONArray countedItemsJson = json.getJSONArray("countedItems");
        if (countedItemsJson != null) {
            for (int i = 0; i < countedItemsJson.length(); i++) {
                JSONObject itemJson = countedItemsJson.getJSONObject(i);
                countedItems.add(Item.deserialize(itemJson));
            }
        }

        String thumbnail = json.optString("thumbnail", null);
        Number color = json.optNumber("color", -1);
        int credits = json.optInt("credits", -1);
        String asString = json.optString("asString", null);
        List<String> items = json.optJSONArray("items") != null
                ? json.getJSONArray("items").toList().stream()
                .map(Object::toString)
                .collect(Collectors.toList())
                : List.of();
        String itemString = json.optString("itemString", null);

        return new Reward(countedItems, thumbnail, color, credits, asString, items, itemString);
    }

    @Override
    public JSONObject serialize() {
        JSONObject json = new JSONObject();
        json.put("countedItems", countedItems.stream().map(Item::serialize).collect(Collectors.toList()));
        json.put("thumbnail", thumbnail);
        json.put("color", color);
        json.put("credits", credits);
        json.put("asString", asString);
        json.put("items", items);
        json.put("itemString", itemString);
        return json;
    }

    /**
     * Represents an individual item in the reward.
     */
    @AllArgsConstructor
    @Getter
    public static class Item implements Serializable, IJsonMapping {

        private static final long serialVersionUID = 9216137312012349224L;

        /** The type of the item. */
        private final String type;

        /** The count of the item. */
        private final int count;

        /**
         * Creates an Item instance from a JSON object.
         *
         * @param json The JSON object containing the item details.
         * @return A new Item instance with the parsed details.
         */
        public static Item deserialize(JSONObject json) {
            if (json == null) {
                return null;
            }

            String type = json.getString("type");
            int count = json.optInt("count", 1);
            return new Item(type, count);
        }

        @Override
        public JSONObject serialize() {
            JSONObject json = new JSONObject();
            json.put("type", type != null ? type : JSONObject.NULL);
            json.put("count", count);
            return json;
        }
    }
}