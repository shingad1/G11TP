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

        InputStream fis = null;
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
        System.out.print(x);
    }
}
