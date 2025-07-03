package de.shurablack.jwsa.api.entities.searchable.weapon;

import de.shurablack.jwsa.api.entities.IJsonMapping;
import de.shurablack.jwsa.api.entities.searchable.others.AltComponent;
import de.shurablack.jwsa.api.entities.searchable.others.Introduced;
import de.shurablack.jwsa.api.entities.searchable.others.Patchlog;
import de.shurablack.jwsa.api.entities.searchable.others.types.Polarity;
import de.shurablack.jwsa.api.entities.searchable.weapon.sub.Attack;
import de.shurablack.jwsa.api.entities.searchable.weapon.sub.DamageType;
import de.shurablack.jwsa.api.requests.Paths;
import de.shurablack.jwsa.api.requests.Requests;
import de.shurablack.jwsa.api.requests.url.Encoder;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.json.JSONObject;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Represents a weapon in the game, including its attributes, attacks, damage values, and other metadata.
 */
@AllArgsConstructor
@Getter
@EqualsAndHashCode
public class Weapon implements Serializable, IJsonMapping {

    private final static long serialVersionUID = -6194869153283224083L;

    /** The name of the weapon. */
    private final String name;

    /** The unique identifier for the weapon. */
    private final String uniqueName;

    /** A description of the weapon. */
    private final String description;

    /** The type of the weapon (e.g., primary, secondary). */
    private final String type;

    /** The category of the weapon. */
    private final String category;

    /** The product category of the weapon. */
    private final String productCategory;

    /** Indicates whether the weapon is tradable. */
    private final boolean tradable;

    /** Indicates whether the weapon is a prime variant. */
    private final boolean prime;

    /** Indicates whether the weapon is masterable. */
    private final boolean masterable;

    /** Indicates whether the weapon is vaulted. */
    private final boolean vaulted;

    /** The release date of the weapon. */
    private final LocalDate releaseDate;

    /** The estimated vault date of the weapon. */
    private final LocalDate estimatedVaultDate;

    /** The mastery rank requirement for the weapon. */
    private final Number masteryReq;

    /** The name of the image associated with the weapon. */
    private final String imageName;

    /** The URL to the weapon's Wikia page. */
    private final String wikiaUrl;

    /** The disposition value of the weapon. */
    private final Number disposition;

    /** The omega attenuation value of the weapon. */
    private final Number omegaAttenuation;

    /** A list of polarities associated with the weapon. */
    private final List<Polarity> polarities;

    /** The slot of the weapon. */
    private final Number slot;

    /** The trigger type of the weapon. */
    private final String trigger;

    /** The noise level of the weapon. */
    private final String noise;

    /** The accuracy of the weapon. */
    private final Number accuracy;

    /** The multishot value of the weapon. */
    private final Number multishot;

    /** The fire rate of the weapon. */
    private final Number fireRate;

    /** The magazine size of the weapon. */
    private final Number magazineSize;

    /** The reload time of the weapon. */
    private final Number reloadTime;

    /** The critical chance of the weapon. */
    private final Number criticalChance;

    /** The critical multiplier of the weapon. */
    private final Number criticalMultiplier;

    /** The proc chance of the weapon. */
    private final Number procChance;

    /** The total damage of the weapon. */
    private final Number totalDamage;

    /** A list of attacks associated with the weapon. */
    private final List<Attack> attacks;

    /** A map of damage types and their corresponding values for the weapon. */
    private final Map<DamageType, Double> damage;

    /** A list of damage values per shot for the weapon. */
    private final List<Number> damagePerShot;

    /** The build price of the weapon. */
    private final Number buildPrice;

    /** The build time of the weapon. */
    private final Number buildTime;

    /** The price to skip the build time of the weapon. */
    private final Number skipBuildTimePrice;

    /** A list of patchlogs associated with the weapon. */
    private final List<Patchlog> patchlogs;

    /** A list of alternative components for the weapon. */
    private final List<AltComponent> components;

    /** The introduction details of the weapon. */
    private final Introduced introduced;

    /** A list of tags associated with the weapon. */
    private final List<String> tags;

