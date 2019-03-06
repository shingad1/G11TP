package com.sps.game.maps;

public class TropicalWorldMap extends Map{

    /**
     * Holds the file path of the Home World map
     */
    private static String mapPath = ASSETS_PATH + "TropicalWorldMap/TropicalWorldMap1.tmx";

    public TropicalWorldMap() {
        super(MapFactory.MapType.TropicalWorld1, mapPath);
    }
}
