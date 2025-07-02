package de.shurablack.jwsa.api.requests;

import de.shurablack.jwsa.api.exception.ResponseException;
import de.shurablack.jwsa.api.requests.cache.ResponseCache;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

/**
 * Handles HTTP requests and responses, providing a singleton instance for making GET requests.
 */
public class HTTPRequester {

    /** Logger instance for logging HTTP request-related events. */
    private static final Logger LOGGER = LogManager.getLogger(HTTPRequester.class);

    /** Singleton instance of the HTTPRequester. */
    private static HTTPRequester instance;

    /** The HttpClient used for sending HTTP requests. */
    private final HttpClient client;

    /** Cache for storing responses to avoid redundant requests. */
    private final ResponseCache responseCache = new ResponseCache();

    /**
     * Retrieves the singleton instance of the HTTPRequester.
     * If the instance does not exist, it is created.
     *
     * @return The singleton instance of HTTPRequester.
     */
    public static HTTPRequester get() {
        if (instance == null) {
            instance = new HTTPRequester();
        }
        return instance;
    }

    /**
     * Private constructor to initialize the HttpClient.
     * Ensures that the class follows the singleton pattern.
     */
    private HTTPRequester() {
        this.client = HttpClient.newBuilder()
                .build();
    }

    /**
     * Sends an HTTP GET request to the specified URL and returns the response body as a string.
     * If the response status code is not 200, a ResponseException is thrown.
     * Logs any errors that occur during the request.
     *
     * @param url The URL to send the GET request to.
     * @return The response body as a string, or null if an error occurs.
     */
    public String request(String url) {
        try {
            final ResponseCache.CacheEntry cached = responseCache.get(url);

            HttpRequest.Builder builder = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .GET();

            if (cached != null) {
                builder.header("If-None-Match", cached.getETag());
            }

            HttpRequest request = builder.build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 304) {
                LOGGER.debug("Using cached response for URL: {}", url);
                return cached.getData();
            } else if (response.statusCode() == 200) {
                LOGGER.debug("Received response for URL: {}", url);
                responseCache.update(url, response.body(), response.headers().firstValue("ETag").orElse(null));
            } else {
                throw new ResponseException(response.statusCode(), response.body());
            }

            return response.body();
        } catch (Exception e) {
            LOGGER.error("Error during HTTP request to {}: {}", url, e.getMessage(), e);
            return null;
        }
    }
}