    /**
     * Deserializes a JSON object into an instance of {@link Weapon}.
     *
     * @param object The JSON object containing weapon data.
     * @return An instance of {@link Weapon} populated with data from the JSON object.
     */
    public static Weapon deserialize(JSONObject object) {
        String name = object.optString("name", null);
        String uniqueName = object.optString("uniqueName", null);
        String description = object.optString("description", null);
        String type = object.optString("type", null);
        String category = object.optString("category", null);
        String productCategory = object.optString("productCategory", null);
        boolean tradable = object.optBoolean("tradable", false);
        boolean prime = object.optBoolean("prime", false);
        boolean masterable = object.optBoolean("masterable", false);
        boolean vaulted = object.optBoolean("vaulted", false);
        LocalDate releaseDate = LocalDate.parse(object.optString("releaseDate", "1970-01-01"));
        LocalDate estimatedVaultDate = LocalDate.parse(object.optString("estimatedVaultDate", "1970-01-01"));
        Number masteryReq = object.optNumber("masteryReq", -1);
        String imageName = object.optString("imageName", null);
        String wikiaUrl = object.optString("wikiaUrl", null);
        Number disposition = object.optNumber("disposition", -1);
        Number omegaAttenuation = object.optNumber("omegaAttenuation", -1);

        List<Polarity> polarities = new ArrayList<>();
        if (object.has("polarities")) {
            object.getJSONArray("polarities").forEach(item -> polarities.add(Polarity.fromString((String) item)));
        }
        Number slot = object.optNumber("slot", -1);
        String trigger = object.optString("trigger", null);
        String noise = object.optString("noise", null);
        Number accuracy = object.optNumber("accuracy", -1);
        Number multishot = object.optNumber("multishot", -1);
        Number fireRate = object.optNumber("fireRate", -1);
        Number magazineSize = object.optNumber("magazineSize", -1);
        Number reloadTime = object.optNumber("reloadTime", -1);
        Number criticalChance = object.optNumber("criticalChance", -1);
        Number criticalMultiplier = object.optNumber("criticalMultiplier", -1);
        Number procChance = object.optNumber("procChance", -1);
        Number totalDamage = object.optNumber("totalDamage", -1);

        List<Attack> attacks = new ArrayList<>();
        if (object.has("attacks")) {
            object.getJSONArray("attacks").forEach(item -> attacks.add(Attack.deserialize((JSONObject) item)));
        }

        Map<DamageType, Double> damage = new HashMap<>();
        JSONObject damageObject = object.optJSONObject("damage");
        if (damageObject != null) {
            for (String key : damageObject.keySet()) {
                damage.put(DamageType.fromString(key), damageObject.optDouble(key, -1));
            }
        }

        List<Number> damagePerShot = new ArrayList<>();
        if (object.has("damagePerShot")) {
            object.getJSONArray("damagePerShot").forEach(item -> damagePerShot.add(((Number) item)));
        }
        Number buildPrice = object.optNumber("buildPrice", -1);
        Number buildTime = object.optNumber("buildTime", -1);
        Number skipBuildTimePrice = object.optNumber("skipBuildTimePrice", -1);
        List<Patchlog> patchlogs = new ArrayList<>();
        if (object.has("patchlogs")) {
            object.getJSONArray("patchlogs").forEach(item -> patchlogs.add(Patchlog.deserialize((JSONObject) item)));
        }
        List<AltComponent> components = new ArrayList<>();
        if (object.has("components")) {
            object.getJSONArray("components").forEach(item -> components.add(AltComponent.deserialize((JSONObject) item)));
        }
        Introduced introduced = Introduced.deserialize(object.optJSONObject("introduced"));
        List<String> tags = new ArrayList<>();
        if (object.has("tags")) {
            object.getJSONArray("tags").forEach(item -> tags.add((String) item));
        }

        return new Weapon(name, uniqueName, description, type, category, productCategory, tradable, prime, masterable,
                vaulted, releaseDate, estimatedVaultDate, masteryReq, imageName, wikiaUrl, disposition,
                omegaAttenuation, polarities, slot, trigger, noise, accuracy, multishot, fireRate,
                magazineSize, reloadTime, criticalChance, criticalMultiplier, procChance,
                totalDamage, attacks, damage, damagePerShot, buildPrice,
                buildTime, skipBuildTimePrice, patchlogs, components,
                introduced, tags);
    }

