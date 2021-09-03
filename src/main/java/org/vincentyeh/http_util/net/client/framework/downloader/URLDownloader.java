package org.vincentyeh.http_util.net.client.framework.downloader;

import org.vincentyeh.http_util.net.client.framework.connection.Response;
import org.vincentyeh.http_util.net.client.framework.header.RequestHeaders;
import org.vincentyeh.http_util.net.client.framework.downloader.exception.NoSpecifyNetUtil;
import org.vincentyeh.http_util.net.client.framework.downloader.listener.URLDownloaderListener;
import org.vincentyeh.http_util.net.client.framework.utils.HttpClientUtil;

import java.io.*;
import java.math.BigDecimal;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.util.concurrent.Callable;

public abstract class URLDownloader<RESULT> implements Callable<RESULT> {
    private static HttpClientUtil httpUtil;


    public static void warpHttpClientUtil(HttpClientUtil util) {
        httpUtil = util;
    }

    private URLDownloaderListener listener;
    protected final URL url;
    private final RequestHeaders headers;
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

    public URLDownloader(URL url, int timeoutMillis, RequestHeaders headers) {
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

        Response response = httpUtil.get(url, headers, timeoutMillis, null);
//        Session session = httpUtil.post(url, headers, timeoutMillis, "aaaffffff".getBytes(StandardCharsets.UTF_8), null);

        System.out.println("response code:" + response.getCode());
        System.out.println("header:");
        response.getHeader().forEach((key, value) -> System.out.printf("\t- %s:%s\n", key, value));

        totalBytes = new BigDecimal(response.getContentLength());

        try {
            InputStream urlInputStream = response.getBodyInputStream();
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
            response.close();
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
