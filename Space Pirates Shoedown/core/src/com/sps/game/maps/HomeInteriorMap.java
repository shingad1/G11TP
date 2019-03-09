package com.sps.game.maps;

public class HomeInteriorMap extends Map {

    /**
     * Holds the file path of the Home World map
     */
    private static String mapPath = ASSETS_PATH + "HomeWorld/HouseInterior/Interior.tmx";

    public HomeInteriorMap() {
        super(MapFactory.MapType.HomeInterior, mapPath);
    }
}
