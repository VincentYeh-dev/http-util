package org.vincentyeh.http_util.example;

import org.vincentyeh.http_util.net.client.concrete.downloader.adaptor.GetInputStreamAdaptor;
import org.vincentyeh.http_util.net.client.concrete.downloader.StringDownloader;
import org.vincentyeh.http_util.net.client.concrete.utils.LocalProxyHttpClientUtil;
import org.vincentyeh.http_util.net.client.framework.connection.data.Cookies;
import org.vincentyeh.http_util.net.client.framework.connection.data.RequestHeaders;
import org.vincentyeh.http_util.net.client.framework.downloader.adaptor.HttpInputStreamAdaptor;
import org.vincentyeh.http_util.net.client.framework.downloader.InputStreamDownloader;
import org.vincentyeh.http_util.net.client.framework.downloader.listener.DownloaderListener;

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

        StringDownloader.setBufferSize(100);
        InputStreamDownloader<String,HttpInputStreamAdaptor> downloader = new StringDownloader<>(adaptor, StandardCharsets.UTF_8);
        downloader.setListener(listener);
        System.out.println(downloader.get());
    }

    private final static DownloaderListener<HttpInputStreamAdaptor> listener=new DownloaderListener<HttpInputStreamAdaptor>() {
        @Override
        public void start(HttpInputStreamAdaptor adaptor) {

        }

        @Override
        public void download(HttpInputStreamAdaptor adaptor, BigDecimal downloadedBytes) {
            System.out.println(downloadedBytes.toString() + "/" + adaptor.getContentLength());
        }

        @Override
        public void done(HttpInputStreamAdaptor adaptor, BigDecimal downloadedBytes) {

        }

        @Override
        public void onIoException(HttpInputStreamAdaptor adaptor, IOException e) {

        }
    };
}
