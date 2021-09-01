package org.vincentyeh.http_util.net.concrete.utils;

import org.vincentyeh.http_util.net.concrete.connection.HttpConnectionAdaptor;
import org.vincentyeh.http_util.net.concrete.connection.HttpsConnectionAdaptor;
import org.vincentyeh.http_util.net.framework.connection.HttpConnection;
import org.vincentyeh.http_util.net.framework.connection.HttpsConnection;
import org.vincentyeh.http_util.net.framework.connection.header.Headers;
import org.vincentyeh.http_util.net.framework.utils.HttpClientUtil;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.IOException;
import java.net.*;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

public class StandardHttpClientUtil implements HttpClientUtil {

    @Override
    public HttpConnection openConnection(URL url, Headers headers, int timeoutMillisecond) throws IOException, NoSuchAlgorithmException, KeyManagementException {
        URLConnection urlConnection = url.openConnection();
        return handleHttpConnection(urlConnection,headers,timeoutMillisecond);
    }

    @Override
    public HttpConnection openWithProxyConnection(URL url, Headers headers, int timeoutMillisecond, Proxy proxy) throws IOException, NoSuchAlgorithmException, KeyManagementException {
        URLConnection urlConnection = url.openConnection(proxy);
        return handleHttpConnection(urlConnection,headers,timeoutMillisecond);
    }



    private HttpConnection handleHttpConnection(URLConnection urlConnection, Headers headers, int timeoutMillisecond) throws NoSuchAlgorithmException, KeyManagementException {
        if (urlConnection instanceof HttpURLConnection) {
            HttpConnection connection;
            if (urlConnection instanceof HttpsURLConnection) {
                connection = new HttpsConnectionAdaptor((HttpsURLConnection) urlConnection);
                SSLContext sc = SSLContext.getInstance("TLSv1.2");

                // Create a trust manager that does not validate certificate chains
                sc.init(null, trustAllCerts, new java.security.SecureRandom());
                ((HttpsConnection) connection).setSSLContext(sc);

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

    private TrustManager[] trustAllCerts = new TrustManager[]{
            new X509TrustManager() {
                public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                    return null;
                }

                public void checkClientTrusted(java.security.cert.X509Certificate[] certs, String authType) {
                }

                public void checkServerTrusted(java.security.cert.X509Certificate[] certs, String authType) {
                }
            }
    };


}
