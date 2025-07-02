package de.shurablack.jwsa.api.entities.worldstate.relay;

import de.shurablack.jwsa.api.entities.IJsonMapping;
import de.shurablack.jwsa.api.requests.Paths;
import de.shurablack.jwsa.api.requests.Requests;
import de.shurablack.jwsa.api.utils.ServerOffsetTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.json.JSONObject;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Represents a Conclave Challenge in the worldstate, containing details such as mode, amount,
 * expiration status, description, and other related attributes.
 */
@AllArgsConstructor
@Getter
public class ConclaveChallenge implements Serializable, IJsonMapping {

    private static final long serialVersionUID = 8818976884682598581L;

    /** The unique identifier of the Conclave Challenge. */
    private final String id;

    /** The mode of the Conclave Challenge. */
    private final String mode;

    /** The amount associated with the Conclave Challenge. */
    private final Number amount;

    /** The estimated time remaining for the Conclave Challenge. */
    private final String eta;

    /** Indicates whether the Conclave Challenge has expired. */
    private final boolean expired;

    /** The end string of the Conclave Challenge. */
    private final String endString;

    /** Indicates whether the Conclave Challenge is a daily challenge. */
    private final boolean daily;

    /** The description of the Conclave Challenge. */
    private final String description;

    /** The expiry time of the Conclave Challenge. */
    private final LocalDateTime expiry;

    /** The string representation of the Conclave Challenge. */
    private final String asString;

    /** The category of the Conclave Challenge. */
    private final String category;

    /** Indicates whether the Conclave Challenge is a root challenge. */
    private final boolean rootChallenge;

    /**
     * Creates a ConclaveChallenge instance from a JSON object.
     *
     * @param json The JSON object containing the Conclave Challenge details.
     * @return A new ConclaveChallenge instance with the parsed details.
     */
    public static ConclaveChallenge deserialize(org.json.JSONObject json) {
        String id = json.optString("id", null);
        String mode = json.optString("mode", null);
        Number amount = json.optNumber("amount", -1);
        String eta = json.optString("eta", null);
        boolean expired = json.optBoolean("expired", false);
        String endString = json.optString("endString", null);
        boolean daily = json.optBoolean("daily", false);
        String description = json.optString("description", null);
        LocalDateTime expiry = ServerOffsetTime.of(json.optString("expiry", null));
        String asString = json.optString("asString", null);
        String category = json.optString("category", null);
        boolean rootChallenge = json.optBoolean("rootChallenge", false);

        return new ConclaveChallenge(id, mode, amount, eta, expired, endString, daily, description, expiry, asString, category, rootChallenge);
    }

    @Override
    public JSONObject serialize() {
        JSONObject json = new JSONObject();
        json.put("id", id != null ? id : JSONObject.NULL);
        json.put("mode", mode != null ? mode : JSONObject.NULL);
        json.put("amount", amount != null ? amount : JSONObject.NULL);
        json.put("eta", eta != null ? eta : JSONObject.NULL);
        json.put("expired", expired);
        json.put("endString", endString != null ? endString : JSONObject.NULL);
        json.put("daily", daily);
        json.put("description", description != null ? description : JSONObject.NULL);
        json.put("expiry", expiry != null ? expiry.toString() : JSONObject.NULL);
        json.put("asString", asString != null ? asString : JSONObject.NULL);
        json.put("category", category != null ? category : JSONObject.NULL);
        json.put("rootChallenge", rootChallenge);
        return json;
    }

    /**
     * Requests the list of Conclave Challenges from the server.
     *
     * @return A list of ConclaveChallenge instances retrieved from the server.
     */
    public static List<ConclaveChallenge> request() {
        return Requests.withListMapping(ConclaveChallenge.class, Paths.CONCLAVE_CHALLENGES);
    }
}