package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class BottomPaddle extends Paddle {
    Texture texture;

    int x, y;
    public BottomPaddle() {
        texture = new Texture("paddle.bmp");
        x = (Gdx.graphics.getWidth() - texture.getWidth()) / 2;
        y = 0;
    }

    @Override
    public void draw() {
        Storage.batch.draw(texture, x,  y);

    }

    @Override
    public void move() {
        if(PongGame.isGameOver) {return;}
        if(Gdx.input.isTouched()){
            x = Gdx.input.getX() -  texture.getWidth() / 2;
            //preventing bottomPaddle from escaping left wall
            if( x < 0){
                x = 0;
            }
            //preventing bottomPaddle from escaping right wall
            if( x +  texture.getWidth() > Gdx.graphics.getWidth()){
                x = Gdx.graphics.getWidth() -  texture.getWidth();
            }
        }
    }

    @Override
    public void dispose() {
        texture.dispose();
    }

    @Override
    public int getX() {
        return super.getX();
    }

    @Override
    public int getY() {
        return super.getY();
    }

    @Override
    public void setX(int x) {
        super.setX(x);
    }

    @Override
    public void setY(int y) {
        super.setY(y);
    }
}
