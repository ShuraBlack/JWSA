package de.shurablack.jwsa.api.entities.searchable.others;

import de.shurablack.jwsa.api.entities.IJsonMapping;
import de.shurablack.jwsa.api.utils.ServerOffsetTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.json.JSONObject;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Represents a patchlog entry, containing details about updates such as additions, changes, and fixes.
 */
@AllArgsConstructor
@Getter
public class Patchlog implements Serializable, IJsonMapping {

    private static final long serialVersionUID = 4318858338278276770L;

    /** The name of the patchlog entry. */
    private final String name;

    /** The date and time when the patchlog was created. */
    private final LocalDateTime date;

    /** The URL associated with the patchlog entry. */
    private final String url;

    /** A description of additions introduced in the patch. */
    private final String additions;

    /** A description of changes made in the patch. */
    private final String changes;

    /** A description of fixes included in the patch. */
    private final String fixes;

    /**
     * Deserializes a JSON object into an instance of {@link Patchlog}.
     *
     * @param object The JSON object containing patchlog data.
     * @return An instance of {@link Patchlog} populated with data from the JSON object.
     */
    public static Patchlog deserialize(JSONObject object) {
        if (object == null) {
            return null;
        }

        String name = object.optString("name", null);
        LocalDateTime date = ServerOffsetTime.of(object.optString("date", null));
        String url = object.optString("url", null);
        String additions = object.optString("additions", null);
        String changes = object.optString("changes", null);
        String fixes = object.optString("fixes", null);

        return new Patchlog(name, date, url, additions, changes, fixes);
    }

    /**
     * Serializes this {@link Patchlog} instance into a JSON object.
     *
     * @return A {@link JSONObject} representation of this {@link Patchlog}.
     */
    @Override
    public JSONObject serialize() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", name != null ? name : JSONObject.NULL);
        jsonObject.put("date", date != null ? date.toString() : JSONObject.NULL);
        jsonObject.put("url", url != null ? url : JSONObject.NULL);
        jsonObject.put("additions", additions != null ? additions : JSONObject.NULL);
        jsonObject.put("changes", changes != null ? changes : JSONObject.NULL);
        jsonObject.put("fixes", fixes != null ? fixes : JSONObject.NULL);
        return jsonObject;
    }

}