package com.lidl.httpclient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * httpClient demo
 */
public class UrlConnection {


    private String doGet() {
        String urlAddress = "http://10.7.13.152:8080/api/get";
        String getUrl = urlAddress + "/" + "admin" + "/" + "123";
        try {
            URL url = new URL(getUrl);
            HttpURLConnection uRLConnection = (HttpURLConnection) url.openConnection();
            InputStream is = uRLConnection.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            StringBuilder response = new StringBuilder();
            String readLine = null;
            while ((readLine = br.readLine()) != null) {
                //response = br.readLine();
                response.append(readLine);
            }
            is.close();
            br.close();
            uRLConnection.disconnect();
            return response.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) {
        UrlConnection urlConnection = new UrlConnection();
        String returnValue = urlConnection.doGet();
        System.out.println("retuenValue = " + returnValue);
    }
}
