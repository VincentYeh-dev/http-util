package org.vincentyeh.http_util.net.client.framework.downloader.adaptor;

import org.vincentyeh.http_util.net.client.framework.utils.HttpClientUtil;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public abstract class HttpInputStreamAdaptor extends InputStream {

    protected static HttpClientUtil httpUtil;
    public static void warpHttpClientUtil(HttpClientUtil util) {
        httpUtil = util;
    }

    public abstract long getContentLength();

    public abstract void close();

    public abstract InputStream getInputStream() throws IOException;

    public abstract URL getUrl();

    @Override
    public final int read() throws IOException {
        return getInputStream().read();
    }
}
