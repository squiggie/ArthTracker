package com.arthtracker.arthtracker;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.util.Log;

public class JSONclient extends AsyncTask<String, Void, JSONObject>{
    GetJSONListener getJSONListener;
    Context curContext;
    Exception exceptionToBeThrown;

    public JSONclient(Context context, GetJSONListener listener){
        this.getJSONListener = listener;
        curContext = context;
    }
    private static String convertStreamToString(InputStream is) {
        /*
         * To convert the InputStream to String we use the BufferedReader.readLine()
         * method. We iterate until the BufferedReader return null which means
         * there's no more data to read. Each line will appended to a StringBuilder
         * and returned as String.
         */
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();

        String line = null;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return sb.toString();
    }


    public JSONObject connect(String url)
    {
        HttpClient httpclient = new DefaultHttpClient();

        // Prepare a request object
        HttpGet httpget = new HttpGet(url);

        // Execute the request
        HttpResponse response;
        try {
            response = httpclient.execute(httpget);

            // Get hold of the response entity
            HttpEntity entity = response.getEntity();

            if (entity != null) {

                // A Simple JSON Response Read
                InputStream instream = entity.getContent();
                String result= convertStreamToString(instream);

                // A Simple JSONObject Creation
                JSONObject json=new JSONObject(result);

                // Closing the input stream will trigger connection release
                instream.close();

                return json;
            }


        } catch (ClientProtocolException e) {
            exceptionToBeThrown = e;
        } catch (IOException e) {
            exceptionToBeThrown = e;
        } catch (JSONException e) {
            exceptionToBeThrown = e;
        }
        return null;
    }
    @Override
    public void onPreExecute() {
        //progressDialog = new ProgressDialog(curContext);
        //progressDialog.setMessage("Getting Data. Please wait....");
        //progressDialog.setCancelable(false);
        //progressDialog.setIndeterminate(true);
        //progressDialog.show();
    }

    @Override
    protected JSONObject doInBackground(String... urls) {
        return connect(urls[0]);
    }

    @Override
    protected void onPostExecute(JSONObject json ) {
        if (json != null){
            getJSONListener.onRemoteCallComplete(json);
        }
        else {
            AlertDialog.Builder builder = new AlertDialog.Builder(curContext);
            builder.setMessage("Error getting weather data. Weather information may not be saved for today!")
                .setTitle("Connection Error")
                .setNeutralButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                }).create().show();
        }
    }
}
