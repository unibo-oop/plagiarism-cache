package com.PsyKoMariLabs.gameobjects;

import java.util.Random;

import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;

public class Platform extends Scrollable {

	private Random r;

	private Rectangle  platform, piston, walkablePlatform;

//	Vertical distance
	public static final int VERTICAL_GAP = 45;
	public static final int PLATFORM_WIDTH = 30;
	public static final int PLATFORM_HEIGHT = 12;
	private float groundY;

	private boolean isScored = false;


	public Platform(float x, float y, int width, int height, float scrollSpeed,
			float groundY) {
		super(x, y, width, height, scrollSpeed);
		// Initialize a Random object for Random number generation
		r = new Random();
		platform = new Rectangle();
		piston = new Rectangle();
		walkablePlatform = new Rectangle();

		this.groundY = groundY;
	}

	@Override
	public void update(float delta) {
		// Call the update method in the superclass (Scrollable)
		super.update(delta);

		// The set() method allows you to set the top left corner's x, y
		// coordinates,
		// along with the width and height of the rectangle
		piston.set(position.x, position.y + height + VERTICAL_GAP, width,
				groundY - (position.y + height + VERTICAL_GAP));


		// This shift is equivalent to: (SKULL_WIDTH - width) / 2 to center platform on piston
		platform.set(position.x - (PLATFORM_WIDTH - width) / 2, piston.y -PLATFORM_HEIGHT,
				PLATFORM_WIDTH, PLATFORM_HEIGHT);

		walkablePlatform.set(platform.getX()+1, platform.getY(),
				PLATFORM_WIDTH-1, 1);
		
	}

	@Override
	public void reset(float newX) {
		// Call the reset method in the superclass (Scrollable)
		super.reset(newX);
		// Change the height to a random number
		height = r.nextInt(80) + 50;
		isScored = false;
		update(0);
	}

	public void onRestart(float x, float scrollSpeed) {
		velocity.x = scrollSpeed;
		reset(x);
		height = r.nextInt(20) + 120 ; 
	}


	public Rectangle getPlatform() {
		return platform;
	}


	public Rectangle getPiston() {
		return piston;
	}

	public boolean collides(GameCharacter bird) {

		if((Intersector.overlaps(bird.getBoundingRectangle(), walkablePlatform))) { 
			bird.slide(walkablePlatform.y-12);
			return false;
		}
			
		if (Intersector.overlaps(bird.getBoundingRectangle(), piston)) {
			return (Intersector.overlaps(bird.getBoundingRectangle(), piston));
		}
			
			
		if((Intersector.overlaps(bird.getBoundingRectangle(), platform))) { 
			return (Intersector.overlaps(bird.getBoundingRectangle(), platform));
		}
			
		return false;
	}

	public boolean isScored() {
		return isScored;
	}

	public void setScored(boolean b) {
		isScored = b;
	}
}
