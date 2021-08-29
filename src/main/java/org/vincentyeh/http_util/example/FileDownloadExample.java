package org.vincentyeh.http_util.example;

import org.vincentyeh.http_util.net.concrete.downloader.FileDownloader;
import org.vincentyeh.http_util.net.concrete.utils.StandardHttpUtil;
import org.vincentyeh.http_util.net.framework.downloader.URLDownloader;

import java.io.File;
import java.net.URL;

public class FileDownloadExample {
    public static void main(String[] args) throws Exception {
        URLDownloader.warpHttpUtil(new StandardHttpUtil());
        FileDownloader.setBufferSize(65536);
        URLDownloader<File> downloader = new FileDownloader(new URL("https://example.org/"), new File("test.html"), 1000);
        System.out.println(downloader.call());
    }
}
