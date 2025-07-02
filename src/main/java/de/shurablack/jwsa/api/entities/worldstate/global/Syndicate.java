package de.shurablack.jwsa.api.entities.worldstate.global;

import de.shurablack.jwsa.api.entities.worldstate.others.Job;
import de.shurablack.jwsa.api.entities.worldstate.others.types.SyndicateType;
import de.shurablack.jwsa.api.requests.Paths;
import de.shurablack.jwsa.api.requests.Requests;
import de.shurablack.jwsa.api.utils.ServerOffsetTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.json.JSONObject;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Represents a Syndicate in the worldstate, containing details such as activation time, expiry,
 * associated nodes, jobs, and the syndicate type.
 */
@AllArgsConstructor
@Getter
public class Syndicate {

    /** The unique identifier of the Syndicate. */
    private final String id;

    /** The activation time of the Syndicate. */
    private final LocalDateTime activation;

    /** The expiry time of the Syndicate. */
    private final LocalDateTime expiry;

    /** The list of nodes associated with the Syndicate. */
    private final List<String> nodes;

    /** The estimated time remaining for the Syndicate. */
    private final String eta;

    /** The list of jobs available for the Syndicate. */
    private final List<Job> jobs;

    /** The type of the Syndicate. */
    private final SyndicateType syndicate;

    /**
     * Creates a Syndicate object from a JSON representation.
     *
     * @param object The JSON object containing the Syndicate data.
     * @return A Syndicate object populated with data from the JSON object.
     */
    public static Syndicate fromJSON(JSONObject object) {
        String id = object.optString("id", null);
        LocalDateTime activation = ServerOffsetTime.of(object.optString("activation", null));
        LocalDateTime expiry = ServerOffsetTime.of(object.optString("expiry", null));
        List<String> nodes = object.has("nodes") ? object.getJSONArray("nodes").toList().stream()
                .map(Object::toString).collect(Collectors.toList()) : List.of();
        String eta = object.optString("eta", null);
        List<Job> jobs = new ArrayList<>();
        if (object.has("jobs")) {
            for (Object jobObj : object.getJSONArray("jobs")) {
                jobs.add(Job.fromJSON((JSONObject) jobObj));
            }
        }
        SyndicateType syndicate = SyndicateType.fromString(object.optString("syndicate", null));

        return new Syndicate(id, activation, expiry, nodes, eta, jobs, syndicate);
    }

    /**
     * Requests the list of current Syndicates from the server.
     *
     * @return A list of Syndicate objects representing the current Syndicates.
     */
    public static List<Syndicate> request() {
        return Requests.withListMapping(Syndicate.class, Paths.SYNDICATE);
    }
}