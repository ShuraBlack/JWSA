package de.shurablack.jwsa.api.entities.worldstate.global;

import de.shurablack.jwsa.api.requests.Paths;
import de.shurablack.jwsa.api.requests.Requests;
import de.shurablack.jwsa.api.utils.ServerOffsetTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.json.JSONObject;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Represents global upgrades in the worldstate, containing details such as start and end times,
 * upgrade type, operation details, and additional attributes like expiration status and description.
 */
@AllArgsConstructor
@Getter
public class GlobalUpgrades {

    /** The start time of the global upgrade. */
    private final LocalDateTime start;

    /** The end time of the global upgrade. */
    private final LocalDateTime end;

    /** The type of upgrade being applied. */
    private final String upgrade;

    /** The operation associated with the upgrade. */
    private final String operation;

    /** The symbol representing the operation. */
    private final String operationSymbol;

    /** The value associated with the upgrade operation. */
    private final Number upgradeOperationValue;

    /** Indicates whether the global upgrade has expired. */
    private final boolean expired;

    /** The estimated time remaining for the global upgrade. */
    private final String eta;

    /** A description of the global upgrade. */
    private final String desc;

    /**
     * Creates a GlobalUpgrades object from a JSON representation.
     *
     * @param object The JSON object containing the global upgrade data.
     * @return A GlobalUpgrades object populated with data from the JSON object.
     */
    public static GlobalUpgrades fromJSON(JSONObject object) {
        LocalDateTime start = ServerOffsetTime.of(object.optString("start", null));
        LocalDateTime end = ServerOffsetTime.of(object.optString("end", null));
        String upgrade = object.optString("upgrade", null);
        String operation = object.optString("operation", null);
        String operationSymbol = object.optString("operationSymbol", null);
        Number upgradeOperationValue = object.optNumber("upgradeOperationValue", -1);
        boolean expired = object.optBoolean("expired", false);
        String eta = object.optString("eta", null);
        String desc = object.optString("desc", null);

        return new GlobalUpgrades(start, end, upgrade, operation, operationSymbol, upgradeOperationValue, expired, eta, desc);
    }

    /**
     * Requests the list of current global upgrades from the server.
     *
     * @return A list of GlobalUpgrades objects representing the current global upgrades.
     */
    public static List<GlobalUpgrades> request() {
        return Requests.withListMapping(GlobalUpgrades.class, Paths.GLOBAL_UPGRADES);
    }

}