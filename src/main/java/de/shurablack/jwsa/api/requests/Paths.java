package de.shurablack.jwsa.api.requests;

/**
 * Contains the API endpoint paths for accessing various world state data in the Warframe API.
 * Each constant represents a specific endpoint with the base URL and query parameters included.
 */
public class Paths {

    /** The base URL for the Warframe API. */
    private static final String BASE_URL = "https://api.warframestat.us";

    /** The base URL for the PC world state. */
    private static final String WORLD_STATE = BASE_URL + "/pc/";

    /** Endpoint for retrieving the general world state. */
    public static final String GENERAL_WORLD_STATE = WORLD_STATE + "?language=en";

    /** Endpoint for retrieving active alerts. */
    public static final String ALERTS = WORLD_STATE + "alerts/?language=en";

    /** Endpoint for retrieving Archon Hunt data. */
    public static final String ARCHON = WORLD_STATE + "archonHunt/?language=en";

    /** Endpoint for retrieving Arbitration data. */
    public static final String ARBITRATION = WORLD_STATE + "arbitration/?language=en";

    /** Endpoint for retrieving Cambion Drift cycle data. */
    public static final String CAMBION = WORLD_STATE + "cambionCycle/?language=en";

    /** Endpoint for retrieving Cetus cycle data. */
    public static final String CETUS = WORLD_STATE + "cetusCycle/?language=en";

    /** Endpoint for retrieving Conclave challenges. */
    public static final String CONCLAVE_CHALLENGES = WORLD_STATE + "conclaveChallenges/?language=en";

    /** Endpoint for retrieving construction progress data. */
    public static final String CONSTRUCTION_PROGRESS = WORLD_STATE + "constructionProgress/?language=en";

    /** Endpoint for retrieving daily Darvo deals. */
    public static final String DAILY_DARVO_DEALS = WORLD_STATE + "dailyDeals/?language=en";

    /** Endpoint for retrieving Deep Archimedea data. */
    public static final String DEEP_ARCHIMEDEA = WORLD_STATE + "deepArchimedea/?language=en";

    /** Endpoint for retrieving Earth cycle data. */
    public static final String EARTH_CYCLE = WORLD_STATE + "earthCycle/?language=en";

    /** Endpoint for retrieving active events. */
    public static final String EVENTS = WORLD_STATE + "events/?language=en";

    /** Endpoint for retrieving fissure data. */
    public static final String FISSURES = WORLD_STATE + "fissures/?language=en";

    /** Endpoint for retrieving flash sales. */
    public static final String FLASH_SALES = WORLD_STATE + "flashSales/?language=en";

    /** Endpoint for retrieving global upgrades. */
    public static final String GLOBAL_UPGRADES = WORLD_STATE + "globalUpgrades/?language=en";

    /** Endpoint for retrieving invasion data. */
    public static final String INVASIONS = WORLD_STATE + "invasions/?language=en";

    /** Endpoint for retrieving Kuva missions. */
    public static final String KUVA_MISSION = WORLD_STATE + "kuva/?language=en";

    /** Endpoint for retrieving news. */
    public static final String NEWS = WORLD_STATE + "news/?language=en";

    /** Endpoint for retrieving Nightwave data. */
    public static final String NIGHTWAVE = WORLD_STATE + "nightwave/?language=en";

    /** Endpoint for retrieving Sanctuary Target data. */
    public static final String SANCTUARY_TARGET = WORLD_STATE + "simaris/?language=en";

    /** Endpoint for retrieving sortie data. */
    public static final String SORTIES = WORLD_STATE + "sortie/?language=en";

    /** Endpoint for retrieving Steel Path data. */
    public static final String STEEL_PATH = WORLD_STATE + "steelPath/?language=en";

    /** Endpoint for retrieving syndicate missions. */
    public static final String SYNDICATE = WORLD_STATE + "syndicateMissions/?language=en";

    /** Endpoint for retrieving the current timestamp. */
    public static final String TIMESTAMP = WORLD_STATE + "timestamp/?language=en";

    /** Endpoint for retrieving Orb Vallis cycle data. */
    public static final String ORB_VALLIS = WORLD_STATE + "vallisCycle/?language=en";

    /** Endpoint for retrieving Vault Trader data. */
    public static final String VAULT_TRADER = WORLD_STATE + "vaultTrader/?language=en";

    /** Endpoint for retrieving Void Trader data. */
    public static final String VOID_TRADER = WORLD_STATE + "voidTrader/?language=en";
}