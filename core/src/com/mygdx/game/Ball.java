package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;

public class Ball {
    private int x, y, velocityX = 9, velocityY = 9;
    private Texture texture;
    int ballStartFrameCounter, ballFlyFrameCounter;
    final int FRAMES_TO_WAIT_BEFORE_BALL_START = 60, FRAMES_TO_WAIT_BEFORE_SPEEDING_UP_BALL = 100;

    public Ball() {
        texture = new Texture("ball_small.png");
    }

    public void draw(){
        Storage.batch.draw(texture, x, y);
    }
    public void restart(Paddle paddle) {
          setY(paddle.y + paddle.texture.getHeight());
          setX(paddle.x + paddle.texture.getWidth() / 2 -   getWidth() / 2);
          ballStartFrameCounter = 0;
    }

    public void move(Paddle paddle) {
        if(PongGame.isGameOver) {return;}
        if(ballStartFrameCounter > FRAMES_TO_WAIT_BEFORE_BALL_START) {
            setX(  getX() +   getVelocityX());
            setY(  getY() +   getVelocityY());
            ballFlyFrameCounter++;
            speedUpIfNeeded();
        }
        else{
              setX(paddle.x + paddle.texture.getWidth() / 2 -   getWidth() / 2);
              setY(paddle.y + paddle.texture.getHeight());
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
