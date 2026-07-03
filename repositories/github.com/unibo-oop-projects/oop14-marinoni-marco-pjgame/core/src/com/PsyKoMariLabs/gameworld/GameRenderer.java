package com.PsyKoMariLabs.gameworld;

import java.util.List;

import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenEquations;
import aurelienribon.tweenengine.TweenManager;

import com.PsyKoMariLabs.gameobjects.Background;
import com.PsyKoMariLabs.gameobjects.GameCharacter;
import com.PsyKoMariLabs.gameobjects.Floor;
import com.PsyKoMariLabs.gameobjects.Platform;
import com.PsyKoMariLabs.gameobjects.ScrollHandler;
import com.PsyKoMariLabs.PJHelper.AssetLoader;
import com.PsyKoMariLabs.PJHelper.InputHandler;
import com.PsyKoMariLabs.TweenAccessors.Value;
import com.PsyKoMariLabs.TweenAccessors.ValueAccessor;
import com.PsyKoMariLabs.ui.SimpleButton;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

public class GameRenderer {

	private final boolean debugRender = false;

	private GameWorld myWorld;
	private OrthographicCamera cam;
	private ShapeRenderer shapeRenderer;

	private SpriteBatch batcher;

	private int midPointY;

	// Game Objects
	private GameCharacter cube;
	private ScrollHandler scroller;
	private Floor frontFloor, backFloor;
	private Background frontBackground, rearBackground;
	private Platform platform1, platform2, platform3;

	// Game Assets
	private TextureRegion background,backgroundFront, floor, cubeMid, platform, piston, ready,
			pjLogo, gameOver, highScore, scoreboard, star, noStar, retry;
	private Animation cubeAnimation;

	// Tween stuff
	private TweenManager manager;
	private Value alpha = new Value();

	// Buttons
	private List<SimpleButton> menuButtons;
	private Color transitionColor;


	public GameRenderer(GameWorld world, int gameHeight, int midPointY) {
		myWorld = world;

		this.midPointY = midPointY;
		this.menuButtons = ((InputHandler) Gdx.input.getInputProcessor())
				.getMenuButtons();

		cam = new OrthographicCamera();
		cam.setToOrtho(true, 136, gameHeight);

		batcher = new SpriteBatch();
		batcher.setProjectionMatrix(cam.combined);
		shapeRenderer = new ShapeRenderer();
		shapeRenderer.setProjectionMatrix(cam.combined);

		initGameObjects();
		initAssets();

		transitionColor = new Color();
		prepareTransition(255, 255, 255, .5f);
	}

	private void initGameObjects() {
		// Retrive Game objects
		cube = myWorld.getCube();
		scroller = myWorld.getScroller();
		rearBackground= scroller.getRearBackground();
		frontBackground= scroller.getFrontBackground();
		frontFloor = scroller.getFrontFloor();
		backFloor = scroller.getBackFloor();
		platform1 = scroller.getPlatform1();
		platform2 = scroller.getPlatform2();
		platform3 = scroller.getPlatform3();
	}

	private void initAssets() {
		// Initialize assets for Objects
		background = AssetLoader.bg;
		backgroundFront = AssetLoader.bg2;
		floor = AssetLoader.floor;
		cubeAnimation = AssetLoader.cubeAnimation;
		cubeMid = AssetLoader.cube;
		platform = AssetLoader.platform;
		piston = AssetLoader.piston;
		ready = AssetLoader.ready;
		pjLogo = AssetLoader.pjLogo;
		gameOver = AssetLoader.gameOver;
		highScore = AssetLoader.highScore;
		scoreboard = AssetLoader.scoreboard;
		retry = AssetLoader.retry;
		star = AssetLoader.star;
		noStar = AssetLoader.noStar;
	}

	private void drawFloor() {
		// Draw the floor
		batcher.draw(floor, frontFloor.getX(), frontFloor.getY(),
				frontFloor.getWidth(), frontFloor.getHeight());
		batcher.draw(floor, backFloor.getX(), backFloor.getY(),
				backFloor.getWidth(), backFloor.getHeight());
	}
	
	private void drawBackground() {
		// Draw Background
		batcher.draw(background, rearBackground.getX(), rearBackground.getY(), rearBackground.getWidth(), rearBackground.getHeight());
		batcher.draw(backgroundFront, frontBackground.getX(), frontBackground.getY(), frontBackground.getWidth(), frontBackground.getHeight());
	}

