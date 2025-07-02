package de.shurablack.jwsa.api.entities.worldstate.others.types;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;

/**
 * Represents a Faction in the worldstate, which includes details such as its name.
 * This enum provides utility methods for retrieving the name and converting from a string representation.
 */
@AllArgsConstructor
@Getter
public enum Faction implements Serializable {
    /** The Orokin faction. */
    OROKIN("Orokin"),
    /** The Corrupted faction. */
    CORRUPTED("Corrupted"),
    /** The Infested faction. */
    INFESTED("Infested"),
    /** The Corpus faction. */
    CORPUS("Corpus"),
    /** The Grineer faction. */
    GRINEER("Grineer"),
    /** The Tenno faction. */
    TENNO("Tenno"),
    /** The Narmer faction. */
    NARMER("Narmer"),
    /** An unknown faction, used as a fallback for unrecognized names. */
    UNKNOWN("Unknown");

    private static final long serialVersionUID = 7063857228890341106L;

    /** The name of the faction. */
    private final String name;

    /**
     * Converts a string to its corresponding Faction.
     *
     * @param name The name of the faction as a string.
     * @return The matching Faction, or {@code UNKNOWN} if no match is found.
     */
    public static Faction fromString(String name) {
        if (name == null || name.isEmpty()) {
            return UNKNOWN;
        }

        for (Faction faction : Faction.values()) {
            if (faction.name.equalsIgnoreCase(name)) {
                return faction;
            }
        }
        return UNKNOWN;
    }
}