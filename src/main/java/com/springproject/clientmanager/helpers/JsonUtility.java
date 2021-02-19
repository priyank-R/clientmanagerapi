package com.springproject.clientmanager.helpers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.*;
import com.springproject.clientmanager.domains.Client;

public class JsonUtility {
    public static JsonNode addPropertyToObject(Object object, String key, String value){
        System.out.println("Received Object is: "+ object.toString());
        try{
            String jsonString = JsonUtility.ObjectToString(object);
            JsonObject jsonObject = new JsonParser().parse(jsonString).getAsJsonObject();
            jsonObject.addProperty(key,value);

            jsonString = jsonObject.toString();
            ObjectMapper om = new ObjectMapper();
            JsonNode obj = om.readTree(jsonString);
           return obj;
        }catch(Exception e){
            System.out.println("addPropertyToObject Error: "+e);
            e.printStackTrace();
            return null;
        }
    }

    public static JsonObject buildJsonObject(String key, String value){
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty(key,value);
        return jsonObject;
    }

    public static String ObjectToString(Object object){
        try{

            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            return gson.toJson(object);
        }catch(Exception e){
            System.out.println("ObjectToString method: " + e);
            return null;
        }

    }

}
