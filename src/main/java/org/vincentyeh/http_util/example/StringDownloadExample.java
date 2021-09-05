package org.vincentyeh.http_util.example;

import org.vincentyeh.http_util.net.client.concrete.downloader.adaptor.GetInputStreamAdaptor;
import org.vincentyeh.http_util.net.client.concrete.downloader.StringDownloader;
import org.vincentyeh.http_util.net.client.concrete.downloader.adaptor.PostInputStreamAdaptor;
import org.vincentyeh.http_util.net.client.concrete.utils.LocalProxyHttpClientUtil;
import org.vincentyeh.http_util.net.client.framework.connection.data.Cookies;
import org.vincentyeh.http_util.net.client.framework.connection.data.RequestHeaders;
import org.vincentyeh.http_util.net.client.framework.downloader.adaptor.HttpInputStreamAdaptor;
import org.vincentyeh.http_util.net.client.framework.downloader.URLDownloader;
import org.vincentyeh.http_util.net.client.framework.downloader.listener.URLDownloaderListener;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class StringDownloadExample {
    static {
        HttpInputStreamAdaptor.warpHttpClientUtil(new LocalProxyHttpClientUtil());
    }

    public static void main(String[] args) throws Exception {

        RequestHeaders headers = new RequestHeaders();
        headers.setUserAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/78.0.3904.108 Safari/537.36");
        headers.setConnection(RequestHeaders.CONNECTION_CLOSE);
        headers.setReferer("example.org");
        Cookies cookies = new Cookies();
        cookies.setProperty("username", "user");
        headers.setCookies(cookies);
        headers.setAuthorization("Basic", "account:password");

        URL url = new URL(args[0]);

        HttpInputStreamAdaptor adaptor = new GetInputStreamAdaptor(url, headers, 2000, null);

//        String text="Hello";
//        HttpInputStreamAdaptor adaptor = new PostInputStreamAdaptor(url, headers, 2000, text.getBytes(StandardCharsets.UTF_8),null);

        URLDownloader<String> downloader = new StringDownloader(StandardCharsets.UTF_8, adaptor);
        downloader.setListener(listener);
        System.out.println(downloader.get());
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
