package com.sps.game.profile;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
/*
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;*/
/*java -classpath .;json-simple-1.1.1.jar*/
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonValue;

public class test {

    /*JSONParser parser;
    JSONArray array;*/

    public static final String JSON_FILE="default.json";


    public void test() {
        /*JSONParser parser = new JSONParser();

        try {
            Object obj = parser.parse(new FileReader("default.json"));
            JSONObject jsonObject = (JSONObject) obj;
            String name = (String) jsonObject.get("name");

            Long x = Long.parseLong((String) jsonObject.get("x"));

            //int x = Integer.parseInt((String) jsonObject.get("x:"));
            System.out.println(x);

           *//* JsonArray jsonArray = jsonObject.getJsonArray("phoneNumbers");
            long[] numbers = new long[jsonArray.size()];
            int index = 0;
            for(JsonValue value : jsonArray){
                numbers[index++] = Long.parseLong(value.toString());
            }*//*

        }catch(FileNotFoundException e){
            e.printStackTrace();
        }catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        catch (Exception e){
            e.printStackTrace();
        }*/

       /* InputStream fis = null;
        try {
            fis = new FileInputStream(JSON_FILE);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        JsonReader jsonReader = Json.createReader(fis);
        JsonObject jsonObject = jsonReader.readObject();
        jsonReader.close();
        try {
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        String name = jsonObject.getString("name");
        int x = jsonObject.getInt("x");
        System.out.print(x);*/

        /*int x = 0;
        int y = 0;
        ProfileManager profileManager = new ProfileManager();
        InputStream fis = null;
        JsonReader jsonReader;
        JsonObject jsonObject;
        if (profileManager.doesProfileExist("default")) {
            System.out.print("exist");
            try {
                fis = new FileInputStream("default.json");
                System.out.print("reading");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            jsonReader = Json.createReader(fis);
            jsonObject = jsonReader.readObject();
            //String temp = jsonObject.getString("homeWorldMap2StartPosition");
            JsonObject innerJsonObject = jsonObject.getJsonObject("homeWorldMap2StartPosition");
            x = innerJsonObject.getInt("x");
            y = innerJsonObject.getInt("y");
            //x = jsonObject.getInt("x");
            //y = jsonObject.getInt("y");
            System.out.print(x);
            System.out.print(y);
        }*/

        /*JsonObject innerJsonObject = jsonObject.getJsonObject("address");
        Address address = new Address();
        address.setStreet(innerJsonObject.getString("street"));
        address.setCity(innerJsonObject.getString("city"));
        address.setZipcode(innerJsonObject.getInt("zipcode"));
        emp.setAddress(address);*/

        /*jsonReader.close();
            try {
                fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }*/
        //System.out.print(x + "guy");
        //System.out.print(y);

    }
}
