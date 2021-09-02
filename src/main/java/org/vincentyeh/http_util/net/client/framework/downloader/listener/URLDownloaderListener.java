package org.vincentyeh.http_util.net.client.framework.downloader.listener;

import org.vincentyeh.http_util.net.client.framework.downloader.URLDownloader;

import java.io.IOException;
import java.math.BigDecimal;

public interface URLDownloaderListener {
    void start(URLDownloader<?> downloader);
    void download(URLDownloader<?> downloader, BigDecimal downloadedBytes);
    void done(URLDownloader<?> downloader, BigDecimal downloadedBytes);
    void onTimeout(URLDownloader<?> downloader, Exception e);
    void onIoException(URLDownloader<?> downloader, IOException e);
}
