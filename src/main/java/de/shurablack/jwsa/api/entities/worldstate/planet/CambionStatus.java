package de.shurablack.jwsa.api.entities.worldstate.planet;

import de.shurablack.jwsa.api.requests.Paths;
import de.shurablack.jwsa.api.requests.Requests;
import de.shurablack.jwsa.api.utils.ServerOffsetTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.json.JSONObject;

import java.time.LocalDateTime;

/**
 * Represents the status of Cambion in the worldstate, containing details such as activation time,
 * expiry time, state, and other related attributes.
 */
@AllArgsConstructor
@Getter
public class CambionStatus {

    /** The unique identifier of the Cambion status. */
    private final String id;

    /** The activation time of the Cambion status. */
    private final LocalDateTime activation;

    /** The expiry time of the Cambion status. */
    private final LocalDateTime expiry;

    /** The current state of the Cambion status. */
    private final String state;

    /** The active state of the Cambion status. */
    private final String active;

    /** The remaining time left for the Cambion status. */
    private final String timeLeft;

    /**
     * Creates a CambionStatus instance from a JSON object.
     *
     * @param json The JSON object containing the Cambion status details.
     * @return A new CambionStatus instance with the parsed details.
     */
    public static CambionStatus fromJSON(JSONObject json) {
        String id = json.optString("id", null);
        LocalDateTime activation = ServerOffsetTime.of(json.optString("activation", null));
        LocalDateTime expiry = ServerOffsetTime.of(json.optString("expiry", null));
        String state = json.optString("state", null);
        String active = json.optString("active", null);
        String timeLeft = json.optString("timeLeft", null);

        return new CambionStatus(id, activation, expiry, state, active, timeLeft);
    }

    /**
     * Requests the CambionStatus from the server.
     *
     * @return A CambionStatus instance retrieved from the server.
     */
    public static CambionStatus request() {
        return Requests.withDirectMapping(CambionStatus.class, Paths.CAMBION);
    }
}