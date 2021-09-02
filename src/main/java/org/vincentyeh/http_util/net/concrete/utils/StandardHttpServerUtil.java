package org.vincentyeh.http_util.net.concrete.utils;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import org.vincentyeh.http_util.net.framework.utils.HttpServerUtil;
import org.vincentyeh.http_util.net.framework.utils.RequestListener;

import java.io.IOException;
import java.net.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class StandardHttpServerUtil implements HttpServerUtil {

    public void createOneTimeServer(String path, RequestListener listener) throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(80), 100);

        ExecutorService httpThreadPool = Executors.newSingleThreadExecutor();
        server.setExecutor(httpThreadPool);

        server.createContext(path, httpExchange -> {
            synchronized (this) {
                listener.onRequest(httpExchange);
                server.stop(500);
                httpThreadPool.shutdownNow();
            }
        });
        server.start();
    }


}
