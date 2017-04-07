package com.example.holmesk.weektest;

import android.os.AsyncTask;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * 作者：holmes k
 * 时间：2017.04.07 20:21
 */


public class Util extends AsyncTask<String, String, String> {


    private InputStream inputStream;
    private OnReadData readData;

    @Override
    protected String doInBackground(String... strings) {
        try {
            URL url = new URL(strings[0]);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            if (connection.getResponseCode() == 200) {
                InputStream inputStream = connection.getInputStream();
                byte b[] = new byte[1024];
                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                int len = 0;
                while ((len = inputStream.read(b)) != -1) {
                    bos.write(b, 0, len);
                }
                return bos.toString();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        readData.getData(s);

    }

    public void setGetData(OnReadData readData) {
        this.readData = readData;
    }

    interface OnReadData {
        public void getData(String data);
    }
}
