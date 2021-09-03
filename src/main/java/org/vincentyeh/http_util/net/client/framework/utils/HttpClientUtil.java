package org.vincentyeh.http_util.net.client.framework.utils;


import org.vincentyeh.http_util.net.client.framework.connection.HttpConnection;
import org.vincentyeh.http_util.net.client.framework.header.RequestHeaders;

import java.net.Proxy;
import java.net.URL;

public interface HttpClientUtil {
    HttpConnection get(URL url, RequestHeaders headers, int timeoutMillisecond, Proxy proxy) throws Exception;

    HttpConnection post(URL url, RequestHeaders headers, int timeoutMillisecond, byte[] body, Proxy proxy) throws Exception;

}
