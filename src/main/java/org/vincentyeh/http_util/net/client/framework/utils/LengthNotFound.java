package org.vincentyeh.http_util.net.client.framework.utils;

import java.io.IOException;

public class LengthNotFound extends IOException {
    public LengthNotFound(String message) {
        super(message);
    }
}
