package org.vincentyeh.http_util.net.client.framework.utils;


import org.vincentyeh.http_util.net.client.framework.connection.Response;

import java.net.Proxy;
import java.net.URL;
import java.util.List;
import java.util.Map;

public interface HttpClientUtil {
    Response get(URL url, Map<String, List<String>> headers, int timeoutMillisecond, Proxy proxy) throws Exception;

    Response post(URL url, Map<String, List<String>> headers, int timeoutMillisecond, byte[] body, Proxy proxy) throws Exception;

//    Session sendRequest(Request request, int timeoutMillisecond, byte[] body) throws Exception;
}
