package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class Button {
    Texture texture;
    int x, y;

    public Button(String textureName) {
        texture = new Texture(textureName);
    }

    public void draw(){
        Storage.batch.draw(texture, x, y);
    }
    public void dispose(){
        texture.dispose();
    }
    public boolean isClicked(){
        if(Gdx.input.justTouched()){
            int touchY = Gdx.graphics.getHeight() - Gdx.input.getY();

            return (touchY > y && touchY < y + texture.getHeight() &&
                    Gdx.input.getX() > x && Gdx.input.getX() < x + texture.getWidth());
        }
        return false;
    }
    public void setX(int x) {
        this.x = x;
    }
    public void setY(int y) {
        this.y = y;
    }
}
