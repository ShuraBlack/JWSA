package de.shurablack.jwsa.api.entities.searchable.others.types;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Represents the polarity types in the game, such as Vazarin, Madurai, Naramon, etc.
 * Each polarity type has a name associated with it.
 */
@AllArgsConstructor
@Getter
public enum Polarity {
    /** Represents the Vazarin polarity type. */
    VAZARIN("Vazarin"),

    /** Represents the Madurai polarity type. */
    MADURAI("Madurai"),

    /** Represents the Naramon polarity type. */
    NARAMON("Naramon"),

    /** Represents the Zenurik polarity type. */
    ZENURIK("Zenurik"),

    /** Represents the Unairu polarity type. */
    UNAIRU("Unairu"),

    /** Represents the Penjaga polarity type. */
    PENJAGA("Penjaga"),

    /** Represents the Unbra polarity type. */
    UNBRA("Unbra"),

    /** Represents an unknown polarity type. */
    UNKNOWN("Unknown");

    /** The name of the polarity type. */
    private final String name;

    /**
     * Returns the string representation of the polarity type.
     *
     * @return The name of the polarity type.
     */
    @Override
    public String toString() {
        return name;
    }

    /**
     * Converts a string to a corresponding {@link Polarity} enum value.
     *
     * @param polarity The string representation of the polarity type.
     * @return The corresponding {@link Polarity} enum value, or {@link Polarity#UNKNOWN} if no match is found.
     */
    public static Polarity fromString(String polarity) {
        if (polarity == null || polarity.isEmpty()) {
            return UNKNOWN;
        }

        for (Polarity p : Polarity.values()) {
            if (p.name.equalsIgnoreCase(polarity)) {
                return p;
            }
        }
        return UNKNOWN;
    }
}