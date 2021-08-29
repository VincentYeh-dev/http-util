package org.vincentyeh.http_util.net.framework.connection;

import org.vincentyeh.http_util.net.framework.utils.LengthNotFound;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.Map;

public interface HttpConnection {

    InputStream getInputStream() throws IOException;

    OutputStream getOutputStream() throws IOException;

    void setReadTimeout(int timeInMillisSecond);

    void setConnectTimeout(int timeInMillisSecond);

    void setHeader(Map<String, String> headers);

    void setUseCaches(boolean b);

    void setDoInput(boolean b);

    void disconnect();

    BigDecimal getLength() throws LengthNotFound;

    void connect() throws IOException;
}
