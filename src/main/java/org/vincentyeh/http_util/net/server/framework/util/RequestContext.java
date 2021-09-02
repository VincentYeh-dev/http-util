package org.vincentyeh.http_util.net.server.framework.util;

import com.sun.net.httpserver.HttpHandler;

public class RequestContext {
    private final String path;
    private final HttpHandler handler;

    public RequestContext(String path, HttpHandler handler) {
        this.path = path;
        this.handler = handler;
    }

    public HttpHandler getHandler() {
        return handler;
    }

    public String getPath() {
        return path;
    }
}
