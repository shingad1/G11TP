package com.sps.game.maps;

public class CandyWorldMap extends Map {

    /**
     * Holds the file path of the Home World map
     */
    private static String mapPath = ASSETS_PATH + "CandyLand/CandyLandMap1.tmx";

    public CandyWorldMap() {
        super(MapFactory.MapType.CandyWorld1, mapPath);
    }
}
