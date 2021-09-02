package org.vincentyeh.http_util.net.server.framework.util;

import com.sun.net.httpserver.HttpExchange;

public interface RequestListener {
    void onRequest(HttpExchange exchange);
}
