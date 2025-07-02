package de.shurablack.jwsa.api.entities.worldstate.global.sub;

import de.shurablack.jwsa.api.entities.worldstate.others.Reward;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.json.JSONObject;

/**
 * Represents an interim step in a worldstate event, containing details such as the goal, reward, message, and winner count.
 */
@AllArgsConstructor
@Getter
public class InterimSteps {

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
    public static InterimSteps fromJSON(JSONObject object) {
        if (object == null) {
            return null;
        }
        Number goal = object.optNumber("goal", -1);
        Reward reward = Reward.fromJSON(object.optJSONObject("reward", null));
        Message message = Message.fromJSON(object.optJSONObject("message", null));
        Number winnerCount = object.optNumber("winnerCount", -1);

        return new InterimSteps(goal, reward, message, winnerCount);
    }
}