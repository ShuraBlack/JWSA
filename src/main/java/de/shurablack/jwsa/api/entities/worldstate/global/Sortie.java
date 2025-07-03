package de.shurablack.jwsa.api.entities.worldstate.global;

import de.shurablack.jwsa.api.entities.IJsonMapping;
import de.shurablack.jwsa.api.entities.worldstate.global.sub.Variant;
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

/**
 * Represents a Sortie in the worldstate, containing details such as activation time, expiry,
 * reward pool, boss, faction, and associated mission variants.
 */
@AllArgsConstructor
@Getter
@EqualsAndHashCode
public class Sortie implements Serializable, IJsonMapping {

    private static final long serialVersionUID = -8058115596506760118L;

    /** The unique identifier of the Sortie. */
    private final String id;

    /** The activation time of the Sortie. */
    private final LocalDateTime activation;

    /** The expiry time of the Sortie. */
    private final LocalDateTime expiry;

    /** A string describing the start of the Sortie. */
    private final String startString;

    /** Indicates whether the Sortie is currently active. */
    private final boolean active;

    /** The reward pool associated with the Sortie. */
    private final String rewardPool;

    /** The list of mission variants in the Sortie. */
    private final List<Variant> variants;

    /** The boss associated with the Sortie. */
    private final String boss;

    /** The faction associated with the Sortie. */
    private final Faction faction;

    /** Indicates whether the Sortie has expired. */
    private final boolean expired;

    /** The estimated time remaining for the Sortie. */
    private final String eta;

    /**
     * Creates a Sortie object from a JSON representation.
     *
     * @param object The JSON object containing the Sortie data.
     * @return A Sortie object populated with data from the JSON object.
     */
    public static Sortie deserialize(JSONObject object) {
        String id = object.optString("id", null);
        LocalDateTime activation = ServerOffsetTime.of(object.optString("activation", null));
        LocalDateTime expiry = ServerOffsetTime.of(object.optString("expiry", null));
        String startString = object.optString("startString", null);
        boolean active = object.optBoolean("active", false);
        String rewardPool = object.optString("rewardPool", null);
        List<Variant> variants = new ArrayList<>();
        if (object.has("variants")) {
            for (Object variantObj : object.getJSONArray("variants")) {
                variants.add(Variant.deserialize((JSONObject) variantObj));
            }
        }
        String boss = object.optString("boss", null);
        Faction faction = Faction.fromString(object.optString("faction", null));
        boolean expired = object.optBoolean("expired", false);
        String eta = object.optString("eta", null);

        return new Sortie(id, activation, expiry, startString, active, rewardPool, variants, boss, faction, expired, eta);
    }

    @Override
    public JSONObject serialize() {
        JSONObject json = new JSONObject();
        json.put("id", id);
        json.put("activation", activation != null ? activation.toString() : JSONObject.NULL);
        json.put("expiry", expiry != null ? expiry.toString() : JSONObject.NULL);
        json.put("startString", startString != null ? startString : JSONObject.NULL);
        json.put("active", active);
        json.put("rewardPool", rewardPool != null ? rewardPool : JSONObject.NULL);

        if (variants != null) {
            List<JSONObject> variantJsons = new ArrayList<>();
            for (Variant variant : variants) {
                variantJsons.add(variant.serialize());
            }
            json.put("variants", variantJsons);
        } else {
            json.put("variants", new ArrayList<>());
        }

        json.put("boss", boss != null ? boss : JSONObject.NULL);
        json.put("faction", faction != null ? faction.toString() : JSONObject.NULL);
        json.put("expired", expired);
        json.put("eta", eta != null ? eta : JSONObject.NULL);

        return json;
    }

    /**
     * Requests the current Sortie from the server.
     *
     * @return A Sortie object representing the current Sortie.
     */
    public static Sortie request() {
        return Requests.withDirectMapping(Sortie.class, Paths.SORTIES);
    }
}