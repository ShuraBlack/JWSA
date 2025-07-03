package de.shurablack.jwsa.api.entities.searchable.item;

import de.shurablack.jwsa.api.entities.IJsonMapping;
import de.shurablack.jwsa.api.entities.searchable.others.Introduced;
import de.shurablack.jwsa.api.entities.searchable.others.Component;
import de.shurablack.jwsa.api.entities.searchable.others.Patchlog;
import de.shurablack.jwsa.api.requests.Paths;
import de.shurablack.jwsa.api.requests.Requests;
import de.shurablack.jwsa.api.requests.url.Encoder;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.json.JSONObject;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Represents an item in the game, including details such as its name, description, type,
 * tradeability, associated components, patchlogs, and other metadata.
 */
@AllArgsConstructor
@Getter
@EqualsAndHashCode
public class Item implements Serializable, IJsonMapping {

    private static final long serialVersionUID = 5391687902857151234L;

    /** The name of the item. */
    private final String name;

    /** The unique identifier for the item. */
    private final String uniqueName;

    /** A description of the item. */
    private final String description;

    /** The type of the item. */
    private final String type;

    /** Indicates whether the item is tradeable. */
    private final boolean tradable;

    /** The category to which the item belongs. */
    private final String category;

    /** The product category of the item. */
    private final String productCategory;

    /** A list of patchlogs associated with the item. */
    private final List<Patchlog> patchlogs;

    /** A list of components associated with the item. */
    private final List<Component> components;

    /** Information about when the item was introduced. */
    private final Introduced introduced;

    /** The estimated date when the item will be vaulted. */
    private final LocalDate estimatedVaultDate;

    /**
     * Deserializes a JSON object into an instance of {@link Item}.
     *
     * @param object The JSON object containing item data.
     * @return An instance of {@link Item} populated with data from the JSON object.
     */
    public static Item deserialize(JSONObject object) {
        String name = object.optString("name", null);
        String uniqueName = object.optString("uniqueName", null);
        String description = object.optString("description", null);
        String type = object.optString("type", null);
        boolean tradable = object.optBoolean("tradable", false);
        String category = object.optString("category", null);
        String productCategory = object.optString("productCategory", null);

        List<Patchlog> patchlogs = new ArrayList<>();
        if (object.has("patchlogs")) {
            for (Object patchlogObj : object.getJSONArray("patchlogs")) {
                patchlogs.add(Patchlog.deserialize((JSONObject) patchlogObj));
            }
        }
        List<Component> components = new ArrayList<>();
        if (object.has("components")) {
            for (Object componentObj : object.getJSONArray("components")) {
                components.add(Component.deserialize((JSONObject) componentObj));
            }
        }
        Introduced introduced = Introduced.deserialize(object.optJSONObject("introduced"));
        LocalDate estimatedVaultDate = LocalDate.parse(object.optString("estimatedVaultDate", "1970-01-01"));

        return new Item(name, uniqueName, description, type, tradable, category, productCategory, patchlogs, components, introduced, estimatedVaultDate);
    }

    /**
     * Serializes this {@link Item} instance into a JSON object.
     *
     * @return A {@link JSONObject} representation of this {@link Item}.
     */
    @Override
    public JSONObject serialize() {
        JSONObject json = new JSONObject();
        json.put("name", name != null ? name : JSONObject.NULL);
        json.put("uniqueName", uniqueName != null ? uniqueName : JSONObject.NULL);
        json.put("description", description != null ? description : JSONObject.NULL);
        json.put("type", type != null ? type : JSONObject.NULL);
        json.put("tradable", tradable);
        json.put("category", category != null ? category : JSONObject.NULL);
        json.put("productCategory", productCategory != null ? productCategory : JSONObject.NULL);

        if (patchlogs != null) {
            json.put("patchlogs", patchlogs.stream().map(Patchlog::serialize).collect(Collectors.toList()));
        }
        if (components != null) {
            json.put("components", components.stream().map(Component::serialize).collect(Collectors.toList()));
        }
        if (introduced != null) {
            json.put("introduced", introduced.serialize());
        }
        json.put("estimatedVaultDate", estimatedVaultDate.toString());

        return json;
    }

    /**
     * Sends a request to retrieve an item based on a search query.
     *
     * @param query The search query for the item.
     * @return An instance of {@link Item} matching the search query.
     */
    public static Item request(String query) {
        return Requests.withDirectMapping(Item.class, String.format(Paths.CLOSEST_ITEM, Encoder.encode(query)));
    }

    /**
     * Sends a request to retrieve a list of items based on a search query.
     *
     * @param query The search query for the items.
     * @return A list of {@link Item} objects matching the search query.
     */
    public static List<Item> requestAll(String query) {
        return Requests.withListMapping(Item.class, String.format(Paths.SEARCH_CLOSEST_ITEMS, Encoder.encode(query)));
    }

}