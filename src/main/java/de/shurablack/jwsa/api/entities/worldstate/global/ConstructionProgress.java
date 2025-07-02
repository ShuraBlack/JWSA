package de.shurablack.jwsa.api.entities.worldstate.global;

import de.shurablack.jwsa.api.requests.Paths;
import de.shurablack.jwsa.api.requests.Requests;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Represents the construction progress of in-game events, such as Fomorian and Razorback.
 * This class provides details about the progress of these events and includes methods
 * for creating instances from JSON data and requesting the current progress from the server.
 */
@AllArgsConstructor
@Getter
public class ConstructionProgress {

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
    public static ConstructionProgress fromJSON(org.json.JSONObject json) {
        String id = json.optString("id", null);
        Number fomorianProgress = json.optNumber("fomorianProgress", -1);
        Number razorbackProgress = json.optNumber("razorbackProgress", -1);

        return new ConstructionProgress(id, fomorianProgress, razorbackProgress);
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
