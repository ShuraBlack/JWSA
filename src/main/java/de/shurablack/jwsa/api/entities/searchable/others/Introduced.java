package de.shurablack.jwsa.api.entities.searchable.others;

import de.shurablack.jwsa.api.entities.IJsonMapping;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.json.JSONObject;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Represents an entity that was introduced in the game, including details such as its name, URL, aliases,
 * parent entity, and the date it was introduced.
 */
@AllArgsConstructor
@Getter
public class Introduced implements Serializable, IJsonMapping {

    private static final long serialVersionUID = -2730800863978323174L;

    /** The name of the introduced entity. */
    private final String name;

    /** The URL associated with the introduced entity. */
    private final String url;

    /** A list of aliases or alternative names for the introduced entity. */
    private final List<String> aliases;

    /** The parent entity associated with the introduced entity. */
    private final String parent;

    /** The date when the entity was introduced. */
    private final LocalDate date;

    /**
     * Deserializes a JSON object into an instance of {@link Introduced}.
     *
     * @param object The JSON object containing data about the introduced entity.
     * @return An instance of {@link Introduced} populated with data from the JSON object.
     */
    public static Introduced deserialize(JSONObject object) {
        if (object == null) {
            return null;
        }

        String name = object.optString("name", null);
        String url = object.optString("url", null);
        List<String> aliases = object.has("aliases") ? object.getJSONArray("aliases").toList().stream()
                .map(Object::toString).collect(Collectors.toList()) : List.of();
        String parent = object.optString("parent", null);
        LocalDate date = LocalDate.parse(object.optString("date", "1970-01-01"));

        return new Introduced(name, url, aliases, parent, date);
    }

    /**
     * Serializes this {@link Introduced} instance into a JSON object.
     *
     * @return A {@link JSONObject} representation of this {@link Introduced}.
     */
    @Override
    public JSONObject serialize() {
        JSONObject object = new JSONObject();
        object.put("name", name != null ? name : JSONObject.NULL);
        object.put("url", url != null ? url : JSONObject.NULL);
        object.put("aliases", aliases);
        object.put("parent", parent != null ? parent : JSONObject.NULL);
        object.put("date", date.toString());
        return object;
    }

}