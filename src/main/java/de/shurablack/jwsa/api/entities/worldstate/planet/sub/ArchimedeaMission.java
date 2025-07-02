package de.shurablack.jwsa.api.entities.worldstate.planet.sub;

import de.shurablack.jwsa.api.entities.IJsonMapping;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Represents an Archimedea mission in the worldstate, containing details about the mission,
 * its deviation, and associated risk variables.
 */
@AllArgsConstructor
@Getter
public class ArchimedeaMission implements Serializable, IJsonMapping {

    private static final long serialVersionUID = 3110195298656157427L;

    /** The name or identifier of the mission. */
    private final String mission;

    /** The deviation details associated with the mission. */
    private final Deviation deviation;

    /** The list of risk variables associated with the mission. */
    private final List<RiskVariable> riskVariables;

    /**
     * Creates an ArchimedeaMission instance from a JSON object.
     *
     * @param object The JSON object containing the mission details.
     * @return A new ArchimedeaMission instance with the parsed details.
     */
    public static ArchimedeaMission deserialize(JSONObject object) {
        String mission = object.optString("mission", null);
        Deviation deviation = Deviation.deserialize(object.optJSONObject("deviation", null));
        List<RiskVariable> riskVariables = new ArrayList<>();
        if (object.has("riskVariables")) {
            for (Object riskVar : object.getJSONArray("riskVariables")) {
                riskVariables.add(RiskVariable.deserialize((JSONObject) riskVar));
            }
        }

        return new ArchimedeaMission(mission, deviation, riskVariables);
    }

    @Override
    public JSONObject serialize() {
        JSONObject json = new JSONObject();
        json.put("mission", mission != null ? mission : JSONObject.NULL);
        json.put("deviation", deviation != null ? deviation.serialize() : JSONObject.NULL);
        if (riskVariables != null) {
            json.put("riskVariables", riskVariables.stream()
                    .map(RiskVariable::serialize)
                    .collect(Collectors.toList()));
        } else {
            json.put("riskVariables", new ArrayList<>());
        }
        return json;
    }
}