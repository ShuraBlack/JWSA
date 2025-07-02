package de.shurablack.jwsa.api.entities.general;

import de.shurablack.jwsa.api.requests.Paths;
import de.shurablack.jwsa.api.requests.Requests;
import de.shurablack.jwsa.api.utils.ServerOffsetTime;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

/**
 * Represents a timestamp in the worldstate, encapsulating a specific point in time.
 * All other data is generated based on this timestamp.
 */
@AllArgsConstructor
@Getter
public class WorldstateTimestamp {

    /** The timestamp representing a specific point in time. */
    private final LocalDateTime timestamp;

    /**
     * Requests the current worldstate timestamp from the server.
     *
     * @return A WorldstateTimestamp object containing the current timestamp.
     */
    public static WorldstateTimestamp request() {
        return new WorldstateTimestamp(ServerOffsetTime.of(Requests.raw(Paths.TIMESTAMP).replaceAll("\"", "")));
    }
}