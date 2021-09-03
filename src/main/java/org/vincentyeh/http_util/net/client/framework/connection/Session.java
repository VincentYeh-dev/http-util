package org.vincentyeh.http_util.net.client.framework.connection;

public interface Session {
    Response getResponse();
    void close();
}
