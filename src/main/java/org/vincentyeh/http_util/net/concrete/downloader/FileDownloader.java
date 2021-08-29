package org.vincentyeh.http_util.net.concrete.downloader;

import org.vincentyeh.http_util.net.framework.downloader.URLDownloader;
import org.vincentyeh.http_util.net.framework.downloader.listener.URLDownloaderListener;

import java.io.*;
import java.math.BigDecimal;
import java.net.URL;

public class FileDownloader extends URLDownloader<File> {
    private static int bufferSize=65536;

    public static void setBufferSize(int bufferSize){
        FileDownloader.bufferSize=bufferSize;
    }

    private BigDecimal downloadBytes = new BigDecimal(0);
    private final File target;

    public FileDownloader(URL url, File target, int timeoutMillis) {
        super(url, timeoutMillis);
        this.target = target;
    }

    @Override
    protected void handleInputStream(InputStream inputStream, URLDownloaderListener listener) throws IOException {
        downloadBytes=new BigDecimal(0);
        OutputStream targetFileStream = new FileOutputStream(target);
        try{
            byte[] bytes = new byte[bufferSize];
            int len;
            while ((len = inputStream.read(bytes)) != -1) {
                targetFileStream.write(bytes, 0, len);
                synchronized (this) {
                    downloadBytes = downloadBytes.add(new BigDecimal(len));
                    if(listener!=null)
                        listener.download(this,downloadBytes);
                }
            }

        }finally {
            targetFileStream.flush();
            targetFileStream.close();

            if(listener!=null)
                listener.done(this,downloadBytes);
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
    public BigDecimal getDownloadedBytes() {
        return downloadBytes;
    }
}