	private void drawPlatforms() {
		batcher.draw(platform, platform1.getPlatform().getX(), platform1
				.getPlatform().getY(), platform1.getPlatform().getWidth(), platform1
				.getPlatform().getHeight());

		batcher.draw(platform, platform2.getPlatform().getX(), platform2
				.getPlatform().getY(), platform2.getPlatform().getWidth(), platform2
				.getPlatform().getHeight());

		batcher.draw(platform, platform3.getPlatform().getX(), platform3
				.getPlatform().getY(), platform3.getPlatform().getWidth(), platform3
				.getPlatform().getHeight());
	}

	private void drawPistons() {
		batcher.draw(piston, platform1.getX(), platform1.getY() + platform1.getHeight() + 45,
				platform1.getWidth(), midPointY + 66 - (platform1.getHeight() + 45));

		batcher.draw(piston, platform2.getX(), platform2.getY() + platform2.getHeight() + 45,
				platform2.getWidth(), midPointY + 66 - (platform2.getHeight() + 45));

		batcher.draw(piston, platform3.getX(), platform3.getY() + platform3.getHeight() + 45,
				platform3.getWidth(), midPointY + 66 - (platform3.getHeight() + 45));
	}

	private void drawCubeCentered(float runTime) {
		batcher.draw(cubeAnimation.getKeyFrame(runTime), 59, cube.getY(),
				cube.getWidth() / 2.0f, cube.getHeight() / 2.0f,
				cube.getWidth(), cube.getHeight(), 1, 1, cube.getRotation());
	}

	private void drawCube (float runTime) {

		if (cube.shouldntJump()) {
			batcher.draw(cubeMid, cube.getX(), cube.getY(),
					cube.getWidth() / 2.0f, cube.getHeight() / 2.0f,
					cube.getWidth(), cube.getHeight(), 1, 1, cube.getRotation());

		} else {
			batcher.draw(cubeAnimation.getKeyFrame(runTime), cube.getX(),
					cube.getY(), cube.getWidth() / 2.0f,
					cube.getHeight() / 2.0f, cube.getWidth(), cube.getHeight(),
					1, 1, cube.getRotation());
		}

	}

	private void drawMenuUI() {
		batcher.draw(pjLogo, 136 / 2 - 56, midPointY - 70,
				pjLogo.getRegionWidth() / 2.2f, pjLogo.getRegionHeight() / 2.2f);

		for (SimpleButton button : menuButtons) {
			button.draw(batcher);
		}

	}

	private void drawScoreboard() {
		batcher.draw(scoreboard, 22, midPointY - 30, 97, 37);

		batcher.draw(noStar, 25, midPointY - 15, 10, 10);
		batcher.draw(noStar, 37, midPointY - 15, 10, 10);
		batcher.draw(noStar, 49, midPointY - 15, 10, 10);
		batcher.draw(noStar, 61, midPointY - 15, 10, 10);
		batcher.draw(noStar, 73, midPointY - 15, 10, 10);

		if (myWorld.getScore() > 2) {
			batcher.draw(star, 73, midPointY - 15, 10, 10);
		}

		if (myWorld.getScore() > 17) {
			batcher.draw(star, 61, midPointY - 15, 10, 10);
		}

		if (myWorld.getScore() > 50) {
			batcher.draw(star, 49, midPointY - 15, 10, 10);
		}

		if (myWorld.getScore() > 80) {
			batcher.draw(star, 37, midPointY - 15, 10, 10);
		}

		if (myWorld.getScore() > 120) {
			batcher.draw(star, 25, midPointY - 15, 10, 10);
		}

		int length = ("" + myWorld.getScore()).length();

		AssetLoader.whiteFont.draw(batcher, "" + myWorld.getScore(),
				104 - (2 * length), midPointY - 20);

		int length2 = ("" + AssetLoader.getHighScore()).length();
		AssetLoader.whiteFont.draw(batcher, "" + AssetLoader.getHighScore(),
				104 - (2.5f * length2), midPointY - 3);

	}

	private void drawRetry() {
		batcher.draw(retry, 36, midPointY + 10, 66, 14);
	}

	private void drawReady() {
		batcher.draw(ready, 36, midPointY - 50, 68, 14);
	}

	private void drawGameOver() {
		batcher.draw(gameOver, 24, midPointY - 50, 92, 14);
	}

	private void drawScore() {
		int length = ("" + myWorld.getScore()).length();
		AssetLoader.shadow.draw(batcher, "" + myWorld.getScore(),
				68 - (3 * length), midPointY - 82);
		AssetLoader.font.draw(batcher, "" + myWorld.getScore(),
				68 - (3 * length), midPointY - 83);
	}

