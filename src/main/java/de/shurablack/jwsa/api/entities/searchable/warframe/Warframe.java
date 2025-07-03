package de.shurablack.jwsa.api.entities.searchable.warframe;

import de.shurablack.jwsa.api.entities.IJsonMapping;
import de.shurablack.jwsa.api.entities.searchable.others.AltComponent;
import de.shurablack.jwsa.api.entities.searchable.others.Introduced;
import de.shurablack.jwsa.api.entities.searchable.others.Patchlog;
import de.shurablack.jwsa.api.entities.searchable.warframe.sub.Ability;
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
import java.util.List;
import java.util.stream.Collectors;

/**
 * Represents a Warframe in the game, including its attributes, abilities, components, and metadata.
 */
@AllArgsConstructor
@Getter
@EqualsAndHashCode
public class Warframe implements Serializable, IJsonMapping {

    /** Serial version UID for serialization. */
    private static final long serialVersionUID = -5069834832560094266L;

    /** The name of the Warframe. */
    private final String name;

    /** The unique identifier for the Warframe. */
    private final String uniqueName;

    /** A description of the Warframe. */
    private final String description;

    /** The passive ability description of the Warframe. */
    private final String passiveDescription;

    /** The type of the Warframe. */
    private final String type;

    /** The sex of the Warframe (e.g., Male, Female). */
    private final String sex;

    /** The category of the Warframe. */
    private final String category;

    /** The product category of the Warframe. */
    private final String productCategory;

    /** Indicates whether the Warframe is tradable. */
    private final boolean tradable;

    /** Indicates whether the Warframe is a Prime variant. */
    private final boolean prime;

    /** Indicates whether the Warframe is masterable. */
    private final boolean masterable;

    /** Indicates whether the Warframe is vaulted. */
    private final boolean vaulted;

    /** Indicates whether the Warframe is usable in Conclave. */
    private final boolean conclave;

    /** The mastery rank requirement for the Warframe. */
    private final Number masteryReq;

    /** The release date of the Warframe. */
    private final LocalDate releaseDate;

    /** The estimated vault date of the Warframe. */
    private final LocalDate estimatedVaultDate;

    /** The date at which the warframe went vaulted. */
    private final LocalDate vaultDate;

    /** The name of the image associated with the Warframe. */
    private final String imageName;

    /** The URL to the Warframe's Wikia page. */
    private final String wikiaUrl;

    /** The color code of the Warframe. */
    private final Number color;

    /** The sprint value of the Warframe. */
    private final Number sprint;

    /** The sprint speed of the Warframe. */
    private final Number sprintSpeed;

    /** The stamina value of the Warframe. */
    private final Number stamina;

    /** The shield value of the Warframe. */
    private final Number shield;

    /** The health value of the Warframe. */
    private final Number health;

    /** The armor value of the Warframe. */
    private final Number armor;

    /** The power value of the Warframe. */
    private final Number power;

    /** The aura polarity of the Warframe. */
    private final String aura;

    /** The list of polarities associated with the Warframe. */
    private final List<String> polarities;

    /** The list of exalted weapons associated with the Warframe. */
    private final List<String> exalted;

    /** The list of abilities of the Warframe. */
    private final List<Ability> abilities;

    /** The list of patchlogs associated with the Warframe. */
    private final List<Patchlog> patchlogs;

    /** The list of components associated with the Warframe. */
    private final List<AltComponent> components;

    /** Information about when the Warframe was introduced. */
    private final Introduced introduced;

    /** The blueprint cost of the Warframe. */
    private final Number bpCost;

    /** The build price of the Warframe. */
    private final Number buildPrice;

    /** The build time of the Warframe. */
    private final Number buildTime;

    /** The market cost of the Warframe. */
    private final Number marketCost;

    /** The rush cost to reduce the build time of the Warframe. */
    private final Number skipBuildTimePrice;

