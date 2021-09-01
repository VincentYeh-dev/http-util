package org.vincentyeh.http_util.net.framework.downloader;

import org.vincentyeh.http_util.net.framework.connection.HttpConnection;
import org.vincentyeh.http_util.net.framework.downloader.exception.NoSpecifyNetUtil;
import org.vincentyeh.http_util.net.framework.downloader.listener.URLDownloaderListener;
import org.vincentyeh.http_util.net.framework.utils.HttpClientUtil;
import org.vincentyeh.http_util.net.framework.utils.LengthNotFound;

import java.io.*;
import java.math.BigDecimal;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;

public abstract class URLDownloader<RESULT> implements Callable<RESULT> {
    private static HttpClientUtil httpUtil;

    public static void warpHttpClientUtil(HttpClientUtil util) {
        httpUtil = util;
    }

    private URLDownloaderListener listener;
    protected final URL url;
    private final Map<String, String> headers;
    private final int timeoutMillis;
    private BigDecimal totalBytes = new BigDecimal(0);

    protected abstract void handleInputStream(InputStream inputStream, URLDownloaderListener listener) throws Exception;

    protected abstract RESULT getResult();

    public abstract BigDecimal getDownloadedBytes();

    public URLDownloader(URL url, int timeoutMillis) {
        this.url = url;
        this.timeoutMillis = timeoutMillis;
        this.headers = new HashMap<>();
        headers.put("User-Agent",
                "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/78.0.3904.108 Safari/537.36");
    }


    @Override
    public final RESULT call() throws Exception {

        if (httpUtil == null)
            throw new NoSpecifyNetUtil("Specify util before starting.");

        if (listener != null)
            listener.start(this);

        HttpConnection connection = httpUtil.openConnection(url, headers, timeoutMillis);

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

    public final RESULT get() throws Exception{
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
