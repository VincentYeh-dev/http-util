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

    private final StringBuilder builder = new StringBuilder();

    public StringDownloader(URL url, Charset charset, int timeoutMillis, Headers headers) {
        super(url, timeoutMillis, headers);
        this.charset = charset;
    }

    @Override
    protected void handleInputStream(InputStream inputStream, URLDownloaderListener listener) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, charset));

        String buf;
        while ((buf = reader.readLine()) != null) {
            builder.append(buf).append("\n");
        }
    }

    @Override
    protected String getResult() {
        return builder.toString();
    }

    @Override
    public BigDecimal getDownloadedBytes() {
        return new BigDecimal(0);
    }

}
