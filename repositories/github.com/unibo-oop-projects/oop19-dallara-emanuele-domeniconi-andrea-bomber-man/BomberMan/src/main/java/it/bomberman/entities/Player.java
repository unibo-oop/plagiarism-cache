package it.bomberman.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.HashSet;
import java.util.Set;
import it.bomberman.collisions.Body;
import it.bomberman.collisions.ICollidable;
import it.bomberman.collisions.Rectangle;
import it.bomberman.collisions.Vector2;
import it.bomberman.entities.PowerUp.PowerUpType;
import it.bomberman.gfx.*;
import it.bomberman.input.KeyManager;

public class Player extends AbstractEntity {

	public static final long DEFAULT_DROP_COOL_DOWN = (long) 5e8; // 0.5 s
	public static final int DEFAULT_PLAYER_WIDTH = 128;
	public static final int DEFAULT_PLAYER_HEIGHT = 100;
	public static final int SPEED_MULTIPLIER = 2;
	private Animation animDown, animUp, animLeft, animRight, animBomb;
	private KeyManager keyManager;
	private int playerNumb;
	private Body body;
	private final int cropOffsetX = 17;
	private final int cropOffsetY = 22;

	private final int initialPosX;
	private final int initialPosY;
	private int health;
	private int speed;
	private int nBombs;
	private int bombExtension;
	
	private long spawnTime;
	private boolean invulnerable;

	private long lastBombDroppedTime = 0;
	private long bombDroppedCoolDown = DEFAULT_DROP_COOL_DOWN;
	private int xMove;
	private int yMove;
	private Set<Bomb> bombs;

	protected Player(int x, int y, int width, int height, EntityController controller) {
		super(x, y, width, height, controller);
		this.initialPosX = x;
		this.initialPosY = y;
		init();
	}

	public Player(int x, int y, int n, KeyManager keyManager, EntityController controller) {
		this(x, y, DEFAULT_PLAYER_WIDTH, DEFAULT_PLAYER_HEIGHT, controller);
		this.playerNumb = n;
		this.keyManager = keyManager;

		this.bombs = new HashSet<Bomb>();
		// !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
		// Creare una classe esterna che gestisce i player per animazioni!!

		if (playerNumb == 1) {
			animDown = new Animation(150, Assets.player_d);
			animUp = new Animation(150, Assets.player_u);
			animLeft = new Animation(150, Assets.player_l);
			animRight = new Animation(150, Assets.player_r);
			animBomb = new Animation(150, Assets.player_bomb);
		}
		if (playerNumb == 2) {
			animDown = new Animation(150, Assets.player_d2);
			animUp = new Animation(150, Assets.player_u2);
			animLeft = new Animation(150, Assets.player_l2);
			animRight = new Animation(150, Assets.player_r2);
			animBomb = new Animation(200, Assets.player_bomb2);
		}
	}

	private void init() {
		this.x = initialPosX;
		this.y = initialPosY;
		this.health = 1;
		this.nBombs = 1;
		this.speed = 1;
		this.bombExtension = 1;
		this.invulnerable = true;
		this.spawnTime = System.nanoTime();
	}

	@Override
	protected void initBody() {
		this.body = new Body();
		this.body.add(new Rectangle(this.x + cropOffsetX, this.y + cropOffsetY, 40, 70));
	}

	public void getInput() {
		xMove = 0;
		yMove = 0;
		
		// v(x) = 2x + 2
		int s = SPEED_MULTIPLIER * this.speed +2;
		
		if (this.playerNumb == 1) {
			if (this.keyManager.up)
				yMove -= s;
			if (this.keyManager.down)
				yMove = s;
			if (this.keyManager.left)
				xMove -= s;
			if (this.keyManager.right)
				xMove = s;
		}

		if (this.playerNumb == 2) {
			if (this.keyManager.up2)
				yMove -= s;
			if (this.keyManager.down2)
				yMove = s;
			if (this.keyManager.left2)
				xMove -= s;
			if (this.keyManager.right2)
				xMove = s;
		}
		if (this.keyManager.drop && playerNumb == 1) {
			dropBomb();
		}

		if (this.keyManager.drop2 && playerNumb == 2) {
			dropBomb();
		}
	}

