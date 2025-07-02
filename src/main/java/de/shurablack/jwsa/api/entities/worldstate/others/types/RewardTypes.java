package de.shurablack.jwsa.api.entities.worldstate.others.types;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;

/**
 * Represents the various reward types available in the worldstate.
 * Each reward type is associated with a specific name.
 */
@AllArgsConstructor
@Getter
public enum RewardTypes implements Serializable {
    /** The Vauban reward type. */
    VAUBAN("vauban"),
    /** The Vandal reward type. */
    VANDAL("vandal"),
    /** The Wraith reward type. */
    WRAITH("wraith"),
    /** The Skin reward type. */
    SKIN("skin"),
    /** The Helmet reward type. */
    HELMET("helmet"),
    /** The Nitain reward type. */
    NITAIN("nitain"),
    /** The Mutalist reward type. */
    MUTALIST("mutalist"),
    /** The Weapon reward type. */
    WEAPON("weapon"),
    /** The Fieldron reward type. */
    FIELDRON("fieldron"),
    /** The Detonite reward type. */
    DETONITE("detonite"),
    /** The Mutagen reward type. */
    MUTAGEN("mutagen"),
    /** The Aura reward type. */
    AURA("aura"),
    /** The Neural Sensors reward type. */
    NEURAL_SENSORS("neuralSensors"),
    /** The Orokin Cell reward type. */
    OROKIN_CELL("orokinCell"),
    /** The Alloy Plate reward type. */
    ALLOY_PLATE("alloyPlate"),
    /** The Circuits reward type. */
    CIRCUITS("circuits"),
    /** The Control Module reward type. */
    CONTROL_MODULE("controlModule"),
    /** The Ferrite reward type. */
    FERRITE("ferrite"),
    /** The Gallium reward type. */
    GALLIUM("gallium"),
    /** The Morphics reward type. */
    MORPHICS("morphics"),
    /** The Nano Spores reward type. */
    NANO_SPORES("nanoSpores"),
    /** The Oxium reward type. */
    OXIUM("oxium"),
    /** The Rubedo reward type. */
    RUBEDO("rubedo"),
    /** The Salvage reward type. */
    SALVAGE("salvage"),
    /** The Plastids reward type. */
    PLASTIDS("plastids"),
    /** The Polymer Bundle reward type. */
    POLYMER_BUNDLE("polymerBundle"),
    /** The Argon Crystal reward type. */
    ARGON_CRYSTAL("argonCrystal"),
    /** The Cryotic reward type. */
    CRYOTIC("cryotic"),
    /** The Tellurium reward type. */
    TELLURIUM("tellurium"),
    /** The Neurodes reward type. */
    NEURODES("neurodes"),
    /** The Nightmare reward type. */
    NIGHTMARE("nightmare"),
    /** The Endo reward type. */
    ENDO("endo"),
    /** The Reactor reward type. */
    REACTOR("reactor"),
    /** The Catalyst reward type. */
    CATALYST("catalyst"),
    /** The Forma reward type. */
    FORMA("forma"),
    /** The Synthula reward type. */
    SYNTHULA("synthula"),
    /** The Exilus reward type. */
    EXILUS("exilus"),
    /** The Riven reward type. */
    RIVEN("riven"),
    /** The Kavat Gene reward type. */
    KAVAT_GENE("kavatGene"),
    /** The Kubrow Gene reward type. */
    KUBROW_GENE("kubrowGene"),
    /** The Traces reward type. */
    TRACES("traces"),
    /** The Other reward type, used as a fallback for unrecognized names. */
    OTHER("other"),
    /** The Credits reward type. */
    CREDITS("credits"),
    /** For any reward type that is not recognized or specified. */
    UNKNOWN("unknown");

    private static final long serialVersionUID = 2347782504143332038L;

    /** The name of the reward type. */
    private final String name;

    /**
     * Converts a string to its corresponding RewardTypes.
     *
     * @param name The name of the reward type as a string.
     * @return The matching RewardTypes, or {@code OTHER} if no match is found.
     */
    public static RewardTypes fromString(String name) {
        if (name == null || name.isEmpty()) {
            return UNKNOWN;
        }

        for (RewardTypes alertRewardTypes : RewardTypes.values()) {
            if (alertRewardTypes.name.equalsIgnoreCase(name)) {
                return alertRewardTypes;
            }
        }
        return UNKNOWN;
    }
}