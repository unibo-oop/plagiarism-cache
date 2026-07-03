package com.PsyKoMariLabs.gameobjects;

import java.util.Random;

import com.PsyKoMariLabs.gameworld.GameWorld;
import com.PsyKoMariLabs.PJHelper.AssetLoader;

public class ScrollHandler {

	private Floor frontFloor, rearFloor;
	private Background frontBackground, rearBackground;
	private Platform platform1, platform2, platform3;
	public static final int SCROLL_SPEED = -59;
//	Distance between pistons
	public  int PISTON_GAP = 80;
	Random random ;
	private GameWorld gameWorld;

	public ScrollHandler(GameWorld gameWorld, float yPos) {
		this.gameWorld = gameWorld;
		frontFloor = new Floor(0, yPos, 143, 11, SCROLL_SPEED);
		rearFloor = new Floor(frontFloor.getTailX(), yPos, 143, 11,
				SCROLL_SPEED);
		
		frontBackground = new Background(0, 22, 237, 164, SCROLL_SPEED );
		rearBackground = new Background(frontBackground.getTailX(), 22, 237, 164,
				SCROLL_SPEED );
		
		random = new Random();
		PISTON_GAP = random.nextInt(80) +20 ;
		platform1 = new Platform(210, 0, 7, 130, SCROLL_SPEED, yPos);
		PISTON_GAP = random.nextInt(80) +20 ;
		platform2 = new Platform(platform1.getTailX() + PISTON_GAP, 0, 7, 120, SCROLL_SPEED,
				yPos);
		PISTON_GAP = random.nextInt(80) +20 ;
		platform3 = new Platform(platform2.getTailX() + PISTON_GAP, 0, 7, 100, SCROLL_SPEED,
				yPos);
	}

	public void updateReady(float delta) {

		frontFloor.update(delta);
		rearFloor.update(delta);
		frontBackground.update(delta);
		rearBackground.update(delta);

		// Same with grass
		if (frontFloor.isScrolledLeft()) {
			frontFloor.reset(rearFloor.getTailX());

		} else if (rearFloor.isScrolledLeft()) {
			rearFloor.reset(frontFloor.getTailX());

		}
		
		if (frontBackground.isScrolledLeft()) {
			frontBackground.reset(rearBackground.getTailX());

		} else if (rearBackground.isScrolledLeft()) {
			rearBackground.reset(frontBackground.getTailX());

		}

	}

	public void update(float delta) {
		// Update our objects
		frontFloor.update(delta);
		rearFloor.update(delta);
		platform1.update(delta);
		platform2.update(delta);
		platform3.update(delta);
		frontBackground.update(delta);
		rearBackground.update(delta);

		// Check if any of the pipes are scrolled left,
		// and reset accordingly
		PISTON_GAP = random.nextInt(40) +40 ;
		if (platform1.isScrolledLeft()) {
			platform1.reset(platform3.getTailX() + PISTON_GAP);
		} else if (platform2.isScrolledLeft()) {
			platform2.reset(platform1.getTailX() + PISTON_GAP);

		} else if (platform3.isScrolledLeft()) {
			platform3.reset(platform2.getTailX() + PISTON_GAP);
		}

		// Same with grass
		if (frontFloor.isScrolledLeft()) {
			frontFloor.reset(rearFloor.getTailX());

		} else if (rearFloor.isScrolledLeft()) {
			rearFloor.reset(frontFloor.getTailX());

		}
		
		// And Background
		if (frontBackground.isScrolledLeft()) {
			frontBackground.reset(rearBackground.getTailX());

		} else if (rearBackground.isScrolledLeft()) {
			rearBackground.reset(frontBackground.getTailX());

		}
	}

	public void stop() {
		frontFloor.stop();
		rearFloor.stop();
		platform1.stop();
		platform2.stop();
		platform3.stop();
		frontBackground.stop();
		rearBackground.stop();
	}

	public boolean collides(GameCharacter cube) {

		// Score if after mid piston
		if (!platform1.isScored()&& platform1.getX() + (platform1.getWidth() / 2) < cube.getX()+ cube.getWidth()) {
			addScore(1);
			platform1.setScored(true);
			AssetLoader.coin.play();
		} else if (!platform2.isScored()
				&& platform2.getX() + (platform2.getWidth() / 2) < cube.getX()
						+ cube.getWidth()) {
			addScore(1);
			platform2.setScored(true);
			AssetLoader.coin.play();

		} else if (!platform3.isScored()
				&& platform3.getX() + (platform3.getWidth() / 2) < cube.getX()
						+ cube.getWidth()) {
			addScore(1);
			platform3.setScored(true);
			AssetLoader.coin.play();

		}

		return (platform1.collides(cube) || platform2.collides(cube) || platform3
				.collides(cube));
	}

	private void addScore(int increment) {
		gameWorld.addScore(increment);
	}

	public Floor getFrontFloor() {
		return frontFloor;
	}

	public Floor getBackFloor() {
		return rearFloor;
	}
	
	public Background getRearBackground() {
		return rearBackground;
	}

	public Background getFrontBackground() {
		return frontBackground;
	}

	public Platform getPlatform1() {
		return platform1;
	}

	public Platform getPlatform2() {
		return platform2;
	}

	public Platform getPlatform3() {
		return platform3;
	}

	// Handle the restart of the game and reset Game Objects
	public void onRestart() {
		PISTON_GAP = random.nextInt(60) +20;
		frontFloor.onRestart(0, SCROLL_SPEED);
		rearFloor.onRestart(frontFloor.getTailX(), SCROLL_SPEED);
		platform1.onRestart(210, SCROLL_SPEED);
		platform2.onRestart(platform1.getTailX() + PISTON_GAP, SCROLL_SPEED);
		platform3.onRestart(platform2.getTailX() + PISTON_GAP, SCROLL_SPEED);
		frontBackground.onRestart(0, SCROLL_SPEED);
		rearBackground.onRestart(frontFloor.getTailX(), SCROLL_SPEED);
	}

}
