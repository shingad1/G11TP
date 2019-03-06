package com.sps.game.maps;

public class TropicalWorld extends Map{

    /**
     * Holds the file path of the Home World map
     */
    private static String mapPath = ASSETS_PATH + "TropicalWorld/TropicalWorldMap1.tmx";

    public TropicalWorld() {
        super(MapFactory.MapType.TropicalWorld1, mapPath);
    }
}
