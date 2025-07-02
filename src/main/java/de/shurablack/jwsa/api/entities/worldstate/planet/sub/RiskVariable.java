package de.shurablack.jwsa.api.entities.worldstate.planet.sub;

import de.shurablack.jwsa.api.entities.IJsonMapping;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.json.JSONObject;

import java.io.Serializable;

/**
 * Represents a risk variable in the worldstate, containing details such as a key, name, and description.
 */
@AllArgsConstructor
@Getter
public class RiskVariable implements Serializable, IJsonMapping {

    private static final long serialVersionUID = -4013287106254500230L;

    /** The unique key identifying the risk variable. */
    private final String key;

    /** The name of the risk variable. */
    private final String name;

    /** The description of the risk variable. */
    private final String description;

    /**
     * Creates a RiskVariable instance from a JSON object.
     *
     * @param object The JSON object containing the risk variable details.
     * @return A new RiskVariable instance with the parsed details.
     */
    public static RiskVariable deserialize(JSONObject object) {
        if (object == null) {
            return null;
        }

        String key = object.optString("key", null);
        String name = object.optString("name", null);
        String description = object.optString("description", null);

        return new RiskVariable(key, name, description);
    }

    @Override
    public JSONObject serialize() {
        JSONObject json = new JSONObject();
        json.put("key", key != null ? key : JSONObject.NULL);
        json.put("name", name != null ? name : JSONObject.NULL);
        json.put("description", description != null ? description : JSONObject.NULL);
        return json;
    }
}