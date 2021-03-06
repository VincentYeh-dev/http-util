package org.vincentyeh.http_util.example;

import org.vincentyeh.http_util.net.client.concrete.downloader.DownloadUtil;
import org.vincentyeh.http_util.net.client.concrete.downloader.FileDownloader;
import org.vincentyeh.http_util.net.client.concrete.utils.LocalProxyHttpClientUtil;
import org.vincentyeh.http_util.net.client.framework.connection.data.RequestHeaders;

import java.io.File;
import java.net.URL;

public class FileDownloadExample {
    static {
        DownloadUtil.warpHttpClientUtil(new LocalProxyHttpClientUtil());
    }

    public static void main(String[] args) throws Exception {
        FileDownloader.setBufferSize(65536);

        RequestHeaders headers = new RequestHeaders();
        headers.setUserAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/78.0.3904.108 Safari/537.36");
        headers.setConnection(RequestHeaders.CONNECTION_KEEP_ALIVE);

        URL url = new URL("https://example.org/");
        File file = DownloadUtil.downloadFile(url, headers, 2000, null, null, new File("test.html"));
        System.out.println(file);
    }

}
