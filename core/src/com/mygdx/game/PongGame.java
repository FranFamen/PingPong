package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Random;

public class PongGame extends ApplicationAdapter {
	Ball ball;
	final int CATCH_BALL_BONUS = 100;
	SoundManager soundManager;
	Paddle paddle;
	int score = 0;
	int livesCount = 1;
	BitmapFont font;
	Texture gameOverTexture;
	static boolean isGameOver;
	Button closeBtn, replayBtn;
	String closeBtnName = "close_btn.png", replayBtnName = "replay_btn.png";

	@Override
	public void create () {
		Storage.batch = new SpriteBatch();
		soundManager = new SoundManager();
		font = new BitmapFont();
		font.getData().setScale(5);
		paddle = new Paddle();
		ball = new Ball();
		ball.restart(paddle);

	}

	@Override
	public void render () {
	    if(closeBtn != null && closeBtn.isClicked()){
	        System.exit(0);
        }
		paddle.move();
		ball.ballStartFrameCounter++;
		ball.move(paddle);
		colidingBall();
		if(ball.getY() + ball.getHeight() < 0){
			ball.restart(paddle);
			soundManager.playLoseLifeSound();
			livesCount--;
            gameOverTexture = new Texture("game_over_logo.jpg");
            if(livesCount == 0){
            	isGameOver = true;
				closeBtn = new Button(closeBtnName);
				replayBtn = new Button(replayBtnName);
				closeBtn.setX(Gdx.graphics.getWidth() - closeBtn.texture.getWidth());
				replayBtn.setX(Gdx.graphics.getWidth() - closeBtn.texture.getWidth());
			}
        }
		draw();
	}

	private void draw() {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		Storage.batch.begin();
		ball.draw();
		paddle.draw();
		if(livesCount == 0) {
            Storage.batch.draw(gameOverTexture, (Gdx.graphics.getWidth() - gameOverTexture.getWidth()) / 2,
                    (Gdx.graphics.getHeight() - gameOverTexture.getHeight()) / 2);

            closeBtn.draw();
        }
		font.draw(Storage.batch, "Score: " + score + "    Lives: " + livesCount, 0, Gdx.graphics.getHeight());
		Storage.batch.end();
	}

	private void colidingBall() {
		//Ball colides with a right wall
		if(ball.getX() + ball.getWidth() >= Gdx.graphics.getWidth()){
            ball.setVelocityX(-ball.getVelocityX());
            soundManager.playRandomBounceSound();
        }
		//Ball colides with a top wall
		if(ball.getY() + ball.getHeight() >= Gdx.graphics.getHeight()){
			ball.setVelocityY(-ball.getVelocityY());
			soundManager.playRandomBounceSound();
		}
		//Ball colides with a left wall
		if(ball.getX() <= 0){
			ball.setVelocityX(-ball.getVelocityX());
			soundManager.playRandomBounceSound();
		}
		//Ball colides with a paddle
		if(ball.getX() + ball.getWidth() / 2 > paddle.x && ball.getX() + ball.getWidth() / 2 < paddle.x + paddle.texture.getWidth()){
			if(ball.getY() < paddle.y + paddle.texture.getHeight()){
				ball.setVelocityY(-ball.getVelocityY());
				soundManager.playRandomBounceSound();
				score += CATCH_BALL_BONUS * Math.abs(ball.getVelocityX());
			}
		}
	}


	@Override
	public void dispose () {
		Storage.batch.dispose();
		paddle.dispose();
		ball.free();
		soundManager.dispose();
		if(gameOverTexture != null) {
            gameOverTexture.dispose();
        }
        if(closeBtn != null){
			closeBtn.dispose();
		}
		font.dispose();
	}

}
