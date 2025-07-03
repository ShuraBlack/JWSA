package de.shurablack.jwsa.api.entities.worldstate.planet;

import de.shurablack.jwsa.api.entities.IJsonMapping;
import de.shurablack.jwsa.api.entities.worldstate.planet.sub.ArchimedeaMission;
import de.shurablack.jwsa.api.entities.worldstate.planet.sub.PersonalModifier;
import de.shurablack.jwsa.api.requests.Paths;
import de.shurablack.jwsa.api.requests.Requests;
import de.shurablack.jwsa.api.utils.Persistence;
import de.shurablack.jwsa.api.utils.ServerOffsetTime;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.json.JSONObject;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Represents the Deep Archimedea status in the worldstate, containing details such as activation time,
 * expiry time, associated missions, and personal modifiers.
 */
@AllArgsConstructor
@Getter
@EqualsAndHashCode
public class DeepArchimedea implements Serializable, IJsonMapping {

    private static final long serialVersionUID = 3167937844845357714L;

    /** The unique identifier of the Deep Archimedea status. */
    private final String id;

    /** The activation time of the Deep Archimedea status. */
    private final LocalDateTime activation;

    /** The expiry time of the Deep Archimedea status. */
    private final LocalDateTime expiry;

    /** The list of missions associated with the Deep Archimedea status. */
    private final List<ArchimedeaMission> missions;

    /** The list of personal modifiers associated with the Deep Archimedea status. */
    private final List<PersonalModifier> personalModifiers;

    /**
     * Creates a DeepArchimedea instance from a JSON object.
     *
     * @param object The JSON object containing the Deep Archimedea details.
     * @return A new DeepArchimedea instance with the parsed details.
     */
    public static DeepArchimedea deserialize(JSONObject object) {
        String id = object.optString("id", null);
        LocalDateTime activation = ServerOffsetTime.of(object.optString("activation", null));
        LocalDateTime expiry = ServerOffsetTime.of(object.optString("expiry", null));

        List<ArchimedeaMission> missions = new ArrayList<>();
        if (object.has("missions")) {
            for (Object missionObj : object.getJSONArray("missions")) {
                missions.add(ArchimedeaMission.deserialize((JSONObject) missionObj));
            }
        }

        List<PersonalModifier> personalModifiers = new ArrayList<>();
        if (object.has("personalModifiers")) {
            for (Object modifierObj : object.getJSONArray("personalModifiers")) {
                personalModifiers.add(PersonalModifier.deserialize((JSONObject) modifierObj));
            }
        }

        return new DeepArchimedea(id, activation, expiry, missions, personalModifiers);
    }

    @Override
    public JSONObject serialize() {
        JSONObject json = new JSONObject();
        json.put("id", id != null ? id : JSONObject.NULL);
        json.put("activation", activation != null ? activation.toString() : JSONObject.NULL);
        json.put("expiry", expiry != null ? expiry.toString() : JSONObject.NULL);

        if (missions != null) {
            json.put("missions", missions.stream()
                    .map(ArchimedeaMission::serialize)
                    .collect(Collectors.toList()));
        } else {
            json.put("missions", new ArrayList<>());
        }

        if (personalModifiers != null) {
            json.put("personalModifiers", personalModifiers.stream()
                    .map(PersonalModifier::serialize)
                    .collect(Collectors.toList()));
        } else {
            json.put("personalModifiers", new ArrayList<>());
        }

        return json;
    }

    /**
     * Requests the DeepArchimedea status from the server.
     *
     * @return A DeepArchimedea instance retrieved from the server.
     */
    public static DeepArchimedea request() {
        return Requests.withDirectMapping(DeepArchimedea.class, Paths.DEEP_ARCHIMEDEA);
    }
}