package de.shurablack.jwsa.api.entities.worldstate.global.sub;

import de.shurablack.jwsa.api.entities.worldstate.others.Reward;
import de.shurablack.jwsa.api.entities.worldstate.others.types.Faction;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.json.JSONObject;

/**
 * Represents a party involved in an invasion event, containing details such as the reward and faction.
 */
@AllArgsConstructor
@Getter
public class InvasionParty {

    /** The reward associated with this invasion party. */
    private final Reward reward;

    /** The faction associated with this invasion party. */
    private final Faction faction;

    /**
     * Creates an InvasionParty object from a JSON representation.
     *
     * @param object The JSON object containing the invasion party data.
     * @return An InvasionParty object populated with data from the JSON object, or null if the input is null.
     */
    public static InvasionParty fromJSON(JSONObject object) {
        if (object == null) {
            return null;
        }
        Reward reward = Reward.fromJSON(object.optJSONObject("reward", null));

        Faction faction = Faction.fromString(object.optString("faction", null));

        return new InvasionParty(reward, faction);
    }

}