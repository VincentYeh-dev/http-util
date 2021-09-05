package org.vincentyeh.http_util.net.client.framework.downloader.listener;

import org.vincentyeh.http_util.net.client.framework.downloader.adaptor.InputStreamAdaptor;

import java.io.IOException;
import java.math.BigDecimal;

public interface DownloaderListener<ADAPTOR extends InputStreamAdaptor> {
    void start(ADAPTOR adaptor);

    void download(ADAPTOR adaptor, BigDecimal downloadedBytes);

    void done(ADAPTOR adaptor, BigDecimal downloadedBytes);

    void onIoException(ADAPTOR adaptor, IOException e);
}