	private void drawHighScore() {
		batcher.draw(highScore, 22, midPointY - 50, 96, 14);
	}

	public void render(float delta, float runTime) {

		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

		shapeRenderer.begin(ShapeType.Filled);

		// Draw Background color
		shapeRenderer.setColor(248 / 255.0f, 249 / 255.0f, 250 / 255.0f, 1);
		shapeRenderer.rect(0, 0, 136, midPointY + 66);

		// Draw Floor
		shapeRenderer.setColor(138 / 255.0f, 172 / 255.0f, 222 / 255.0f, 1);
		shapeRenderer.rect(0, midPointY + 66, 136, 11);

		// Draw Dirt
		shapeRenderer.setColor(138 / 255.0f, 172 / 255.0f, 222 / 255.0f, 1);
		shapeRenderer.rect(0, midPointY + 77, 136, 52);

		shapeRenderer.end();

		batcher.begin();
		batcher.disableBlending();

		drawBackground();

		drawPistons();

		batcher.enableBlending();
		drawPlatforms();

		if (myWorld.isRunning()) {
			drawCube(runTime);
			drawScore();
		} else if (myWorld.isReady()) {
			drawCube(runTime);
			drawReady();
		} else if (myWorld.isMenu()) {
			drawCubeCentered(runTime);
			drawMenuUI();
		} else if (myWorld.isGameOver()) {
			drawScoreboard();
			drawCube(runTime);
			drawGameOver();
			drawRetry();
		} else if (myWorld.isHighScore()) {
			drawScoreboard();
			drawCube(runTime);
			drawHighScore();
			drawRetry();
		}

		drawFloor();
		batcher.end();

		if (debugRender) {

			 shapeRenderer.begin(ShapeType.Filled);
			 shapeRenderer.setColor(Color.RED);
			 shapeRenderer.rect(cube.getBoundingRectangle().x,
			 cube.getBoundingRectangle().y, 10,10);
			
			 // Draw debug for piston1,2,3
			 shapeRenderer.rect(platform1.getPiston().x, platform1.getPiston().y,
			 platform1.getPiston().width, platform1.getPiston().height);
			 shapeRenderer.rect(platform2.getPiston().x, platform2.getPiston().y,
			 platform2.getPiston().width, platform2.getPiston().height);
			 shapeRenderer.rect(platform3.getPiston().x, platform3.getPiston().y,
			 platform3.getPiston().width, platform3.getPiston().height);
			
			// Draw debug for platform1,2,3
			 shapeRenderer.rect(platform1.getPlatform().x,platform1.getPlatform().y,
			 platform1.getPlatform().width, platform1.getPlatform().height);
			
			 // Draw debug for walkable platform 1
			 shapeRenderer.setColor(Color.GREEN);
			 shapeRenderer.rect(platform1.getPlatform().x, platform1.getPlatform().y
			 ,platform1.getPlatform().width, 1);
			 
			 shapeRenderer.setColor(Color.RED);
			 shapeRenderer.rect(platform2.getPlatform().x,platform2.getPlatform().y,
			 platform2.getPlatform().width, platform2.getPlatform().height);
			 shapeRenderer.rect(platform3.getPlatform().x,platform3.getPlatform().y,
			 platform3.getPlatform().width, platform3.getPlatform().height);			
			 shapeRenderer.end();
			
		}
		drawTransition(delta);

	}

	public void prepareTransition(int r, int g, int b, float duration) {
		transitionColor.set(r / 255.0f, g / 255.0f, b / 255.0f, 1);
		alpha.setValue(1);
		Tween.registerAccessor(Value.class, new ValueAccessor());
		manager = new TweenManager();
		Tween.to(alpha, -1, duration).target(0)
				.ease(TweenEquations.easeOutQuad).start(manager);
	}

	private void drawTransition(float delta) {
		if (alpha.getValue() > 0) {
			manager.update(delta);
			Gdx.gl.glEnable(GL10.GL_BLEND);
			Gdx.gl.glBlendFunc(GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA);
			shapeRenderer.begin(ShapeType.Filled);
			shapeRenderer.setColor(transitionColor.r, transitionColor.g,
					transitionColor.b, alpha.getValue());
			shapeRenderer.rect(0, 0, 136, 300);
			shapeRenderer.end();
			Gdx.gl.glDisable(GL10.GL_BLEND);

		}
	}

}
