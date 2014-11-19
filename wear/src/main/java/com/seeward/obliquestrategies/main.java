package com.seeward.obliquestrategies;

import android.app.Activity;
import android.os.Bundle;
import android.support.wearable.view.WatchViewStub;
import android.view.View;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.Random;

public class main extends Activity {

    private TextView mTextView;
    private static String json;
    private static JSONObject selectedRandomObject;
    private static String strat;
    private static JSONObject all;
    private static JSONArray strategies;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final WatchViewStub stub = (WatchViewStub) findViewById(R.id.watch_view_stub);
        stub.setOnLayoutInflatedListener(new WatchViewStub.OnLayoutInflatedListener() {
            @Override
            public void onLayoutInflated(WatchViewStub stub) {
                mTextView = (TextView) stub.findViewById(R.id.mainText);
                mTextView.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                       getRandom();
                    }
                });
             getRandom();

            }
        });
    }


    public void getRandom(){
        json = loadJSONFromAsset();


        try {
            all = new JSONObject(json);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        try {
            strategies = all.getJSONArray("all");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        int len = strategies.length();

        Random r = new Random();
        int randomObjectIndex = r.nextInt(len-0) + 0;

        try {
            selectedRandomObject = strategies.getJSONObject(randomObjectIndex);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        try {
            strat = selectedRandomObject.getString("strategy");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        mTextView.setText(strat);
    }



    public String loadJSONFromAsset() {
        String json = null;
        try {

            InputStream is = getAssets().open("strategies.json");

            int size = is.available();

            byte[] buffer = new byte[size];

            is.read(buffer);

            is.close();

            json = new String(buffer, "UTF-8");



        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;

    }




}
