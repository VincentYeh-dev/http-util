package org.vincentyeh.http_util.net.server.framework.util;



import java.io.IOException;

public interface HttpServerUtil {

    void createOneTimeServer(String path, RequestListener listener) throws IOException;

}
