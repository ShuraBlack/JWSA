package de.shurablack.jwsa.api.entities.worldstate.global;

import de.shurablack.jwsa.api.annotation.Unstable;
import de.shurablack.jwsa.api.entities.IJsonMapping;
import de.shurablack.jwsa.api.entities.worldstate.others.types.Faction;
import de.shurablack.jwsa.api.entities.worldstate.others.types.MissionType;
import de.shurablack.jwsa.api.requests.Paths;
import de.shurablack.jwsa.api.requests.Requests;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.json.JSONObject;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * <h2>UNSTABLE</h2>
 * This class is marked as unstable and may be subject to changes.<br><br>
 * Represents a Kuva Mission in the worldstate, containing details such as activation time, expiry,
 * node, enemy faction, mission type, and additional attributes like Archwing and Sharkwing support.
 */
@Unstable
@AllArgsConstructor
@Getter
public class Kuva implements Serializable, IJsonMapping {

    private static final long serialVersionUID = 7369560755853194139L;

    /** The unique identifier of the Kuva Mission. */
    private final String id;

    /** The activation time of the Kuva Mission. */
    private final LocalDateTime activation;

    /** The expiry time of the Kuva Mission. */
    private final LocalDateTime expiry;

    /** A string describing the start of the Kuva Mission. */
    private final String startString;

    /** Indicates whether the Kuva Mission is currently active. */
    private final boolean active;

    /** The node where the Kuva Mission is taking place. */
    private final String node;

    /** The enemy faction associated with the Kuva Mission. */
    private final Faction enemy;

    /** The mission type of the Kuva Mission. */
    private final MissionType type;

    /** Indicates whether the Kuva Mission supports Archwing gameplay. */
    private final boolean archwing;

    /** Indicates whether the Kuva Mission supports Sharkwing gameplay. */
    private final boolean sharkwing;

    /**
     * Creates a KuvaMission object from a JSON representation.
     *
     * @param object The JSON object containing the Kuva Mission data.
     * @return A KuvaMission object populated with data from the JSON object.
     */
    public static Kuva deserialize(JSONObject object) {
        String id = object.optString("id", null);
        LocalDateTime activation = LocalDateTime.parse(object.optString("activation", null));
        LocalDateTime expiry = LocalDateTime.parse(object.optString("expiry", null));
        String startString = object.optString("startString", null);
        boolean active = object.optBoolean("active", false);
        String node = object.optString("node", null);
        Faction enemy = Faction.fromString(object.optString("enemy", null));
        MissionType type = MissionType.fromString(object.optString("type", null));
        boolean archwing = object.optBoolean("archwing", false);
        boolean sharkwing = object.optBoolean("sharkwing", false);

        return new Kuva(id, activation, expiry, startString, active, node, enemy, type, archwing, sharkwing);
    }

    @Override
    public JSONObject serialize() {
        JSONObject json = new JSONObject();
        json.put("id", id);
        json.put("activation", activation.toString());
        json.put("expiry", expiry.toString());
        json.put("startString", startString);
        json.put("active", active);
        json.put("node", node);
        json.put("enemy", enemy.toString());
        json.put("type", type.toString());
        json.put("archwing", archwing);
        json.put("sharkwing", sharkwing);
        return json;
    }

    /**
     * Requests the list of current Kuva Missions from the server.
     *
     * @return A list of KuvaMission objects representing the current Kuva Missions.
     */
    public static List<Kuva> request() {
        return Requests.withListMapping(Kuva.class, Paths.KUVA_MISSION);
    }
}