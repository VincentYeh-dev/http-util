package org.vincentyeh.http_util.net.client.framework.connection;

import java.io.IOException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.List;
import java.util.Map;

public abstract class Request {
    public abstract Request setHeaders(Map<String, List<String>> map);

    //    protected RequestHeaders headers;
    public abstract Map<String, List<String>> getHeaders();

    public abstract URL getUrl();

    public abstract RequestMethod getMethod();

    public abstract Request setMethod(RequestMethod method) throws ProtocolException;

    public abstract void setBody(byte[] body);

    public abstract void sendBody() throws IOException;

    public abstract Response open() throws IOException;

    public abstract void close();
}
