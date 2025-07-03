package de.shurablack.jwsa.api.entities.worldstate.global;

import de.shurablack.jwsa.api.annotation.Unstable;
import de.shurablack.jwsa.api.entities.IJsonMapping;
import de.shurablack.jwsa.api.entities.worldstate.others.types.Faction;
import de.shurablack.jwsa.api.entities.worldstate.others.types.MissionType;
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
 * <h2>UNSTABLE</h2>
 * This class is marked as unstable and may be subject to changes.<br><br>
 * Represents an Arbitration in the worldstate, containing details such as activation time, expiry,
 * node, faction, mission type, and additional attributes like archwing and sharkwing requirements.
 */
@Unstable
@AllArgsConstructor
@Getter
@EqualsAndHashCode
public class Arbitration implements Serializable, IJsonMapping {

    private static final long serialVersionUID = -8475766480607519309L;

    /** The unique identifier of the Arbitration. */
    private final String id;

    /** The activation time of the Arbitration. */
    private final LocalDateTime activation;

    /** The expiry time of the Arbitration. */
    private final LocalDateTime expiry;

    /** A string describing the start of the Arbitration. */
    private final String startString;

    /** Indicates whether the Arbitration is currently active. */
    private final boolean active;

    /** The node associated with the Arbitration. */
    private final String node;

    /** The faction associated with the Arbitration. */
    private final Faction faction;

    /** The mission type associated with the Arbitration. */
    private final MissionType type;

    /** Indicates whether the Arbitration requires archwing. */
    private final boolean archwing;

    /** Indicates whether the Arbitration requires sharkwing. */
    private final boolean sharkwing;

    /**
     * Creates an Arbitration object from a JSON representation.
     *
     * @param json The JSON object containing the Arbitration data.
     * @return An Arbitration object populated with data from the JSON object.
     */
    public static Arbitration deserialize(JSONObject json) {
        String id = json.optString("id", null);
        LocalDateTime activation = ServerOffsetTime.of(json.optString("activation", null));
        LocalDateTime expiry = ServerOffsetTime.of(json.optString("expiry", null));
        String startString = json.optString("startString", null);
        boolean active = json.optBoolean("active", false);
        String node = json.optString("node", null);
        Faction faction = Faction.fromString(json.optString("enemy", null));
        MissionType type = MissionType.fromString(json.optString("type", null));
        boolean archwing = json.optBoolean("archwing", false);
        boolean sharkwing = json.optBoolean("sharkwing", false);

        return new Arbitration(id, activation, expiry, startString, active, node, faction, type, archwing, sharkwing);
    }

    @Override
    public JSONObject serialize() {
        JSONObject json = new JSONObject();
        json.put("id", id != null ? id : JSONObject.NULL);
        json.put("activation", activation != null ? activation.toString() : JSONObject.NULL);
        json.put("expiry", expiry != null ? expiry.toString() : JSONObject.NULL);
        json.put("startString", startString != null ? startString : JSONObject.NULL);
        json.put("active", active);
        json.put("node", node != null ? node : JSONObject.NULL);
        json.put("enemy", faction != null ? faction.toString() : JSONObject.NULL);
        json.put("type", type != null ? type.toString() : JSONObject.NULL);
        json.put("archwing", archwing);
        json.put("sharkwing", sharkwing);
        return json;
    }

    /**
     * Requests the current Arbitration data from the server.
     *
     * @return An Arbitration object representing the current Arbitration data.
     */
    public static Arbitration request() {
        return Requests.withDirectMapping(Arbitration.class, Paths.ARBITRATION);
    }
}