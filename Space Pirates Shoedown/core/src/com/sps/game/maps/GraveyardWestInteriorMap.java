package com.sps.game.maps;

/**
 * This class creates the western interior map of the Graveyard world.
 * @author Miraj Shah
 * @version 1.0
 */
public class GraveyardWestInteriorMap extends Map{

    /**
     * Holds the file path of the Graveyard western interior map
     */
    private static String mapPath = ASSETS_PATH + "Graveyard/GraveyardWestInterior.tmx";

    public GraveyardWestInteriorMap() {
        super(MapFactory.MapType.GraveyardWestInterior, mapPath);
    }
}
