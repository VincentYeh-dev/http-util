package org.vincentyeh.http_util.net.client.concrete.downloader.adaptor;

import org.vincentyeh.http_util.net.client.framework.downloader.adaptor.InputStreamAdaptor;
import org.vincentyeh.http_util.net.client.framework.utils.HttpClientUtil;

import java.net.Proxy;
import java.net.URL;
import java.util.List;
import java.util.Map;

public abstract class HttpInputStreamAdaptor extends InputStreamAdaptor {

    protected static HttpClientUtil httpUtil;
    public static void warpHttpClientUtil(HttpClientUtil util) {
        httpUtil = util;
    }

    public abstract URL getUrl();

    public static HttpInputStreamAdaptor createGetInputStreamAdaptor(URL url, Map<String, List<String>> headers, int timeoutMillis, Proxy proxy){
        return new GetInputStreamAdaptor(url, headers, timeoutMillis, proxy);
    }

    public static HttpInputStreamAdaptor createPostInputStreamAdaptor(URL url, Map<String, List<String>> headers, int timeoutMillis, byte[] body, Proxy proxy){
        return new PostInputStreamAdaptor(url, headers, timeoutMillis, body, proxy);
    }

}
