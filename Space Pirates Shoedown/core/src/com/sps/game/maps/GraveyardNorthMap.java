package com.sps.game.maps;
/**
 * This class creates the north map of the Graveyard world.
 * @author Miraj Shah
 * @version 1.0
 */
public class GraveyardNorthMap extends Map {

    /**
     * Holds the file path of the Graveyard north map
     */
    private static String mapPath = ASSETS_PATH + "Graveyard/GraveyardNorth.tmx";

    public GraveyardNorthMap() {
        super(MapFactory.MapType.GraveyardNorth, mapPath);
    }
}
