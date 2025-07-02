package de.shurablack.jwsa.api.entities.worldstate.global;

import de.shurablack.jwsa.api.entities.IJsonMapping;
import de.shurablack.jwsa.api.entities.worldstate.global.sub.Incursions;
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

/**
 * Represents the Steel Path in the worldstate, containing details such as activation and expiry times,
 * current rewards, rotation rewards, evergreen rewards, and incursions.
 */
@AllArgsConstructor
@Getter
public class SteelPath implements Serializable, IJsonMapping {

    private static final long serialVersionUID = -6935127185215539705L;

    /** The activation time of the Steel Path. */
    private final LocalDateTime activation;

    /** The expiry time of the Steel Path. */
    private final LocalDateTime expiry;

    /** The current reward available in the Steel Path. */
    private final SingleMap currentReward;

    /** The remaining time for the current Steel Path rotation. */
    private final String remaining;

    /** The list of rewards in the Steel Path rotation. */
    private final List<SingleMap> rotation;

    /** The list of evergreen rewards in the Steel Path. */
    private final List<SingleMap> evergreens;

    /** The incursions associated with the Steel Path. */
    private final Incursions incursions;

    /**
     * Creates a SteelPath object from a JSON representation.
     *
     * @param object The JSON object containing the Steel Path data.
     * @return A SteelPath object populated with data from the JSON object.
     */
    public static SteelPath deserialize(JSONObject object) {
        LocalDateTime activation = ServerOffsetTime.of(object.optString("activation", null));
        LocalDateTime expiry = ServerOffsetTime.of(object.optString("expiry", null));
        SingleMap currentRewardObj = SingleMap.deserialize(object.optJSONObject("currentReward", null));
        String remaining = object.optString("remaining", null);
        List<SingleMap> rotation = new ArrayList<>();
        if (object.has("rotation")) {
            for (Object rotationObj : object.getJSONArray("rotation")) {
                rotation.add(SingleMap.deserialize((JSONObject) rotationObj));
            }
        }
        List<SingleMap> evergreens = new ArrayList<>();
        if (object.has("evergreens")) {
            for (Object evergreenObj : object.getJSONArray("evergreens")) {
                evergreens.add(SingleMap.deserialize((JSONObject) evergreenObj));
            }
        }
        Incursions incursions = Incursions.deserialize(object.optJSONObject("incursions", null));

        return new SteelPath(activation, expiry, currentRewardObj, remaining, rotation, evergreens, incursions);
    }

    @Override
    public JSONObject serialize() {
        JSONObject json = new JSONObject();
        json.put("activation", activation == null ? null : activation.toString());
        json.put("expiry", expiry == null ? null : expiry.toString());
        json.put("currentReward", currentReward == null ? null : currentReward.serialize());
        json.put("remaining", remaining);

        if (rotation != null) {
            List<JSONObject> rotationJson = new ArrayList<>();
            for (SingleMap map : rotation) {
                rotationJson.add(map.serialize());
            }
            json.put("rotation", rotationJson);
        }

        if (evergreens != null) {
            List<JSONObject> evergreensJson = new ArrayList<>();
            for (SingleMap map : evergreens) {
                evergreensJson.add(map.serialize());
            }
            json.put("evergreens", evergreensJson);
        }

        json.put("incursions", incursions == null ? null : incursions.serialize());

        return json;
    }

    /**
     * Requests the current Steel Path data from the server.
     *
     * @return A SteelPath object representing the current Steel Path.
     */
    public static SteelPath request() {
        return Requests.withDirectMapping(SteelPath.class, Paths.STEEL_PATH);
    }

    /**
     * Represents a single reward or map in the Steel Path.
     */
    @AllArgsConstructor
    @Getter
    public static class SingleMap implements Serializable, IJsonMapping {

        private static final long serialVersionUID = 5909150833664493564L;

        /** The name of the reward or map. */
        private final String name;

        /** The cost associated with the reward or map. */
        private final String cost;

        /**
         * Creates a SingleMap object from a JSON representation.
         *
         * @param json The JSON object containing the SingleMap data.
         * @return A SingleMap object populated with data from the JSON object.
         */
        public static SingleMap deserialize(JSONObject json) {
            if (json == null) {
                return new SingleMap(null, null);
            }

            String name = json.optString("name", null);
            String cost = json.optString("cost", null);

            return new SingleMap(name, cost);
        }

        @Override
        public JSONObject serialize() {
            JSONObject json = new JSONObject();
            json.put("name", name != null ? name : JSONObject.NULL);
            json.put("cost", cost != null ? cost : JSONObject.NULL);
            return json;
        }
    }
}