	@Override
	public void tick() {
		// se � spawnato da piu' di 1s allora puo' deve essere vulenrabile
		if(System.nanoTime() - this.spawnTime > 2e9) { //2s
			this.invulnerable = false;
		}
		
		// rimuove il riferimento ad ogni bomba gia' esplosa
		this.bombs.removeIf(Bomb::hasFinished);
		animDown.tick();
		animLeft.tick();
		animRight.tick();
		animUp.tick();
		animBomb.tick();

		getInput();
		int oldX = this.x;
		int oldY = this.y;
		this.x += xMove;
		this.y += yMove;
		this.body.move(this.x + cropOffsetX, this.y + cropOffsetY);
		if (this.controller.verifyCollision(this)) {
			this.x = oldX;
			this.y = oldY;
			this.xMove = 0;
			this.yMove = 0;
			this.body.move(this.x + cropOffsetX, this.y + cropOffsetY);
		}
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(getCurrentAnimationFrame(), x, y, width, height, null);

		// debug only
		// this.body.render(g, Color.RED);
	}

	private BufferedImage getCurrentAnimationFrame() {
		if (this.keyManager.drop && playerNumb == 1 && canDropBomb()) {
			return animBomb.getCurrentFrame();
		}
		if (this.keyManager.drop2 && playerNumb == 2 && canDropBomb())
			return animBomb.getCurrentFrame();

		if (xMove < 0) {
			return animLeft.getCurrentFrame();
		} else if (xMove > 0) {
			return animRight.getCurrentFrame();
		} else if (yMove < 0) {
			return animUp.getCurrentFrame();
		} else {
			return animDown.getCurrentFrame();
		}
	}

	@Override
	public Vector2 getPosition() {
		return new Vector2(this.x, this.y);
	}

	@Override
	public Body getBody() {
		// versione non modificabile per preservare l'incapsulamento
		return this.body;
	}

	@Override
	public boolean shouldCollide(ICollidable collidable) {
		if (collidable instanceof Wall) {
			return true;
		}
		return false;
	}

	@Override
	public void collision(ICollidable collidable) {
		if (collidable instanceof Explosion) {
			this.collision((Explosion) collidable);
		}
		if (collidable instanceof PowerUp) {
			collision((PowerUp) collidable);
		}
	}

	public void collision(Explosion exp) {
		if (!this.invulnerable) {
			hit();
		}
	}

	public void collision(Wall wall) {
		// Do Nothing
		// Die if deathWall

		// Qui andrebbe implementata la dinamica di rollback

	}

	public void collision(PowerUp up) {
		if (up.getType() == PowerUpType.BOMB_NUM) {
			this.nBombs += up.getValue();
		}

		if (up.getType() == PowerUpType.LIFE) {
			this.health += up.getValue();
		}

		if (up.getType() == PowerUpType.BOMB_EXTENSION) {
			this.bombExtension += up.getValue();
		}

		if (up.getType() == PowerUpType.SPEED) {
			this.speed += up.getValue();
		}
	}

	public void dropBomb() {
		if (canDropBomb()) {
			Bomb b = new Bomb(this.x + cropOffsetX, this.y + cropOffsetY, this.bombExtension, this.controller);
			this.bombs.add(b);
			this.controller.register(b);
			this.lastBombDroppedTime = System.nanoTime();
		}
	}

	@Override
	public void dispose() {
		this.controller.notifyDisposal(this);
	}

	private void die() {
		if (this.health >= 1) {
			respawn();
		} else {
			this.dispose();
		}
	}

	private boolean canDropBomb() {
		// se non ci sono bombe sul campo
		// oppure
		// ci sono meno bombe del massi ed e' passato abbastanza tempo dall'ultima bomba
		return (this.bombs.size() == 0) || ((this.bombs.size() < this.nBombs)
				&& (System.nanoTime() - this.lastBombDroppedTime > this.bombDroppedCoolDown));
	}

	private void hit() {
		this.health--;
		die();
	}

	private void respawn() {
		int tmp= this.getHealth();
		init();
		this.health=tmp;
	}

	public int getHealth() {
		return this.health;
	}

	public int getSpeed() {
		return this.speed;
	}

	public int getBombsNumber() {
		return nBombs;
	}

	public int getBombExtension() {
		return this.bombExtension;
	}

	public int getPlayerNumb() {
		return this.playerNumb;
	}

}
