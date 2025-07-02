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
 * Represents the status of Cetus in the worldstate, containing details such as activation time,
 * expiry time, state, and other related attributes.
 */
@AllArgsConstructor
@Getter
public class CetusStatus implements Serializable, IJsonMapping {

    private static final long serialVersionUID = 7609706985791774831L;

    /** The unique identifier of the Cetus status. */
    private final String id;

    /** The activation time of the Cetus status. */
    private final LocalDateTime activation;

    /** The expiry time of the Cetus status. */
    private final LocalDateTime expiry;

    /** The current state of the Cetus status. */
    private final String state;

    /** The active state of the Cetus status. */
    private final String active;

    /** The remaining time left for the Cetus status. */
    private final String timeLeft;

    /**
     * Creates a CetusStatus instance from a JSON object.
     *
     * @param json The JSON object containing the Cetus status details.
     * @return A new CetusStatus instance with the parsed details.
     */
    public static CetusStatus deserialize(JSONObject json) {
        String id = json.optString("id", null);
        LocalDateTime activation = ServerOffsetTime.of(json.optString("activation", null));
        LocalDateTime expiry = ServerOffsetTime.of(json.optString("expiry", null));
        String state = json.optString("state", null);
        String active = json.optString("active", null);
        String timeLeft = json.optString("timeLeft", null);

        return new CetusStatus(id, activation, expiry, state, active, timeLeft);
    }

    @Override
    public JSONObject serialize() {
        JSONObject json = new JSONObject();
        json.put("id", id != null ? id : JSONObject.NULL);
        json.put("activation", activation != null ? activation.toString() : JSONObject.NULL);
        json.put("expiry", expiry != null ? expiry.toString() : JSONObject.NULL);
        json.put("state", state != null ? state : JSONObject.NULL);
        json.put("active", active != null ? active : JSONObject.NULL);
        json.put("timeLeft", timeLeft != null ? timeLeft : JSONObject.NULL);
        return json;
    }

    /**
     * Requests the CetusStatus from the server.
     *
     * @return A CetusStatus instance retrieved from the server.
     */
    public static CetusStatus request() {
        return Requests.withDirectMapping(CetusStatus.class, Paths.CETUS);
    }
}