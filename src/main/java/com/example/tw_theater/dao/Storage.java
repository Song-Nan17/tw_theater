package com.example.tw_theater.dao;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;

public class Storage {
    public static void main(String[] args) {
        String ticket_url = "http://api.douban.com/v2/movie/in_theaters";
        String response = getData(ticket_url);
        JSONObject root = JSONObject.parseObject(response, (Type) Object.class);
        String count = root.getString("count");
        System.out.println(count);
        JSONArray movies = root.getJSONArray("subjects");
        Object movie = movies.getObject(0, Object.class);
        System.out.println(movie);
    }

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