    /**
     * Serializes this {@link Weapon} instance into a JSON object.
     *
     * @return A {@link JSONObject} representation of this {@link Weapon}.
     */
    @Override
    public JSONObject serialize() {
        JSONObject object = new JSONObject();
        object.put("name", name != null ? name : JSONObject.NULL);
        object.put("uniqueName", uniqueName != null ? uniqueName : JSONObject.NULL);
        object.put("description", description != null ? description : JSONObject.NULL);
        object.put("type", type != null ? type : JSONObject.NULL);
        object.put("category", category != null ? category : JSONObject.NULL);
        object.put("productCategory", productCategory != null ? productCategory : JSONObject.NULL);
        object.put("tradable", tradable);
        object.put("prime", prime);
        object.put("masterable", masterable);
        object.put("vaulted", vaulted);
        object.put("releaseDate", releaseDate != null ? releaseDate.toString() : "1970-01-01");
        object.put("estimatedVaultDate", estimatedVaultDate != null ? estimatedVaultDate.toString() : "1970-01-01");
        object.put("masteryReq", masteryReq != null ? masteryReq : -1);
        object.put("imageName", imageName != null ? imageName : JSONObject.NULL);
        object.put("wikiaUrl", wikiaUrl != null ? wikiaUrl : JSONObject.NULL);
        object.put("disposition", disposition != null ? disposition : -1);
        object.put("omegaAttenuation", omegaAttenuation != null ? omegaAttenuation : -1);

        List<String> polaritiesList = new ArrayList<>();
        for (Polarity polarity : polarities) {
            polaritiesList.add(polarity.toString());
        }
        object.put("polarities", polaritiesList);

        object.put("slot", slot != null ? slot : -1);
        object.put("trigger", trigger != null ? trigger : JSONObject.NULL);
        object.put("noise", noise != null ? noise : JSONObject.NULL);
        object.put("accuracy", accuracy != null ? accuracy : -1);
        object.put("multishot", multishot != null ? multishot : -1);
        object.put("fireRate", fireRate != null ? fireRate : -1);
        object.put("magazineSize", magazineSize != null ? magazineSize : -1);
        object.put("reloadTime", reloadTime != null ? reloadTime : -1);
        object.put("criticalChance", criticalChance != null ? criticalChance : -1);
        object.put("criticalMultiplier", criticalMultiplier != null ? criticalMultiplier : -1);
        object.put("procChance", procChance != null ? procChance : -1);
        object.put("totalDamage", totalDamage != null ? totalDamage : -1);
        if (attacks != null) {
            object.put("attacks", attacks.stream().map(Attack::serialize).collect(Collectors.toList()));
        } else {
            object.put("attacks", new ArrayList<>());
        }
        JSONObject damageObject = new JSONObject();
        for (Map.Entry<DamageType, Double> entry : damage.entrySet()) {
            damageObject.put(entry.getKey().toString(), entry.getValue());
        }
        object.put("damage", damageObject);
        if (damagePerShot != null) {
            object.put("damagePerShot", damagePerShot.stream().map(Number::doubleValue).collect(Collectors.toList()));
        } else {
            object.put("damagePerShot", new ArrayList<>());
        }
        object.put("buildPrice", buildPrice != null ? buildPrice : -1);
        object.put("buildTime", buildTime != null ? buildTime : -1);
        object.put("skipBuildTimePrice", skipBuildTimePrice != null ? skipBuildTimePrice : -1);
        if (patchlogs != null) {
            object.put("patchlogs", patchlogs.stream().map(Patchlog::serialize).collect(Collectors.toList()));
        } else {
            object.put("patchlogs", new ArrayList<>());
        }
        if (components != null) {
            object.put("components", components.stream().map(AltComponent::serialize).collect(Collectors.toList()));
        } else {
            object.put("components", new ArrayList<>());
        }
        if (introduced != null) {
            object.put("introduced", introduced.serialize());
        } else {
            object.put("introduced", JSONObject.NULL);
        }
        if (tags != null) {
            object.put("tags", tags);
        } else {
            object.put("tags", new ArrayList<>());
        }

        return object;
    }

    /**
     * Requests a weapon by its query string.
     *
     * @param query The query string to search for the weapon.
     * @return The {@link Weapon} instance matching the query.
     */
    public static Weapon request(String query) {
        return Requests.withDirectMapping(Weapon.class, String.format(Paths.CLOSEST_WEAPON, Encoder.encode(query)));
    }

    /**
     * Requests a list of weapons matching the query string.
     *
     * @param query The query string to search for weapons.
     * @return A list of {@link Weapon} instances matching the query.
     */
    public static List<Weapon> requestAll(String query) {
        return Requests.withListMapping(Weapon.class, String.format(Paths.SEARCH_CLOSEST_WEAPONS, Encoder.encode(query)));
    }

}
