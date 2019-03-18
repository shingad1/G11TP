package com.sps.game.maps;

/**
 * This class creates the first map of the Home World.
 * @author Miraj Shah
 * @version 1.0
 */
public class HomeWorldMap extends Map {

    /**
     * Holds the file path of the Home World map
     */
    private static String mapPath = ASSETS_PATH + "HomeWorld/HomeWorldMap1.tmx";

    public HomeWorldMap() {
        super(MapFactory.MapType.HomeWorldMap1, mapPath);
    }

    //Have methods to load music/special features for the world
}
