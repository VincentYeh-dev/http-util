package org.vincentyeh.http_util.net.server.framework.util;



import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.util.List;

public interface HttpServerUtil {

    HttpServer createOneTimeServer(String path, int port, RequestListener listener) throws IOException;
    HttpServer createServer(List<RequestContext> contextList, int port) throws IOException;


}
