package org.vincentyeh.http_util.net.client.framework.downloader;

import org.vincentyeh.http_util.net.client.framework.downloader.adaptor.HttpInputStreamAdaptor;
import org.vincentyeh.http_util.net.client.framework.downloader.listener.URLDownloaderListener;

import java.io.*;
import java.math.BigDecimal;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.util.concurrent.Callable;

public abstract class URLDownloader<RESULT> implements Callable<RESULT> {
    private URLDownloaderListener listener;
    private final HttpInputStreamAdaptor adaptor;
    private BigDecimal totalBytes = new BigDecimal(0);


    protected abstract void handleInputStream(InputStream inputStream, URLDownloaderListener listener) throws Exception;

    protected abstract RESULT getResult();

    public abstract BigDecimal getDownloadedBytes();

    protected abstract void resetSubclass();

    private void reset() {
        totalBytes = new BigDecimal(0);
        resetSubclass();
    }

    public URLDownloader(HttpInputStreamAdaptor adaptor) {
        this.adaptor = adaptor;
    }

    @Override
    public final RESULT call() throws Exception {
        reset();

        if (listener != null)
            listener.start(this);
//        Response response = httpUtil.post(url, headers, timeoutMillis, "aaaffffff".getBytes(StandardCharsets.UTF_8), null);

//        System.out.println("response code:" + response.getCode());
//        System.out.println("header:");
//        response.getHeader().forEach((key, value) -> System.out.printf("\t- %s:%s\n", key, value));

        totalBytes = new BigDecimal(adaptor.getContentLength());

        try {
            InputStream urlInputStream = adaptor.getInputStream();
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
            adaptor.close();
        }
    }

    public final RESULT get() throws Exception {
        return call();
    }

    public final BigDecimal getTotalBytes() {
        return totalBytes;
    }

    public final URL getUrl() {
        return adaptor.getUrl();
    }

    public final void setListener(URLDownloaderListener listener) {
        this.listener = listener;
    }
}
