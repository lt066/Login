package com.example.administrator.login.volly;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;


/**
 * Created by lt413 on 2015/12/27.
 */
public class MyJsonObject {

    private String url;
    private JSONObject myJsonObject =null;

    public MyJsonObject(String url)
    {
        this.url = url;
    }

    public JSONObject JsonRequest() throws IOException {
        URL  img_url = new URL(url);
        URLConnection urlConnection = img_url.openConnection();
        HttpURLConnection httpURLConnection = (HttpURLConnection) urlConnection;

        httpURLConnection.setConnectTimeout(5000); //设置连接超时为5秒
        // http正文内，因此需要设为true, 默认情况下是false;
        httpURLConnection.setDoOutput(true);

        // 设置是否从httpUrlConnection读入，默认情况下是true;
        httpURLConnection.setDoInput(true);

        // 设定请求的方法为"POST"，默认是GET
        httpURLConnection.setRequestMethod("GET");

        // Post 请求不能使用缓存
//       httpURLConnection.setUseCaches(false);

//        DataInputStream dis = new DataInputStream(httpURLConnection.getInputStream());
        InputStream is = httpURLConnection.getInputStream();
        byte[] data = new byte[0];
        try {
            data = StreamTool.readInputStream(is);
        } catch (Exception e) {
            e.printStackTrace();
        }
        String json = new String(data);
        try {
            myJsonObject = new JSONObject(json);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return myJsonObject;
    }
    public static class StreamTool
    {
        /**
         * 从输入流中获取数据
         * @param inStream 输入流
         * @return
         * @throws Exception
         */
        public static byte[] readInputStream(InputStream inStream) throws Exception{
            ByteArrayOutputStream outStream = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int len = 0;
            while( (len=inStream.read(buffer)) != -1 ){
                outStream.write(buffer, 0, len);
            }
            inStream.close();
            return outStream.toByteArray();
        }
    }



}
