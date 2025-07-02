package de.shurablack.jwsa.api.entities.worldstate.planet.sub;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents an Archimedea mission in the worldstate, containing details about the mission,
 * its deviation, and associated risk variables.
 */
@AllArgsConstructor
@Getter
public class ArchimedeaMission {

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
    public static ArchimedeaMission fromJSON(JSONObject object) {
        String mission = object.optString("mission", null);
        Deviation deviation = Deviation.fromJSON(object.optJSONObject("deviation", null));
        List<RiskVariable> riskVariables = new ArrayList<>();
        if (object.has("riskVariables")) {
            for (Object riskVar : object.getJSONArray("riskVariables")) {
                riskVariables.add(RiskVariable.fromJSON((JSONObject) riskVar));
            }
        }

        return new ArchimedeaMission(mission, deviation, riskVariables);
    }

}