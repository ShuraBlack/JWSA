package de.shurablack.jwsa.api.entities.worldstate.global.sub;

import de.shurablack.jwsa.api.utils.ServerOffsetTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.json.JSONObject;

import java.time.LocalDateTime;

/**
 * Represents an Incursion event in the worldstate, containing details such as activation time, expiry, and status.
 */
@AllArgsConstructor
@Getter
public class Incursions {

    /** The unique identifier of the incursion. */
    private final String id;

    /** The activation time of the incursion. */
    private final LocalDateTime activation;

    /** The expiry time of the incursion. */
    private final LocalDateTime expiry;

    /** A string describing the start of the incursion. */
    private final String startString;

    /** Indicates whether the incursion is currently active. */
    private final boolean active;

    /**
     * Creates an Incursions object from a JSON representation.
     *
     * @param object The JSON object containing the incursion data.
     * @return An Incursions object populated with data from the JSON object.
     */
    public static Incursions fromJSON(JSONObject object) {
        if (object == null) {
            return null;
        }

        String id = object.optString("id", null);
        LocalDateTime activation = ServerOffsetTime.of(object.optString("activation", null));
        LocalDateTime expiry = ServerOffsetTime.of(object.optString("expiry", null));
        String startString = object.optString("startString", null);
        boolean active = object.optBoolean("active", false);

        return new Incursions(id, activation, expiry, startString, active);
    }
}