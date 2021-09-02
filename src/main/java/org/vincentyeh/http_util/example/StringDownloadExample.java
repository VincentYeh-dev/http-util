package org.vincentyeh.http_util.example;

import org.vincentyeh.http_util.net.client.concrete.downloader.StringDownloader;
import org.vincentyeh.http_util.net.client.concrete.utils.LocalProxyHttpClientUtil;
import org.vincentyeh.http_util.net.client.framework.connection.header.Cookies;
import org.vincentyeh.http_util.net.client.framework.connection.header.Headers;
import org.vincentyeh.http_util.net.client.framework.downloader.URLDownloader;
import org.vincentyeh.http_util.net.client.framework.downloader.listener.URLDownloaderListener;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class StringDownloadExample {


    public static void main(String[] args) throws Exception {
        URLDownloader.warpHttpClientUtil(new LocalProxyHttpClientUtil());
        Headers headers=new Headers();
        headers.setUserAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/78.0.3904.108 Safari/537.36");
        headers.setConnection(Headers.CONNECTION_CLOSE);
        headers.setReferer("example.org");
        Cookies cookies=new Cookies();
        cookies.setProperty("username","user");
        headers.setCookies(cookies);
        headers.setAuthorization("Basic","account:password");

        URLDownloader<String> downloader = new StringDownloader(new URL("https://example.org/"), StandardCharsets.UTF_8, 1000,headers);
        downloader.setListener(listener);
        System.out.println(downloader.get());
    }

    private static final URLDownloaderListener listener=new URLDownloaderListener() {
        @Override
        public void start(URLDownloader<?> downloader) {

        }

        @Override
        public void download(URLDownloader<?> downloader, BigDecimal downloadedBytes) {
            System.out.println(downloadedBytes.toString()+"/"+downloader.getTotalBytes());
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
