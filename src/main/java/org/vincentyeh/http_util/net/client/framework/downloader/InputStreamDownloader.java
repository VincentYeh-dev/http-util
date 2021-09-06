package org.vincentyeh.http_util.net.client.framework.downloader;

import org.vincentyeh.http_util.net.client.framework.downloader.adaptor.InputStreamAdaptor;
import org.vincentyeh.http_util.net.client.framework.downloader.listener.DownloaderListener;

import java.io.*;
import java.util.concurrent.Callable;

public abstract class InputStreamDownloader<RESULT,ADAPTOR extends InputStreamAdaptor> implements Callable<RESULT> {
    private DownloaderListener<ADAPTOR> listener;
    protected final ADAPTOR adaptor;

    protected abstract void handleInputStream(InputStream inputStream, DownloaderListener<ADAPTOR> listener) throws Exception;

    protected abstract RESULT getResult();

    protected abstract void resetSubclass();

    private void reset() {
        resetSubclass();
    }

    public InputStreamDownloader(ADAPTOR adaptor) {
        this.adaptor = adaptor;
    }

    @Override
    public final RESULT call() throws Exception {
        reset();

        if (listener != null)
            listener.start(adaptor);

        adaptor.open();

        try {
            InputStream urlInputStream = adaptor.getInputStream();
            handleInputStream(urlInputStream, listener);
            urlInputStream.close();
            return getResult();
//        } catch (SocketTimeoutException | ConnectException e) {
//            if (listener != null)
//                listener.onTimeout(this, e);
//            throw e;
        } catch (IOException e) {
            if (listener != null)
                listener.onIoException(adaptor, e);
            throw e;
        } finally {
            adaptor.close();
        }
    }

    public final RESULT get() throws Exception {
        return call();
    }

    public final void setListener(DownloaderListener<ADAPTOR> listener) {
        this.listener = listener;
    }

    public ADAPTOR getAdaptor(){
        return adaptor;
    }
}
