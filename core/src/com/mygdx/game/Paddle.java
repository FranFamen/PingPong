package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class Paddle {
    Texture texture;

    int x, y;

    public Paddle() {
        texture = new Texture("paddle.bmp");
        x = (Gdx.graphics.getWidth() - texture.getWidth()) / 2;
        y = 0;
    }
    public void draw(){
        Storage.batch.draw(texture, x,  y);
    }
    public void move() {
        if(PongGame.isGameOver) {return;}
        if(Gdx.input.isTouched()){
             x = Gdx.input.getX() -  texture.getWidth() / 2;
            //preventing paddle from escaping left wall
            if( x < 0){
                 x = 0;
            }
            //preventing paddle from escaping right wall
            if( x +  texture.getWidth() > Gdx.graphics.getWidth()){
                 x = Gdx.graphics.getWidth() -  texture.getWidth();
            }
        }
    }


    public void dispose(){
        texture.dispose();
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
