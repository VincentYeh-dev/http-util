package org.vincentyeh.http_util.net.client.concrete.utils;

import org.vincentyeh.http_util.net.client.framework.connection.HttpConnection;
import org.vincentyeh.http_util.net.client.framework.header.RequestHeaders;
import org.vincentyeh.http_util.net.client.framework.utils.HttpClientUtil;
import java.net.*;

public class LocalProxyHttpClientUtil implements HttpClientUtil {

    private static final Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("127.0.0.1", 8080));

    private final StandardHttpClientUtil util = new StandardHttpClientUtil();

    @Override
    public HttpConnection get(URL url, RequestHeaders headers, int timeoutMillisecond, Proxy proxy) throws Exception {
        return util.get(url, headers, timeoutMillisecond, LocalProxyHttpClientUtil.proxy);
    }

    @Override
    public HttpConnection post(URL url, RequestHeaders headers, int timeoutMillisecond, byte[] body, Proxy proxy) throws Exception {
        return util.post(url, headers, timeoutMillisecond, body, LocalProxyHttpClientUtil.proxy);
    }


}
