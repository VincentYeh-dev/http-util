package org.vincentyeh.http_util.net.client.framework.downloader.adaptor;

import org.vincentyeh.http_util.net.client.framework.utils.HttpClientUtil;

import java.net.URL;

public abstract class HttpInputStreamAdaptor extends InputStreamAdaptor{

    protected static HttpClientUtil httpUtil;
    public static void warpHttpClientUtil(HttpClientUtil util) {
        httpUtil = util;
    }

    public abstract URL getUrl();

}
