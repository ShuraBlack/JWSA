package de.shurablack.jwsa.api.entities.worldstate.global;

import de.shurablack.jwsa.api.entities.IJsonMapping;
import de.shurablack.jwsa.api.entities.worldstate.global.sub.NightwaveChallenge;
import de.shurablack.jwsa.api.requests.Paths;
import de.shurablack.jwsa.api.requests.Requests;
import de.shurablack.jwsa.api.utils.ServerOffsetTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.json.JSONObject;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Represents a Nightwave event in the worldstate, containing details such as activation time, expiry,
 * reward types, season, phase, and associated challenges.
 */
@AllArgsConstructor
@Getter
public class Nightwave implements Serializable, IJsonMapping {

    private static final long serialVersionUID = 5891060165251363115L;

    /** The unique identifier of the Nightwave event. */
    private final String id;

    /** The activation time of the Nightwave event. */
    private final LocalDateTime activation;

    /** The expiry time of the Nightwave event. */
    private final LocalDateTime expiry;

    /** A string describing the start of the Nightwave event. */
    private final String startString;

    /** Indicates whether the Nightwave event is currently active. */
    private final boolean active;

    /** The list of reward types associated with the Nightwave event. */
    private final List<String> rewardTypes;

    /** The season number of the Nightwave event. */
    private final Number season;

    /** The tag associated with the Nightwave event. */
    private final String tag;

    /** The current phase of the Nightwave event. */
    private final Number phase;

    /** The list of possible challenges for the Nightwave event. */
    private final List<NightwaveChallenge> possibleChallenges;

    /** The list of active challenges for the Nightwave event. */
    private final List<NightwaveChallenge> activeChallenges;

    /**
     * Creates a Nightwave object from a JSON representation.
     *
     * @param object The JSON object containing the Nightwave data.
     * @return A Nightwave object populated with data from the JSON object.
     */
    public static Nightwave deserialize(JSONObject object) {
        String id = object.optString("id", null);
        LocalDateTime activation = ServerOffsetTime.of(object.optString("activation", null));
        LocalDateTime expiry = ServerOffsetTime.of(object.optString("expiry", null));
        String startString = object.optString("startString", null);
        boolean active = object.optBoolean("active", false);
        List<String> rewardTypes = object.getJSONArray("rewardTypes").toList().stream()
                .map(Object::toString)
                .collect(Collectors.toList());
        Number season = object.optNumber("season", -1);
        String tag = object.optString("tag", null);
        Number phase = object.optNumber("phase", -1);

        List<NightwaveChallenge> possibleChallenges = new ArrayList<>();
        if (object.has("possibleChallenges")) {
            for (Object obj : object.getJSONArray("possibleChallenges")) {
                possibleChallenges.add(NightwaveChallenge.deserialize((JSONObject) obj));
            }
        }

        List<NightwaveChallenge> activeChallenges = new ArrayList<>();
        if (object.has("activeChallenges")) {
            for (Object obj : object.getJSONArray("activeChallenges")) {
                activeChallenges.add(NightwaveChallenge.deserialize((JSONObject) obj));
            }
        }

        return new Nightwave(id, activation, expiry, startString, active, rewardTypes, season, tag, phase, possibleChallenges, activeChallenges);
    }

    @Override
    public JSONObject serialize() {
        JSONObject json = new JSONObject();
        json.put("id", id != null ? id : JSONObject.NULL);
        json.put("activation", activation != null ? activation.toString() : JSONObject.NULL);
        json.put("expiry", expiry != null ? expiry.toString() : JSONObject.NULL);
        json.put("startString", startString != null ? startString : JSONObject.NULL);
        json.put("active", active);
        json.put("rewardTypes", rewardTypes != null ? rewardTypes : new ArrayList<>());
        json.put("season", season != null ? season : JSONObject.NULL);
        json.put("tag", tag != null ? tag : JSONObject.NULL);
        json.put("phase", phase != null ? phase : JSONObject.NULL);

        if (possibleChallenges != null) {
            json.put("possibleChallenges", possibleChallenges.stream().map(NightwaveChallenge::serialize).collect(Collectors.toList()));
        } else {
            json.put("possibleChallenges", new ArrayList<>());
        }

        if (activeChallenges != null) {
            json.put("activeChallenges", activeChallenges.stream().map(NightwaveChallenge::serialize).collect(Collectors.toList()));
        } else {
            json.put("activeChallenges", new ArrayList<>());
        }

        return json;
    }

    /**
     * Requests the current Nightwave event from the server.
     *
     * @return A Nightwave object representing the current Nightwave event.
     */
    public static Nightwave request() {
        return Requests.withDirectMapping(Nightwave.class, Paths.NIGHTWAVE);
    }
}