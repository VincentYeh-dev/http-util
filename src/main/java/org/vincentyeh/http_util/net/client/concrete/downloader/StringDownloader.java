package org.vincentyeh.http_util.net.client.concrete.downloader;


import org.vincentyeh.http_util.net.client.framework.downloader.InputStreamDownloader;
import org.vincentyeh.http_util.net.client.framework.downloader.adaptor.InputStreamAdaptor;
import org.vincentyeh.http_util.net.client.framework.downloader.listener.DownloaderListener;

import java.io.*;
import java.math.BigDecimal;
import java.nio.charset.Charset;

public class StringDownloader<ADAPTOR extends InputStreamAdaptor> extends InputStreamDownloader<String,ADAPTOR> {

    private static int bufferSize = 2048;

    public static void setBufferSize(int bufferSize) {
        StringDownloader.bufferSize = bufferSize;
    }

    private final Charset charset;
    private BigDecimal downloadBytes = new BigDecimal(0);

    private final StringBuilder builder = new StringBuilder();

    public StringDownloader(ADAPTOR adaptor, Charset charset) {
        super(adaptor);
        this.charset = charset;
    }

    @Override
    protected void handleInputStream(InputStream inputStream, DownloaderListener<ADAPTOR> listener) throws IOException {
        Reader reader = new InputStreamReader(inputStream, charset);

        char[] buffer = new char[bufferSize];
        for (int numRead; (numRead = reader.read(buffer, 0, buffer.length)) > 0; ) {
            builder.append(buffer, 0, numRead);
            synchronized (this) {
                downloadBytes = downloadBytes.add(new BigDecimal(numRead));
                if (listener != null)
                    listener.download(adaptor, downloadBytes);
            }

        }
    }

    @Override
    protected String getResult() {
        return builder.toString();
    }

//    @Override
//    public BigDecimal getDownloadedBytes() {
//        return downloadBytes;
//    }

    @Override
    protected void resetSubclass() {
        builder.setLength(0);
        downloadBytes = new BigDecimal(0);
    }

}
