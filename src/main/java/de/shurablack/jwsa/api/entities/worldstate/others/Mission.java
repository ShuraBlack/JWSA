package de.shurablack.jwsa.api.entities.worldstate.others;

import de.shurablack.jwsa.api.entities.IJsonMapping;
import de.shurablack.jwsa.api.entities.worldstate.others.types.Faction;
import de.shurablack.jwsa.api.entities.worldstate.others.types.MissionType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Represents a mission in the worldstate, containing details such as rewards, node information,
 * faction, enemy levels, mission type, and other mission-specific attributes.
 */
@AllArgsConstructor
@Getter
public class Mission implements Serializable, IJsonMapping {

    private static final long serialVersionUID = -7796708072113978598L;

    /** The reward associated with the mission. */
    private final Reward reward;

    /** The name of the node where the mission takes place. */
    private final String node;

    /** The key of the node where the mission takes place. */
    private final String nodeKey;

    /** The faction associated with the mission. */
    private final Faction faction;

    /** The maximum enemy level in the mission. */
    private final Number maxEnemyLevel;

    /** The minimum enemy level in the mission. */
    private final Number minEnemyLevel;

    /** The maximum number of waves in the mission. */
    private final Number maxWaveNum;

    /** The type of the mission. */
    private final MissionType type;

    /** Indicates if the mission is a nightmare mission. */
    private final boolean nightmare;

    /** Indicates if the mission requires Archwing. */
    private final boolean archwingRequired;

    /** Indicates if the mission involves Sharkwing gameplay. */
    private final boolean sharkwing;

    /** The enemy specification for the mission. */
    private final String enemySpec;

    /** The level override for the mission. */
    private final String levelOverride;

    /** The list of advanced spawners used in the mission. */
    private final List<String> advancedSpawners;

    /** The list of items required to start the mission. */
    private final List<String> requiredItems;

    /** Indicates if the required items are consumed upon mission start. */
    private final boolean consumeRequiredItems;

    /** Indicates if leaders are always allowed in the mission. */
    private final boolean leadersAlwaysAllowed;

    /** The list of level auras applied in the mission. */
    private final List<String> levelAuras;

    /** The description of the mission. */
    private final String description;

    /**
     * Creates a Mission instance from a JSON object.
     *
     * @param object The JSON object containing the mission details.
     * @return A new Mission instance with the parsed details.
     */
    public static Mission deserialize(JSONObject object) {
        Reward reward = object.has("reward") ? Reward.deserialize(object.getJSONObject("reward")) : null;
        String node = object.optString("node", null);
        String nodeKey = object.optString("nodeKey", null);
        Faction faction = object.has("faction") ? Faction.fromString(object.getString("faction")) : Faction.UNKNOWN;
        int maxEnemyLevel = object.optInt("maxEnemyLevel", -1);
        int minEnemyLevel = object.optInt("minEnemyLevel", -1);
        int maxWaveNum = object.optInt("maxWaveNum", -1);
        MissionType type = MissionType.fromString(object.optString("type", null));
        boolean nightmare = object.optBoolean("nightmare", false);
        boolean archwingRequired = object.optBoolean("archwingRequired", false);
        boolean sharkwing = object.optBoolean("sharkwing", false);
        String enemySpec = object.optString("enemySpec", null);
        String levelOverride = object.optString("levelOverride", null);
        List<String> advancedSpawners = object.has("advancedSpawners") ? object.getJSONArray("advancedSpawners").toList().stream().map(Object::toString).collect(Collectors.toList()) : List.of();
        List<String> requiredItems = object.has("requiredItems") ? object.getJSONArray("requiredItems").toList().stream().map(Object::toString).collect(Collectors.toList()) : List.of();
        boolean consumeRequiredItems = object.optBoolean("consumeRequiredItems", false);
        boolean leadersAlwaysAllowed = object.optBoolean("leadersAlwaysAllowed", false);
        List<String> levelAuras = object.has("levelAuras") ? object.getJSONArray("levelAuras").toList().stream().map(Object::toString).collect(Collectors.toList()) : List.of();
        String description = object.optString("description", null);

        return new Mission(reward, node, nodeKey, faction, maxEnemyLevel, minEnemyLevel, maxWaveNum,
                type, nightmare, archwingRequired, sharkwing, enemySpec, levelOverride,
                advancedSpawners, requiredItems, consumeRequiredItems, leadersAlwaysAllowed,
                levelAuras, description);
    }

    @Override
    public JSONObject serialize() {
        JSONObject json = new JSONObject();
        json.put("reward", reward != null ? reward.serialize() : JSONObject.NULL);
        json.put("node", node != null ? node : JSONObject.NULL);
        json.put("nodeKey", nodeKey != null ? nodeKey : JSONObject.NULL);
        json.put("faction", faction != null ? faction.toString() : JSONObject.NULL);
        json.put("maxEnemyLevel", maxEnemyLevel != null ? maxEnemyLevel : -1);
        json.put("minEnemyLevel", minEnemyLevel != null ? minEnemyLevel : -1);
        json.put("maxWaveNum", maxWaveNum != null ? maxWaveNum : -1);
        json.put("type", type != null ? type.toString() : JSONObject.NULL);
        json.put("nightmare", nightmare);
        json.put("archwingRequired", archwingRequired);
        json.put("sharkwing", sharkwing);
        json.put("enemySpec", enemySpec != null ? enemySpec : JSONObject.NULL);
        json.put("levelOverride", levelOverride != null ? levelOverride : JSONObject.NULL);
        json.put("advancedSpawners", advancedSpawners);
        json.put("requiredItems", requiredItems);
        json.put("consumeRequiredItems", consumeRequiredItems);
        json.put("leadersAlwaysAllowed", leadersAlwaysAllowed);
        json.put("levelAuras", levelAuras);
        json.put("description", description != null ? description : JSONObject.NULL);

        return json;
    }
}