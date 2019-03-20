package com.sps.game.maps;
/**
 * This class creates the east map of the Graveyard world.
 * @author Miraj Shah
 * @version 1.0
 */
public class GraveyardEastMap extends Map{

    /**
     * Holds the file path of the Graveyard east map
     */
    private static String mapPath = ASSETS_PATH + "Graveyard/GraveyardEast.tmx";

    public GraveyardEastMap() {
        super(MapFactory.MapType.GraveyardEast, mapPath);
    }
}
