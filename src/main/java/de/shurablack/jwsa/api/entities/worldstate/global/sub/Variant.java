package de.shurablack.jwsa.api.entities.worldstate.global.sub;

import de.shurablack.jwsa.api.entities.IJsonMapping;
import de.shurablack.jwsa.api.entities.worldstate.others.types.MissionType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.json.JSONObject;

import java.io.Serializable;

/**
 * Represents a Variant in the worldstate, containing details such as the node, boss, mission type, planet,
 * modifier, and modifier description.
 */
@AllArgsConstructor
@Getter
public class Variant implements Serializable, IJsonMapping {

    private static final long serialVersionUID = -1162029298995190541L;

    /** The node associated with this variant. */
    private final String node;

    /** The boss associated with this variant. */
    private final String boss;

    /** The mission type associated with this variant. */
    private final MissionType missionType;

    /** The planet associated with this variant. */
    private final String planet;

    /** The modifier applied to this variant. */
    private final String modifier;

    /** The description of the modifier applied to this variant. */
    private final String modifierDescription;

    /**
     * Creates a Variant object from a JSON representation.
     *
     * @param object The JSON object containing the variant data.
     * @return A Variant object populated with data from the JSON object.
     */
    public static Variant deserialize(JSONObject object) {
        String node = object.optString("node", null);
        String boss = object.optString("boss", null);
        MissionType missionType = MissionType.fromString(object.optString("missionType", null));
        String planet = object.optString("planet", null);
        String modifier = object.optString("modifier", null);
        String modifierDescription = object.optString("modifierDescription", null);

        return new Variant(node, boss, missionType, planet, modifier, modifierDescription);
    }

    @Override
    public JSONObject serialize() {
        JSONObject json = new JSONObject();
        json.put("node", node != null ? node : JSONObject.NULL);
        json.put("boss", boss != null ? boss : JSONObject.NULL);
        json.put("missionType", missionType != null ? missionType.toString() : JSONObject.NULL);
        json.put("planet", planet != null ? planet : JSONObject.NULL);
        json.put("modifier", modifier != null ? modifier : JSONObject.NULL);
        json.put("modifierDescription", modifierDescription != null ? modifierDescription : JSONObject.NULL);
        return json;
    }
}