package com.sps.game.maps;

/**
 * This class creates the first tropical house interior map.
 * @author Miraj Shah
 * @version 1.0
 */
public class TropicalInteriorMap2 extends Map {

    /**
     * Holds the file path of the first candyland interior map
     */
    private static String mapPath = ASSETS_PATH + "TropicalWorld/HouseInterior/HouseInterior2.tmx";

    public TropicalInteriorMap2() {
        super(MapFactory.MapType.TropicalInterior2, mapPath);
    }

}
