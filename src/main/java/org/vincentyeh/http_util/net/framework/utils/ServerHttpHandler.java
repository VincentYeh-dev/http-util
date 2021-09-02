package org.vincentyeh.http_util.net.framework.utils;


import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;

public abstract class ServerHttpHandler implements HttpHandler {
    protected final HttpServer server;

    public ServerHttpHandler(HttpServer server) {
        this.server = server;
    }

    @Override
    public final void handle(HttpExchange httpExchange) throws IOException {
        handle(httpExchange, server);
    }

    public abstract void handle(HttpExchange exchange, HttpServer server);
}
