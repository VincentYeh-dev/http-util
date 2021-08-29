package org.vincentyeh.http_util.net.framework.downloader.listener;


import org.vincentyeh.http_util.net.framework.downloader.URLDownloader;

import java.io.IOException;
import java.math.BigDecimal;

public interface BufferedDownloaderListener {


    void onDownloadPartialStart(int index,int total, URLDownloader<?> downloader);
    void onDownloadPartialDone(int index,int total, URLDownloader<?> downloader);
    void onDownloadPartialRetry(int index,int total, URLDownloader<?> downloader);
    void onDownloadPartialUpdate(int index, int total, BigDecimal bigDecimal, URLDownloader<?> downloader);
    void onAllDone();
    void onDownloadPartialTimeout(int index, int total, URLDownloader<?> downloader);
    void onDownloadPartialIoError(int index, int total, URLDownloader<?> downloader, IOException e);
}
