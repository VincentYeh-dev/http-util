package org.vincentyeh.http_util.example;

import org.vincentyeh.http_util.net.concrete.downloader.StringDownloader;
import org.vincentyeh.http_util.net.concrete.utils.LocalProxyHttpClientUtil;
import org.vincentyeh.http_util.net.framework.connection.header.Cookies;
import org.vincentyeh.http_util.net.framework.connection.header.Headers;
import org.vincentyeh.http_util.net.framework.downloader.URLDownloader;

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
        System.out.println(downloader.get());
    }
}
