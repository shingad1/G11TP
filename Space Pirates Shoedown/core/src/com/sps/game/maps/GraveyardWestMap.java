package com.sps.game.maps;
/**
 * This class creates the west map of the Graveyard world.
 * @author Miraj Shah
 * @version 1.0
 */
public class GraveyardWestMap extends Map {
    /**
     * Holds the file path of the Home World map
     */
    private static String mapPath = ASSETS_PATH + "Graveyard/GraveyardWest2.tmx";

    public GraveyardWestMap() {
        super(MapFactory.MapType.GraveyardWest, mapPath);
    }
}
