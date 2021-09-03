package org.vincentyeh.http_util.net.client.framework.utils;


import org.vincentyeh.http_util.net.client.framework.connection.Session;

import java.net.Proxy;
import java.net.URL;
import java.util.List;
import java.util.Map;

public interface HttpClientUtil {
    Session get(URL url, Map<String, List<String>> headers, int timeoutMillisecond, Proxy proxy) throws Exception;

    Session post(URL url, Map<String, List<String>> headers, int timeoutMillisecond, byte[] body, Proxy proxy) throws Exception;

//    Session sendRequest(Request request, int timeoutMillisecond, byte[] body) throws Exception;
}
