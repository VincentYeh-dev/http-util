package org.vincentyeh.http_util.net.client.framework.header;

import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;

import static java.lang.String.format;

public class RequestHeaders {
    public static String CONNECTION_KEEP_ALIVE = "keep-alive";
    public static String CONNECTION_CLOSE = "close";

    private final Map<String, Object> map;

    public RequestHeaders() {
        this.map = new HashMap<>();
    }

    public void setUserAgent(String agent) {
        put("User-Agent", agent);
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
        put("Referer", referer);
    }

    public void setConnection(String connection) {
        put("Connection", connection);
    }

    public void setCookies(Cookies cookies) {
        put("Cookie", cookies);
    }

    public void setAuthorization(String type, String credentials) {
        Base64.Encoder encoder = Base64.getEncoder();
        String encoded = encoder.encodeToString(credentials.getBytes(StandardCharsets.UTF_8));
        put("Authorization", format("%s %s", type, encoded));
    }

    private void put(String key, Object object) {
        map.put(key, object);
    }

    public void put(String key, String string) {
        map.put(key, string);
    }

    public String get(String key) {
        Object value = map.get(key);
        if (value instanceof List)
            return listToString((List<?>) value);
        else if (value instanceof String)
            return (String) value;
        else
            return value.toString();
    }


    public Set<String> getKeys() {
        return map.keySet();
    }

    private String listToString(List<?> list) {
        return list.stream().map(Object::toString).collect(Collectors.joining(","));
    }
}
