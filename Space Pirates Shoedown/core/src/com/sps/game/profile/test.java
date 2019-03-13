package com.sps.game.profile;

import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
/*java -classpath .;json-simple-1.1.1.jar*/

public class test {

    JSONParser parser;
    JSONArray array;

    public void test() {
        parser = new JSONParser();
        try {
            array = (JSONArray) parser.parse(new FileReader("core/src/com/sps/game/profile/test.json"));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        for (Object o : array) {
            JSONObject obj = (JSONObject) o;

            int x = Integer.parseInt((String) obj.get("x"));
            //System.out.println(x);

            int y = Integer.parseInt((String) obj.get("y"));
            //System.out.println(y);
        }
    }
}
