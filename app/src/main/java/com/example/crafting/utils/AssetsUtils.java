package com.example.crafting.utils;

import android.content.Context;

import com.example.crafting.Data;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class AssetsUtils {
    private Context context;

    public AssetsUtils(Context context) {
        this.context = context;
    }

    public String loadJSONFromAsset() {
        String json = null;
        try {
            InputStream is = context.getAssets().open("data.json");
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

    public  List<Data>getData(String jsondata){
        Gson gson= new Gson();
        TypeToken<List<Data>> token= new TypeToken<List<Data>>(){};
        return gson.fromJson(jsondata,token.getType());
    }

}
