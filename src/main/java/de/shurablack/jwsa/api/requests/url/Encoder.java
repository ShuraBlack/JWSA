package de.shurablack.jwsa.api.requests.url;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class Encoder {

    public static String encode(String value) {
        return URLEncoder.encode(value, StandardCharsets.UTF_8).replaceAll("\\+", "%20");
    }

}
