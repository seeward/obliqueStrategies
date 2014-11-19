package com.seeward.obliquestrategies;



import org.json.JSONObject;

public class Strategies {
    String ID;
    String STRATEGY;

    @Override
    public String toString() {


        return this.getStrategy();
    }

    public void Strategy (JSONObject json)
    {
        this.ID = json.optString("ID", null);
        this.STRATEGY = json.optString("STRATEGY", null);

    }

    public void Strategy ()
    {
        this.ID = "";
        this.STRATEGY = "";

    }

    String getStrategy() {
        return this.STRATEGY;
    }
    String getID() {
        return this.ID;
    }



}