package de.shurablack.jwsa.api.entities.worldstate.global;

import de.shurablack.jwsa.api.entities.IJsonMapping;
import de.shurablack.jwsa.api.requests.Paths;
import de.shurablack.jwsa.api.requests.Requests;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.json.JSONObject;

import java.io.Serializable;

/**
 * Represents the construction progress of in-game events, such as Fomorian and Razorback.
 * This class provides details about the progress of these events and includes methods
 * for creating instances from JSON data and requesting the current progress from the server.
 */
@AllArgsConstructor
@Getter
public class ConstructionProgress implements Serializable, IJsonMapping {

    private static final long serialVersionUID = -5310155051652426558L;

    /** The unique identifier of the construction progress entry. */
    private final String id;

    /** The progress percentage of the Fomorian event. */
    private final Number fomorianProgress;

    /** The progress percentage of the Razorback event. */
    private final Number razorbackProgress;

    /**
     * Creates a ConstructionProgress object from a JSON representation.
     *
     * @param json The JSON object containing the construction progress data.
     * @return A ConstructionProgress object populated with data from the JSON object.
     */
    public static ConstructionProgress deserialize(org.json.JSONObject json) {
        String id = json.optString("id", null);
        Number fomorianProgress = json.optNumber("fomorianProgress", -1);
        Number razorbackProgress = json.optNumber("razorbackProgress", -1);

        return new ConstructionProgress(id, fomorianProgress, razorbackProgress);
    }

    @Override
    public JSONObject serialize() {
        JSONObject json = new JSONObject();
        json.put("id", id != null ? id : JSONObject.NULL);
        json.put("fomorianProgress", fomorianProgress != null ? fomorianProgress : JSONObject.NULL);
        json.put("razorbackProgress", razorbackProgress != null ? razorbackProgress : JSONObject.NULL);
        return json;
    }

    /**
     * Requests the current construction progress data from the server.
     *
     * @return A ConstructionProgress object representing the current construction progress data.
     */
    public static ConstructionProgress request() {
        return Requests.withDirectMapping(ConstructionProgress.class, Paths.CONSTRUCTION_PROGRESS);
    }
}
