package de.shurablack.jwsa.api.entities.searchable.weapon.sub;

import de.shurablack.jwsa.api.entities.IJsonMapping;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Represents an attack in the game, including its name, shot type, speed, critical stats, falloff, and damage values.
 */
@AllArgsConstructor
@Getter
public class Attack implements Serializable, IJsonMapping {

    private static final long serialVersionUID = 1990061627989241816L;

    /** The name of the attack. */
    private final String name;

    /** The type of shot for the attack (e.g., projectile, hitscan). */
    private final String shotType;

    /** The speed of the attack. */
    private final Number speed;

    /** The speed of the shot for the attack. */
    private final Number shotSpeed;

    /** The flight time of the attack. */
    private final Number flight;

    /** The critical chance of the attack. */
    private final Number criticalChance;

    /** The critical multiplier of the attack. */
    private final Number criticalMultiplier;

    /** The status chance of the attack. */
    private final Number statusChance;

    /** The falloff details of the attack, including start and end distances and damage reduction. */
    private final Falloff falloff;

    /** A map of damage types and their corresponding values for the attack. */
    private final Map<DamageType, Double> damage;

    /**
     * Deserializes a JSON object into an instance of {@link Attack}.
     *
     * @param object The JSON object containing attack data.
     * @return An instance of {@link Attack} populated with data from the JSON object, or null if the input is null.
     */
    public static Attack deserialize(JSONObject object) {
        if (object == null) {
            return null;
        }

        String name = object.optString("name", null);
        String shotType = object.optString("shot_type", null);
        Number speed = object.optNumber("speed", -1);
        Number shotSpeed = object.optNumber("shot_speed", -1);
        Number flight = object.optNumber("flight", -1);
        Number criticalChance = object.optNumber("crit_chance", -1);
        Number criticalMultiplier = object.optNumber("crit_mult", -1);
        Number statusChance = object.optNumber("status_chance", -1);
        Falloff falloff = Falloff.deserialize(object.optJSONObject("falloff", null));
        Map<DamageType, Double> damage = new HashMap<>();

        JSONObject damageObject = object.optJSONObject("damage");
        if (damageObject != null) {
            for (String key : damageObject.keySet()) {
                damage.put(DamageType.fromString(key), damageObject.optDouble(key, -1));
            }
        }

        return new Attack(name, shotType, speed, shotSpeed, flight, criticalChance, criticalMultiplier, statusChance, falloff, damage);
    }

    /**
     * Serializes this {@link Attack} instance into a JSON object.
     *
     * @return A {@link JSONObject} representation of this {@link Attack}.
     */
    @Override
    public JSONObject serialize() {
        JSONObject object = new JSONObject();
        object.put("name", name != null ? name : "");
        object.put("shot_type", shotType != null ? shotType : "");
        object.put("speed", speed != null ? speed : -1);
        object.put("shot_speed", shotSpeed != null ? shotSpeed : -1);
        object.put("flight", flight != null ? flight : -1);
        object.put("crit_chance", criticalChance != null ? criticalChance : -1);
        object.put("crit_mult", criticalMultiplier != null ? criticalMultiplier : -1);
        object.put("status_chance", statusChance != null ? statusChance : -1);
        object.put("falloff", falloff != null ? falloff.serialize() : JSONObject.NULL);

        JSONObject damageObject = new JSONObject();
        for (Map.Entry<DamageType, Double> entry : damage.entrySet()) {
            damageObject.put(entry.getKey().toString(), entry.getValue());
        }
        object.put("damage", damageObject);

        return object;
    }
}