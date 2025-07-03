package de.shurablack.jwsa.api.entities.searchable.weapon.sub;

import de.shurablack.jwsa.api.entities.IJsonMapping;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.json.JSONObject;

import java.io.Serializable;

/**
 * Represents a pellet in the game, including its name and count.
 */
@AllArgsConstructor
@Getter
public class Pellet implements Serializable, IJsonMapping {

    private static final long serialVersionUID = -8917017843988559835L;

    /** The name of the pellet. */
    private final String name;

    /** The count of the pellet. */
    private final Number count;

    /**
     * Deserializes a JSON object into an instance of {@link Pellet}.
     *
     * @param object The JSON object containing pellet data.
     * @return An instance of {@link Pellet} populated with data from the JSON object, or null if the input is null.
     */
    public static Pellet deserialize(JSONObject object) {
        if (object == null) {
            return null;
        }

        String name = object.optString("name", null);
        Number count = object.optNumber("count", -1);

        return new Pellet(name, count);
    }

    /**
     * Serializes this {@link Pellet} instance into a JSON object.
     *
     * @return A {@link JSONObject} representation of this {@link Pellet}.
     */
    @Override
    public JSONObject serialize() {
        JSONObject object = new JSONObject();
        object.put("name", name != null ? name : "");
        object.put("count", count != null ? count : -1);
        return object;
    }
}