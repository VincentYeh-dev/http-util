package org.vincentyeh.http_util.net.client.framework.downloader;

import org.vincentyeh.http_util.net.client.framework.connection.HttpConnection;
import org.vincentyeh.http_util.net.client.framework.connection.header.Headers;
import org.vincentyeh.http_util.net.client.framework.downloader.exception.NoSpecifyNetUtil;
import org.vincentyeh.http_util.net.client.framework.downloader.listener.URLDownloaderListener;
import org.vincentyeh.http_util.net.client.framework.utils.HttpClientUtil;
import org.vincentyeh.http_util.net.client.framework.utils.LengthNotFound;

import java.io.*;
import java.math.BigDecimal;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.Callable;

public abstract class URLDownloader<RESULT> implements Callable<RESULT> {
    private static HttpClientUtil httpUtil;


    public static void warpHttpClientUtil(HttpClientUtil util) {
        httpUtil = util;
    }

    private URLDownloaderListener listener;
    protected final URL url;
    private final Headers headers;
    private final int timeoutMillis;
    private BigDecimal totalBytes = new BigDecimal(0);


    protected abstract void handleInputStream(InputStream inputStream, URLDownloaderListener listener) throws Exception;

    protected abstract RESULT getResult();

    public abstract BigDecimal getDownloadedBytes();

    protected abstract void resetSubclass();

    private void reset() {
        totalBytes = new BigDecimal(0);
        resetSubclass();
    }

    public URLDownloader(URL url, int timeoutMillis, Headers headers) {
        this.url = url;
        this.timeoutMillis = timeoutMillis;
        this.headers = headers;
    }

    @Override
    public final RESULT call() throws Exception {
        reset();

        if (httpUtil == null)
            throw new NoSpecifyNetUtil("Specify util before starting.");

        if (listener != null)
            listener.start(this);

//        HttpConnection connection = httpUtil.post(url,headers, timeoutMillis,"abc".getBytes(StandardCharsets.UTF_8),null);
        HttpConnection connection = httpUtil.get(url,headers, timeoutMillis,null);
        try {
            totalBytes = connection.getLength();
        } catch (LengthNotFound e) {
            totalBytes = new BigDecimal(0);
        }

        try {
            InputStream urlInputStream = connection.getInputStream();
            handleInputStream(urlInputStream, listener);
            urlInputStream.close();
            return getResult();
        } catch (SocketTimeoutException | ConnectException e) {
            if (listener != null)
                listener.onTimeout(this, e);
            throw e;
        } catch (IOException e) {
            if (listener != null)
                listener.onIoException(this, e);
            throw e;
        } finally {
            connection.disconnect();
        }
    }

    public final RESULT get() throws Exception {
        return call();
    }

    public BigDecimal getTotalBytes() {
        return totalBytes;
    }

    public URL getUrl() {
        return url;
    }

    public void setListener(URLDownloaderListener listener) {
        this.listener = listener;
    }
}
