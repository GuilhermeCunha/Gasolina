package com.example.savegas.Mapa.Helpers;

import android.util.Log;

import com.google.android.gms.maps.model.LatLng;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;


public class MapsDistanceMatrix {
    public static InputStream is = null;
    public static JSONObject jObj = null;
    public static String json = "";

    public static double getDistanceInfo(LatLng a, LatLng d) {
        String url = "http://maps.googleapis.com/maps/api/distancematrix/json?origins=" +
                a.latitude + "," + a.longitude +
                "&destinations=" +
                d.latitude + "," + d.longitude +
                "&departure_time=now&key=AIzaSyAdFxhY1adIeIwLc2iUKEbLtjlw6LJmgWc";

        // Making HTTP request
        try {
            // defaultHttpClient
            DefaultHttpClient httpClient = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost(url);

            HttpResponse httpResponse = httpClient.execute(httpPost);
            HttpEntity httpEntity = httpResponse.getEntity();
            is = httpEntity.getContent();

        } catch (UnsupportedEncodingException e) {
        } catch (ClientProtocolException e) {
        } catch (IOException e) {
        }

        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    is, "iso-8859-1"), 8);
            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
                //System.out.println(line);
            }
            is.close();
            json = sb.toString();

        } catch (Exception e) {
            //alterei
            //System.out.println("Buffer Error", "Error converting result " + e.toString());
        }

        // try parse the string to a JSON object
        try {
            jObj = new JSONObject(json);
        } catch (JSONException e) {
            System.out.println("error on parse data in jsonparser.java");
        }
        JSONObject jsonObject = new JSONObject();
        try {
            Log.e("DISTANCIA", "TO NO ULTIMO TRY");
/*
            jsonObject = new JSONObject(stringBuilder.toString());

            JSONArray array = jsonObject.getJSONArray("routes");

            JSONObject routes = array.getJSONObject(0);

            JSONArray legs = routes.getJSONArray("legs");

            JSONObject steps = legs.getJSONObject(0);

            JSONObject distance = steps.getJSONObject("distance");

            Log.e("DISTANCIA", distance.toString());
            dist = Double.parseDouble(distance.getString("text").replaceAll("[^\\.0123456789]","") );
*/
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return 0.0;
    }
}
