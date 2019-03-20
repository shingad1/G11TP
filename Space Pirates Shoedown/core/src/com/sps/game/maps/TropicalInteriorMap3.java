package com.sps.game.maps;

/**
 * This class creates the third tropical interior map.
 * @author Miraj Shah
 * @version 1.0
 */
public class TropicalInteriorMap3 extends Map {

    /**
     * Holds the file path of the third tropical World interior map
     */
    private static String mapPath = ASSETS_PATH + "TropicalWorld/HouseInterior/HouseInterior3.tmx";

    public TropicalInteriorMap3() {
        super(MapFactory.MapType.TropicalInterior3, mapPath);
    }
}
