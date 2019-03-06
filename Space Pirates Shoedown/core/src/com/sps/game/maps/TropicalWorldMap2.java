package com.sps.game.maps;

public class TropicalWorldMap2 extends Map {

    /**
     * Holds the file path of the Home World map
     */
    private static String mapPath = ASSETS_PATH + "TropicalWorldMap/TropicalWorldMap2.tmx";

    public TropicalWorldMap2() {
        super(MapFactory.MapType.TropicalWorld2, mapPath);
    }
}
