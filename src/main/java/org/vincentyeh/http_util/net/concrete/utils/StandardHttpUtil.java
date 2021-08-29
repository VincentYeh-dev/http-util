package org.vincentyeh.http_util.net.concrete.utils;

import org.vincentyeh.http_util.net.concrete.connection.HttpConnectionAdaptor;
import org.vincentyeh.http_util.net.concrete.connection.HttpsConnectionAdaptor;
import org.vincentyeh.http_util.net.framework.connection.HttpConnection;
import org.vincentyeh.http_util.net.framework.utils.HttpUtil;

import javax.net.ssl.HttpsURLConnection;
import java.io.IOException;
import java.net.*;
import java.util.Map;

public class StandardHttpUtil implements HttpUtil {

    @Override
    public HttpConnection openConnection(URL url, Map<String, String> headers, int timeoutMillisecond) throws IOException {
        URLConnection urlConnection = url.openConnection();
        return handleHttpConnection(urlConnection,headers,timeoutMillisecond);
    }

    @Override
    public HttpConnection openWithProxyConnection(URL url, Map<String, String> headers, int timeoutMillisecond, Proxy proxy) throws IOException {
        URLConnection urlConnection = url.openConnection(proxy);
        return handleHttpConnection(urlConnection,headers,timeoutMillisecond);
    }


    private HttpConnection handleHttpConnection(URLConnection urlConnection, Map<String, String> headers, int timeoutMillisecond) {
        if (urlConnection instanceof HttpURLConnection) {
            HttpConnection connection;
            if (urlConnection instanceof HttpsURLConnection) {
                connection = new HttpsConnectionAdaptor((HttpsURLConnection) urlConnection);
            } else {
                connection = new HttpConnectionAdaptor((HttpURLConnection) urlConnection);
            }

            connection.setConnectTimeout(timeoutMillisecond);
            if (headers != null)
                connection.setHeader(headers);

            connection.setUseCaches(false);
            connection.setReadTimeout(timeoutMillisecond);
            connection.setDoInput(true);
            return connection;
        }
        throw new RuntimeException();
    }


}
