package org.vincentyeh.http_util.net.client.concrete.downloader;

import org.vincentyeh.http_util.net.client.concrete.downloader.adaptor.HttpInputStreamAdaptor;
import org.vincentyeh.http_util.net.client.framework.downloader.InputStreamDownloader;
import org.vincentyeh.http_util.net.client.framework.downloader.listener.DownloaderListener;
import org.vincentyeh.http_util.net.client.framework.utils.HttpClientUtil;

import java.io.File;
import java.net.Proxy;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Map;

public class DownloadUtil {
    public static void warpHttpClientUtil(HttpClientUtil util){
        HttpInputStreamAdaptor.warpHttpClientUtil(util);
    }

    public static byte[] downloadBytes(URL url, Map<String, List<String>> headers, int timeoutMillis, Proxy proxy, DownloaderListener<HttpInputStreamAdaptor> listener) throws Exception {
        HttpInputStreamAdaptor adaptor = HttpInputStreamAdaptor.createGetInputStreamAdaptor(url, headers,timeoutMillis, proxy);
        InputStreamDownloader<List<Byte>, HttpInputStreamAdaptor> downloader = new BytesDownloader<>(adaptor);
        downloader.setListener(listener);
        List<Byte> byteList = downloader.call();
        byte[] buffer = new byte[byteList.size()];
        for (int i = 0; i < buffer.length; i++) {
            buffer[i] = byteList.get(i);
        }
        return buffer;
    }

    public static String downloadString(URL url, Map<String, List<String>> headers, int timeoutMillis, Proxy proxy, DownloaderListener<HttpInputStreamAdaptor> listener, Charset charset) throws Exception {
        HttpInputStreamAdaptor adaptor = HttpInputStreamAdaptor.createGetInputStreamAdaptor(url, headers, timeoutMillis, proxy);
        InputStreamDownloader<String, HttpInputStreamAdaptor> downloader = new StringDownloader<>(adaptor, charset);
        downloader.setListener(listener);
        return downloader.call();
    }

    public static File downloadFile(URL url, Map<String, List<String>> headers, int timeoutMillis, Proxy proxy, DownloaderListener<HttpInputStreamAdaptor> listener, File file) throws Exception {
        HttpInputStreamAdaptor adaptor = HttpInputStreamAdaptor.createGetInputStreamAdaptor(url, headers, timeoutMillis, proxy);
        InputStreamDownloader<File, HttpInputStreamAdaptor> downloader = new FileDownloader<>(adaptor, file);
        downloader.setListener(listener);
        return downloader.call();
    }

}
