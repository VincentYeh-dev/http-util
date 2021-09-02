package org.vincentyeh.http_util.net.client.framework.connection.header;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.lang.String.format;

public class Headers {
    public static String CONNECTION_KEEP_ALIVE = "keep-alive";
    public static String CONNECTION_CLOSE = "close";

    private final Map<String, Object> map;

    private Headers(Map<String, Object> map) {
        this.map = map;
    }

    public Headers() {
        this.map = new HashMap<>();
    }

    public void setUserAgent(String agent) {
        map.put("User-Agent", agent);
    }

    public void setAccept(List<String> accepts) {
        map.put("Accept", accepts);
    }

    public void setAcceptEncoding(List<String> encoding) {
        map.put("Accept-Encoding", encoding);
    }

    public void setAcceptLanguage(List<String> languages) {
        map.put("Accept-Language", languages);
    }

    public void setReferer(String referer) {
        map.put("Referer", referer);
    }

    public void setConnection(String connection) {
        map.put("Connection", connection);
    }

    public void setCookies(Cookies cookies){
        map.put("Cookie",cookies);
    }

    public void setAuthorization(String type,String credentials){
        Base64.Encoder encoder=Base64.getEncoder();
        String encoded=encoder.encodeToString(credentials.getBytes(StandardCharsets.UTF_8));
        map.put("Authorization",format("%s %s",type,encoded));

    }

    public Map<String, String> getMap() {

        Map<String, String> map = new HashMap<>();
        this.map.forEach((key, value) -> {
            if (value instanceof List) {
                map.put(key, listToString((List<?>) value));
            } else {
                map.put(key, value.toString());
            }
        });
        return map;
    }

    private String listToString(List<?> list) {
        return list.stream().map(Object::toString).collect(Collectors.joining(","));
    }

    public static Headers create(Map<String, Object> map) {
        return new Headers(map);
    }

}
