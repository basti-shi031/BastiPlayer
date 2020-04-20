package com.basti.downloader;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;

public class Downloader {

    public static void download(String fileDownLoadPath, String dir, String fileName) throws IOException {
        URL url = new URL(fileDownLoadPath);
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        httpURLConnection.setRequestMethod("GET");
        httpURLConnection.setConnectTimeout(3000);
        if (httpURLConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
            int length = httpURLConnection.getContentLength();
            File file = new File(dir);
            if (!file.exists()) {
                file.mkdirs();
            }
            file = new File(dir, fileName);
            RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rwd");
            InputStream inputStream = httpURLConnection.getInputStream();
            byte[] bytes = new byte[1024 * 4];
            int len = -1;
            while ((len = inputStream.read(bytes)) != -1) {
                randomAccessFile.write(bytes, 0, len);
            }
        }
    }

}

