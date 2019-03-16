package com.sps.game.maps;

/**
 * This class creates the second candy house interior map.
 * @author Miraj Shah
 * @version 1.0
 */
public class CandyInteriorMap2 extends Map {

    /**
     * Holds the file path of the first candyland interior map
     */
    private static String mapPath = ASSETS_PATH + "CandyLand/HouseInterior/HouseInterior2.tmx";

    public CandyInteriorMap2() {
        super(MapFactory.MapType.CandyInterior2, mapPath);
    }
}
