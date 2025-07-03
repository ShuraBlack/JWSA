package de.shurablack.jwsa.api.entities.searchable.others;

import de.shurablack.jwsa.api.entities.IJsonMapping;
import de.shurablack.jwsa.api.entities.searchable.warframe.sub.Drop;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Represents an alternative component in the game, including its name, description, image,
 * tradeability, item count, ducat value, and associated drops.
 */
@AllArgsConstructor
@Getter
public class AltComponent implements Serializable, IJsonMapping {

    private final static long serialVersionUID = -682644954673366475L;

    /** The name of the alternative component. */
    private final String name;

    /** The unique identifier for the alternative component. */
    private final String uniqueName;

    /** A description of the alternative component. */
    private final String description;

    /** The name of the image associated with the alternative component. */
    private final String imageName;

    /** Indicates whether the alternative component is tradable. */
    private final boolean tradable;

    /** The item count of the alternative component. */
    private final Number itemCount;

    /** The ducat value of the alternative component. */
    private final Number ducats;

    /** A list of drops associated with the alternative component. */
    private final List<Drop> drops;

    /**
     * Deserializes a JSON object into an instance of {@link AltComponent}.
     *
     * @param object The JSON object containing alternative component data.
     * @return An instance of {@link AltComponent} populated with data from the JSON object, or null if the input is null.
     */
    public static AltComponent deserialize(JSONObject object) {
        if (object == null) {
            return null;
        }

        String name = object.optString("name", null);
        String uniqueName = object.optString("uniqueName", null);
        String description = object.optString("description", null);
        String imageName = object.optString("imageName", null);
        boolean tradable = object.optBoolean("tradable", false);
        Number itemCount = object.optNumber("itemCount", -1);
        Number ducats = object.optNumber("ducats", -1);
        List<Drop> drops = new ArrayList<>();
        if (object.has("drops")) {
            object.getJSONArray("drops").forEach(item -> {
                drops.add(Drop.deserialize((JSONObject) item));
            });
        }

        return new AltComponent(name, uniqueName, description, imageName, tradable, itemCount, ducats, drops);
    }

    /**
     * Serializes this {@link AltComponent} instance into a JSON object.
     *
     * @return A {@link JSONObject} representation of this {@link AltComponent}.
     */
    @Override
    public JSONObject serialize() {
        JSONObject object = new JSONObject();
        object.put("name", name != null ? name : "");
        object.put("uniqueName", uniqueName != null ? uniqueName : "");
        object.put("description", description != null ? description : "");
        object.put("imageName", imageName != null ? imageName : "");
        object.put("tradable", tradable);
        object.put("itemCount", itemCount != null ? itemCount : -1);
        object.put("ducats", ducats != null ? ducats : -1);

        if (drops != null && !drops.isEmpty()) {
            object.put("drops", drops.stream().map(Drop::serialize).collect(Collectors.toList()));
        }

        return object;
    }

}