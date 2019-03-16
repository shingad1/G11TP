package com.sps.game.maps;

/**
 * This class creates the first tropical house interior map.
 * @author Miraj Shah
 * @version 1.0
 */
public class TropicalInteriorMap extends Map {

    /**
     * Holds the file path of the first candyland interior map
     */
    private static String mapPath = ASSETS_PATH + "TropicalWorld/HouseInterior/HouseInterior1.tmx";

    public TropicalInteriorMap() {
        super(MapFactory.MapType.TropicalInterior1, mapPath);
    }
}
