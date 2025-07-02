package de.shurablack.jwsa.api.entities.worldstate.global.sub;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.json.JSONObject;

import java.util.List;

/**
 * Represents a message in the worldstate, containing details such as the sender, subject, content, sender icon, and attachments.
 */
@AllArgsConstructor
@Getter
public class Message {

    /** The sender of the message. */
    private final String sender;

    /** The subject of the message. */
    private final String subject;

    /** The content of the message. */
    private final String message;

    /** The icon associated with the sender of the message. */
    private final String senderIcon;

    /** A list of attachments included in the message. */
    private final List<String> attachments;

    /**
     * Creates a Message object from a JSON representation.
     *
     * @param object The JSON object containing the message data.
     * @return A Message object populated with data from the JSON object.
     */
    public static Message fromJSON(JSONObject object) {
        String sender = object.optString("sender", null);
        String subject = object.optString("subject", null);
        String message = object.optString("message", null);
        String senderIcon = object.optString("senderIcon", null);
        List<String> attachments = object.optJSONArray("attachments") != null
                ? object.getJSONArray("attachments").toList().stream()
                .map(Object::toString)
                .collect(java.util.stream.Collectors.toList())
                : List.of();

        return new Message(sender, subject, message, senderIcon, attachments);
    }

}