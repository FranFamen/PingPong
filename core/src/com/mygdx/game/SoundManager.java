package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;

import java.util.Random;

public class SoundManager {
    private Sound bounceSound1, bounceSound2, bounceSound3, loseLifeSound;
    private Random random;



    public SoundManager() {
        bounceSound1 = Gdx.audio.newSound(Gdx.files.internal("beep1.ogg"));
        bounceSound2 = Gdx.audio.newSound(Gdx.files.internal("beep2.ogg"));
        bounceSound3 = Gdx.audio.newSound(Gdx.files.internal("beep3.ogg"));
        loseLifeSound = Gdx.audio.newSound(Gdx.files.internal("loseLife.ogg"));
    }
    public void dispose(){
        bounceSound1.dispose();
        bounceSound2.dispose();
        bounceSound3.dispose();
        loseLifeSound.dispose();
    }
    public void playRandomBounceSound(){
        if(random == null){
            random = new Random();
        }
        switch(random.nextInt(3)){
            case 0:
                bounceSound1.play();
                break;
            case 1:
                bounceSound2.play();
                break;
            case 2:
                bounceSound3.play();
        }
    }
    public  void playLoseLifeSound(){
        loseLifeSound.play();
    }
}
