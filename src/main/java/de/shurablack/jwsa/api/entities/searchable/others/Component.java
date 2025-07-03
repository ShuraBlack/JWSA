package de.shurablack.jwsa.api.entities.searchable.others;

import de.shurablack.jwsa.api.entities.IJsonMapping;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.json.JSONObject;

import java.io.Serializable;

/**
 * Represents a component in the game, including details such as its name, description, type,
 * tradeability, and category information.
 */
@AllArgsConstructor
@Getter
public class Component implements Serializable, IJsonMapping {

    private static final long serialVersionUID = 2556348707183289127L;

    /** The name of the component. */
    private final String name;

    /** The unique identifier for the component. */
    private final String uniqueName;

    /** A description of the component. */
    private final String description;

    /** The type of the component. */
    private final String type;

    /** Indicates whether the component is tradeable. */
    private final boolean tradeable;

    /** The category to which the component belongs. */
    private final String category;

    /** The product category of the component. */
    private final String productCategory;

    /**
     * Deserializes a JSON object into an instance of {@link Component}.
     *
     * @param object The JSON object containing component data.
     * @return An instance of {@link Component} populated with data from the JSON object.
     */
    public static Component deserialize(JSONObject object) {
        if (object == null) {
            return null;
        }

        String name = object.optString("name", null);
        String uniqueName = object.optString("uniqueName", null);
        String description = object.optString("description", null);
        String type = object.optString("type", null);
        boolean tradeable = object.optBoolean("tradeable", false);
        String category = object.optString("category", null);
        String productCategory = object.optString("productCategory", null);

        return new Component(name, uniqueName, description, type, tradeable, category, productCategory);
    }

    /**
     * Serializes this {@link Component} instance into a JSON object.
     *
     * @return A {@link JSONObject} representation of this {@link Component}.
     */
    @Override
    public JSONObject serialize() {
        JSONObject object = new JSONObject();
        object.put("name", name != null ? name : JSONObject.NULL);
        object.put("uniqueName", uniqueName != null ? uniqueName : JSONObject.NULL);
        object.put("description", description != null ? description : JSONObject.NULL);
        object.put("type", type != null ? type : JSONObject.NULL);
        object.put("tradeable", tradeable);
        object.put("category", category != null ? category : JSONObject.NULL);
        object.put("productCategory", productCategory != null ? productCategory : JSONObject.NULL);
        return object;
    }

}