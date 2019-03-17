package com.sps.game.maps;

import java.util.Hashtable;

/**
 * This class holds each type of map and keeps them in a Hash Table.
 * @author Miraj Shah
 * @version 1.0
 */
public class MapFactory {

    /**
     * Holds all the map types used in the game
     */
    private static Hashtable<MapType, Map> mapTable = new Hashtable<MapType, Map>();

    public static enum MapType{
        HomeWorldMap1,
        HomeWorldMap2,
        HomeInterior,
        HomeInterior2,
        CandyWorld1,
        CandyWorld2,
        CandyInterior,
        CandyInterior2,
        CandyMansion,
        TropicalWorld1,
        TropicalWorld2,
        TropicalInterior1,
        TropicalInterior2,
        GraveyardWorld1,
        GraveyardWest
    }

    /**
     * Gets the map according to the MapType and adds it to the map table.
     * @param mapType
     * @return
     */
    static public Map getMap(MapType mapType){
        Map map = null;
        switch (mapType){
            case HomeWorldMap1:
                map = mapTable.get(MapType.HomeWorldMap1);
                if(map == null){
                   map = new HomeWorldMap();
                   mapTable.put(MapType.HomeWorldMap1, map);
                }
                break;
            case HomeWorldMap2:
                map = mapTable.get(MapType.HomeWorldMap2);
                if(map == null){
                    map = new HomeWorldMap2();
                    mapTable.put(MapType.HomeWorldMap2, map);
                }
                break;
            default:
                    break;
        }
        return map;
    }

    /**
     * Clears all the maps from the map table.
     */
    public static void clearCache(){
        for(Map map: mapTable.values()){
            map.dispose();
        }
        mapTable.clear();
    }
}
