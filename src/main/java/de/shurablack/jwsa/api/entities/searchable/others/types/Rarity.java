package de.shurablack.jwsa.api.entities.searchable.others.types;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Represents the rarity levels of an item, such as Common, Uncommon, Rare, Legendary, or Unknown.
 */
@AllArgsConstructor
@Getter
public enum Rarity {
    /** Represents a common rarity level. */
    COMMON("Common"),

    /** Represents an uncommon rarity level. */
    UNCOMMON("Uncommon"),

    /** Represents a rare rarity level. */
    RARE("Rare"),

    /** Represents a legendary rarity level. */
    LEGENDARY("Legendary"),

    /** Represents an unknown rarity level. */
    UNKNOWN("Unknown");

    /** The name of the rarity level. */
    private final String name;

    /**
     * Returns the string representation of the rarity level.
     *
     * @return The name of the rarity level.
     */
    @Override
    public String toString() {
        return name;
    }

    /**
     * Converts a string to a corresponding {@link Rarity} enum value.
     *
     * @param rarity The string representation of the rarity level.
     * @return The corresponding {@link Rarity} enum value, or {@link Rarity#UNKNOWN} if no match is found.
     */
    public static Rarity fromString(String rarity) {
        if (rarity == null || rarity.isEmpty()) {
            return UNKNOWN;
        }

        for (Rarity r : Rarity.values()) {
            if (r.name.equalsIgnoreCase(rarity)) {
                return r;
            }
        }
        return UNKNOWN;
    }
}