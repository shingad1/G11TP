package com.sps.game.Sprites;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.*;

public class Player{

    //public World world;
    //public Body b2body;

    private int x;
    private int y;

    public Player(int x, int y){
        this.x = x;
        this.y = y;
       // this.world = world;
        //definePlayer();
    }

    public void move(int dx, int dy){
        x += dx;
        y += dy;
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    /*
    public void definePlayer(){
        BodyDef bdef = new BodyDef();
        bdef.position.set(32, 32); //temp
        bdef.type = BodyDef.BodyType.DynamicBody;
        b2body = world.createBody(bdef);
        FixtureDef fdef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(5);
        fdef.shape = shape;
        b2body.createFixture(fdef);
    }
    */
}
