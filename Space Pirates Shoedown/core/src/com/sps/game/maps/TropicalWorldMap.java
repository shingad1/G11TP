package com.sps.game.maps;

/**
 * This class creates the first map of the Tropical World.
 * @author Miraj Shah
 * @version 1.0
 */
public class TropicalWorldMap extends Map{

    /**
     * Holds the file path of the Home World map
     */
    private static String mapPath = ASSETS_PATH + "TropicalWorld/TropicalWorldMap1.tmx"; //tropical map error

    public TropicalWorldMap() {
        super(MapFactory.MapType.TropicalWorld1, mapPath);
    }
}
