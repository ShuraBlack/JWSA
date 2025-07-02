package de.shurablack.jwsa.api.entities.worldstate.global.sub;

import de.shurablack.jwsa.api.entities.IJsonMapping;
import de.shurablack.jwsa.api.entities.worldstate.others.Reward;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.json.JSONObject;

import java.io.Serializable;

/**
 * Represents an interim step in a worldstate event, containing details such as the goal, reward, message, and winner count.
 */
@AllArgsConstructor
@Getter
public class InterimSteps implements Serializable, IJsonMapping {

    private static final long serialVersionUID = 6107945682822164L;

    /** The goal value for this interim step. */
    private final Number goal;

    /** The reward associated with achieving this interim step. */
    private final Reward reward;

    /** The message associated with this interim step. */
    private final Message message;

    /** The number of winners for this interim step. */
    private final Number winnerCount;

    /**
     * Creates an InterimSteps object from a JSON representation.
     *
     * @param object The JSON object containing the interim step data.
     * @return An InterimSteps object populated with data from the JSON object, or null if the input is null.
     */
    public static InterimSteps deserialize(JSONObject object) {
        if (object == null) {
            return null;
        }
        Number goal = object.optNumber("goal", -1);
        Reward reward = Reward.deserialize(object.optJSONObject("reward", null));
        Message message = Message.deserialize(object.optJSONObject("message", null));
        Number winnerCount = object.optNumber("winnerCount", -1);

        return new InterimSteps(goal, reward, message, winnerCount);
    }

    @Override
    public JSONObject serialize() {
        JSONObject json = new JSONObject();
        json.put("goal", goal);
        json.put("reward", reward != null ? reward.serialize() : JSONObject.NULL);
        json.put("message", message != null ? message.serialize() : JSONObject.NULL);
        json.put("winnerCount", winnerCount);
        return json;
    }
}