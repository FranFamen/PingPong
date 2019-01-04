package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class PongGame extends ApplicationAdapter {
	Ball ball;
	final int CATCH_BALL_BONUS = 100, INITIAL_AMOUNT_OF_LIVES = 3;
	static final int PIXELSFORTEXT = 100;
	SoundManager soundManager;
	BottomPaddle bottomPaddle;
	TopPaddle topPaddle;
	int score = 0;
	int livesCount = INITIAL_AMOUNT_OF_LIVES;
	BitmapFont font;
	Texture gameOverTexture, backgroundTexture;
	static boolean isGameOver;
	Button closeBtn, replayBtn;
	String closeBtnName = "close_btn.png", replayBtnName = "replay_btn.png";


	@Override
	public void create () {
		Storage.batch = new SpriteBatch();
		soundManager = new SoundManager();
		font = new BitmapFont();
		font.getData().setScale(5);
		bottomPaddle = new BottomPaddle();
		topPaddle = new TopPaddle();
		ball = new Ball();
		ball.restart(bottomPaddle);
		backgroundTexture = new Texture("sky_jpeg.jpg");
	}

	@Override
	public void render () {
		if(isGameOver && closeBtn.isClicked()){
	        System.exit(0);
        }
        if(isGameOver && replayBtn.isReleased()){
            restartGame();
        }
		bottomPaddle.move(ball);
		topPaddle.move(ball);
		ball.ballStartFrameCounter++;
		ball.move(bottomPaddle);
		colidingBall();
		if(ball.getY() + ball.getHeight() < 0){
			ball.restart(bottomPaddle);
			soundManager.playLoseLifeSound();
			livesCount--;
            if(livesCount == 0){
                isGameOver = true;
                gameOverTexture = new Texture("game_over_logo.jpg");
                closeBtn = new Button(closeBtnName);
				replayBtn = new Button(replayBtnName);
				closeBtn.setX(Gdx.graphics.getWidth() - closeBtn.texture.getWidth());
			}
        }
		draw();
	}

    private void restartGame() {
        ball.ballStartFrameCounter = 0;
        isGameOver = false;
        livesCount = INITIAL_AMOUNT_OF_LIVES;
        score = 0;
        ball = new Ball();
        ball.restart(bottomPaddle);
        bottomPaddle = new BottomPaddle();
        if(gameOverTexture != null){
			gameOverTexture.dispose();
			gameOverTexture = null;
		}

        closeBtn.dispose();
        replayBtn.dispose();
    }

    private void draw() {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		Storage.batch.begin();
		Storage.batch.draw(backgroundTexture, 0,0,Gdx.graphics.getWidth(), Gdx.graphics.getHeight() - PIXELSFORTEXT);
		ball.draw();
		bottomPaddle.draw();
		topPaddle.draw();
		if(isGameOver) {
            Storage.batch.draw(gameOverTexture, (Gdx.graphics.getWidth() - gameOverTexture.getWidth()) / 2,
                    (Gdx.graphics.getHeight() - gameOverTexture.getHeight()) / 2);
            closeBtn.draw();
            replayBtn.draw();
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
		if(ball.getY() + ball.getHeight() >= Gdx.graphics.getHeight() - PIXELSFORTEXT){
			ball.setVelocityY(-ball.getVelocityY());
			soundManager.playRandomBounceSound();
		}
		//Ball colides with a left wall
		if(ball.getX() <= 0){
			ball.setVelocityX(-ball.getVelocityX());
			soundManager.playRandomBounceSound();
		}
		//Ball colides with a topPaddle
		if(ball.getX() + ball.getWidth() / 2 > topPaddle.x && ball.getX() + ball.getWidth() / 2 < topPaddle.x + topPaddle.texture.getWidth()){
			if(ball.getY() + ball.getHeight() < topPaddle.getY()){
				ball.setVelocityY(-ball.getVelocityY());
				soundManager.playRandomBounceSound();
			}
		}
		//Ball colides with a bottomPaddle
		if(ball.getX() + ball.getWidth() / 2 > bottomPaddle.x && ball.getX() + ball.getWidth() / 2 < bottomPaddle.x + bottomPaddle.texture.getWidth()){
			if(ball.getY() < bottomPaddle.y + bottomPaddle.texture.getHeight()){

				ball.setVelocityY(-ball.getVelocityY());
				soundManager.playRandomBounceSound();
				score += CATCH_BALL_BONUS * Math.abs(ball.getVelocityX());
			}
		}
		//мяч отскакивает от левого края битки
		if(ball.getX() > bottomPaddle.getX() - ball.getWidth() && ball.getX() < bottomPaddle.getX() - ball.getWidth() / 2 + 1){
			if(ball.getY() < bottomPaddle.y + bottomPaddle.texture.getHeight()){
				if(ball.getVelocityX() > 0) {
					ball.setVelocityX(-ball.getVelocityX());
					soundManager.playRandomBounceSound();
				}
			}
		}
		//мяч отскакивает от правого края битки
		if(ball.getX() > bottomPaddle.getX() + bottomPaddle.texture.getWidth() - ball.getWidth() / 2 - 1
				&& ball.getX() < bottomPaddle.getX() + bottomPaddle.texture.getWidth()){
			if(ball.getY() < bottomPaddle.y + bottomPaddle.texture.getHeight()){
				if(ball.getVelocityX() < 0) {
					ball.setVelocityX(-ball.getVelocityX());
					soundManager.playRandomBounceSound();
				}
			}
		}
	}


	@Override
	public void dispose () {
		Storage.batch.dispose();
		bottomPaddle.dispose();
		ball.free();
		soundManager.dispose();
		if(gameOverTexture != null) {
            gameOverTexture.dispose();
        }
        if(closeBtn != null){
			closeBtn.dispose();
			replayBtn.dispose();

		}
		font.dispose();
		backgroundTexture.dispose();
	}

}
