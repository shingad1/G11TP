package com.sps.game.maps;
/**
 * This class creates the eastern interior map of the Graveyard world.
 * @author Miraj Shah
 * @version 1.0
 */
public class GraveyardEastInteriorMap extends Map{

    /**
     * Holds the file path of the Graveyard east interior map
     */
    private static String mapPath = ASSETS_PATH + "Graveyard/GraveyardEastInterior.tmx";


    public GraveyardEastInteriorMap() {
        super(MapFactory.MapType.GraveyardEastInterior, mapPath);
    }
}
