package it.bomberman.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import it.bomberman.collisions.Body;
import it.bomberman.collisions.ICollidable;
import it.bomberman.collisions.Rectangle;
import it.bomberman.gfx.Animation;
import it.bomberman.gfx.Assets;

//import org.dyn4j.geometry.Vector2;

public class Bomb extends AbstractEntity implements ICollidable {

	public static final long DEFAULT_TIMER_LENGTH = (long) 3e+9; // 3s espressi in nano secondi
	public static final int DEFAULT_WIDTH = 100;
	public static final int DEFAULT_EXPLOSION_EXTENTION = 1;

	private long startTime;
	private final long timerLength = DEFAULT_TIMER_LENGTH;
	private int exlposionExtention;
	private boolean exploded = false;
	private final int SCALE = 2;
	private Animation animation;

	protected Bomb(int x, int y, int width, int height, EntityController controller) {
		super(x, y, width, height, controller);
	}

	public Bomb(int x, int y, int explosionExtension, EntityController controller) {
		this(x, y, DEFAULT_WIDTH, DEFAULT_WIDTH, controller);
		this.exploded = false;
		this.exlposionExtention = explosionExtension;
		this.animation = new Animation(600, Assets.bomb);
		this.startTime = System.nanoTime();
	}

	@Override
	protected void initBody() {
		this.body = new Body();
		this.body.add(new Rectangle(x, y, width, height));
	}

	@Override
	public void tick() {
		long now = System.nanoTime();
		if (now - this.startTime > this.timerLength) {
			this.explode();
		}
		this.animation.tick();
	}

	@Override
	public void render(Graphics g) {

		// this.body.render(g, Color.BLUE);
		g.drawImage(getCurrentAnimationFrame(), this.x-40, this.y-20, this.width * SCALE, this.height * SCALE, null);
	}

	public void explode() {
		this.exploded = true;

		this.controller.register(new Explosion(this.x, this.y, this.exlposionExtention, this.controller));
		this.dispose();
	}

	@Override
	public boolean shouldCollide(ICollidable collidable) {
		return collidable instanceof Explosion;
	}

	@Override
	public void collision(ICollidable collidable) {
		if (collidable instanceof Explosion) {
			this.collision((Explosion) collidable);
		}
	}

	public void collision(Explosion explosion) {
		this.explode();
	}

	@Override
	public void dispose() {
		this.controller.notifyDisposal(this);
	}

	public boolean hasFinished() {
		return this.exploded;
	}

	private BufferedImage getCurrentAnimationFrame() {
		return this.animation.getCurrentFrame();
	}

}
