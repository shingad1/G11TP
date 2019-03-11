/*
package com.sps.game.profile;

import org.json.JSONArray;
import org.json.JSONObject;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


public class test {
    public static void main(String[] args) {
        try {
            FileReader fileReader = new FileReader("default.sav");
            JSONArray jsonArray = new jsonReader.readArray();
            JSONParser parser = new JSONParser();
            JSONObject jsonObject = (JSONObject) parser.parse(fileReader);
            //JSONArray jsonArray = (JSONArray) parser.parse(new FileReader("default.sav"));
        }catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (ParseException ex) {
            ex.printStackTrace();
        } catch (NullPointerException ex) {
            ex.printStackTrace();
        }

        for (
                Object o : jsonArray) {
            JSONObject person = (JSONObject) o;

            String strName = (String) person.get("name");
            System.out.println("Name::::" + strName);

            String strCity = (String) person.get("city");
            System.out.println("City::::" + strCity);

            JSONArray arrays = (JSONArray) person.get("cars");
            for (Object object : arrays) {
                System.out.println("cars::::" + object);
            }
            String strJob = (String) person.get("job");
            System.out.println("Job::::" + strJob);
            System.out.println();
        }

    }
}
*/
