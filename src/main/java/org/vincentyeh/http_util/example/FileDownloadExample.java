package org.vincentyeh.http_util.example;

import org.vincentyeh.http_util.net.client.concrete.downloader.FileDownloader;
import org.vincentyeh.http_util.net.client.concrete.downloader.adaptor.GetInputStreamAdaptor;
import org.vincentyeh.http_util.net.client.concrete.utils.LocalProxyHttpClientUtil;
import org.vincentyeh.http_util.net.client.framework.connection.data.RequestHeaders;
import org.vincentyeh.http_util.net.client.framework.downloader.adaptor.HttpInputStreamAdaptor;
import org.vincentyeh.http_util.net.client.framework.downloader.URLDownloader;

import java.io.File;
import java.net.URL;

public class FileDownloadExample {
    static {
        HttpInputStreamAdaptor.warpHttpClientUtil(new LocalProxyHttpClientUtil());
    }

    public static void main(String[] args) throws Exception {
        FileDownloader.setBufferSize(65536);

        RequestHeaders headers = new RequestHeaders();
        headers.setUserAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/78.0.3904.108 Safari/537.36");
        headers.setConnection(RequestHeaders.CONNECTION_KEEP_ALIVE);

        URL url = new URL("https://example.org/");

        HttpInputStreamAdaptor adaptor = new GetInputStreamAdaptor(url, headers, 2000, null);

        URLDownloader<File> downloader = new FileDownloader(new File("test.html"), adaptor);
        System.out.println(downloader.get());
    }
}
