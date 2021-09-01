package org.vincentyeh.http_util.example;

import org.vincentyeh.http_util.net.concrete.downloader.StringDownloader;
import org.vincentyeh.http_util.net.concrete.utils.LocalProxyHttpClientUtil;
import org.vincentyeh.http_util.net.concrete.utils.StandardHttpClientUtil;
import org.vincentyeh.http_util.net.framework.downloader.URLDownloader;

import java.net.URL;
import java.nio.charset.StandardCharsets;

public class StringDownloadExample {
    public static void main(String[] args) throws Exception {
        URLDownloader.warpHttpClientUtil(new LocalProxyHttpClientUtil());
        URLDownloader<String> downloader = new StringDownloader(new URL("https://example.org/"), StandardCharsets.UTF_8, 1000);
        System.out.println(downloader.get());
    }
}
