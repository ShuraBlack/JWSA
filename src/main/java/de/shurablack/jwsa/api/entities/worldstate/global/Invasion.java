package de.shurablack.jwsa.api.entities.worldstate.global;

import de.shurablack.jwsa.api.entities.worldstate.global.sub.InvasionParty;
import de.shurablack.jwsa.api.entities.worldstate.others.types.RewardTypes;
import de.shurablack.jwsa.api.requests.Paths;
import de.shurablack.jwsa.api.requests.Requests;
import de.shurablack.jwsa.api.utils.ServerOffsetTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.json.JSONObject;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents an Invasion in the worldstate, containing details such as activation time, expiry,
 * attacker and defender parties, rewards, and other attributes.
 */
@AllArgsConstructor
@Getter
public class Invasion {

    /** The unique identifier of the invasion. */
    private final String id;

    /** The activation time of the invasion. */
    private final LocalDateTime activation;

    /** The expiry time of the invasion. */
    private final LocalDateTime expiry;

    /** A string describing the start of the invasion. */
    private final String startString;

    /** Indicates whether the invasion is currently active. */
    private final boolean active;

    /** The attacking party in the invasion. */
    private final InvasionParty attacker;

    /** Indicates whether the invasion is completed. */
    private final boolean completed;

    /** The completion percentage of the invasion. */
    private final Number completion;

    /** The current count of runs completed for the invasion. */
    private final Number count;

    /** The defending party in the invasion. */
    private final InvasionParty defender;

    /** A description of the invasion. */
    private final String desc;

    /** The estimated time remaining for the invasion. */
    private final String eta;

    /** The node where the invasion is taking place. */
    private final String node;

    /** The number of runs required to complete the invasion. */
    private final Number requiredRuns;

    /** The list of reward types associated with the invasion. */
    private final List<RewardTypes> rewardTypes;

    /** Indicates whether the invasion is against the Infestation faction. */
    private final boolean vsInfestation;

    /**
     * Creates an Invasion object from a JSON representation.
     *
     * @param object The JSON object containing the invasion data.
     * @return An Invasion object populated with data from the JSON object.
     */
    public static Invasion fromJSON(JSONObject object) {
        String id = object.optString("id", null);
        LocalDateTime activation = ServerOffsetTime.of(object.optString("activation", null));
        LocalDateTime expiry = ServerOffsetTime.of(object.optString("expiry", null));
        String startString = object.optString("startString", null);
        boolean active = object.optBoolean("active", false);
        InvasionParty attacker = InvasionParty.fromJSON(object.optJSONObject("attacker", null));
        boolean completed = object.optBoolean("completed", false);
        Number completion = object.optNumber("completion", -1);
        Number count = object.optNumber("count", -1);
        InvasionParty defender = InvasionParty.fromJSON(object.optJSONObject("defender", null));
        String desc = object.optString("desc", null);
        String eta = object.optString("eta", null);
        String node = object.optString("node", null);
        Number requiredRuns = object.optNumber("requiredRuns", -1);

        List<RewardTypes> rewardTypes = new ArrayList<>();
        if (object.has("rewardTypes")) {
            for (Object type : object.getJSONArray("rewardTypes")) {
                rewardTypes.add(RewardTypes.fromString(type.toString()));
            }
        }

        boolean vsInfestation = object.optBoolean("vsInfestation", false);

        return new Invasion(id, activation, expiry, startString, active, attacker, completed, completion, count, defender, desc, eta, node, requiredRuns, rewardTypes, vsInfestation);
    }

    /**
     * Requests the list of current invasions from the server.
     *
     * @return A list of Invasion objects representing the current invasions.
     */
    public static List<Invasion> request() {
        return Requests.withListMapping(Invasion.class, Paths.INVASIONS);
    }
}