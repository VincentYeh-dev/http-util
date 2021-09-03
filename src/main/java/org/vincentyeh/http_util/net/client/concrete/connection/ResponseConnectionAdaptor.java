package org.vincentyeh.http_util.net.client.concrete.connection;

import org.vincentyeh.http_util.net.client.framework.connection.Response;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.util.List;
import java.util.Map;

public class ResponseConnectionAdaptor extends Response {
    private final HttpURLConnection connection;

    public ResponseConnectionAdaptor(HttpURLConnection connection) throws IOException {
        super(connection.getResponseCode(),connection.getContentLength());
        this.connection = connection;
    }


    @Override
    public Map<String, List<String>> getHeader() {
        return connection.getHeaderFields();
    }

    @Override
    public InputStream getBodyInputStream() throws IOException {
        return connection.getInputStream();
    }
}
