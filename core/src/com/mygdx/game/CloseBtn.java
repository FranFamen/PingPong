package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class CloseBtn {
    Texture texture;
    int x ,y;

    public CloseBtn() {
        texture = new Texture("close_btn.png");

    }

    public void draw(){
        Storage.batch.draw(texture, x, y);
    }
    public void dispose(){
        texture.dispose();
    }
}
