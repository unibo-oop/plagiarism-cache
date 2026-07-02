package it.bomberman.entities;

import java.awt.Graphics;

import it.bomberman.collisions.Body;
import it.bomberman.collisions.ICollidable;
import it.bomberman.collisions.Rectangle;
import it.bomberman.gfx.Assets;

public class PowerUp extends AbstractEntity {
	private final static int DEFAULT_WIDTH = 50;
	private PowerUpType type;
	private int value;

	public enum PowerUpType {
		LIFE, SPEED, BOMB_NUM, BOMB_EXTENSION
	}

	protected PowerUp(int x, int y, int width, int height, EntityController controller) {
		super(x, y, width, height, controller);
	}

	public PowerUp(int x, int y, int width, int height, PowerUpType type, int value, EntityController controller) {
		this(x, y, width, height, controller);
		this.type = type;
		this.value = value;
	}

	public PowerUp(int x, int y, PowerUpType type, int value, EntityController controller) {
		this(x, y, DEFAULT_WIDTH, DEFAULT_WIDTH, controller);

	}

	@Override
	public boolean shouldCollide(ICollidable collidable) {
		return collidable instanceof Player;
	}

	@Override
	public void collision(ICollidable collidable) {
		collidable.collision(this);
		dispose();
	}

	@Override
	public void dispose() {
		this.controller.notifyDisposal(this);
	}

	@Override
	public void tick() {
		this.controller.checkAndResolveCollisions(this);
	}

	@Override
	public void render(Graphics g) {

		if (this.type == PowerUpType.SPEED) {
			g.drawImage(Assets.upgrade[0], x, y, null);
		}
		if (this.type == PowerUpType.BOMB_NUM) {
			g.drawImage(Assets.upgrade[1], x, y, null);
		}
		if (this.type == PowerUpType.LIFE) {
			g.drawImage(Assets.upgrade[2], x, y, null);
		}
		if (this.type == PowerUpType.BOMB_EXTENSION) {
			g.drawImage(Assets.upgrade[3], x, y, null);
		}
	}

	public int getValue() {
		return value;
	}

	public PowerUpType getType() {
		return type;
	}

	public static class PowerUpBuilder {
		private int x;
		private int y;
		private EntityController controller;
		private PowerUpType type;
		private int value;
		private int width = DEFAULT_WIDTH;
		private int height = DEFAULT_WIDTH;

		private PowerUpBuilder() {

		}

		public PowerUpBuilder setX(int x) {
			this.x = x;
			return this;
		}

		public PowerUpBuilder setY(int y) {
			this.y = y;
			return this;
		}

		public PowerUpBuilder setController(EntityController controller) {
			this.controller = controller;
			return this;
		}

		public PowerUpBuilder setType(PowerUpType type) {
			this.type = type;
			return this;
		}

		/**
		 * 
		 * @param value deve essere < di 6 e > 0
		 * @return
		 */
		public PowerUpBuilder setValue(int value) {
			if (!checkValue(value)) {
				throw new IllegalArgumentException("Value di PowerUp deve essere 0 < v < 6");
			}
			this.value = value;
			return this;
		}

		public PowerUpBuilder setWidth(int width) {
			this.width = width;
			return this;
		}

		public PowerUpBuilder setHeight(int height) {
			this.height = height;
			return this;
		}

		private boolean checkValue(int value) {
			return value > 0 && value < 6;
		}

		public PowerUp build() {
			if (this.controller == null || this.type == null || !checkValue(this.value)) {
				throw new IllegalStateException("Un dato ha valore inaccettabile!");
			}
			return new PowerUp(this.x, this.y, this.width, this.height, this.type, this.value, this.controller);
		}

		public static PowerUpBuilder newBuilder() {
			return new PowerUpBuilder();
		}
	}

	@Override
	protected void initBody() {
		this.body = new Body();
		this.body.add(new Rectangle(x, y, width, height));
	}
}
