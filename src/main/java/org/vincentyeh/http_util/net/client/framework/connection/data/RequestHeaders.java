package org.vincentyeh.http_util.net.client.framework.connection.data;

import java.nio.charset.StandardCharsets;
import java.util.*;

import static java.lang.String.format;

public class RequestHeaders extends HashMap<String, List<String>> {
    public static String CONNECTION_KEEP_ALIVE = "keep-alive";
    public static String CONNECTION_CLOSE = "close";

    public void setUserAgent(String agent) {
        put("User-Agent", single(agent));
    }

    public void setAccept(List<String> accepts) {
        put("Accept", accepts);
    }

    public void setAcceptEncoding(List<String> encoding) {
        put("Accept-Encoding", encoding);
    }

    public void setAcceptLanguage(List<String> languages) {
        put("Accept-Language", languages);
    }

    public void setReferer(String referer) {
        put("Referer", single(referer));
    }

    public void setConnection(String connection) {
        put("Connection", single(connection));
    }

    public void setCookies(Cookies cookies) {
        put("Cookie", single(cookies.toString()));
    }

    public void setAuthorization(String type, String credentials) {
        Base64.Encoder encoder = Base64.getEncoder();
        String encoded = encoder.encodeToString(credentials.getBytes(StandardCharsets.UTF_8));
        put("Authorization", single(format("%s %s", type, encoded)));
    }

    private List<String> single(String object) {
        List<String> list = new ArrayList<>();
        list.add(object);
        return list;
    }
}