    /**
     * Deserializes a JSON object into an instance of {@link Warframe}.
     *
     * @param object The JSON object containing Warframe data.
     * @return An instance of {@link Warframe} populated with data from the JSON object.
     */
    public static Warframe deserialize(JSONObject object) {
        String name = object.optString("name", null);
        String uniqueName = object.optString("uniqueName", null);
        String description = object.optString("description", null);
        String passiveDescription = object.optString("passiveDescription", null);
        String type = object.optString("type", null);
        String sex = object.optString("sex", null);
        String category = object.optString("category", null);
        String productCategory = object.optString("productCategory", null);
        boolean tradable = object.optBoolean("tradable", false);
        boolean prime = object.optBoolean("isPrime", false);
        boolean masterable = object.optBoolean("masterable", false);
        boolean vaulted = object.optBoolean("vaulted", false);
        boolean conclave = object.optBoolean("conclave", false);
        Number masteryReq = object.optNumber("masteryReq", -1);
        LocalDate releaseDate = object.has("releaseDate") ? LocalDate.parse(object.getString("releaseDate")) : null;
        LocalDate estimatedVaultDate = object.has("estimatedVaultDate") ? LocalDate.parse(object.getString("estimatedVaultDate")) : null;
        LocalDate vaultDate = object.has("vaultDate") ? LocalDate.parse(object.getString("vaultDate")) : null;
        String imageName = object.optString("imageName", null);
        String wikiaUrl = object.optString("wikiaUrl", null);
        Number color = object.optNumber("color", -1);
        Number sprint = object.optNumber("sprint", -1);
        Number sprintSpeed = object.optNumber("sprintSpeed", -1);
        Number stamina = object.optNumber("stamina", -1);
        Number shield = object.optNumber("shield", -1);
        Number health = object.optNumber("health", -1);
        Number armor = object.optNumber("armor", -1);
        Number power = object.optNumber("power", -1);
        String aura = object.optString("aura", null);
        List<String> polarities = object.has("polarities") ? object.getJSONArray("polarities").toList().stream()
                .map(Object::toString).collect(Collectors.toList()) : new ArrayList<>();
        List<String> exalted = object.has("exalted") ? object.getJSONArray("exalted").toList().stream()
                .map(Object::toString).collect(Collectors.toList()) : new ArrayList<>();
        List<Ability> abilities = new ArrayList<>();
        if (object.has("abilities")) {
            for (Object abilityObj : object.getJSONArray("abilities")) {
                abilities.add(Ability.deserialize((JSONObject) abilityObj));
            }
        }
        List<Patchlog> patchlogs = new ArrayList<>();
        if (object.has("patchlogs")) {
            for (Object patchlogObj : object.getJSONArray("patchlogs")) {
                patchlogs.add(Patchlog.deserialize((JSONObject) patchlogObj));
            }
        }
        List<AltComponent> components = new ArrayList<>();
        if (object.has("components")) {
            for (Object componentObj : object.getJSONArray("components")) {
                components.add(AltComponent.deserialize((JSONObject) componentObj));
            }
        }
        Introduced introduced = Introduced.deserialize(object.optJSONObject("introduced", null));
        Number bpCost = object.optNumber("bpCost", -1);
        Number buildPrice = object.optNumber("buildPrice", -1);
        Number buildTime = object.optNumber("buildTime", -1);
        Number marketCost = object.optNumber("marketCost", -1);
        Number skipBuildTimePrice = object.optNumber("skipBuildTimePrice", -1);

        return new Warframe(name, uniqueName, description, passiveDescription, type, sex, category, productCategory,
                tradable, prime, masterable, vaulted, conclave, masteryReq, releaseDate, estimatedVaultDate, vaultDate, imageName, wikiaUrl,
                color, sprint, sprintSpeed, stamina, shield, health, armor, power, aura, polarities, exalted,
                abilities, patchlogs, components, introduced, bpCost, buildPrice, buildTime, marketCost,
                skipBuildTimePrice);
    }

