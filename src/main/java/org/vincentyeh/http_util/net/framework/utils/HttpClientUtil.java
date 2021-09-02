package org.vincentyeh.http_util.net.framework.utils;


import org.vincentyeh.http_util.net.framework.connection.HttpConnection;
import org.vincentyeh.http_util.net.framework.connection.header.Headers;

import java.net.Proxy;
import java.net.URL;

public interface HttpClientUtil {
    HttpConnection get(URL url, Headers headers, int timeoutMillisecond, Proxy proxy) throws Exception;

    HttpConnection post(URL url, Headers headers, int timeoutMillisecond,byte[] body, Proxy proxy) throws Exception;

}
