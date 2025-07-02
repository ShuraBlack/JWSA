package de.shurablack.jwsa.api.entities.worldstate.global;

import de.shurablack.jwsa.api.entities.worldstate.others.types.Faction;
import de.shurablack.jwsa.api.entities.worldstate.others.Mission;
import de.shurablack.jwsa.api.requests.Paths;
import de.shurablack.jwsa.api.requests.Requests;
import de.shurablack.jwsa.api.utils.ServerOffsetTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.json.JSONArray;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents an Archon in the worldstate, containing details such as activation time, expiry,
 * reward pool, associated missions, boss, faction, and other attributes.
 */
@AllArgsConstructor
@Getter
public class Archon {

    /** The unique identifier of the Archon. */
    private final String id;

    /** The activation time of the Archon. */
    private final LocalDateTime activation;

    /** The expiry time of the Archon. */
    private final LocalDateTime expiry;

    /** A string describing the start of the Archon event. */
    private final String startString;

    /** Indicates whether the Archon is currently active. */
    private final boolean active;

    /** The reward pool associated with the Archon. */
    private final String rewardPool;

    /** The list of missions associated with the Archon. */
    private final List<Mission> missions;

    /** The boss associated with the Archon. */
    private final String boss;

    /** The faction associated with the Archon. */
    private final Faction faction;

    /** Indicates whether the Archon event has expired. */
    private final boolean expired;

    /** The estimated time remaining for the Archon event. */
    private final String eta;

    /**
     * Creates an Archon object from a JSON representation.
     *
     * @param json The JSON object containing the Archon data.
     * @return An Archon object populated with data from the JSON object.
     */
    public static Archon fromJSON(org.json.JSONObject json) {
        String id = json.optString("id", null);
        LocalDateTime activation = ServerOffsetTime.of(json.optString("activation", null));
        LocalDateTime expiry = ServerOffsetTime.of(json.optString("expiry", null));
        String startString = json.optString("startString", null);
        boolean active = json.optBoolean("active", false);
        String rewardPool = json.optString("rewardPool", null);
        List<Mission> missions = new ArrayList<>();
        JSONArray missionsJson = json.optJSONArray("missions");
        for (int i = 0; missionsJson != null && i < missionsJson.length(); i++) {
            org.json.JSONObject missionJson = missionsJson.getJSONObject(i);
            missions.add(Mission.fromJSON(missionJson));
        }
        String boss = json.optString("boss", null);
        Faction faction = Faction.fromString(json.optString("faction", null));
        boolean expired = json.optBoolean("expired", false);
        String eta = json.optString("eta", null);

        return new Archon(id, activation, expiry, startString, active, rewardPool, missions, boss, faction, expired, eta);
    }

    /**
     * Requests the current Archon data from the server.
     *
     * @return An Archon object representing the current Archon data.
     */
    public static Archon request() {
        return Requests.withDirectMapping(Archon.class, Paths.ARCHON);
    }
}