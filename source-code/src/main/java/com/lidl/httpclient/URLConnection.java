package com.lidl.httpclient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class URLConnection {

    private static String urlAddress = "http://10.7.13.152:8080/api/get";
    private URL url;
    private HttpURLConnection uRLConnection;


    private String doGet(String username, String password) {
        String getUrl = urlAddress + "/" + username + "/" + password;
        try {
            url = new URL(getUrl);
            uRLConnection = (HttpURLConnection) url.openConnection();
            InputStream is = uRLConnection.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String response = "";
            String readLine = null;
            while ((readLine = br.readLine()) != null) {
                //response = br.readLine();
                response = response + readLine;
            }
            is.close();
            br.close();
            uRLConnection.disconnect();
            return response;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) {
        URLConnection urlConnection = new URLConnection();
        String returnValue = urlConnection.doGet("admin", "123");
        System.out.println("retuenValue = " + returnValue);
    }
}
