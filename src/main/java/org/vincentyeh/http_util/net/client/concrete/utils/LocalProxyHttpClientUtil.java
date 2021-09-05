package org.vincentyeh.http_util.net.client.concrete.utils;

import org.vincentyeh.http_util.net.client.framework.connection.Response;

import java.net.*;
import java.util.List;
import java.util.Map;

public class LocalProxyHttpClientUtil extends StandardHttpClientUtil{

    private static final Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("127.0.0.1", 8080));

    @Override
    public Response get(URL url, Map<String, List<String>> headers, int timeoutMillisecond, Proxy ignored) throws Exception {
        return super.get(url, headers, timeoutMillisecond, LocalProxyHttpClientUtil.proxy);
    }

    @Override
    public Response post(URL url, Map<String, List<String>> headers, int timeoutMillisecond, byte[] body, Proxy ignored) throws Exception {
        return super.post(url, headers, timeoutMillisecond, body, LocalProxyHttpClientUtil.proxy);
    }
}
