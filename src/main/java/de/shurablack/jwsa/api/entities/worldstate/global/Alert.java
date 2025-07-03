package de.shurablack.jwsa.api.entities.worldstate.global;

import de.shurablack.jwsa.api.entities.IJsonMapping;
import de.shurablack.jwsa.api.entities.worldstate.others.Mission;
import de.shurablack.jwsa.api.entities.worldstate.others.types.RewardTypes;
import de.shurablack.jwsa.api.requests.Paths;
import de.shurablack.jwsa.api.requests.Requests;
import de.shurablack.jwsa.api.utils.ServerOffsetTime;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.json.JSONObject;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents an in-game alert with details such as activation time, expiry time, mission details, and rewards.
 */
@AllArgsConstructor
@Getter
@EqualsAndHashCode
public class Alert implements Serializable, IJsonMapping {

    private static final long serialVersionUID = -5675638743068076620L;

    /** The unique identifier of the alert. */
    private final String id;

    /** The activation time of the alert. */
    private final LocalDateTime activation;

    /** The expiry time of the alert. */
    private final LocalDateTime expiry;

    /** A string describing when the alert started. */
    private final String startString;

    /** Indicates whether the alert is currently active. */
    private final boolean active;

    /** The mission associated with the alert. */
    private final Mission mission;

    /** Indicates whether the alert has expired. */
    private final boolean expired;

    /** The estimated time remaining for the alert. */
    private final String eta;

    /** The list of reward types associated with the alert. */
    private final List<RewardTypes> alertRewardTypes;

    /**
     * Creates an instance of {@link Alert} from a JSON object.
     *
     * @param json The JSON object containing alert data.
     * @return An instance of {@link Alert} populated with data from the JSON object.
     */
    public static Alert deserialize(JSONObject json) {
        String id = json.optString("id", null);
        LocalDateTime activation = ServerOffsetTime.of(json.optString("activation", null));
        LocalDateTime expiry = ServerOffsetTime.of(json.optString("expiry", null));
        String startString = json.optString("startString", null);
        boolean active = json.optBoolean("active", false);
        boolean expired = json.optBoolean("expired", false);
        String eta = json.optString("eta", null);

        List<RewardTypes> alertRewardTypes = new ArrayList<>();
        if (json.has("rewardTypes")) {
            for (Object type : json.getJSONArray("rewardTypes")) {
                alertRewardTypes.add(RewardTypes.fromString(type.toString()));
            }
        }

        Mission mission = Mission.deserialize(json.optJSONObject("mission", null));

        return new Alert(id, activation, expiry, startString, active, mission, expired, eta, alertRewardTypes);
    }

    @Override
    public JSONObject serialize() {
        JSONObject json = new JSONObject();
        json.put("id", id != null ? id : JSONObject.NULL);
        json.put("activation", activation != null ? activation.toString() : JSONObject.NULL);
        json.put("expiry", expiry != null ? expiry.toString() : JSONObject.NULL);
        json.put("startString", startString != null ? startString : JSONObject.NULL);
        json.put("active", active);
        json.put("expired", expired);
        json.put("eta", eta != null ? eta : JSONObject.NULL);

        if (mission != null) {
            json.put("mission", mission.serialize());
        } else {
            json.put("mission", JSONObject.NULL);
        }

        if (alertRewardTypes != null && !alertRewardTypes.isEmpty()) {
            List<String> types = new ArrayList<>();
            for (RewardTypes type : alertRewardTypes) {
                types.add(type.toString());
            }
            json.put("rewardTypes", types);
        } else {
            json.put("rewardTypes", new ArrayList<>());
        }

        return json;
    }

    /**
     * Requests a list of active alerts from the server.
     *
     * @return A list of {@link Alert} objects representing the active alerts.
     */
    public static List<Alert> request() {
        return Requests.withListMapping(Alert.class, Paths.ALERTS);
    }
}