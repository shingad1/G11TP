package com.sps.game.maps;

public class GraveyardMap extends Map{

    /**
     * Holds the file path of the Home World map
     */
    private static String mapPath = ASSETS_PATH + "Graveyard/GraveyardStart.tmx";

    public GraveyardMap() {
        super(MapFactory.MapType.GraveyardWorld1, mapPath);
    }
}
