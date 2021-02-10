package com.springproject.clientmanager.helpers;

import com.google.gson.Gson;
import com.google.gson.JsonElement;

public class JsonUtility {
    public static String addPropertyToObject(Object object, String key, String value){
        Gson gson = new Gson();
        JsonElement jsonElement = gson.toJsonTree(object);
        jsonElement.getAsJsonObject().addProperty(key,value);
        String jsonString = jsonElement.getAsString();
        return jsonString;
    }
}
