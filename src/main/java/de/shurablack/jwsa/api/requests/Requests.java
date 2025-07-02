package de.shurablack.jwsa.api.requests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Provides utility methods for making HTTP requests and mapping responses to objects or lists of objects.
 */
public class Requests {

    /** Logger instance for logging request-related events and errors. */
    private static final Logger LOGGER = LogManager.getLogger(Requests.class);

    /**
     * Sends an HTTP request to the specified URL and maps the response directly to an object of the specified class.
     * The class must have a static `fromJSON` method to parse the JSON response.
     *
     * @param <T> The type of the object to map the response to.
     * @param cls The class of the object to map the response to.
     * @param url The URL to send the request to.
     * @return An instance of the specified class, or null if an error occurs or the response is null.
     */
    public static <T> T withDirectMapping(Class<T> cls, String url) {
        String raw = HTTPRequester.get().request(url);
        if (raw == null) {
            return null;
        }
        JSONObject json = new JSONObject(raw);
        try {
            return (T) cls.getDeclaredMethod("fromJSON", JSONObject.class).invoke(null, json);
        } catch (Exception e) {
            LOGGER.error("Error while parsing response for class: " + cls.getSimpleName(), e);
            return null;
        }
    }

    /**
     * Sends an HTTP request to the specified URL and maps the response to a list of objects of the specified class.
     * The class must have a static `fromJSON` method to parse each JSON object in the response array.
     *
     * @param <T> The type of the objects in the list.
     * @param cls The class of the objects to map the response to.
     * @param url The URL to send the request to.
     * @return A list of objects of the specified class, or an empty list if an error occurs or the response is null.
     */
    public static <T> List<T> withListMapping(Class<T> cls, String url) {
        String raw = HTTPRequester.get().request(url);
        if (raw == null) {
            return Collections.emptyList();
        }
        JSONArray data = new JSONArray(raw);
        List<T> list = new ArrayList<>();
        try {
            for (int i = 0; i < data.length(); i++) {
                JSONObject json = data.getJSONObject(i);
                T item = (T) cls.getDeclaredMethod("fromJSON", JSONObject.class).invoke(null, json);
                list.add(item);
            }
        } catch (Exception e) {
            LOGGER.error("Error while parsing response for class: " + cls.getSimpleName(), e);
        }
        return list;
    }

    /**
     * Sends an HTTP request to the specified URL and retrieves the raw response as a string.
     * Logs a warning if the response is null.
     *
     * @param url The URL to send the request to.
     * @return The raw response as a string, or null if an error occurs or the response is null.
     */
    public static String raw(String url) {
        String raw = HTTPRequester.get().request(url);
        if (raw == null) {
            LOGGER.warn("Received null response for URL: " + url);
            return null;
        }
        return raw;
    }
}