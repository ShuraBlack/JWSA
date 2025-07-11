package de.shurablack.jwsa.api.entities.worldstate.global;

import de.shurablack.jwsa.api.entities.IJsonMapping;
import de.shurablack.jwsa.api.entities.worldstate.global.sub.InterimSteps;
import de.shurablack.jwsa.api.entities.worldstate.others.Job;
import de.shurablack.jwsa.api.entities.worldstate.global.sub.ProgressionStep;
import de.shurablack.jwsa.api.entities.worldstate.others.Alt;
import de.shurablack.jwsa.api.entities.worldstate.others.Reward;
import de.shurablack.jwsa.api.entities.worldstate.others.types.SyndicateType;
import de.shurablack.jwsa.api.entities.worldstate.others.types.Faction;
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
import java.util.stream.Collectors;

/**
 * Represents an in-game event in the worldstate, containing details such as activation time, expiry,
 * faction, rewards, progression steps, and other attributes.
 */
@AllArgsConstructor
@Getter
@EqualsAndHashCode
public class Event implements Serializable, IJsonMapping {

    private static final long serialVersionUID = 6497712314404948857L;

    /** The unique identifier of the event. */
    private final String id;

    /** The activation time of the event. */
    private final LocalDateTime activation;

    /** The expiry time of the event. */
    private final LocalDateTime expiry;

    /** A string describing the start of the event. */
    private final String startString;

    /** Indicates whether the event is currently active. */
    private final boolean active;

    /** The maximum score achievable in the event. */
    private final Number maximumScore;

    /** The current score of the event. */
    private final Number currentScore;

    /** The small interval value for the event. */
    private final Number smallInterval;

    /** The large interval value for the event. */
    private final Number largeInterval;

    /** The faction associated with the event. */
    private final Faction faction;

    /** A description of the event. */
    private final String description;

    /** A tooltip providing additional information about the event. */
    private final String tooltip;

    /** The node associated with the event. */
    private final String node;

    /** A list of concurrent nodes associated with the event. */
    private final List<String> concurrentNodes;

    /** The victim node associated with the event. */
    private final String victimNode;

    /** The score localization tag for the event. */
    private final String scoreLocTag;

    /** A list of rewards associated with the event. */
    private final List<Reward> rewards;

    /** The health value associated with the event. */
    private final Number health;

    /** The syndicate type affiliated with the event. */
    private final SyndicateType affiliatedWith;

    /** A list of jobs associated with the event. */
    private final List<Job> jobs;

    /** The interim steps associated with the event. */
    private final InterimSteps interimSteps;

    /** A list of progression steps for the event. */
    private final List<ProgressionStep> progressionSteps;

    /** The total progress value for the event. */
    private final Number progressTotal;

    /** Indicates whether the total progress is shown at the end of the mission. */
    private final boolean showTotalAtEndOfMission;

    /** Indicates whether the event is personal. */
    private final boolean personal;

    /** Indicates whether the event is community-based. */
    private final boolean community;

    /** A list of region drops associated with the event. */
    private final List<String> regionDrops;

    /** A string representation of the event. */
    private final String asString;

    /** A list of completion bonuses for the event. */
    private final List<Number> completionBonuses;

    /** The score variable associated with the event. */
    private final String scoreVar;

    /** The alternate expiry time for the event. */
    private final LocalDateTime altExpiry;

    /** The alternate activation time for the event. */
    private final LocalDateTime altActivation;

    /** The next alternate event associated with this event. */
    private final Alt nextAlt;

    /** The tag associated with the event. */
    private final String tag;

    /**
     * Creates an Event object from a JSON representation.
     *
     * @param object The JSON object containing the event data.
     * @return An Event object populated with data from the JSON object.
     */
    public static Event deserialize(JSONObject object) {
        String id = object.optString("id", null);
        LocalDateTime activation = ServerOffsetTime.of(object.optString("activation", null));
        LocalDateTime expiry = ServerOffsetTime.of(object.optString("expiry", null));
        String startString = object.optString("startString", null);
        boolean active = object.optBoolean("active", false);
        Number maximumScore = object.optNumber("maximumScore", -1);
        Number currentScore = object.optNumber("currentScore", -1);
        Number smallInterval = object.optNumber("smallInterval", -1);
        Number largeInterval = object.optNumber("largeInterval", -1);
        Faction faction = Faction.fromString(object.optString("faction", null));
        String description = object.optString("description", null);
        String tooltip = object.optString("tooltip", null);
        String node = object.optString("node", null);
        List<String> concurrentNodes = object.getJSONArray("concurrentNodes").toList().stream()
                .map(Object::toString)
                .collect(Collectors.toList());
        String victimNode = object.optString("victimNode", null);
        String scoreLocTag = object.optString("scoreLocTag", null);
        List<Reward> rewards = new ArrayList<>();
        if (object.has("rewards")) {
            for (Object rewardObj : object.getJSONArray("rewards")) {
                rewards.add(Reward.deserialize((JSONObject) rewardObj));
            }
        }
        Number health = object.optNumber("health", -1);
        SyndicateType affiliatedWith = SyndicateType.fromString(object.optString("affiliatedWith", null));
        List<Job> jobs = new ArrayList<>();
        if (object.has("jobs")) {
            for (Object jobObj : object.getJSONArray("jobs")) {
                jobs.add(Job.deserialize((JSONObject) jobObj));
            }
        }
        InterimSteps interimSteps = InterimSteps.deserialize(object.optJSONObject("interimSteps", null));
        List<ProgressionStep> progressionSteps = new ArrayList<>();
        if (object.has("progressionSteps")) {
            for (Object stepObj : object.getJSONArray("progressionSteps")) {
                progressionSteps.add(ProgressionStep.deserialize((JSONObject) stepObj));
            }
        }
        Number progressTotal = object.optNumber("progressTotal", -1);
        boolean showTotalAtEndOfMission = object.optBoolean("showTotalAtEndOfMission", false);
        boolean personal = object.optBoolean("isPersonal", false);
        boolean community = object.optBoolean("isCommunity", false);
        List<String> regionDrops = object.getJSONArray("regionDrops").toList().stream()
                .map(Object::toString)
                .collect(Collectors.toList());
        String asString = object.optString("asString", null);
        List<Number> completionBonuses = object.getJSONArray("completionBonuses").toList().stream()
                .map(obj -> (Number) obj)
                .collect(Collectors.toList());
        String scoreVar = object.optString("scoreVar", null);
        LocalDateTime altExpiry = object.has("altExpiry") ? ServerOffsetTime.of(object.getString("altExpiry")) : null;
        LocalDateTime altActivation = object.has("altActivation") ? ServerOffsetTime.of(object.getString("altActivation")) : null;
        Alt nextAlt = object.has("nextAlt") ? Alt.deserialize(object.getJSONObject("nextAlt")) : null;
        String tag = object.optString("tag", null);

        return new Event(id, activation, expiry, startString, active, maximumScore, currentScore, smallInterval,
                largeInterval, faction, description, tooltip, node, concurrentNodes, victimNode, scoreLocTag,
                rewards, health, affiliatedWith, jobs, interimSteps, progressionSteps, progressTotal,
                showTotalAtEndOfMission, personal, community, regionDrops, asString,
                completionBonuses, scoreVar, altExpiry, altActivation, nextAlt, tag);
    }

