package de.shurablack.jwsa.api.entities.worldstate.others.types;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Represents the various mission types available in the worldstate.
 * Each mission type is associated with a specific name.
 */
@AllArgsConstructor
@Getter
public enum MissionType {
    /** The Ancient Retribution mission type. */
    ANCIENT_RETRIBUTION("Ancient Retribution"),
    /** The Arena mission type. */
    ARENA("Arena"),
    /** The Assassination mission type. */
    ASSASSINATION("Assassination"),
    /** The Assault mission type. */
    ASSAULT("Assault"),
    /** The Capture mission type. */
    CAPTURE("Capture"),
    /** The Conclave mission type. */
    CONCLAVE("Conclave"),
    /** The Dark Sector Defection mission type. */
    DARK_SECTOR_DEFECTION("Dark Sector Defection"),
    /** The Dark Sector Defense mission type. */
    DARK_SECTOR_DEFENSE("Dark Sector Defense"),
    /** The Dark Sector Disruption mission type. */
    DARK_SECTOR_DISRUPTION("Dark Sector Disruption"),
    /** The Dark Sector Excavation mission type. */
    DARK_SECTOR_EXCAVATION("Dark Sector Excavation"),
    /** The Dark Sector Sabotage mission type. */
    DARK_SECTOR_SABOTAGE("Dark Sector Sabotage"),
    /** The Dark Sector Survival mission type. */
    DARK_SECTOR_SURVIVAL("Dark Sector Survival"),
    /** The Defense mission type. */
    DEFENSE("Defense"),
    /** The Disruption mission type. */
    DISRUPTION("Disruption"),
    /** The Excavation mission type. */
    EXCAVATION("Excavation"),
    /** The Extermination (Archwing) mission type. */
    EXTERMINATION_ARCHWING("Extermination (Archwing)"),
    /** The Extermination mission type. */
    EXTERMINATION("Extermination"),
    /** The Free Roam mission type. */
    FREE_ROAM("Free Roam"),
    /** The Hijack mission type. */
    HIJACK("Hijack"),
    /** The Hive mission type. */
    HIVE("Hive"),
    /** The Hive Sabotage mission type. */
    HIVE_SABOTAGE("Hive Sabotage"),
    /** The Interception mission type. */
    INTERCEPTION("Interception"),
    /** The Interception (Archwing) mission type. */
    INTERCEPTION_ARCHWING("Interception (Archwing)"),
    /** The Mobile Defense mission type. */
    MOBILE_DEFENSE("Mobile Defense"),
    /** The Mobile Defense (Archwing) mission type. */
    MOBILE_DEFENSE_ARCHWING("Mobile Defense (Archwing)"),
    /** The Orokin Sabotage mission type. */
    OROKIN_SABOTAGE("Orokin Sabotage"),
    /** The Orphix mission type. */
    ORPHIX("Orphix"),
    /** The Pursuit (Archwing) mission type. */
    PURSUIT_ARCHWING("Pursuit (Archwing)"),
    /** The Relay mission type. */
    RELAY("Relay"),
    /** The Rescue mission type. */
    RESCUE("Rescue"),
    /** The Rush (Archwing) mission type. */
    RUSH_ARCHWING("Rush (Archwing)"),
    /** The Sabotage mission type. */
    SABOTAGE("Sabotage"),
    /** The Sabotage (Archwing) mission type. */
    SABOTAGE_ARCHWING("Sabotage (Archwing)"),
    /** The Skirmish mission type. */
    SKIRMISH("Skirmish"),
    /** The Spy mission type. */
    SPY("Spy"),
    /** The Survival mission type. */
    SURVIVAL("Survival"),
    /** The Volatile mission type. */
    VOLATILE("Volatile"),
    /** The Alchemy mission type. */
    ALCHEMY("Alchemy"),
    /** The Void Cascade mission type. */
    VOID_CASCADE("Void Cascade"),
    /** The Corruption mission type. */
    CORRUPTION("Corruption"),
    /** An unknown mission type, used as a fallback for unrecognized names. */
    UNKNOWN("Unknown");

    /** The name of the mission type. */
    private final String name;

    /**
     * Converts a string to its corresponding MissionType.
     *
     * @param name The name of the mission type as a string.
     * @return The matching MissionType, or {@code UNKNOWN} if no match is found.
     */
    public static MissionType fromString(String name) {
        if (name == null || name.isEmpty()) {
            return UNKNOWN;
        }

        for (MissionType type : MissionType.values()) {
            if (type.name.equalsIgnoreCase(name)) {
                return type;
            }
        }
        return UNKNOWN;
    }
}