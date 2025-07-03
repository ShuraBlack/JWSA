package de.shurablack.jwsa.api.requests.cache;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * A utility class for caching HTTP response data associated with requested URLs.
 * Provides methods to store, retrieve, and update cached responses.
 */
public class ResponseCache implements Serializable {

    private static final long serialVersionUID = -1121615555295222053L;

    /**
     * A thread-safe map that stores the requested URL as the key and its associated
     * response data along with the last access time as the value.
     */
    private final Map<String, CacheEntry> cache = new ConcurrentHashMap<>();

    /**
     * Retrieves the cached response data for the specified URL.
     *
     * @param url The URL whose cached response data is to be retrieved.
     * @return The {@link CacheEntry} containing the response data and last access time,
     *         or null if no entry exists for the given URL.
     */
    public CacheEntry get(final String url) {
        return cache.get(url);
    }

    /**
     * Stores the response data for the specified URL in the cache.
     * If an entry already exists for the URL, it will be replaced.
     *
     * @param url  The URL whose response data is to be cached.
     * @param data The response data to be cached.
     * @param eTag The ETag value associated with the response data.
     */
    public void put(final String url, final String data, final String eTag) {
        cache.put(url, new CacheEntry(eTag, data));
    }

    /**
     * Updates the cached response data for the specified URL.
     * If an entry exists for the URL, its data and last access time are updated.
     * If no entry exists, a new one is created.
     *
     * @param url  The URL whose cached response data is to be updated.
     * @param data The new response data to be cached.
     * @param eTag The new ETag value to be cached.
     */
    public void update(final String url, final String data, final String eTag) {
        CacheEntry entry = cache.get(url);
        if (entry != null) {
            entry.update(data, eTag);
        } else {
            put(url, data, eTag);
        }
    }

    /**
     * Represents a single cache entry containing the response data and the last access time.
     */
    @AllArgsConstructor
    @Getter
    public static class CacheEntry {

        /** The timestamp of the last access or update to this cache entry. */
        private String eTag;

        /** The cached response data. */
        private String data;

        /**
         * Updates the response data and refreshes the last access time for this cache entry.
         *
         * @param data The new response data to be stored in this cache entry.
         * @param eTag The new ETag value to be stored in this cache entry.
         */
        public void update(String data, String eTag) {
            this.data = data;
            this.eTag = eTag;
        }
    }
}