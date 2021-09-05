package org.vincentyeh.http_util.net.client.concrete.downloader;

import org.vincentyeh.http_util.net.client.framework.downloader.InputStreamDownloader;
import org.vincentyeh.http_util.net.client.framework.downloader.adaptor.InputStreamAdaptor;
import org.vincentyeh.http_util.net.client.framework.downloader.listener.DownloaderListener;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

public class BytesDownloader<ADAPTOR extends InputStreamAdaptor> extends InputStreamDownloader<List<Byte>, ADAPTOR> {
    private final List<Byte> bytes = new LinkedList<>();

    public BytesDownloader(ADAPTOR adaptor) {
        super(adaptor);
    }

    @Override
    protected void handleInputStream(InputStream inputStream, DownloaderListener<ADAPTOR> listener) throws IOException {
        int b;

        while ((b = inputStream.read()) != -1) {
            bytes.add((byte) b);
            synchronized (this) {
                BigDecimal downloadBytes = new BigDecimal(bytes.size());
                if (listener != null)
                    listener.download(adaptor, downloadBytes);
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
