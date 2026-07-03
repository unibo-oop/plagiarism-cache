package com.PsyKoMariLabs.gameobjects;


import com.PsyKoMariLabs.PJHelper.AssetLoader;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class GameCharacter {

	private Vector2 position;
	private Vector2 velocity;
	private Vector2 acceleration;
	
	private int availableClicks;
	
	private float rotation;
	private int width;
	private float height;

	private float originalY;

	private boolean isAlive;

	private Rectangle boundingRectangle;

	public GameCharacter(float x, float y, int width, int height) {
		this.width = width;
		this.height = height;
		this.originalY = y;
		position = new Vector2(x, y);
		velocity = new Vector2(0, 0);
		acceleration = new Vector2(0, 460);
		boundingRectangle = new Rectangle();
		isAlive = true;
		availableClicks = 2;
	}

	public void update(float delta) {

		velocity.add(acceleration.cpy().scl(delta));

		if (velocity.y > 200) {
			velocity.y = 200;
		}

		// CEILING CHECK
		if (position.y < -13) {
			position.y = -13;
			velocity.y = 0;
		}

		position.add(velocity.cpy().scl(delta));


		boundingRectangle.set(position.x , position.y, 10, 10);
		// Rotate 45
		if (velocity.y < 0) {
			rotation -= 600 * delta;

			if (rotation < 45) {
				rotation = 45;
			}
		}

		// Rotate 90
		if (isFalling() || !isAlive) {
			rotation += 480 * delta;
			if (rotation > 90) {
				rotation = 90;
			}

		}

	}

	public void updateReady(float runTime) {
		position.y = 2 * (float) Math.sin(7 * runTime) + originalY;
	}

	public boolean isFalling() {
		return velocity.y > 110;
	}

	public boolean shouldntJump() {
		return velocity.y > 70 || !isAlive;
	}

	public void onClick() {
		if (isAlive && ( availableClicks > 0) ) {
			AssetLoader.jump.play();
			velocity.y = -180;
			availableClicks --; 
		}
	}

	public void die() {
		isAlive = false;
		velocity.y = 0;
	}

	public void decelerate() {
		acceleration.y = 0;
	}

	public void onRestart(int y) {
		rotation = 0;
		position.y = y;
		velocity.x = 0;
		velocity.y = 0;
		acceleration.x = 0;
		acceleration.y = 460;
		isAlive = true;
	}

	public float getX() {
		return position.x;
	}

	public float getY() {
		return position.y;
	}

	public float getWidth() {
		return width;
	}

	public float getHeight() {
		return height;
	}

	public float getRotation() {
		return rotation;
	}

	public Rectangle getBoundingRectangle() {
		return boundingRectangle;
	}

	public boolean isAlive() {
		return isAlive;
	}
	
	public void slide(float y) {
		position.y=y;
		rotation=0;
		availableClicks = 2;
	}

}
