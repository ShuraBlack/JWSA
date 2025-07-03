package de.shurablack.jwsa.api.entities.searchable.warframe.sub;

import de.shurablack.jwsa.api.entities.IJsonMapping;
import de.shurablack.jwsa.api.entities.searchable.others.types.Rarity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.json.JSONObject;

import java.io.Serializable;

/**
 * Represents a drop in the game, including details such as its unique name, rarity, type, location, and drop chance.
 */
@AllArgsConstructor
@Getter
public class Drop implements Serializable, IJsonMapping {

    private static final long serialVersionUID = -1928149036790543014L;

    /** The unique identifier for the drop. */
    private final String uniqueName;

    /** The rarity level of the drop. */
    private final Rarity rarity;

    /** The type of the drop. */
    private final String type;

    /** The location where the drop can be obtained. */
    private final String location;

    /** The chance of obtaining the drop. */
    private final Number chance;

    /**
     * Deserializes a JSON object into an instance of {@link Drop}.
     *
     * @param object The JSON object containing drop data.
     * @return An instance of {@link Drop} populated with data from the JSON object, or null if the input is null.
     */
    public static Drop deserialize(JSONObject object) {
        if (object == null) {
            return null;
        }

        String uniqueName = object.optString("uniqueName", null);
        Rarity rarity = Rarity.fromString(object.optString("rarity", null));
        String type = object.optString("type", null);
        String location = object.optString("location", null);
        Number chance = object.optNumber("chance", -1.0);

        return new Drop(uniqueName, rarity, type, location, chance);
    }

    /**
     * Serializes this {@link Drop} instance into a JSON object.
     *
     * @return A {@link JSONObject} representation of this {@link Drop}.
     */
    @Override
    public JSONObject serialize() {
        JSONObject object = new JSONObject();
        object.put("uniqueName", uniqueName);
        object.put("rarity", rarity.toString());
        object.put("type", type);
        object.put("location", location);
        object.put("chance", chance);
        return object;
    }
}