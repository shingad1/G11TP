package com.sps.game.maps;

public class HomeWorldMap2 extends Map {

    /**
     * Holds the file path of the Home World map 2
     */
    private static String mapPath = ASSETS_PATH + "HomeWorld/HomeWorldMap2.tmx";

    public HomeWorldMap2() {
        super(MapFactory.MapType.HomeWorldMap2, mapPath);
    }

    //methods for sound, special features and npcs
}
