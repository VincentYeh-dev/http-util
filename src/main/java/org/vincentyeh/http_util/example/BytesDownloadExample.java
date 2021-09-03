package org.vincentyeh.http_util.example;

import org.vincentyeh.http_util.net.client.concrete.downloader.BytesDownloader;
import org.vincentyeh.http_util.net.client.concrete.utils.LocalProxyHttpClientUtil;
import org.vincentyeh.http_util.net.client.framework.connection.data.RequestHeaders;
import org.vincentyeh.http_util.net.client.framework.downloader.URLDownloader;
import org.vincentyeh.http_util.net.client.framework.downloader.listener.URLDownloaderListener;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.util.List;
import java.util.stream.Collectors;

public class BytesDownloadExample {


    public static void main(String[] args) throws Exception {
        URLDownloader.warpHttpClientUtil(new LocalProxyHttpClientUtil());
        RequestHeaders headers = new RequestHeaders();
        headers.setUserAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/78.0.3904.108 Safari/537.36");
        headers.setConnection(RequestHeaders.CONNECTION_CLOSE);

        URLDownloader<List<Byte>> downloader = new BytesDownloader(new URL("https://ts6.hhmm0.com:9999/20210331/lRpXNQKR/1000kb/hls/key.key"), 1000, headers);
        downloader.setListener(listener);
        System.out.println(downloader.get().stream().map(b->(char)b.byteValue()+"").collect(Collectors.joining()));
    }

    private static final URLDownloaderListener listener = new URLDownloaderListener() {
        @Override
        public void start(URLDownloader<?> downloader) {

        }

        @Override
        public void download(URLDownloader<?> downloader, BigDecimal downloadedBytes) {
            System.out.println(downloadedBytes.toString() + "/" + downloader.getTotalBytes());
        }

        @Override
        public void done(URLDownloader<?> downloader, BigDecimal downloadedBytes) {

        }

        @Override
        public void onTimeout(URLDownloader<?> downloader, Exception e) {

        }

        @Override
        public void onIoException(URLDownloader<?> downloader, IOException e) {

        }
    };
}
