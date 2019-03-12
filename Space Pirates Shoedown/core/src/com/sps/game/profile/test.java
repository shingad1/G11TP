package com.sps.game.profile;

import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
/*java -classpath.json-simple-1.1.1.jar;
        java -classpath .;json-simple-1.1.1.jar*/


public class test {

    static JSONParser parser;
    static JSONObject game;

    public void test(){


        parser = new JSONParser();
        JSONArray array = null;
        try {
            array = (JSONArray) parser.parse(new FileReader("core/src/com/sps/game/profile/test.json"));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        for (Object object : array) {
            game = (JSONObject) object;

            String n = "";

            if (n.equals("x :")) {
                String p = (String) game.get("x");
                int x = Integer.parseInt(p);
                System.out.println(x);
            }

            /*String strName = (String) gameState.get("name");
            System.out.println("Name: " + strName);

            int xx = Integer.getInteger("x");
            (long) x = gameState.get("x");
            System.out.println(x);
            int y = gameState.getInt("y");
            System.out.println(y);
*/
        }

    }
}
