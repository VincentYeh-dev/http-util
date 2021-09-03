package org.vincentyeh.http_util.net.client.concrete.connection;

import org.vincentyeh.http_util.net.client.framework.connection.Request;
import org.vincentyeh.http_util.net.client.framework.connection.RequestMethod;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class RequestConnectionAdaptor extends Request {
    private final HttpURLConnection connection;
    private byte[] body;
    public RequestConnectionAdaptor(HttpURLConnection connection) {
        this.connection = connection;
    }

    @Override
    public Request setHeaders(Map<String, List<String>> map){
        if(map==null)
            return this;

        Set<String> keys=map.keySet();
        for(String key:keys){
            connection.setRequestProperty(key,listToString(map.get(key)));
        }
        return this;
    }

    private String listToString(List<String> list){
        return String.join(",", list);
    }

    @Override
    public Map<String, List<String>> getHeaders(){
        return connection.getRequestProperties();
    }

    @Override
    public URL getUrl() {
        return connection.getURL();
    }

    @Override
    public RequestMethod getMethod() {
        return RequestMethod.valueOf(connection.getRequestMethod());
    }

    @Override
    public Request setMethod(RequestMethod method) throws ProtocolException {
        connection.setRequestMethod(method.toString());
        return this;
    }

    @Override
    public void setBody(byte[] body) {
        this.body=body;
        connection.setFixedLengthStreamingMode(body!=null?body.length:0);
    }

    @Override
    public void sendBody() throws IOException {
        if(body==null)
            return;
        connection.getOutputStream().write(body);
    }

    @Override
    public HttpURLConnection open() throws IOException {
        connection.connect();
        return connection;
    }


}
