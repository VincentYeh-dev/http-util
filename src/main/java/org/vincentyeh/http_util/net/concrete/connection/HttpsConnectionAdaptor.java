package org.vincentyeh.http_util.net.concrete.connection;

import org.vincentyeh.http_util.net.framework.connection.HttpsConnection;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;

public class HttpsConnectionAdaptor extends HttpConnectionAdaptor implements HttpsConnection{
    private final HttpsURLConnection connection;

    public HttpsConnectionAdaptor(HttpsURLConnection connection, boolean doInput, boolean doOutput) {
        super(connection,doInput,doOutput);
        this.connection = connection;
    }


    @Override
    public void setSSLContext(SSLContext context) {
        connection.setSSLSocketFactory(context.getSocketFactory());
    }
}
