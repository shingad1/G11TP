package com.sps.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.sps.game.maps.Map;
import com.sps.game.maps.MapFactory;

public class MiniMapScreen {

    private MapFactory.MapType world;
    private Texture homeMap1, homeMap2, candyMap1, candyMap2, tropicalMap1, tropicalMap2, graveyardMap1, graveyardMap2;
    private SpriteBatch batch;

    public MiniMapScreen(MapFactory.MapType world){
        this.world = world;
        homeMap1 = new Texture("core/assets/Worlds/homeMap1.png");
        homeMap2 = new Texture("core/assets/Worlds/homeMap2.png");
    }

    private MapFactory.MapType map;

    public void miniMap() {
        if (Gdx.input.isKeyPressed(Input.Keys.M)) {
            if(world.equals(MapFactory.MapType.HomeWorldMap1)){
                batch.begin();
                batch.draw(homeMap1,PlayScreen.gamecam.position.x - 240,PlayScreen.gamecam.position.y - 240,480,480);
                batch.end();
            }
        }
    }

    public MapFactory.MapType getWorld () {
        return world;
    }
}
