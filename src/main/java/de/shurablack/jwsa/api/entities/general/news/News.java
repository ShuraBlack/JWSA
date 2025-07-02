package de.shurablack.jwsa.api.entities.general.news;

import de.shurablack.jwsa.api.requests.Paths;
import de.shurablack.jwsa.api.requests.Requests;
import de.shurablack.jwsa.api.utils.ServerOffsetTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.json.JSONObject;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Represents a news item with various attributes such as ID, date, image link, and more.
 */
@AllArgsConstructor
@Getter
public class News {

    /** The unique identifier of the news item. */
    private final String id;

    /** The date and time when the news item was published. */
    private final LocalDateTime date;

    /** The URL of the image associated with the news item. */
    private final String imageLink;

    /** The estimated time of arrival (ETA) for the news item. */
    private final String eta;

    /** Indicates whether the news item is related to prime access. */
    private final boolean primeAccess;

    /** Indicates whether the news item is related to a stream. */
    private final boolean stream;

    /** The URL link to the full news item. */
    private final String link;

    /** Indicates whether the news item is an update. */
    private final boolean update;

    /** The string representation of the news item. */
    private final String asString;

    /** The message content of the news item. */
    private final String message;

    /** Indicates whether the news item has priority. */
    private final boolean priority;

    /**
     * Creates a News object from a JSON representation.
     *
     * @param object The JSON object containing the news data.
     * @return A News object populated with data from the JSON object.
     */
    public static News fromJSON(JSONObject object) {
        String id = object.optString("id", null);
        LocalDateTime date = ServerOffsetTime.of(object.optString("date", null));
        String imageLink = object.optString("imageLink", null);
        String eta = object.optString("eta", null);
        boolean primeAccess = object.optBoolean("primeAccess", false);
        boolean stream = object.optBoolean("stream", false);
        String link = object.optString("link", null);
        boolean update = object.optBoolean("update", false);
        String asString = object.optString("asString", null);
        String message = object.optString("message", null);
        boolean priority = object.optBoolean("priority", false);

        return new News(id, date, imageLink, eta, primeAccess, stream, link, update, asString, message, priority);
    }

    /**
     * Requests a list of news items from the server and filters out items older than 90 days.
     *
     * @return A list of News objects representing the latest news items.
     */
    public static List<News> request() {
        List<News> newsList = Requests.withListMapping(News.class, Paths.NEWS);

        final LocalDateTime ninetyDaysAgo = LocalDateTime.now().minusDays(90);
        newsList.removeIf(news -> news.getDate().isBefore(ninetyDaysAgo));
        newsList.sort((news1, news2) -> news2.getDate().compareTo(news1.getDate()));

        return newsList;
    }
}