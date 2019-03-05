package com.sps.game.maps;

public class CandyWorldMap2 extends Map {

    /**
     * Holds the file path of the Home World map
     */
    private static String mapPath = ASSETS_PATH + "CandyLand/CandyLandMap2.tmx";

    public CandyWorldMap2() {
        super(MapFactory.MapType.CandyWorld2, mapPath);
    }
}
