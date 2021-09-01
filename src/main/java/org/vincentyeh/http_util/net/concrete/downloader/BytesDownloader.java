package org.vincentyeh.http_util.net.concrete.downloader;

import org.vincentyeh.http_util.net.framework.connection.header.Headers;
import org.vincentyeh.http_util.net.framework.downloader.URLDownloader;
import org.vincentyeh.http_util.net.framework.downloader.listener.URLDownloaderListener;

import java.io.InputStream;
import java.math.BigDecimal;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class BytesDownloader extends URLDownloader<List<Byte>> {
    private List<Byte> bytes;

    public BytesDownloader(URL url, int timeoutMillis, Headers headers) {
        super(url, timeoutMillis, headers);
    }

    @Override
    protected void handleInputStream(InputStream inputStream, URLDownloaderListener listener) throws Exception {
        bytes = new ArrayList<>();
        int b;
        while ((b = inputStream.read()) != -1) {
            bytes.add((byte) b);
        }
    }

    @Override
    protected List<Byte> getResult() {
        return bytes;
    }

    @Override
    public BigDecimal getDownloadedBytes() {
        return new BigDecimal(bytes.size());
    }

}
