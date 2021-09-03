package org.vincentyeh.http_util.net.client.concrete.connection;

import org.vincentyeh.http_util.net.client.framework.connection.Response;
import org.vincentyeh.http_util.net.client.framework.connection.Session;

import java.net.HttpURLConnection;

public class SessionConnectionAdaptor implements Session {
    private final HttpURLConnection connection;
    private final Response response;

    public SessionConnectionAdaptor(HttpURLConnection connection,Response response) {
        this.connection = connection;
        this.response = response;
    }

    @Override
    public Response getResponse() {
        return response;
    }

    @Override
    public void close() {
        connection.disconnect();
    }
}
