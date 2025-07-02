package de.shurablack.jwsa.api.entities.worldstate.planet;

import de.shurablack.jwsa.api.requests.Paths;
import de.shurablack.jwsa.api.requests.Requests;
import de.shurablack.jwsa.api.utils.Logging;
import de.shurablack.jwsa.api.utils.ServerOffsetTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.apache.logging.log4j.Level;
import org.json.JSONObject;

import java.time.LocalDateTime;

/**
 * Represents the Earth cycle in the worldstate, containing details such as activation time,
 * expiry time, whether it is day or night, and other related attributes.
 */
@AllArgsConstructor
@Getter
public class EarthCycle {

    /** The unique identifier of the Earth cycle. */
    private final String id;

    /** The activation time of the Earth cycle. */
    private final LocalDateTime activation;

    /** The expiry time of the Earth cycle. */
    private final LocalDateTime expiry;

    /** The start string of the Earth cycle. */
    private final String startString;

    /** Indicates whether the Earth cycle is currently active. */
    private final boolean active;

    /** Indicates whether it is currently day in the Earth cycle. */
    private final boolean day;

    /** The remaining time left for the Earth cycle. */
    private final String timeLeft;

    /**
     * Creates an EarthCycle instance from a JSON object.
     *
     * @param object The JSON object containing the Earth cycle details.
     * @return A new EarthCycle instance with the parsed details.
     */
    public static EarthCycle fromJSON(JSONObject object) {
        String id = object.optString("id", null);
        LocalDateTime activation = ServerOffsetTime.of(object.optString("activation", null));
        LocalDateTime expiry = ServerOffsetTime.of(object.optString("expiry", null));
        String startString = object.optString("startString", null);
        boolean active = object.optBoolean("active", false);
        boolean day = object.optBoolean("isDay", false);
        String timeLeft = object.optString("timeLeft", null);

        return new EarthCycle(id, activation, expiry, startString, active, day, timeLeft);
    }

    /**
     * Requests the EarthCycle from the server.
     *
     * @return An EarthCycle instance retrieved from the server.
     */
    public static EarthCycle request() {
        return Requests.withDirectMapping(EarthCycle.class, Paths.EARTH_CYCLE);
    }
}