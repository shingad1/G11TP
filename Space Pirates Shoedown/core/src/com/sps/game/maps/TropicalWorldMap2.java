package com.sps.game.maps;

/**
 * This class creates the second map of Tropical World.
 * @author Miraj Shah
 * @version 1.0
 */
public class TropicalWorldMap2 extends Map {

    /**
     * Holds the file path of the second Tropical World map
     */
    private static String mapPath = ASSETS_PATH + "TropicalWorld/TropicalWorldMap2.tmx";

    public TropicalWorldMap2() {
        super(MapFactory.MapType.TropicalWorld2, mapPath);
    }
}
