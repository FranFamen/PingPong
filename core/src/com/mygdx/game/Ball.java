package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;

public class Ball {
    final int INITIAL_VELOCITY = 20;
    private int x, y, velocityX = INITIAL_VELOCITY, velocityY = INITIAL_VELOCITY;
    private Texture texture;
    public int ballStartFrameCounter, ballFlyFrameCounter;
    final int FRAMES_TO_WAIT_BEFORE_BALL_START = 60, FRAMES_TO_WAIT_BEFORE_SPEEDING_UP_BALL = 100;

    public Ball() {
        texture = new Texture("ball_small.png");
    }

    public void draw(){
        Storage.batch.draw(texture, x, y);
    }
    public void restart(BottomPaddle bottomPaddle) {
          setY(bottomPaddle.y + bottomPaddle.texture.getHeight());
          setX(bottomPaddle.x + bottomPaddle.texture.getWidth() / 2 -   getWidth() / 2);
          ballStartFrameCounter = 0;
          velocityY = INITIAL_VELOCITY;
          velocityX = INITIAL_VELOCITY;
    }

    public void move(BottomPaddle bottomPaddle) {
        if(PongGame.isGameOver) {return;}
        if(ballStartFrameCounter > FRAMES_TO_WAIT_BEFORE_BALL_START) {
            setX(  getX() +   getVelocityX());
            setY(  getY() +   getVelocityY());
            ballFlyFrameCounter++;
            speedUpIfNeeded();
        }
        else{
              setX(bottomPaddle.x + bottomPaddle.texture.getWidth() / 2 -   getWidth() / 2);
              setY(bottomPaddle.y + bottomPaddle.texture.getHeight());
        }

    }
    private void speedUpIfNeeded(){
        if(ballFlyFrameCounter == FRAMES_TO_WAIT_BEFORE_SPEEDING_UP_BALL){
            if(velocityX > 0)
                velocityX++;
            else
                velocityX--;
            if(velocityY > 0)
                velocityY++;
            else
                velocityY--;
            ballFlyFrameCounter = 0;
        }
    }


    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getVelocityX() {
        return velocityX;
    }

    public void setVelocityX(int velocityX) {
        this.velocityX = velocityX;
    }

    public int getVelocityY() {
        return velocityY;
    }

    public void setVelocityY(int velocityY) {
        this.velocityY = velocityY;
    }
    public int getWidth() {
        return texture.getWidth();
    }
    public int getHeight() {
        return texture.getHeight();
    }
    public void free(){
        texture.dispose();
    }
}
