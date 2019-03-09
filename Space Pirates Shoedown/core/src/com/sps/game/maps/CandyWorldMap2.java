package com.sps.game.maps;

/**
 * This class creates the second map of Candy Land.
 * @author Miraj Shah
 * @version 1.0
 */
public class CandyWorldMap2 extends Map {

    /**
     * Holds the file path of the Home World map
     */
    private static String mapPath = ASSETS_PATH + "CandyLand/CandyLandMap2.tmx";

    public CandyWorldMap2() {
        super(MapFactory.MapType.CandyWorld2, mapPath);
    }
}
