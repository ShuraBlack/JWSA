package de.shurablack.jwsa.api.entities.worldstate.others.types;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;

/**
 * Represents the various Syndicate types available in the worldstate.
 * Each Syndicate type is associated with a specific name.
 */
@AllArgsConstructor
@Getter
public enum SyndicateType implements Serializable {
    /** The Arbiters of Hexis Syndicate. */
    ARBITERS_OF_HEXIS("Arbiters of Hexis"),
    /** The Cephalon Suda Syndicate. */
    CEPHALON_SUDA("Cephalon Suda"),
    /** The Assassins Syndicate. */
    ASSASINS("Assassins"),
    /** The Nightwave Syndicate. */
    NIGHTWAVE("Nightwave"),
    /** The Ostrons Syndicate. */
    OSTRONS("Ostrons"),
    /** The Vox Solaris Syndicate. */
    VOX_SOLARIS("Vox Solaris"),
    /** The Solaris United Syndicate. */
    SOLARIS_UNITED("Solaris United"),
    /** The Perrin Sequence Syndicate. */
    PERRIN_SEQUENCE("Perrin Sequence"),
    /** The Steel Meridian Syndicate. */
    STEEL_MERIDIAN("Steel Meridian"),
    /** The Red Veil Syndicate. */
    RED_VEIL("Red Veil"),
    /** The New Loka Syndicate. */
    NEW_LOKA("New Loka"),
    /** An unknown Syndicate, used as a fallback for unrecognized names. */
    UNKNOWN("Unknown");

    private static final long serialVersionUID = 3308965940726681533L;

    /** The name of the Syndicate type. */
    private final String name;

    /**
     * Returns the string representation of the Syndicate type.
     *
     * @return The name of the Syndicate type.
     */
    @Override
    public String toString() {
        return name;
    }

    /**
     * Converts a string to its corresponding SyndicateType.
     *
     * @param name The name of the Syndicate type as a string.
     * @return The matching SyndicateType, or {@code UNKNOWN} if no match is found.
     */
    public static SyndicateType fromString(String name) {
        if (name == null || name.isEmpty()) {
            return UNKNOWN;
        }

        for (SyndicateType affiliation : values()) {
            if (affiliation.name.equalsIgnoreCase(name)) {
                return affiliation;
            }
        }
        return UNKNOWN;
    }
}