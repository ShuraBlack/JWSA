package de.shurablack.jwsa.api.entities.worldstate.others;

import de.shurablack.jwsa.api.utils.ServerOffsetTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.json.JSONObject;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Represents a Job in the worldstate, which includes details such as activation and expiry times,
 * reward pool, job type, enemy levels, standing stages, and minimum mastery rank (MR) required.
 */
@AllArgsConstructor
@Getter
public class Job {

    /** The activation time of the job. */
    private final LocalDateTime activation;

    /** The expiry time of the job. */
    private final LocalDateTime expiry;

    /** The list of rewards available in the job's reward pool. */
    private final List<String> rewardPool;

    /** The type of the job. */
    private final String type;

    /** The list of enemy levels associated with the job. */
    private final List<Number> enemyLevels;

    /** The list of standing stages required for the job. */
    private final List<Number> standingStages;

    /** The minimum mastery rank (MR) required to participate in the job. */
    private final Number minMR;

    /**
     * Creates a Job instance from a JSON object.
     *
     * @param json The JSON object containing the job details.
     * @return A new Job instance with the parsed details.
     */
    public static Job fromJSON(JSONObject json) {
        LocalDateTime activation = ServerOffsetTime.of(json.optString("activation", null));
        LocalDateTime expiry = ServerOffsetTime.of(json.optString("expiry", null));
        List<String> rewardPool = json.getJSONArray("rewardPool").toList().stream()
                .map(Object::toString)
                .collect(Collectors.toList());
        String type = json.optString("type", null);
        List<Number> enemyLevels = json.getJSONArray("enemyLevels").toList().stream()
                .map(obj -> (Number) obj)
                .collect(Collectors.toList());
        List<Number> standingStages = json.getJSONArray("standingStages").toList().stream()
                .map(obj -> (Number) obj)
                .collect(Collectors.toList());
        Number minMR = json.optNumber("minMR", -1);

        return new Job(activation, expiry, rewardPool, type, enemyLevels, standingStages, minMR);
    }
}