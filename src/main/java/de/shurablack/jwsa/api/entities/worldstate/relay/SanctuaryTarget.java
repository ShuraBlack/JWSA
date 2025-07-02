package de.shurablack.jwsa.api.entities.worldstate.relay;

import de.shurablack.jwsa.api.requests.Paths;
import de.shurablack.jwsa.api.requests.Requests;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.json.JSONObject;

/**
 * Represents a Sanctuary Target in the worldstate, containing details such as the target name,
 * whether the target is active, and a string representation of the target.
 */
@AllArgsConstructor
@Getter
public class SanctuaryTarget {

    /** The name of the Sanctuary Target. */
    private final String target;

    /** Indicates whether the Sanctuary Target is currently active. */
    private final boolean targetActive;

    /** The string representation of the Sanctuary Target. */
    private final String asString;

    /**
     * Creates a SanctuaryTarget instance from a JSON object.
     *
     * @param object The JSON object containing the Sanctuary Target details.
     * @return A new SanctuaryTarget instance with the parsed details.
     */
    public static SanctuaryTarget fromJSON(JSONObject object) {
        String target = object.optString("target", null);
        boolean targetActive = object.optBoolean("targetActive", false);
        String asString = object.optString("asString", null);

        return new SanctuaryTarget(target, targetActive, asString);
    }

    /**
     * Requests the SanctuaryTarget from the server.
     *
     * @return A SanctuaryTarget instance retrieved from the server.
     */
    public static SanctuaryTarget request() {
        return Requests.withDirectMapping(SanctuaryTarget.class, Paths.SANCTUARY_TARGET);
    }
}