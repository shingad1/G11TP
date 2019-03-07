package com.sps.game.maps;

public class HomeInteriorMap2 extends Map {

    /**
     * Holds the file path of the Home World map
     */
    private static String mapPath = ASSETS_PATH + "HomeWorld/HouseInterior/Interior.tmx";

    public HomeInteriorMap2() {
        super(MapFactory.MapType.HomeInterior2, mapPath);
    }
}
