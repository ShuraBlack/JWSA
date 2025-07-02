package de.shurablack.jwsa.api.entities.worldstate.others;

import de.shurablack.jwsa.api.utils.ServerOffsetTime;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

/**
 * Represents an alternative event or state in the worldstate with activation and expiry times.
 */
@AllArgsConstructor
@Getter
public class Alt {

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
    public static Alt fromJSON(org.json.JSONObject json) {
        LocalDateTime activation = ServerOffsetTime.of(json.optString("activation", null));
        LocalDateTime expiry = ServerOffsetTime.of(json.optString("expiry", null));

        return new Alt(activation, expiry);
    }
}