package com.sps.game.maps;

/**
 * This class creates the candy mansion interior map.
 * @author Miraj Shah
 * @version 1.0
 */
public class CandyMansionMap extends Map {

    /**
     * Holds the file path of the candyland mansion map
     */
    private static String mapPath = ASSETS_PATH + "CandyLand/HouseInterior/MansionInterior.tmx";

    public CandyMansionMap() {
        super(MapFactory.MapType.CandyMansion, mapPath);
    }
}
