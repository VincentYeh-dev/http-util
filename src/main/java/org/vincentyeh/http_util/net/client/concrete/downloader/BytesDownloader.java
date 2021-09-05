package org.vincentyeh.http_util.net.client.concrete.downloader;

import org.vincentyeh.http_util.net.client.framework.downloader.adaptor.HttpInputStreamAdaptor;
import org.vincentyeh.http_util.net.client.framework.downloader.URLDownloader;
import org.vincentyeh.http_util.net.client.framework.downloader.listener.URLDownloaderListener;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

public class BytesDownloader extends URLDownloader<List<Byte>> {
    private final List<Byte> bytes = new LinkedList<>();

    public BytesDownloader(HttpInputStreamAdaptor adaptor) {
        super(adaptor);
    }

    @Override
    protected void handleInputStream(InputStream inputStream, URLDownloaderListener listener) throws IOException {
        int b;

        while ((b = inputStream.read()) != -1) {
            bytes.add((byte) b);
            synchronized (this) {
                BigDecimal downloadBytes = new BigDecimal(bytes.size());
                if (listener != null)
                    listener.download(this, downloadBytes);
            }
        }
    }

    @Override
    protected List<Byte> getResult() {
        return bytes;
    }


    @Override
    protected void resetSubclass() {
        bytes.clear();
    }

}
