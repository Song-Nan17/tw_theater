package com.example.tw_theater.dao.initialization;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class Response {

    public static String getData(String url) {
        String response = null;
        String charset = "UTF-8";
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        HttpURLConnection connect = null;
        try {
            connect = (HttpURLConnection) (new URL(url).openConnection());
            connect.setRequestMethod("GET");
            connect.setDoOutput(true);
            connect.setConnectTimeout(1000 * 10);
            connect.setReadTimeout(1000 * 80);
            connect.setRequestProperty("ContentType", "application/x-www-form-urlencoded");
            connect.setDoInput(true);
            connect.connect();
            int responseCode = connect.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                InputStream in = connect.getInputStream();
                byte[] data = new byte[1024];
                int len = 0;
                while ((len = in.read(data, 0, data.length)) != -1) {
                    outStream.write(data, 0, len);
                }
                in.close();
            }
            response = outStream.toString(charset);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            connect.disconnect();
            return response;
        }
    }

}
