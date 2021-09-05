package org.vincentyeh.http_util.net.client.concrete.downloader.adaptor;

import org.vincentyeh.http_util.net.client.framework.connection.Response;
import org.vincentyeh.http_util.net.client.framework.downloader.adaptor.HttpInputStreamAdaptor;
import org.vincentyeh.http_util.net.client.framework.downloader.exception.NoSpecifyNetUtil;
import org.vincentyeh.http_util.net.client.framework.utils.HttpClientUtil;

import java.io.IOException;
import java.io.InputStream;
import java.net.Proxy;
import java.net.URL;
import java.util.List;
import java.util.Map;

public class PostInputStreamAdaptor extends HttpInputStreamAdaptor {
    private final Response response;
    private final URL url;

    public PostInputStreamAdaptor(URL url, Map<String, List<String>> headers, int timeoutMillis, byte[] body, Proxy proxy) throws Exception {
        if (httpUtil == null)
            throw new NoSpecifyNetUtil("Specify util before starting.");
        this.url = url;
        response = httpUtil.post(url, headers, timeoutMillis, body, proxy);
    }

    @Override
    public long getContentLength() {
        return response.getContentLength();
    }

    @Override
    public void close() {
        response.close();
    }

    @Override
    public InputStream getInputStream() throws IOException {
        return response.getBodyInputStream();
    }

    @Override
    public URL getUrl() {
        return url;
    }
}
