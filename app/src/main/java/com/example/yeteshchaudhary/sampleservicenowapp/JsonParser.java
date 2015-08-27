package com.example.yeteshchaudhary.sampleservicenowapp;


/**
 * Created by yetesh.chaudhary on 7/30/15.
 */


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

public class JsonParser
{
    static List<String> getKeys(String s)
    {
        try
        {
            JSONObject responseObject = (JSONObject) new JSONTokener(s).nextValue();
            JSONArray earthquakes = responseObject.getJSONArray("result");
            JSONObject earthquake = (JSONObject) earthquakes.get(0);
            Iterator keysToCopyIterator = earthquake.keys();
            List<String> keysList = new ArrayList<String>();
            while(keysToCopyIterator.hasNext())
            {
                String key = (String) keysToCopyIterator.next();
                keysList.add(key);
            }
            return keysList;
        }
        catch(Exception e)
        {

        }
        return null;
    }

    public static List<String> getValues(String s, String key)
    {
        try
        {
            JSONObject responseObject = (JSONObject) new JSONTokener(s).nextValue();
            JSONArray earthquakes = responseObject.getJSONArray("result");
            List<String> valsList = new ArrayList<String>();
            for(int i=0;i<earthquakes.length();i++)
            {
                JSONObject earthquake = (JSONObject) earthquakes.get(i);
                valsList.add((String)earthquake.get(key));
            }
            return valsList;

        }
        catch(Exception e)
        {

        }
        return null;
    }

}

