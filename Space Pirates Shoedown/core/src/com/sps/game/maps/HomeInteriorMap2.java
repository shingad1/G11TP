package com.sps.game.maps;

/**
 * This class creates the second house interior map.
 * @author Miraj Shah
 * @version 1.0
 */
public class HomeInteriorMap2 extends Map {

    /**
     * Holds the file path of the Home World map
     */
    private static String mapPath = ASSETS_PATH + "HomeWorld/HouseInterior/Interior.tmx";

    public HomeInteriorMap2() {
        super(MapFactory.MapType.HomeInterior2, mapPath);
    }
}
