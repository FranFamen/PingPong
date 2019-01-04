package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class TopPaddle extends Paddle {
    Texture texture;
    int x, y;
    public TopPaddle() {
        texture = new Texture("paddle.bmp");
        x = (Gdx.graphics.getWidth() - texture.getWidth()) / 2;
        y = Gdx.graphics.getHeight() - PongGame.PIXELSFORTEXT;
    }

    @Override
    public void draw() {
        Storage.batch.draw(texture, x , y);
    }

    @Override
    public void move(Ball ball) {
        if (PongGame.isGameOver) {return;}
        x = (ball.getX() + ball.getWidth() / 2) - texture.getWidth() / 2;
        if(x > Gdx.graphics.getWidth() - texture.getWidth()){
            x = Gdx.graphics.getWidth() - texture.getWidth();
        }
        if(x < 0){
            x = 0;
        }
    }

    @Override
    public void dispose() {
        super.dispose();
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
