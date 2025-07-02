package de.shurablack.jwsa.api.entities.worldstate.others;

import de.shurablack.jwsa.api.entities.IJsonMapping;
import de.shurablack.jwsa.api.utils.ServerOffsetTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.json.JSONObject;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Represents an alternative event or state in the worldstate with activation and expiry times.
 */
@AllArgsConstructor
@Getter
public class Alt implements Serializable, IJsonMapping {

    private static final long serialVersionUID = 1053187422491859227L;

    /** The activation time of the alternative event or state. */
    private final LocalDateTime activation;

    /** The expiry time of the alternative event or state. */
    private final LocalDateTime expiry;

    /**
     * Creates an Alt instance from a JSON object.
     *
     * @param json The JSON object containing the activation and expiry times.
     * @return A new Alt instance with the parsed activation and expiry times.
     */
    public static Alt deserialize(org.json.JSONObject json) {
        LocalDateTime activation = ServerOffsetTime.of(json.optString("activation", null));
        LocalDateTime expiry = ServerOffsetTime.of(json.optString("expiry", null));

        return new Alt(activation, expiry);
    }

    @Override
    public JSONObject serialize() {
        JSONObject json = new JSONObject();
        json.put("activation", activation != null ? activation.toString() : JSONObject.NULL);
        json.put("expiry", expiry != null ? expiry.toString() : JSONObject.NULL);
        return json;
    }
}