package org.vincentyeh.http_util.net.framework.utils;


import org.vincentyeh.http_util.net.framework.connection.HttpConnection;

import java.io.IOException;
import java.net.Proxy;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

public interface HttpUtil {
    HttpConnection openConnection(URL url, Map<String, String> headers, int timeoutMillisecond) throws IOException, NoSuchAlgorithmException, KeyManagementException;
    HttpConnection openWithProxyConnection(URL url, Map<String, String> headers, int timeoutMillisecond, Proxy proxy) throws IOException, NoSuchAlgorithmException, KeyManagementException;
}
