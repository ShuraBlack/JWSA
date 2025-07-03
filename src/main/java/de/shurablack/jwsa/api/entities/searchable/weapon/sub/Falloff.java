package de.shurablack.jwsa.api.entities.searchable.weapon.sub;

import de.shurablack.jwsa.api.entities.IJsonMapping;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.json.JSONObject;

import java.io.Serializable;

/**
 * Represents the falloff of a weapon in the game, including the start and end distances
 * and the damage reduction percentage.
 */
@AllArgsConstructor
@Getter
public class Falloff implements Serializable, IJsonMapping {

    private static final long serialVersionUID = -7184325835971488988L;

    /** The starting distance of the falloff. */
    private final Number start;

    /** The ending distance of the falloff. */
    private final Number end;

    /** The damage reduction percentage over the falloff range. */
    private final Number reduction;

    /**
     * Deserializes a JSON object into an instance of {@link Falloff}.
     *
     * @param object The JSON object containing falloff data.
     * @return An instance of {@link Falloff} populated with data from the JSON object, or null if the input is null.
     */
    public static Falloff deserialize(JSONObject object) {
        if (object == null) {
            return null;
        }

        Number start = object.optNumber("start", -1);
        Number end = object.optNumber("end", -1);
        Number reduction = object.optNumber("reduction", -1);

        return new Falloff(start, end, reduction);
    }

    /**
     * Serializes this {@link Falloff} instance into a JSON object.
     *
     * @return A {@link JSONObject} representation of this {@link Falloff}.
     */
    @Override
    public JSONObject serialize() {
        JSONObject object = new JSONObject();
        object.put("start", start != null ? start : -1);
        object.put("end", end != null ? end : -1);
        object.put("reduction", reduction != null ? reduction : -1);
        return object;
    }
}