    /**
     * Serializes this {@link Warframe} instance into a JSON object.
     *
     * @return A {@link JSONObject} representation of this {@link Warframe}.
     */
    @Override
    public JSONObject serialize() {
        JSONObject json = new JSONObject();
        json.put("name", name != null ? name : JSONObject.NULL);
        json.put("uniqueName", uniqueName != null ? uniqueName : JSONObject.NULL);
        json.put("description", description != null ? description : JSONObject.NULL);
        json.put("passiveDescription", passiveDescription != null ? passiveDescription : JSONObject.NULL);
        json.put("type", type != null ? type : JSONObject.NULL);
        json.put("sex", sex != null ? sex : JSONObject.NULL);
        json.put("category", category != null ? category : JSONObject.NULL);
        json.put("productCategory", productCategory != null ? productCategory : JSONObject.NULL);
        json.put("tradable", tradable);
        json.put("isPrime", prime);
        json.put("masterable", masterable);
        json.put("vaulted", vaulted);
        json.put("conclave", conclave);
        json.put("masteryReq", masteryReq != null ? masteryReq : JSONObject.NULL);
        json.put("releaseDate", releaseDate != null ? releaseDate.toString() : JSONObject.NULL);
        json.put("estimatedVaultDate", estimatedVaultDate != null ? estimatedVaultDate.toString() : JSONObject.NULL);
        json.put("vaultDate", vaultDate != null ? vaultDate.toString() : JSONObject.NULL);
        json.put("imageName", imageName != null ? imageName : JSONObject.NULL);
        json.put("wikiaUrl", wikiaUrl != null ? wikiaUrl : JSONObject.NULL);
        json.put("color", color != null ? color : JSONObject.NULL);
        json.put("sprint", sprint != null ? sprint : JSONObject.NULL);
        json.put("sprintSpeed", sprintSpeed != null ? sprintSpeed : JSONObject.NULL);
        json.put("stamina", stamina != null ? stamina : JSONObject.NULL);
        json.put("shield", shield != null ? shield : JSONObject.NULL);
        json.put("health", health != null ? health : JSONObject.NULL);
        json.put("armor", armor != null ? armor : JSONObject.NULL);
        json.put("power", power != null ? power : JSONObject.NULL);
        json.put("aura", aura != null ? aura : JSONObject.NULL);
        json.put("polarities", polarities != null ? polarities : new ArrayList<>());
        json.put("exalted", exalted != null ? exalted : new ArrayList<>());
        if (abilities != null) {
            json.put("abilities", abilities.stream().map(Ability::serialize).collect(Collectors.toList()));
        }
        if (patchlogs != null) {
            json.put("patchlogs", patchlogs.stream().map(Patchlog::serialize).collect(Collectors.toList()));
        }
        if (components != null) {
            json.put("components", components.stream().map(AltComponent::serialize).collect(Collectors.toList()));
        }
        if (introduced != null) {
            json.put("introduced", introduced.serialize());
        }
        json.put("bpCost", bpCost != null ? bpCost : JSONObject.NULL);
        json.put("buildPrice", buildPrice != null ? buildPrice : JSONObject.NULL);
        json.put("buildTime", buildTime != null ? buildTime : JSONObject.NULL);
        json.put("marketCost", marketCost != null ? marketCost : JSONObject.NULL);
        json.put("skipBuildTimePrice", skipBuildTimePrice != null ? skipBuildTimePrice : JSONObject.NULL);

        return json;
    }

    /**
     * Sends a request to retrieve a Warframe based on a search query.
     *
     * @param query The search query for the Warframe.
     * @return An instance of {@link Warframe} matching the search query.
     */
    public static Warframe request(String query) {
        return Requests.withDirectMapping(Warframe.class, String.format(Paths.CLOSEST_WARFRAME, Encoder.encode(query)));
    }

    /**
     * Sends a request to retrieve a list of Warframes based on a search query.
     *
     * @param query The search query for the Warframes.
     * @return A list of {@link Warframe} objects matching the search query.
     */
    public static List<Warframe> requestAll(String query) {
        return Requests.withListMapping(Warframe.class, String.format(Paths.SEARCH_CLOSEST_WARFRAMES, Encoder.encode(query)));
    }

}