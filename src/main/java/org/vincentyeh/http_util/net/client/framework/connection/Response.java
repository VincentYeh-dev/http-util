package org.vincentyeh.http_util.net.client.framework.connection;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

public abstract class Response {
    private final int code;
    private final long contentLength;

    public Response(int code,long contentLength) {
        this.code = code;
        this.contentLength=contentLength;
    }

    public abstract Map<String, List<String>> getHeader();

    public abstract InputStream getBodyInputStream() throws IOException;

    public final int getCode() {
        return code;
    }

    public final long getContentLength() {
        return contentLength;
    }

}
