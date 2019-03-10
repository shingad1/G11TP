package com.sps.game.maps;

/**
 * This class creates the first candy house interior map.
 * @author Miraj Shah
 * @version 1.0
 */
public class CandyInteriorMap extends Map {

    /**
     * Holds the file path of the first candyland interior map
     */
    private static String mapPath = ASSETS_PATH + "CandyLand/HouseInterior/HouseInterior1.tmx";

    public CandyInteriorMap() {
        super(MapFactory.MapType.CandyInterior, mapPath);
    }
}
