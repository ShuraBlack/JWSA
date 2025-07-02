package de.shurablack.jwsa.api.entities.worldstate.others.types;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Represents an Era in the worldstate, which includes details such as its name and tier.
 * This enum provides utility methods for retrieving the tier and converting from a string representation.
 */
@AllArgsConstructor
@Getter
public enum Era {
    /** The Lith era, representing tier 1. */
    LITH("Lith", 1),
    /** The Meso era, representing tier 2. */
    MESO("Meso", 2),
    /** The Neo era, representing tier 3. */
    NEO("Neo", 3),
    /** The Axi era, representing tier 4. */
    AXI("Axi", 4),
    /** The Requiem era, representing tier 5. */
    REQUIEM("Requiem", 5),
    /** An unknown era, used as a fallback for unrecognized names. */
    UNKNOWN("Unknown", -1);

    /** The name of the era. */
    private final String name;

    /** The tier of the era. */
    private final int tier;

    /**
     * Returns the string representation of the era.
     *
     * @return The name of the era.
     */
    @Override
    public String toString() {
        return name;
    }

    /**
     * Converts a string to its corresponding Era.
     *
     * @param name The name of the era as a string.
     * @return The matching Era, or {@code UNKNOWN} if no match is found.
     */
    public static Era fromString(String name) {
        if (name == null || name.isEmpty()) {
            return UNKNOWN;
        }

        for (Era era : Era.values()) {
            if (era.name.equalsIgnoreCase(name)) {
                return era;
            }
        }
        return UNKNOWN;
    }
}