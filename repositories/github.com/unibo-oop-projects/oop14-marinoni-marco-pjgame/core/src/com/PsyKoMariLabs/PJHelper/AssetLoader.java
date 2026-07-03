package com.PsyKoMariLabs.PJHelper;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class AssetLoader {

	public static Texture splashLogoTexture,logoTexture, backgroundT,background2T, 
	floorT,cubeT2,cubeT,platformT,pistonT, playButtonUpT,playButtonDownT,readyT,retryT,gameOverT,scoreboardT,starT,noStarT,highscoreT;
	
	public static TextureRegion splashLogo, pjLogo, bg,bg2, floor, cube, cubeDown,
			cubeUp, platform, piston, playButtonUp, playButtonDown,
			ready, gameOver, highScore, scoreboard, star, noStar, retry;
	public static Animation cubeAnimation;
	public static Sound dead, jump, coin, fall;
	public static BitmapFont font, shadow, whiteFont;
	private static Preferences prefs;

	public static void load() {

		splashLogoTexture = new Texture(Gdx.files.internal("data/logo.png"));
		splashLogoTexture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		splashLogo = new TextureRegion(splashLogoTexture, 0, 0, 512, 114);
		
		logoTexture = new Texture(Gdx.files.internal("data/Logo_gioco.png"));
		logoTexture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		pjLogo = new TextureRegion(logoTexture, 0, 0, 512, 128);
		pjLogo.flip(false, true);
		
		floorT = new Texture(Gdx.files.internal("data/Pavimento.png"));
		floorT.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
		floor = new TextureRegion(floorT, 0, 0, 586, 29);
		floor.flip(false, true);
		
		cubeT =   new Texture(Gdx.files.internal("data/Companion Cube.png"));
		cubeT2 =   new Texture(Gdx.files.internal("data/Companion Cube2.png"));
		cubeDown = new TextureRegion(cubeT2, 0, 0, 64, 64);
		cubeDown.flip(false, true);
		cube = new TextureRegion(cubeT, 0, 0, 64, 64);
		cube.flip(false, true);
		cubeUp = new TextureRegion(cubeT, 0, 0, 64, 64);
		cubeUp.flip(true, false);
		TextureRegion[] cubes = { cubeDown, cube };
		cubeAnimation = new Animation(0.5f, cubes);
		cubeAnimation.setPlayMode(Animation.LOOP_PINGPONG);
		
				
		backgroundT = new Texture(Gdx.files.internal("data/Sfondo1.png"));
		backgroundT.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
		background2T = new Texture(Gdx.files.internal("data/Sfondo2.png"));
		background2T.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
		
		playButtonUpT = new Texture(Gdx.files.internal("data/Play-normal.png"));
		playButtonDownT = new Texture(Gdx.files.internal("data/Play-press.png"));
		playButtonUp = new TextureRegion(playButtonUpT, 0, 0, 108, 59);
		playButtonDown = new TextureRegion(playButtonDownT, 0, 0, 108, 59);
		playButtonUp.flip(false, true);
		playButtonDown.flip(false, true);

		readyT = new Texture(Gdx.files.internal("data/Ready.png"));
		ready = new TextureRegion(readyT, 0, 0, 128, 26);
		ready.flip(false, true);

		retryT = new Texture(Gdx.files.internal("data/Retry.png"));
		retry = new TextureRegion(retryT, 0, 0, 125, 26);
		retry.flip(false, true);
		
		
		
		gameOverT = new Texture(Gdx.files.internal("data/Game over.png"));
		gameOver = new TextureRegion(gameOverT, 0, 0, 174, 26);
		gameOver.flip(false, true);

		scoreboardT = new Texture(Gdx.files.internal("data/Ratings.png"));
		scoreboard = new TextureRegion(scoreboardT, 0, 0, 239, 92);
		scoreboard.flip(false, true);

		starT = new Texture(Gdx.files.internal("data/Spia-accesa.png"));
		noStarT = new Texture(Gdx.files.internal("data/Spia-spenta.png"));
		star = new TextureRegion(starT, 0, 0, 32, 32);
		noStar = new TextureRegion(noStarT, 0, 0, 32, 32);

		star.flip(false, true);
		noStar.flip(false, true);

		highscoreT = new Texture(Gdx.files.internal("data/Highscore.png"));
		highScore = new TextureRegion(highscoreT, 0, 0, 181, 26);
		highScore.flip(false, true);

		

		bg = new TextureRegion(backgroundT, 0, 0, 237, 164);
		bg.flip(false, true);
		bg2 = new TextureRegion(background2T, 0, 0, 237, 164);
		bg2.flip(false, true);


		platformT = new Texture(Gdx.files.internal("data/Pedana.png"));
		platformT.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
		platform = new TextureRegion(platformT, 0, 0, 98, 32);
		platform.flip(false, true);

		pistonT = new Texture(Gdx.files.internal("data/Pistone-basso.png"));
		pistonT.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
		piston = new TextureRegion(pistonT, 0, 0, 16, 3);
		piston.flip(false, true);

		dead = Gdx.audio.newSound(Gdx.files.internal("data/dead.wav"));
		jump = Gdx.audio.newSound(Gdx.files.internal("data/jump.wav"));
		coin = Gdx.audio.newSound(Gdx.files.internal("data/coin.wav"));
		fall = Gdx.audio.newSound(Gdx.files.internal("data/fall.wav"));

		font = new BitmapFont(Gdx.files.internal("data/text.fnt"));
		font.setScale(.25f, -.25f);

		whiteFont = new BitmapFont(Gdx.files.internal("data/whitetext.fnt"));
		whiteFont.setScale(.1f, -.1f);

		shadow = new BitmapFont(Gdx.files.internal("data/shadow.fnt"));
		shadow.setScale(.25f, -.25f);

		// Create (or retrieve existing) preferences file
		prefs = Gdx.app.getPreferences("Portal Jumper");

		if (!prefs.contains("highScore")) {
			prefs.putInteger("highScore", 0);
		}
	}

	public static void setHighScore(int val) {
		prefs.putInteger("highScore", val);
		prefs.flush();
	}

	public static int getHighScore() {
		return prefs.getInteger("highScore");
	}

	public static void dispose() {
		// We must dispose of the texture when we are finished.
		splashLogoTexture.dispose();
		logoTexture.dispose(); 
		backgroundT.dispose();
		background2T.dispose(); 
		floorT.dispose();
		cubeT2.dispose();
		cubeT.dispose();
		platformT.dispose();
		pistonT.dispose(); 
		playButtonUpT.dispose();
		playButtonDownT.dispose();
		readyT.dispose();
		retryT.dispose();
		gameOverT.dispose();
		scoreboardT.dispose();
		starT.dispose();
		noStarT.dispose();
		highscoreT.dispose();

		// Dispose sounds
		dead.dispose();
		jump.dispose();
		coin.dispose();

		font.dispose();
		shadow.dispose();
	}

}