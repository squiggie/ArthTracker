package com.arthtracker.arthtracker;

/**
 * Created by squigge on 8/25/2015.
 */
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONObject;

import android.content.Context;
import android.os.AsyncTask;

public class WeatherAPI extends AsyncTask<String, Void, JSONObject> {

    private String YAHOO_API = "https://query.yahooapis.com/v1/public/yql?q=select * from weather.forecast where woeid in (select woeid from geo.places(1) where text=\"%s\")";
    private URL url;

    @Override
    protected JSONObject doInBackground(String... params) {
        try {
            if (params[0] != null){//zip
                url = new URL(String.format(YAHOO_API, params[0]));
            }
            else {
                String loc = params[1] + ", " + params[2]; //params[1] = city params[2] = state
                url = new URL(String.format(YAHOO_API, loc));
            }

            HttpURLConnection connection = (HttpURLConnection)url.openConnection();

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            StringBuffer json = new StringBuffer(1024);
            String tmp = "";
            while((tmp=reader.readLine())!=null)
                json.append(tmp).append("\n");
            reader.close();

            JSONObject data = new JSONObject(json.toString());

            // This value will be 404 if the request was not
            // successful
            if(data.getInt("cod") != 200){
                return null;
            }

            return data;
        }
        catch(Exception e){
            return null;
        }
    }
}
