package de.shurablack.jwsa.api.entities.worldstate.planet;

import de.shurablack.jwsa.api.entities.IJsonMapping;
import de.shurablack.jwsa.api.requests.Paths;
import de.shurablack.jwsa.api.requests.Requests;
import de.shurablack.jwsa.api.utils.ServerOffsetTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.json.JSONObject;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Represents the Earth cycle in the worldstate, containing details such as activation time,
 * expiry time, whether it is day or night, and other related attributes.
 */
@AllArgsConstructor
@Getter
public class EarthCycle implements Serializable, IJsonMapping {

    private static final long serialVersionUID = 4727737361016963042L;

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
    public static EarthCycle deserialize(JSONObject object) {
        String id = object.optString("id", null);
        LocalDateTime activation = ServerOffsetTime.of(object.optString("activation", null));
        LocalDateTime expiry = ServerOffsetTime.of(object.optString("expiry", null));
        String startString = object.optString("startString", null);
        boolean active = object.optBoolean("active", false);
        boolean day = object.optBoolean("isDay", false);
        String timeLeft = object.optString("timeLeft", null);

        return new EarthCycle(id, activation, expiry, startString, active, day, timeLeft);
    }

    @Override
    public JSONObject serialize() {
        JSONObject json = new JSONObject();
        json.put("id", id != null ? id : JSONObject.NULL);
        json.put("activation", activation != null ? activation.toString() : JSONObject.NULL);
        json.put("expiry", expiry != null ? expiry.toString() : JSONObject.NULL);
        json.put("startString", startString != null ? startString : JSONObject.NULL);
        json.put("active", active);
        json.put("isDay", day);
        json.put("timeLeft", timeLeft != null ? timeLeft : JSONObject.NULL);
        return json;
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