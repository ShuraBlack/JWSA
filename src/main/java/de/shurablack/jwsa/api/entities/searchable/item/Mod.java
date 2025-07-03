package de.shurablack.jwsa.api.entities.searchable.item;

import de.shurablack.jwsa.api.entities.IJsonMapping;
import de.shurablack.jwsa.api.entities.searchable.others.Component;
import de.shurablack.jwsa.api.entities.searchable.others.Introduced;
import de.shurablack.jwsa.api.entities.searchable.others.Patchlog;
import de.shurablack.jwsa.api.entities.searchable.others.types.Polarity;
import de.shurablack.jwsa.api.requests.Paths;
import de.shurablack.jwsa.api.requests.Requests;
import de.shurablack.jwsa.api.requests.url.Encoder;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Represents a mod in the game, including details such as its name, description, type,
 * tradeability, associated components, patchlogs, polarity, and other metadata.
 */
@AllArgsConstructor
@Getter
@EqualsAndHashCode
public class Mod implements Serializable, IJsonMapping {

    private static final long serialVersionUID = -2927253511981678329L;

    /** The name of the mod. */
    private final String name;

    /** The unique identifier for the mod. */
    private final String uniqueName;

    /** A description of the mod. */
    private final String description;

    /** The type of the mod. */
    private final String type;

    /** Indicates whether the mod is tradable. */
    private final boolean tradable;

    /** The category to which the mod belongs. */
    private final String category;

    /** The product category of the mod. */
    private final String productCategory;

    /** A list of patchlogs associated with the mod. */
    private final List<Patchlog> patchlogs;

    /** A list of components associated with the mod. */
    private final List<Component> components;

    /** Information about when the mod was introduced. */
    private final Introduced introduced;

    /** The base drain value of the mod. */
    private final int baseDrain;

    /** The compatibility name of the mod. */
    private final String compatName;

    /** The fusion limit of the mod. */
    private final int fusionLimit;

    /** The level stats of the mod. */
    private final List<List<String>> levelStats;

    /** The polarity of the mod. */
    private final Polarity polarity;

    /** Indicates whether the mod is transmutable. */
    private final boolean transmutable;

    /**
     * Deserializes a JSON object into an instance of {@link Mod}.
     *
     * @param object The JSON object containing mod data.
     * @return An instance of {@link Mod} populated with data from the JSON object.
     */
    public static Mod deserialize(JSONObject object) {
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
        int baseDrain = object.optInt("baseDrain", -1);
        String compatName = object.optString("compatName", null);
        int fusionLimit = object.optInt("fusionLimit", -1);
        List<List<String>> levelStats = new ArrayList<>();
        if (object.has("levelStats")) {
            for (Object levelStatObj : object.getJSONArray("levelStats")) {
                List<String> stats = ((JSONObject) levelStatObj).getJSONArray("stats").toList().stream()
                        .map(Object::toString).collect(Collectors.toList());
                levelStats.add(stats);
            }
        }
        Polarity polarity = Polarity.fromString(object.optString("polarity", null));
        boolean transmutable = object.optBoolean("transmutable", false);

        return new Mod(name, uniqueName, description, type, tradable, category, productCategory, patchlogs,
                components, introduced, baseDrain, compatName, fusionLimit, levelStats,
                polarity, transmutable);
    }

    /**
     * Serializes this {@link Mod} instance into a JSON object.
     *
     * @return A {@link JSONObject} representation of this {@link Mod}.
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
        json.put("baseDrain", baseDrain);
        json.put("compatName", compatName != null ? compatName : JSONObject.NULL);
        json.put("fusionLimit", fusionLimit);
        if (levelStats != null) {
            json.put("levelStats", levelStats.stream()
                    .map(stats -> new JSONObject().put("stats", stats)).collect(Collectors.toList()));
        }
        json.put("polarity", polarity != null ? polarity.toString() : JSONObject.NULL);
        json.put("transmutable", transmutable);

        return json;
    }

    /**
     * Sends a request to retrieve a mod based on a search query.
     *
     * @param query The search query for the mod.
     * @return An instance of {@link Mod} matching the search query.
     */
    public static Mod request(String query) {
        return Requests.withDirectMapping(Mod.class, String.format(Paths.CLOSEST_MOD, Encoder.encode(query)));
    }

    /**
     * Sends a request to retrieve a list of mods based on a search query.
     *
     * @param query The search query for the mods.
     * @return A list of {@link Mod} objects matching the search query.
     */
    public static List<Mod> requestAll(String query) {
        return Requests.withListMapping(Mod.class, String.format(Paths.SEARCH_CLOSEST_MODS, Encoder.encode(query)));
    }

}