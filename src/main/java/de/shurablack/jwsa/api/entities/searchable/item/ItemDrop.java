package de.shurablack.jwsa.api.entities.searchable.item;

import de.shurablack.jwsa.api.entities.IJsonMapping;
import de.shurablack.jwsa.api.entities.searchable.others.types.Rarity;
import de.shurablack.jwsa.api.requests.Paths;
import de.shurablack.jwsa.api.requests.Requests;
import de.shurablack.jwsa.api.requests.url.Encoder;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.List;

/**
 * Represents an item drop in the game, including details such as the item name, drop chance,
 * location, and rarity.
 */
@AllArgsConstructor
@Getter
@EqualsAndHashCode
public class ItemDrop implements Serializable, IJsonMapping {

    private static final long serialVersionUID = -5921477444918690593L;

    /** The name of the item. */
    private final String item;

    /** The drop chance of the item. */
    private final double chance;

    /** The location where the item can be obtained. */
    private final String place;

    /** The rarity of the item. */
    private final Rarity rarity;

    /**
     * Deserializes a JSON object into an instance of {@link ItemDrop}.
     *
     * @param object The JSON object containing item drop data.
     * @return An instance of {@link ItemDrop} populated with data from the JSON object.
     */
    public static ItemDrop deserialize(JSONObject object) {
        String item = object.optString("item", null);
        double chance = object.optDouble("chance", -1.0);
        String place = object.optString("place", null);
        Rarity rarity = Rarity.fromString(object.optString("rarity", null));

        return new ItemDrop(item, chance, place, rarity);
    }

    /**
     * Serializes this {@link ItemDrop} instance into a JSON object.
     *
     * @return A {@link JSONObject} representation of this {@link ItemDrop}.
     */
    @Override
    public JSONObject serialize() {
        JSONObject json = new JSONObject();
        json.put("item", item != null ? item : JSONObject.NULL);
        json.put("chance", chance);
        json.put("place", place != null ? place : JSONObject.NULL);
        json.put("rarity", rarity != null ? rarity.toString() : JSONObject.NULL);
        return json;
    }

    /**
     * Sends a request to retrieve a list of item drops based on a search query.
     *
     * @param query The search query for item drops.
     * @return A list of {@link ItemDrop} objects matching the search query.
     */
    public static List<ItemDrop> request(String query) {
        return Requests.withListMapping(ItemDrop.class, String.format(Paths.SEARCH_DROP, Encoder.encode(query)));
    }
}