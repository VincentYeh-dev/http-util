package org.vincentyeh.http_util.example;

import org.vincentyeh.http_util.net.client.concrete.downloader.DownloadUtil;
import org.vincentyeh.http_util.net.client.concrete.utils.LocalProxyHttpClientUtil;
import org.vincentyeh.http_util.net.client.framework.connection.data.RequestHeaders;
import org.vincentyeh.http_util.net.client.concrete.downloader.adaptor.HttpInputStreamAdaptor;

import java.net.URL;

public class BytesDownloadExample {

    static {
        DownloadUtil.warpHttpClientUtil(new LocalProxyHttpClientUtil());
    }

    public static void main(String[] args) throws Exception {
        RequestHeaders headers = new RequestHeaders();
        headers.setUserAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/78.0.3904.108 Safari/537.36");
        headers.setConnection(RequestHeaders.CONNECTION_CLOSE);
        URL url = new URL("https://ts6.hhmm0.com:9999/20210331/lRpXNQKR/1000kb/hls/key.key");
        byte[] array=DownloadUtil.downloadBytes(url,headers,2000, null, null);
        StringBuilder builder=new StringBuilder();

        for(byte b:array){
            builder.append((char) b);
        }
        System.out.println(builder);
    }
}
