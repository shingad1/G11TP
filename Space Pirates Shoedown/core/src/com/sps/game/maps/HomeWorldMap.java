package com.sps.game.maps;

public class HomeWorldMap extends Map {

    /**
     * Holds the file path of the Home World map
     */
    private static String mapPath = ASSETS_PATH + "HomeWorld/HomeWorldMap1.tmx";

    HomeWorldMap() {
        super(MapFactory.MapType.HomeWorldMap1, mapPath);
    }

    //Have methods to load music/special features for the world
}
