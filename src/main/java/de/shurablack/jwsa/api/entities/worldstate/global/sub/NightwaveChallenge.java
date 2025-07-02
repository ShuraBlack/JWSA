package de.shurablack.jwsa.api.entities.worldstate.global.sub;

import de.shurablack.jwsa.api.utils.ServerOffsetTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.json.JSONObject;

import java.time.LocalDateTime;

/**
 * Represents a Nightwave Challenge in the worldstate, containing details such as activation time, expiry,
 * status, type (daily or elite), title, description, and reputation reward.
 */
@AllArgsConstructor
@Getter
public class NightwaveChallenge {

    /** The unique identifier of the Nightwave Challenge. */
    private final String id;

    /** The activation time of the Nightwave Challenge. */
    private final LocalDateTime activation;

    /** The expiry time of the Nightwave Challenge. */
    private final LocalDateTime expiry;

    /** A string describing the start of the Nightwave Challenge. */
    private final String startString;

    /** Indicates whether the Nightwave Challenge is currently active. */
    private final boolean active;

    /** Indicates whether the Nightwave Challenge is a daily challenge. */
    private final boolean daily;

    /** Indicates whether the Nightwave Challenge is an elite challenge. */
    private final boolean elite;

    /** The title of the Nightwave Challenge. */
    private final String title;

    /** The description of the Nightwave Challenge. */
    private final String desc;

    /** The reputation reward for completing the Nightwave Challenge. */
    private final Number reputation;

    /**
     * Creates a NightwaveChallenge object from a JSON representation.
     *
     * @param object The JSON object containing the Nightwave Challenge data.
     * @return A NightwaveChallenge object populated with data from the JSON object.
     */
    public static NightwaveChallenge fromJSON(JSONObject object) {
        String id = object.optString("id", null);
        LocalDateTime activation = ServerOffsetTime.of(object.optString("activation", null));
        LocalDateTime expiry = ServerOffsetTime.of(object.optString("expiry", null));
        String startString = object.optString("startString", null);
        boolean active = object.optBoolean("active", false);
        boolean daily = object.optBoolean("isDaily", false);
        boolean elite = object.optBoolean("isElite", false);
        String title = object.optString("title", null);
        String desc = object.optString("desc", null);
        Number reputation = object.optNumber("reputation", -1);

        return new NightwaveChallenge(id, activation, expiry, startString, active, daily, elite, title, desc, reputation);
    }
}