package org.vincentyeh.http_util.net.client.concrete.utils;

import org.vincentyeh.http_util.net.client.concrete.connection.HttpConnectionAdaptor;
import org.vincentyeh.http_util.net.client.concrete.connection.HttpsConnectionAdaptor;
import org.vincentyeh.http_util.net.client.framework.connection.HttpConnection;
import org.vincentyeh.http_util.net.client.framework.connection.HttpsConnection;
import org.vincentyeh.http_util.net.client.framework.connection.header.Headers;
import org.vincentyeh.http_util.net.client.framework.utils.HttpClientUtil;

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
    public HttpConnection get(URL url, Headers headers, int timeoutMillisecond, Proxy proxy) throws Exception {
        return handleHttpConnection(proxy == null ? url.openConnection() : url.openConnection(proxy), "GET", headers, timeoutMillisecond, null);
    }

    @Override
    public HttpConnection post(URL url, Headers headers, int timeoutMillisecond, byte[] body, Proxy proxy) throws Exception {
        HttpConnection connection = handleHttpConnection(proxy == null ? url.openConnection() : url.openConnection(proxy), "POST", headers, timeoutMillisecond, body);

        if (body != null)
            connection.setLength(body.length);
        else
            connection.setLength(0);

        return connection;
    }


    private HttpConnection handleHttpConnection(URLConnection urlConnection, String requestMethod, Headers headers, int timeoutMillisecond, byte[] body) throws NoSuchAlgorithmException, KeyManagementException, IOException {

        if (urlConnection instanceof HttpURLConnection) {
            HttpConnection connection;
            if (urlConnection instanceof HttpsURLConnection) {
                connection = new HttpsConnectionAdaptor((HttpsURLConnection) urlConnection,true,true);
                SSLContext sc = SSLContext.getInstance("TLSv1.2");

                // Create a trust manager that does not validate certificate chains
                sc.init(null, trustAllCerts, new java.security.SecureRandom());
                ((HttpsConnection) connection).setSSLContext(sc);

            } else {
                connection = new HttpConnectionAdaptor((HttpURLConnection) urlConnection,true,true);
            }

            connection.setConnectTimeout(timeoutMillisecond);

            if (headers != null) {
                connection.setHeader(headers);
            }

            connection.setRequestMethod(requestMethod);

            connection.setUseCaches(false);
            connection.setReadTimeout(timeoutMillisecond);

            if (body != null)
                connection.getOutputStream().write(body);

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
