package org.vincentyeh.http_util.net.framework.utils;

import com.sun.net.httpserver.HttpExchange;

public interface RequestListener {
    void onRequest(HttpExchange exchange);
}
