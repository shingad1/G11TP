package com.sps.game.maps;

/**
 * This class creates the first house interior map.
 * @author Miraj Shah
 * @version 1.0
 */
public class HomeInteriorMap extends Map {

    /**
     * Holds the file path of the first homeworld interior map
     */
    private static String mapPath = ASSETS_PATH + "HomeWorld/HouseInterior/HouseInterior1.tmx";

    public HomeInteriorMap() {
        super(MapFactory.MapType.HomeInterior, mapPath);
    }
}
