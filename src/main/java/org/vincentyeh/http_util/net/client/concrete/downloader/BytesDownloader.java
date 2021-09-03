package org.vincentyeh.http_util.net.client.concrete.downloader;

import org.vincentyeh.http_util.net.client.framework.connection.data.RequestHeaders;
import org.vincentyeh.http_util.net.client.framework.downloader.URLDownloader;
import org.vincentyeh.http_util.net.client.framework.downloader.listener.URLDownloaderListener;

import java.io.InputStream;
import java.math.BigDecimal;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;

public class BytesDownloader extends URLDownloader<List<Byte>> {
    private final List<Byte> bytes = new LinkedList<>();
    private BigDecimal downloadBytes = new BigDecimal(0);

    public BytesDownloader(URL url, int timeoutMillis, RequestHeaders headers) {
        super(url, timeoutMillis, headers);
    }

    @Override
    protected void handleInputStream(InputStream inputStream, URLDownloaderListener listener) throws Exception {
        int b;

        while ((b = inputStream.read()) != -1) {
            bytes.add((byte) b);
            synchronized (this) {
                downloadBytes = new BigDecimal(bytes.size());
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
    public BigDecimal getDownloadedBytes() {
        return downloadBytes;
    }

    @Override
    protected void resetSubclass() {
        bytes.clear();
    }

}
