package com.sps.game.maps;

/**
 * This class creates the second map of Home World.
 * @author Miraj Shah
 * @version 1.0
 */
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
