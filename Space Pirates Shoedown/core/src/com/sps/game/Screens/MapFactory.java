package com.sps.game.Screens;

import java.util.Hashtable;

public class MapFactory {

    /**
     * Holds all the map types used in the game
     */
    private static Hashtable<MapType, Map> mapTable = new Hashtable<MapType, Map>();

    public static enum MapType{
        HomeWorldMap1,
        HomeWorldMap2   //need to add the others
    }

    public static Map getMap(MapType mapType){
        Map map = null;
        switch (mapType){
            case HomeWorldMap1:
                map = mapTable.get(MapType.HomeWorldMap1);
                if(map == null){
                   // map = new
                }
        }
        return null;
    }

}
