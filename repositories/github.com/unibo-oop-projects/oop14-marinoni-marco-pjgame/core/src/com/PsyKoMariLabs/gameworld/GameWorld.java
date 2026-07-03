package com.PsyKoMariLabs.gameworld;

import com.PsyKoMariLabs.gameobjects.GameCharacter;
import com.PsyKoMariLabs.gameobjects.ScrollHandler;
import com.PsyKoMariLabs.PJHelper.AssetLoader;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;

public class GameWorld {

	private GameCharacter cube;
	private ScrollHandler scroller;
	private Rectangle ground;
	private int score = 0;
	private float runTime = 0;
	private int midPointY;
	private GameRenderer renderer;
	
	private GameState currentState;

	public enum GameState {
		MENU, READY, RUNNING, GAMEOVER, HIGHSCORE
	}

	public GameWorld(int midPointY) {
		currentState = GameState.MENU;
		this.midPointY = midPointY;
		cube = new GameCharacter(33, midPointY - 5, 12, 12);
		// The grass should start 66 pixels below the midPointY
		scroller = new ScrollHandler(this, midPointY + 66);
		ground = new Rectangle(0, midPointY + 66, 137, 11);
	}

	public void update(float delta) {
		runTime += delta;

		switch (currentState) {
		case READY:
		case MENU:
			updateReady(delta);
			break;

		case RUNNING:
			updateRunning(delta);
			break;
		default:
			break;
		}

	}

	private void updateReady(float delta) {
		cube.updateReady(runTime);
		scroller.updateReady(delta);
	}

	public void updateRunning(float delta) {
		if (delta > .15f) {
			delta = .15f;
		}

		cube.update(delta);
		scroller.update(delta);

		if (scroller.collides(cube) && cube.isAlive()) {
			scroller.stop();
			cube.die();
			AssetLoader.dead.play();
			renderer.prepareTransition(255, 255, 255, .3f);

			AssetLoader.fall.play();
		}

		if (Intersector.overlaps(cube.getBoundingRectangle(), ground)) {
			cube.slide(ground.y-ground.height);			
			if (!cube.isAlive()) {
				cube.decelerate();
				currentState = GameState.GAMEOVER;
				if (score > AssetLoader.getHighScore()) {
					AssetLoader.setHighScore(score);
					currentState = GameState.HIGHSCORE;
				}
			}
		}
	}

	public GameCharacter getCube() {
		return cube;

	}

	public int getMidPointY() {
		return midPointY;
	}

	public ScrollHandler getScroller() {
		return scroller;
	}

	public int getScore() {
		return score;
	}

	public void addScore(int increment) {
		score += increment;
	}

	public void start() {
		currentState = GameState.RUNNING;
	}

	public void ready() {
		currentState = GameState.READY;
		renderer.prepareTransition(0, 0, 0, 1f);
	}

	public void restart() {
		score = 0;
		cube.onRestart(midPointY - 5);
		scroller.onRestart();
		ready();
	}

	public boolean isReady() {
		return currentState == GameState.READY;
	}

	public boolean isGameOver() {
		return currentState == GameState.GAMEOVER;
	}

	public boolean isHighScore() {
		return currentState == GameState.HIGHSCORE;
	}

	public boolean isMenu() {
		return currentState == GameState.MENU;
	}

	public boolean isRunning() {
		return currentState == GameState.RUNNING;
	}

	public void setRenderer(GameRenderer renderer) {
		this.renderer = renderer;
	}

}
