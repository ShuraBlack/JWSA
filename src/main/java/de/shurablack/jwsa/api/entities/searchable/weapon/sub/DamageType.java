package de.shurablack.jwsa.api.entities.searchable.weapon.sub;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;

/**
 * Enum representing various damage types in the game, each with a name and a unique identifier.
 */
@AllArgsConstructor
@Getter
public enum DamageType implements Serializable {

    /** Total damage type. */
    TOTAL("Total", "total"),

    /** Impact damage type. */
    IMPACT("Impact", "impact"),

    /** Puncture damage type. */
    PUNCTURE("Puncture", "puncture"),

    /** Slash damage type. */
    SLASH("Slash", "slash"),

    /** Heat damage type. */
    HEAT("Heat", "heat"),

    /** Cold damage type. */
    COLD("Cold", "cold"),

    /** Electricity damage type. */
    ELECTRICITY("Electricity", "electricity"),

    /** Toxin damage type. */
    TOXIN("Toxin", "toxin"),

    /** Blast damage type. */
    BLAST("Blast", "blast"),

    /** Radiation damage type. */
    RADIATION("Radiation", "radiation"),

    /** Gas damage type. */
    GAS("Gas", "gas"),

    /** Magnetic damage type. */
    MAGNETIC("Magnetic", "magnetic"),

    /** Viral damage type. */
    VIRAL("Viral", "viral"),

    /** Corrosive damage type. */
    CORROSIVE("Corrosive", "corrosive"),

    /** Void damage type. */
    VOID("Void", "void"),

    /** Tau damage type. */
    TAU("Tau", "tau"),

    /** Cinematic damage type. */
    CINEMATIC("Cinematic", "cinematic"),

    /** Shield drain damage type. */
    SHIELD_DRAIN("Shield Drain", "shieldDrain"),

    /** Health drain damage type. */
    HEALTH_DRAIN("Health Drain", "healthDrain"),

    /** Energy drain damage type. */
    ENERGY_DRAIN("Energy Drain", "energyDrain"),

    /** True damage type. */
    TRUE("True", "true"),

    /** Unknown damage type. */
    UNKNOWN("Unknown", "unknown");

    private final static long serialVersionUID = -2332235765217197817L;

    /** The name of the damage type. */
    private final String name;

    /** The unique identifier for the damage type. */
    private final String uniqueName;

    /**
     * Returns the unique identifier of the damage type as a string.
     *
     * @return The unique identifier of the damage type.
     */
    @Override
    public String toString() {
        return uniqueName;
    }

    /**
     * Retrieves a {@link DamageType} instance based on its unique identifier.
     *
     * @param uniqueName The unique identifier of the damage type.
     * @return The corresponding {@link DamageType}, or {@link DamageType#UNKNOWN} if no match is found.
     */
    public static DamageType fromString(String uniqueName) {
        for (DamageType type : values()) {
            if (type.uniqueName.equalsIgnoreCase(uniqueName)) {
                return type;
            }
        }
        return UNKNOWN;
    }

}