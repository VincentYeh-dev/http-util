package org.vincentyeh.http_util.example;

import org.vincentyeh.http_util.net.server.concrete.StandardHttpServerUtil;
import org.vincentyeh.http_util.net.server.framework.util.HttpServerUtil;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class ServerExample {

    public static void main(String[] args) throws IOException {
        HttpServerUtil util=new StandardHttpServerUtil();
        util.createOneTimeServer("/", exchange -> {
            byte[] data="OK".getBytes(StandardCharsets.UTF_8);
            try {
                System.out.println("OK");
                System.out.println(exchange.getRequestURI().getQuery());

                exchange.sendResponseHeaders(200,data.length);
                exchange.getResponseBody().write(data);
                exchange.getResponseBody().close();
                exchange.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
