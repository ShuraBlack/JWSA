package de.shurablack.jwsa.api.entities.worldstate.global.sub;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.json.JSONObject;

/**
 * Represents a progression step in a worldstate event, containing details such as the type and progress amount.
 */
@AllArgsConstructor
@Getter
public class ProgressionStep {

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
    public static ProgressionStep fromJSON(JSONObject object) {
        String type = object.optString("type", null);
        Number progressAmt = object.optNumber("progressAmt", -1);

        return new ProgressionStep(type, progressAmt);
    }
}