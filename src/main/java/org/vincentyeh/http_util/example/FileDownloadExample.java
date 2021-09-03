package org.vincentyeh.http_util.example;

import org.vincentyeh.http_util.net.client.concrete.downloader.FileDownloader;
import org.vincentyeh.http_util.net.client.concrete.utils.StandardHttpClientUtil;
import org.vincentyeh.http_util.net.client.framework.connection.data.RequestHeaders;
import org.vincentyeh.http_util.net.client.framework.downloader.URLDownloader;

import java.io.File;
import java.net.URL;

public class FileDownloadExample {
    public static void main(String[] args) throws Exception {
        URLDownloader.warpHttpClientUtil(new StandardHttpClientUtil());
        FileDownloader.setBufferSize(65536);

        RequestHeaders headers=new RequestHeaders();
        headers.setUserAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/78.0.3904.108 Safari/537.36");
        headers.setConnection(RequestHeaders.CONNECTION_KEEP_ALIVE);
        URLDownloader<File> downloader = new FileDownloader(new URL("https://example.org/"), new File("test.html"), 1000, headers);
        System.out.println(downloader.get());
    }
}
