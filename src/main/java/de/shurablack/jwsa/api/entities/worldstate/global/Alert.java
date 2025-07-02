package de.shurablack.jwsa.api.entities.worldstate.global;

import de.shurablack.jwsa.api.entities.worldstate.others.Mission;
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

@AllArgsConstructor
@Getter
public class Alert {

    private final String id;

    private final LocalDateTime activation;

    private final LocalDateTime expiry;

    private final String startString;

    private final boolean active;

    private final Mission mission;

    private final boolean expired;

    private final String eta;

    private final List<RewardTypes> alertRewardTypes;

    public static Alert fromJSON(JSONObject json) {
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

        Mission mission = Mission.fromJSON(json.optJSONObject("mission", null));

        return new Alert(id, activation, expiry, startString, active, mission, expired, eta, alertRewardTypes);
    }

    public static List<Alert> request() {
        return Requests.withListMapping(Alert.class, Paths.ALERTS);
    }
}
