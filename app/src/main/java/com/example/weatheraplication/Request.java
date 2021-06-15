package com.example.weatheraplication;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class Request extends AsyncTask<String,Integer,String> {

    String myUrl;
    Context context;
    String singleParsed = "";

    Request(Context context){
        this.context = context;
    }


    @Override
    protected String doInBackground(String... params) {
        myUrl = params[0];
        try {
            URL url = new URL(myUrl);
            HttpsURLConnection urlConnection = (HttpsURLConnection) url.openConnection();
            urlConnection.setRequestProperty("Accept","application/json");

            // read the output from the server
            BufferedReader reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            StringBuilder stringBuilder = new StringBuilder();

            String line = null;
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line + "\n");
            }

            return stringBuilder.toString();

        }
        catch (Exception e) {
            e.printStackTrace();
            return e.toString();
        }
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);

        String def;
           try{
            JSONArray js = new JSONArray(s);

                JSONObject JO = (JSONObject) js.get(0);
                singleParsed =  "Title:" + JO.get("title") + "\n"+
                                "Latt_long" + JO.get("latt_long") + "\n"+
                                "Location_type" + JO.get("location_type") + "\n";

               Toast.makeText(context, singleParsed, Toast.LENGTH_SHORT).show();

        }catch(JSONException e){
            e.printStackTrace();
        }
    }
}

