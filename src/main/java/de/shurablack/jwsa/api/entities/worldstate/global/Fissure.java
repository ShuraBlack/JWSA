package de.shurablack.jwsa.api.entities.worldstate.global;

import de.shurablack.jwsa.api.entities.worldstate.others.types.Era;
import de.shurablack.jwsa.api.entities.worldstate.others.types.Faction;
import de.shurablack.jwsa.api.entities.worldstate.others.types.MissionType;
import de.shurablack.jwsa.api.requests.Paths;
import de.shurablack.jwsa.api.requests.Requests;
import de.shurablack.jwsa.api.utils.ServerOffsetTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.json.JSONObject;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Represents a Fissure in the worldstate, containing details such as activation time, expiry,
 * node, mission type, tier, and other attributes.
 */
@AllArgsConstructor
@Getter
public class Fissure {

    /** The unique identifier of the Fissure. */
    private final String id;

    /** The activation time of the Fissure. */
    private final LocalDateTime activation;

    /** The expiry time of the Fissure. */
    private final LocalDateTime expiry;

    /** A string describing the start of the Fissure. */
    private final String startString;

    /** Indicates whether the Fissure is currently active. */
    private final boolean active;

    /** The node associated with the Fissure. */
    private final String node;

    /** Indicates whether the Fissure has expired. */
    private final boolean expired;

    /** The estimated time remaining for the Fissure. */
    private final String eta;

    /** The mission type associated with the Fissure. */
    private final MissionType missionType;

    /** The tier (or era) of the Fissure. */
    private final Era tier;

    /** The enemy faction associated with the Fissure. */
    private final Faction enemy;

    /** Indicates whether the Fissure is a storm variant. */
    private final boolean storm;

    /** Indicates whether the Fissure is a hard variant. */
    private final boolean hard;

    /**
     * Creates a Fissure object from a JSON representation.
     *
     * @param object The JSON object containing the Fissure data.
     * @return A Fissure object populated with data from the JSON object.
     */
    public static Fissure fromJSON(JSONObject object) {
        String id = object.optString("id", null);
        LocalDateTime activation = ServerOffsetTime.of(object.optString("activation", null));
        LocalDateTime expiry = ServerOffsetTime.of(object.optString("expiry", null));
        String startString = object.optString("startString", null);
        boolean active = object.optBoolean("active", false);
        String node = object.optString("node", null);
        boolean expired = object.optBoolean("expired", false);
        String eta = object.optString("eta", null);
        MissionType missionType = MissionType.fromString(object.optString("missionType", null));
        Era tier = Era.fromString(object.optString("tier", null));
        Faction enemy = Faction.fromString(object.optString("enemy", null));
        boolean storm = object.optBoolean("isStorm", false);
        boolean hard = object.optBoolean("isHard", false);

        return new Fissure(id, activation, expiry, startString, active, node, expired, eta, missionType, tier, enemy, storm, hard);
    }

    /**
     * Requests the list of current Fissures from the server.
     *
     * @return A list of Fissure objects representing the current Fissures.
     */
    public static List<Fissure> request() {
        return Requests.withListMapping(Fissure.class, Paths.FISSURES);
    }
}