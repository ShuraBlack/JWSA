package de.shurablack.jwsa.api.utils;

import java.time.*;

/**
 * Utility class for converting server-provided timestamps into local date-time representations.
 */
public class ServerOffsetTime {

    /**
     * Converts a server-provided timestamp string into a `LocalDateTime` object.
     * The timestamp is parsed as an `OffsetDateTime` and then converted to the system's default time zone.
     *
     * @param stamp The timestamp string in ISO-8601 format (e.g., "2023-03-15T10:00:00+01:00").
     *              If the input is null, the method returns null.
     * @return A `LocalDateTime` object representing the timestamp in the system's default time zone,
     *         or null if the input is null.
     */
    public static LocalDateTime of(String stamp) {
        if (stamp == null) {
            return null;
        }

        OffsetDateTime offsetDateTime = OffsetDateTime.parse(stamp);
        ZonedDateTime zonedDateTime = offsetDateTime.atZoneSameInstant(ZoneId.systemDefault());
        return zonedDateTime.toLocalDateTime();
    }

}