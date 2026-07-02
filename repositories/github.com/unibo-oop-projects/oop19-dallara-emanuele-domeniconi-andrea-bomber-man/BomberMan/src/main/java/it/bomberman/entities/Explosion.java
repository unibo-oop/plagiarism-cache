package it.bomberman.entities;

import java.awt.Color;
import java.awt.Graphics;

import it.bomberman.collisions.Body;
import it.bomberman.collisions.ICollidable;
import it.bomberman.collisions.Rectangle;
import it.bomberman.collisions.Vector2;
import it.bomberman.gfx.Assets;

public class Explosion implements Entity{

	/**
	 * Un esplosione si occupa di gestire due Shape Rettangolari e di innescare
	 * collisioni con Player, Wall e Bomb
	 * 
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 */

	public static final long DEFAULT_TIMER_LENGTH = (long) 1e9; // 1s espressi in nano secondi
	public final static int DEFAULT_EXPLOSION_EXTENSION = 1;
	public final static int UNIT = WallFactoryImpl.DEFAULT_WALL_WIDTH;
	public final static int THICKNESS = 35;
	private int explExtension;
	private long startTime;
	private final long timerLength = DEFAULT_TIMER_LENGTH;
	
	private int x;
	private int y;
	private EntityController controller;
	private Body body;

	public Explosion(int x, int y, int explExtension, EntityController controller) {
		this.x = x;
		this.y = y;
		this.explExtension = explExtension;
		this.controller = controller;
		initBody();
		this.startTime = System.nanoTime();
	}

	private void initBody() {
		this.body = new Body();
		int w = (1 + 2 * this.explExtension) * UNIT;
		this.body.add(new Rectangle(this.x - (w - THICKNESS) / 2, this.y, w, THICKNESS));
		this.body.add(new Rectangle(this.x, this.y - (w - THICKNESS) / 2, THICKNESS, w));
	}

	@Override
	public void tick() {
		if (System.nanoTime() - this.startTime > this.timerLength) {
			this.end();
		}
		this.controller.checkAndResolveCollisions(this);
	}

	@Override
	public void render(Graphics g) {
		int centerX = this.x - 8;
		int centerY = this.y -15;
		this.body.render(g, Color.YELLOW);
		
		// scale � dovuto al crop non corretto
		// viene lasciato un bordo trasparente in alpha layer intorno alla Image
		// Deve essere corretto in Assetts
		double scaleX = 2.135;
		double scaleY = 2.5;
		Rectangle rect = (Rectangle) this.body.getBoundingShapes().get(0);
		int x = rect.getPosition().getX();
		int y = rect.getPosition().getY();
		int w = (int)(rect.getWidth() * scaleX);
		int h = (int)(rect.getHeight() * scaleY);
		// UP|DOWN
		g.drawImage(Assets.explosion[1], x, y, w, h, null);
		rect = (Rectangle) this.body.getBoundingShapes().get(1);
		x = rect.getPosition().getX();
		y = rect.getPosition().getY();
		w = (int) (rect.getWidth() * scaleY);
		h = (int) (rect.getHeight()* scaleX);
		// LEFT|RIGHT
		g.drawImage(Assets.explosion[2], x,y,w,h,null);
		
		// CENTER
		g.drawImage(Assets.explosion[0], centerX, centerY, w-8, w, null);

	}

	@Override
	public Vector2 getPosition() {
		return Vector2.unmodifiableVector2(new Vector2(this.x, this.y));
	}

	@Override
	public boolean shouldCollide(ICollidable collidable) {
		if (collidable instanceof Player) {
			return true;
		}
		if (collidable instanceof Wall) {
			return true;
		}
		if (collidable instanceof Bomb) {
			return true;
		}
		return false;
	}

	public void collision(Player player) {
		player.collision(this);
	}

	public void collision(Bomb bomb) {
		// TODO Auto-generated method stub

	}

	// innesca la collisione e la lascia gestire all'oggetto specifico
	public void collision(ICollidable collidable) {
		collidable.collision(this);
//		if (collidable instanceof Wall) {
//			collision((Wall) collidable);
//		} else if (collidable instanceof Player) {
//			collision((Player) collidable);
//		} else if (collidable instanceof Bomb) {
//			collision((Bomb) collidable);
//		}
	}

//	public void collision(Wall wall) {
//		// Explosion serve solo a innescare collisioni in altre Entity
//		// per questo le consguenze delle collisioni sono gestite in Wall
//		wall.collision(this);
//	}

	@Override
	public Body getBody() {
		return this.body;
	}

	public void end() {
		this.controller.notifyDisposal(this);
	}

	@Override
	public void dispose() {
		this.controller.notifyDisposal(this);
	}
}
