package org.vincentyeh.http_util.net.concrete.connection;

import org.vincentyeh.http_util.net.framework.connection.HttpConnection;
import org.vincentyeh.http_util.net.framework.connection.header.Headers;
import org.vincentyeh.http_util.net.framework.utils.LengthNotFound;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.util.Map;

public class HttpConnectionAdaptor implements HttpConnection {
    private final HttpURLConnection connection;

    public HttpConnectionAdaptor(HttpURLConnection connection) {
        this.connection = connection;
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
    public void setHeader(Headers headers) {
        if (headers != null)
            for (Map.Entry<String, String> entry : headers.getMap().entrySet())
                connection.setRequestProperty(entry.getKey(), entry.getValue());
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

}
