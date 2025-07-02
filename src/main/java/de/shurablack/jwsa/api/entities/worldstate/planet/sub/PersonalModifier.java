package de.shurablack.jwsa.api.entities.worldstate.planet.sub;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.json.JSONObject;

/**
 * Represents a personal modifier in the worldstate, containing details such as a key, name, and description.
 */
@AllArgsConstructor
@Getter
public class PersonalModifier {

    /** The unique key identifying the personal modifier. */
    private final String key;

    /** The name of the personal modifier. */
    private final String name;

    /** The description of the personal modifier. */
    private final String description;

    /**
     * Creates a PersonalModifier instance from a JSON object.
     *
     * @param object The JSON object containing the personal modifier details.
     * @return A new PersonalModifier instance with the parsed details.
     */
    public static PersonalModifier fromJSON(JSONObject object) {
        if (object == null) {
            return null;
        }

        String key = object.optString("key", null);
        String name = object.optString("name", null);
        String description = object.optString("description", null);

        return new PersonalModifier(key, name, description);
    }

}