package org.vincentyeh.http_util.net.client.concrete.downloader.adaptor;

import org.vincentyeh.http_util.net.client.framework.connection.Response;
import org.vincentyeh.http_util.net.client.framework.downloader.adaptor.HttpInputStreamAdaptor;
import org.vincentyeh.http_util.net.client.framework.downloader.exception.NoSpecifyNetUtil;

import java.io.IOException;
import java.io.InputStream;
import java.net.Proxy;
import java.net.URL;
import java.util.List;
import java.util.Map;

public class PostInputStreamAdaptor extends HttpInputStreamAdaptor {
    private final Map<String, List<String>> headers;
    private final int timeoutMillis;
    private final byte[] body;
    private final Proxy proxy;
    private Response response;
    private final URL url;
    private boolean connected = false;

    public PostInputStreamAdaptor(URL url, Map<String, List<String>> headers, int timeoutMillis, byte[] body, Proxy proxy) {
        this.headers = headers;
        this.timeoutMillis = timeoutMillis;
        this.body = body;
        this.proxy = proxy;
        if (httpUtil == null)
            throw new NoSpecifyNetUtil("Specify util before starting.");
        this.url = url;
    }

    @Override
    public long getContentLength() {
        return response.getContentLength();
    }

    @Override
    public void open() throws Exception {
        response = httpUtil.post(url, headers, timeoutMillis, body, proxy);
        connected = true;
    }

    @Override
    public void close() {
        if (!connected)
            throw new RuntimeException("Not connected");
        response.close();
    }

    @Override
    public InputStream getInputStream() throws IOException {
        if (!connected)
            throw new RuntimeException("Not connected");
        return response.getBodyInputStream();
    }

    @Override
    public URL getUrl() {
        return url;
    }
}
