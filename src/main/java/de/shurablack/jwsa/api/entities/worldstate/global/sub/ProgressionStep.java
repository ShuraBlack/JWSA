package de.shurablack.jwsa.api.entities.worldstate.global.sub;

import de.shurablack.jwsa.api.entities.IJsonMapping;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.json.JSONObject;

import java.io.Serializable;

/**
 * Represents a progression step in a worldstate event, containing details such as the type and progress amount.
 */
@AllArgsConstructor
@Getter
public class ProgressionStep implements Serializable, IJsonMapping {

    private static final long serialVersionUID = 5814488428131325120L;

    /** The type of the progression step. */
    private final String type;

    /** The progress amount for this step. */
    private final Number progressAmt;

    /**
     * Creates a ProgressionStep object from a JSON representation.
     *
     * @param object The JSON object containing the progression step data.
     * @return A ProgressionStep object populated with data from the JSON object.
     */
    public static ProgressionStep deserialize(JSONObject object) {
        String type = object.optString("type", null);
        Number progressAmt = object.optNumber("progressAmt", -1);

        return new ProgressionStep(type, progressAmt);
    }

    @Override
    public JSONObject serialize() {
        JSONObject json = new JSONObject();
        json.put("type", type != null ? type : JSONObject.NULL);
        json.put("progressAmt", progressAmt != null ? progressAmt : JSONObject.NULL);
        return json;
    }
}