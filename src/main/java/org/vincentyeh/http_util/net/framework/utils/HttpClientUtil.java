package org.vincentyeh.http_util.net.framework.utils;


import org.vincentyeh.http_util.net.framework.connection.HttpConnection;

import java.net.Proxy;
import java.net.URL;
import java.util.Map;

public interface HttpClientUtil {
    HttpConnection openConnection(URL url, Map<String, String> headers, int timeoutMillisecond) throws Exception;
    HttpConnection openWithProxyConnection(URL url, Map<String, String> headers, int timeoutMillisecond, Proxy proxy) throws Exception;
}
