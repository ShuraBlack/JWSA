package de.shurablack.jwsa.api.entities.worldstate.planet;

import de.shurablack.jwsa.api.entities.IJsonMapping;
import de.shurablack.jwsa.api.requests.Paths;
import de.shurablack.jwsa.api.requests.Requests;
import de.shurablack.jwsa.api.utils.ServerOffsetTime;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.json.JSONObject;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Represents the status of Orb Vallis in the worldstate, containing details such as expiry time,
 * remaining time, and whether it is currently warm.
 */
@AllArgsConstructor
@Getter
@EqualsAndHashCode
public class OrbVallisStatus implements Serializable, IJsonMapping {

    private static final long serialVersionUID = 6375977920070498710L;

    /** The unique identifier of the Orb Vallis status. */
    private final String id;

    /** The expiry time of the Orb Vallis status. */
    private final LocalDateTime expiry;

    /** The remaining time left for the Orb Vallis status. */
    private final String timeLeft;

    /** Indicates whether Orb Vallis is currently in a warm state. */
    private final boolean isWarm;

    /**
     * Creates an OrbVallisStatus instance from a JSON object.
     *
     * @param json The JSON object containing the Orb Vallis status details.
     * @return A new OrbVallisStatus instance with the parsed details.
     */
    public static OrbVallisStatus deserialize(JSONObject json) {
        String id = json.optString("id", null);
        LocalDateTime expiry = ServerOffsetTime.of(json.optString("expiry", null));
        String timeLeft = json.optString("timeLeft", null);
        boolean isWarm = json.optBoolean("isWarm", false);

        return new OrbVallisStatus(id, expiry, timeLeft, isWarm);
    }

    @Override
    public JSONObject serialize() {
        JSONObject json = new JSONObject();
        json.put("id", id);
        json.put("expiry", expiry != null ? expiry.toString() : null);
        json.put("timeLeft", timeLeft);
        json.put("isWarm", isWarm);
        return json;
    }

    /**
     * Requests the OrbVallisStatus from the server.
     *
     * @return An OrbVallisStatus instance retrieved from the server.
     */
    public static OrbVallisStatus request() {
        return Requests.withDirectMapping(OrbVallisStatus.class, Paths.ORB_VALLIS);
    }
}