package com.sps.game.maps;

/**
 * This class creates the first map of Candy Land.
 * @author Miraj Shah
 * @version 1.0
 */
public class CandyWorldMap extends Map {

    /**
     * Holds the file path of the Home World map
     */
    private static String mapPath = ASSETS_PATH + "CandyLand/CandyLandMap1.tmx";

    public CandyWorldMap() {
        super(MapFactory.MapType.CandyWorld1, mapPath);
    }
}
