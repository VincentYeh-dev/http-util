package org.vincentyeh.http_util.net.concrete.connection;

import org.vincentyeh.http_util.net.framework.connection.HttpsConnection;
import org.vincentyeh.http_util.net.framework.utils.LengthNotFound;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.Map;

public class HttpsConnectionAdaptor implements HttpsConnection {
    private final HttpsURLConnection connection;

    public HttpsConnectionAdaptor(HttpsURLConnection connection) {
        this.connection = connection;

        try {
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public OutputStream getOutputStream() throws IOException {
        return connection.getOutputStream();
    }

    @Override
    public InputStream getInputStream() throws IOException {
        return connection.getInputStream();
    }


    @Override
    public void setReadTimeout(int timeInMillisSecond) {
        connection.setReadTimeout(timeInMillisSecond);
    }

    @Override
    public void setConnectTimeout(int timeInMillisSecond) {
        connection.setConnectTimeout(timeInMillisSecond);
    }

    @Override
    public void setHeader(Map<String, String> headers) {
        if (headers != null)
            for (Map.Entry<String, String> entry : headers.entrySet())
                connection.addRequestProperty(entry.getKey(), entry.getValue());
    }

    @Override
    public void setUseCaches(boolean b) {
        connection.setUseCaches(b);
    }

    @Override
    public void setDoInput(boolean b) {
        connection.setDoInput(b);
    }

    @Override
    public void disconnect() {
        connection.disconnect();
    }

    @Override
    public BigDecimal getLength() throws LengthNotFound {
        int len = connection.getContentLength();
        if (len >= 0)
            return new BigDecimal(len);
        else
            throw new LengthNotFound("length not found.");
    }

    @Override
    public void connect() throws IOException {
        connection.connect();
    }

    @Override
    public void setSSLContext(SSLContext context) {
        connection.setSSLSocketFactory(context.getSocketFactory());
    }
}
