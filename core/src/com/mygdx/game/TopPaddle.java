package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;


public class TopPaddle extends Paddle {
    Texture texture;
    int x, y;

    enum Direction{RIGHT, LEFT};
    private Direction curDirection = Direction.RIGHT;


    private int velocity;
    final int INITIAL_POSITIVE_VELOCITY = 20, INITIAL_NEGATIVE_VELOCITY = -20;

    public TopPaddle() {
        texture = new Texture("paddle.bmp");
        x = (Gdx.graphics.getWidth() - texture.getWidth()) / 2;
        y = Gdx.graphics.getHeight() - PongGame.PIXELSFORTEXT;
        velocity = INITIAL_POSITIVE_VELOCITY;
    }

    @Override
    public void draw() {
        Storage.batch.draw(texture, x , y);
    }

    @Override
    public void move(Ball ball) {
        if (PongGame.isGameOver) {return;}
        setX(getX() + getVelocity());
        if(ball.getX() + ball.getWidth() / 2 > getX() + texture.getWidth() / 2){
            moveRight();
        }
        if(ball.getX() + ball.getWidth() / 2 < getX() + texture.getWidth() / 2){
            moveLeft();
        }
        if(x > Gdx.graphics.getWidth() - texture.getWidth()){
            x = Gdx.graphics.getWidth() - texture.getWidth();
        }
        if(x < 0){
            x = 0;
        }

        /*switch(curDirection) {
            case RIGHT: velocity = -velocity;
                break;
            case LEFT: velocity = -velocity;
                break;
        }*/
    }
    /*public void setCurDirection(Ball ball){
        if(ball.getX() + ball.getWidth() / 2 < x + texture.getWidth() / 2){
            curDirection = Direction.LEFT;
        }
        if(ball.getX() + ball.getWidth() / 2 > x + texture.getWidth() / 2){
            curDirection = Direction.RIGHT;
        }
    }*/
    public void moveRight(){
        velocity = INITIAL_POSITIVE_VELOCITY;
    }
    public void moveLeft(){
        velocity = INITIAL_NEGATIVE_VELOCITY;
    }

    @Override
    public void dispose() {
        texture.dispose();
    }

    @Override
    public int getX() {
        return x;
    }


    @Override
    public void setX(int x) {
        this.x = x;
    }

    public int getVelocity() {
        return velocity;
    }
}
