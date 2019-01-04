package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class Paddle {
    Texture texture;
    int x, y;

    public Paddle() {

    }
    public void draw(){

    }
    public void move(Ball ball) {
        return;
    }


    public void dispose(){
    }
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }


}
