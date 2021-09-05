package org.vincentyeh.http_util.example;

import org.vincentyeh.http_util.net.client.concrete.downloader.BytesDownloader;
import org.vincentyeh.http_util.net.client.concrete.downloader.adaptor.GetInputStreamAdaptor;
import org.vincentyeh.http_util.net.client.concrete.utils.LocalProxyHttpClientUtil;
import org.vincentyeh.http_util.net.client.framework.connection.data.RequestHeaders;
import org.vincentyeh.http_util.net.client.framework.downloader.adaptor.HttpInputStreamAdaptor;
import org.vincentyeh.http_util.net.client.framework.downloader.InputStreamDownloader;
import org.vincentyeh.http_util.net.client.framework.downloader.listener.DownloaderListener;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.util.List;
import java.util.stream.Collectors;

public class BytesDownloadExample {


    static {
        HttpInputStreamAdaptor.warpHttpClientUtil(new LocalProxyHttpClientUtil());
    }

    public static void main(String[] args) throws Exception {
        RequestHeaders headers = new RequestHeaders();
        headers.setUserAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/78.0.3904.108 Safari/537.36");
        headers.setConnection(RequestHeaders.CONNECTION_CLOSE);
        URL url = new URL("https://ts6.hhmm0.com:9999/20210331/lRpXNQKR/1000kb/hls/key.key");

        HttpInputStreamAdaptor adaptor = new GetInputStreamAdaptor(url, headers, 1000, null);

        InputStreamDownloader<List<Byte>,HttpInputStreamAdaptor> downloader = new BytesDownloader<>(adaptor);
        System.out.println(downloader.get().stream().map(b -> (char) b.byteValue() + "").collect(Collectors.joining()));
    }

//    private static final DownloaderListener listener = new DownloaderListener() {
//        @Override
//        public void start(InputStreamDownloader<?> downloader) {
//
//        }
//
//        @Override
//        public void download(InputStreamDownloader<?> downloader, BigDecimal downloadedBytes) {
//            System.out.println(downloadedBytes.toString() + "/" + downloader.getTotalBytes());
//        }
//
//        @Override
//        public void done(InputStreamDownloader<?> downloader, BigDecimal downloadedBytes) {
//
//        }
//
//        @Override
//        public void onTimeout(InputStreamDownloader<?> downloader, Exception e) {
//
//        }
//
//        @Override
//        public void onIoException(InputStreamDownloader<?> downloader, IOException e) {
//
//        }
//    };
}
