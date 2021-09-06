package org.vincentyeh.http_util.net.client.concrete.downloader;

import org.vincentyeh.http_util.net.client.framework.downloader.InputStreamDownloader;
import org.vincentyeh.http_util.net.client.framework.downloader.adaptor.InputStreamAdaptor;
import org.vincentyeh.http_util.net.client.framework.downloader.listener.DownloaderListener;

import java.io.*;
import java.math.BigDecimal;

public class FileDownloader<ADAPTOR extends InputStreamAdaptor> extends InputStreamDownloader<File,ADAPTOR> {
    private static int bufferSize = 65536;

    public static void setBufferSize(int bufferSize) {
        FileDownloader.bufferSize = bufferSize;
    }

    private BigDecimal downloadBytes = new BigDecimal(0);
    private final File target;

    public FileDownloader(ADAPTOR adaptor, File target) {
        super(adaptor);
        this.target = target;
    }

    @Override
    protected void handleInputStream(InputStream inputStream, DownloaderListener<ADAPTOR> listener) throws IOException {
        OutputStream targetFileStream = new FileOutputStream(target);
        try {
            byte[] bytes = new byte[bufferSize];
            int len;
            while ((len = inputStream.read(bytes)) != -1) {
                targetFileStream.write(bytes, 0, len);
                synchronized (this) {
                    downloadBytes = downloadBytes.add(new BigDecimal(len));
                    if (listener != null)
                        listener.download(adaptor, downloadBytes);
                }
            }

        } finally {
            targetFileStream.flush();
            targetFileStream.close();

            if (listener != null)
                listener.done(adaptor, downloadBytes);
        }
    }

    @Override
    protected File getResult() {
        return target;
    }

    public File getTarget() {
        return target;
    }

    @Override
    protected void resetSubclass() {
        downloadBytes = new BigDecimal(0);
    }
}
