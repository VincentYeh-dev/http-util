package org.vincentyeh.http_util.net.framework.connection;

import javax.net.ssl.SSLContext;

public interface HttpsConnection extends HttpConnection{
    void setSSLContext(SSLContext context);

}
