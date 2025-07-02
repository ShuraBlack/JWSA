package de.shurablack.jwsa.api.entities.worldstate.planet.sub;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.json.JSONObject;

/**
 * Represents a deviation in the worldstate, containing details such as a key, name, and description.
 */
@AllArgsConstructor
@Getter
public class Deviation {

    /** The unique key identifying the deviation. */
    private final String key;

    /** The name of the deviation. */
    private final String name;

    /** The description of the deviation. */
    private final String description;

    /**
     * Creates a Deviation instance from a JSON object.
     *
     * @param object The JSON object containing the deviation details.
     * @return A new Deviation instance with the parsed details.
     */
    public static Deviation fromJSON(JSONObject object) {
        if (object == null) {
            return null;
        }

        String key = object.optString("key", null);
        String name = object.optString("name", null);
        String description = object.optString("description", null);

        return new Deviation(key, name, description);
    }

}