package org.vincentyeh.http_util.net.client.framework.connection.header;

import java.util.Properties;

public class Cookies extends Properties {

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        forEach((key, value) -> builder.append(key).append("=").append(value).append(";"));
        builder.deleteCharAt(builder.length() - 1);
        return builder.toString();
    }
}