    @Override
    public JSONObject serialize() {
        JSONObject json = new JSONObject();
        json.put("id", id != null ? id : JSONObject.NULL);
        json.put("activation", activation != null ? activation.toString() : JSONObject.NULL);
        json.put("expiry", expiry != null ? expiry.toString() : JSONObject.NULL);
        json.put("startString", startString != null ? startString : JSONObject.NULL);
        json.put("active", active);
        json.put("maximumScore", maximumScore != null ? maximumScore : -1);
        json.put("currentScore", currentScore != null ? currentScore : -1);
        json.put("smallInterval", smallInterval != null ? smallInterval : -1);
        json.put("largeInterval", largeInterval != null ? largeInterval : -1);
        json.put("faction", faction != null ? faction.toString() : JSONObject.NULL);
        json.put("description", description != null ? description : JSONObject.NULL);
        json.put("tooltip", tooltip != null ? tooltip : JSONObject.NULL);
        json.put("node", node != null ? node : JSONObject.NULL);
        json.put("concurrentNodes", concurrentNodes != null ? concurrentNodes : new ArrayList<>());
        json.put("victimNode", victimNode != null ? victimNode : JSONObject.NULL);
        json.put("scoreLocTag", scoreLocTag != null ? scoreLocTag : JSONObject.NULL);

        if (rewards != null && !rewards.isEmpty()) {
            List<JSONObject> rewardsJson = rewards.stream()
                    .map(Reward::serialize)
                    .collect(Collectors.toList());
            json.put("rewards", rewardsJson);
        } else {
            json.put("rewards", new ArrayList<>());
        }

        json.put("health", health != null ? health : -1);
        json.put("affiliatedWith", affiliatedWith != null ? affiliatedWith.toString() : JSONObject.NULL);

        if (jobs != null && !jobs.isEmpty()) {
            List<JSONObject> jobsJson = jobs.stream()
                    .map(Job::serialize)
                    .collect(Collectors.toList());
            json.put("jobs", jobsJson);
        } else {
            json.put("jobs", new ArrayList<>());
        }

        if (interimSteps != null) {
            json.put("interimSteps", interimSteps.serialize());
        } else {
            json.put("interimSteps", JSONObject.NULL);
        }

        if (progressionSteps != null && !progressionSteps.isEmpty()) {
            List<JSONObject> progressionStepsJson = progressionSteps.stream()
                    .map(ProgressionStep::serialize)
                    .collect(Collectors.toList());
            json.put("progressionSteps", progressionStepsJson);
        } else {
            json.put("progressionSteps", new ArrayList<>());
        }
        json.put("progressTotal", progressTotal != null ? progressTotal : -1);
        json.put("showTotalAtEndOfMission", showTotalAtEndOfMission);
        json.put("isPersonal", personal);
        json.put("isCommunity", community);
        json.put("regionDrops", regionDrops != null ? regionDrops : new ArrayList<>());
        json.put("asString", asString != null ? asString : JSONObject.NULL);
        json.put("completionBonuses", completionBonuses != null ? completionBonuses : new ArrayList<>());
        json.put("scoreVar", scoreVar != null ? scoreVar : JSONObject.NULL);
        json.put("altExpiry", altExpiry != null ? altExpiry.toString() : JSONObject.NULL);
        json.put("altActivation", altActivation != null ? altActivation.toString() : JSONObject.NULL);
        if (nextAlt != null) {
            json.put("nextAlt", nextAlt.serialize());
        } else {
            json.put("nextAlt", JSONObject.NULL);
        }
        json.put("tag", tag != null ? tag : JSONObject.NULL);

        return json;
    }

    /**
     * Requests the list of current events from the server.
     *
     * @return A list of Event objects representing the current events.
     */
    public static List<Event> request() {
        return Requests.withListMapping(Event.class, Paths.EVENTS);
    }
}