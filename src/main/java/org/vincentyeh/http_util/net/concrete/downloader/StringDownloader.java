package org.vincentyeh.http_util.net.concrete.downloader;


import org.vincentyeh.http_util.net.framework.connection.header.Headers;
import org.vincentyeh.http_util.net.framework.downloader.URLDownloader;
import org.vincentyeh.http_util.net.framework.downloader.listener.URLDownloaderListener;

import java.io.*;
import java.math.BigDecimal;
import java.net.URL;
import java.nio.charset.Charset;

public class StringDownloader extends URLDownloader<String> {

    private final Charset charset;
    private BigDecimal downloadBytes=new BigDecimal(0);

    private final StringBuilder builder = new StringBuilder();

    public StringDownloader(URL url, Charset charset, int timeoutMillis, Headers headers) {
        super(url, timeoutMillis, headers);
        this.charset = charset;
    }

    @Override
    protected void handleInputStream(InputStream inputStream, URLDownloaderListener listener) throws IOException {
        Reader reader = new InputStreamReader(inputStream, charset);

        int bufferSize = 1024;
        char[] buffer = new char[bufferSize];
        for (int numRead; (numRead = reader.read(buffer, 0, buffer.length)) > 0; ) {
            builder.append(buffer, 0, numRead);
            downloadBytes = downloadBytes.add(new BigDecimal(numRead));

            if (listener != null)
                listener.download(this, downloadBytes);

        }
    }

    @Override
    protected String getResult() {
        return builder.toString();
    }

    @Override
    public BigDecimal getDownloadedBytes() {
        return downloadBytes;
    }

    @Override
    protected void resetSubclass() {
        builder.setLength(0);
        downloadBytes=new BigDecimal(0);
    }

}
