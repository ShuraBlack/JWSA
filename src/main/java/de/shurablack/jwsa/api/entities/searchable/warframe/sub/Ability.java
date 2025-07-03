package de.shurablack.jwsa.api.entities.searchable.warframe.sub;

import de.shurablack.jwsa.api.entities.IJsonMapping;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.json.JSONObject;

import java.io.Serializable;

/**
 * Represents an ability of a Warframe, including its name, unique identifier, description, and image.
 */
@AllArgsConstructor
@Getter
public class Ability implements Serializable, IJsonMapping {

    private static final long serialVersionUID = -8090833364873418201L;

    /** The name of the ability. */
    private final String name;

    /** The unique identifier for the ability. */
    private final String uniqueName;

    /** A description of the ability. */
    private final String description;

    /** The name of the image associated with the ability. */
    private final String imageName;

    /**
     * Deserializes a JSON object into an instance of {@link Ability}.
     *
     * @param object The JSON object containing ability data.
     * @return An instance of {@link Ability} populated with data from the JSON object, or null if the input is null.
     */
    public static Ability deserialize(JSONObject object) {
        if (object == null) {
            return null;
        }

        String name = object.optString("name", null);
        String uniqueName = object.optString("uniqueName", null);
        String description = object.optString("description", null);
        String imageName = object.optString("imageName", null);

        return new Ability(name, uniqueName, description, imageName);
    }

    /**
     * Serializes this {@link Ability} instance into a JSON object.
     *
     * @return A {@link JSONObject} representation of this {@link Ability}.
     */
    @Override
    public JSONObject serialize() {
        JSONObject object = new JSONObject();
        object.put("name", name != null ? name : "");
        object.put("uniqueName", uniqueName != null ? uniqueName : "");
        object.put("description", description != null ? description : "");
        object.put("imageName", imageName != null ? imageName : "");
        return object;
    }

}