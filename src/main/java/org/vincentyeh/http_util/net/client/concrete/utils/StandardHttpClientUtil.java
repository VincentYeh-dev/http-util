package org.vincentyeh.http_util.net.client.concrete.utils;

import org.vincentyeh.http_util.net.client.concrete.connection.ResponseConnectionAdaptor;
import org.vincentyeh.http_util.net.client.concrete.connection.RequestConnectionAdaptor;
import org.vincentyeh.http_util.net.client.framework.connection.Request;
import org.vincentyeh.http_util.net.client.framework.connection.RequestMethod;
import org.vincentyeh.http_util.net.client.framework.connection.Response;
import org.vincentyeh.http_util.net.client.framework.utils.HttpClientUtil;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.net.*;
import java.util.List;
import java.util.Map;

public class StandardHttpClientUtil implements HttpClientUtil {

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


    @Override
    public Response get(URL url, Map<String, List<String>> headers, int timeoutMillisecond, Proxy proxy) throws Exception {
        Request request = new RequestConnectionAdaptor(createConnection(url, timeoutMillisecond, proxy));
        request.setMethod(RequestMethod.GET).setHeaders(headers);
        return sendRequest(request);
    }

    @Override
    public Response post(URL url, Map<String, List<String>> headers, int timeoutMillisecond, byte[] body, Proxy proxy) throws Exception {
        Request request = new RequestConnectionAdaptor(createConnection(url, timeoutMillisecond, proxy));
        request.setMethod(RequestMethod.POST).setHeaders(headers).setBody(body);
        return sendRequest(request);
    }

    private Response sendRequest(Request request) throws Exception {
        request.sendBody();
        return request.open();
    }

    private HttpURLConnection createConnection(URL url, int timeoutMillisecond, Proxy proxy) throws Exception {
        if (proxy != null)
            return httpConnectionSetup(url.openConnection(proxy), timeoutMillisecond);
        else
            return httpConnectionSetup(url.openConnection(), timeoutMillisecond);
    }


    private HttpURLConnection httpConnectionSetup(URLConnection urlConnection, int timeoutMillisecond) throws Exception {
        HttpURLConnection connection;
        if (urlConnection instanceof HttpURLConnection) {
            if (urlConnection instanceof HttpsURLConnection) {
                connection = (HttpsURLConnection) urlConnection;
                SSLContext sc = SSLContext.getInstance("TLSv1.2");

                // Create a trust manager that does not validate certificate chains
                sc.init(null, trustAllCerts, new java.security.SecureRandom());
                ((HttpsURLConnection) connection).setSSLSocketFactory(sc.getSocketFactory());

            } else {
                connection = (HttpURLConnection) urlConnection;
            }

            connection.setReadTimeout(timeoutMillisecond);
            connection.setConnectTimeout(timeoutMillisecond);
            connection.setUseCaches(false);
            connection.setDoInput(true);
            connection.setDoOutput(true);

            return connection;
        }
        throw new RuntimeException();
    }
}
