package tq2.implementations.entity.active.actor;

import java.util.HashMap;

import tq2.implementations.*;
import tq2.implementations.entity.AttackRectangle;
import tq2.implementations.entity.active.Fireball;
import tq2.implementations.entity.tile.Tile;
import tq2.implementations.graphics.Spritesheet;
import tq2.interfaces.Entity;
import tq2.interfaces.Handler;
import tq2.interfaces.LevelLayer;
import tq2.interfaces.platform.tqgame.TQPlayer;

/**
 * The Nui Jaga is an enemy to the player in the game Toa's Quest 2. It's a scorpion-like NPC that walks back and forth and attacks
 * the player if he enters his field of view.
 * 
 * @author Francesco Gori
 */
public class NuiJaga extends ActorImpl {
	
	/** The width of the bounding box of this object. */
	protected static final Integer WIDTH = 54;
	
	/** The height of the bounding box of this object. */
	protected static final Integer HEIGHT = 34;
	
	/** The gravity acceleration the object is subject to. */
	protected static final Double GRAVITY = 0.27;
	
	/** The maximum velocity the object can reach along the X axis. */
	protected static final Integer MAXVELX = 4;
	
	/** The  maximum velocity the object can reach along the Y axis. */
	protected static final Integer MAXVELY = 7;
	
	/** The speed the object reaches when running. */
	protected static final Integer RUNSPEED = 1;
	
	/** The damage of an attack from the object. */
	protected static final Integer ATTACK_DAMAGE = 5;
	
	/** The range of an attack of the object. */
	protected static final Integer ATTACK_RANGE= 10;
	
	/** The width of the field of view of the object. If an Entity enters the area, a specific behavior can be triggered. */
	protected static final Integer AREA_WIDTH = 160;
	
	/** The height of the field of view of the object. If an Entity enters the area, a specific behavior can be triggered. */
	protected static final Integer AREA_HEIGHT= 60;
	
	/** The the maximum amount of HP of the object. */
	protected static final Integer HP = 50;
	
	/** The X offset of the animations in relation to the object. */
	protected static final Integer OFFSETX = -3;
	
	/** The Y offset of the animations in relation to the object. */
	protected static final Integer OFFSETY = -3;

	/** The spritesheet containing the frames of the animations of this object. */
	protected static final String SPRITESHEET = "/nui-jaga.png";
	
	/** The left limit of the path back and forth of this object. */
	protected Integer leftLimit;
	
	/** The right limit of the path back and forth of this object. */
	protected Integer rightLimit;
	
	/** If the object can move. */
	protected Boolean stop;

	/**
	 * Instantiates a new NuiJaga.
	 *
	 * @param x the X coordinate
	 * @param y the Y coordinate
	 * @param facing the direction the object is facing
	 * @param range the range of the path back and forth of this object
	 * @param solid whether the object is solid
	 * @param enabled whether the object is enabled
	 * @param scaleX the scale of the object along the X axis
	 * @param scaleY the scale of the object along the Y axis
	 * @param alpha the alpha value of the object
	 * @param handler the Handler
	 * @param layer the layer the object resides in
	 */
	public NuiJaga (
			Integer x, Integer y,
			Integer facing,
			Integer range,
			Boolean solid,
			Boolean enabled,
			Double scaleX, Double scaleY,
			Float alpha,
			Handler handler,
			LevelLayer layer) {
		
		super(x, y, WIDTH, HEIGHT, facing, solid, enabled, scaleX, scaleY, 0.0, 0.0, alpha, handler, layer, GRAVITY, MAXVELX, MAXVELY, HEIGHT, 0.0, 0, HP, 0, 0.0, RUNSPEED, RUNSPEED, ATTACK_DAMAGE, ATTACK_RANGE, AREA_WIDTH, AREA_HEIGHT);

		this.id = Id.nuiJaga;
		
		this.leftLimit = x - range;
		this.rightLimit = x + range;
		
		this.stop = false;
		
		HashMap <String, Spritesheet> animationsheets = this.getHandler().getGame().getCurrentLevel().getSpritesheets();
		animationsheets.put(SPRITESHEET, new Spritesheet(SPRITESHEET));
		
		//Stopped
		animations.put("Idle", animationsheets.get(SPRITESHEET).getAnim(0, 0, 58, 38, OFFSETX, OFFSETY, 1, 4, 0));
		//Running
		animations.put("Run", animationsheets.get(SPRITESHEET).getAnim(6*58, 0, 58, 38, OFFSETX, OFFSETY, 3, 2, 0));
		//Hit
		animations.put("Hit", animationsheets.get(SPRITESHEET).getAnim(9*58, 0, 58, 38, OFFSETX, OFFSETY, 2, 4, 0));
		//Attack
		animations.put("Attack", animationsheets.get(SPRITESHEET).getAnim(1*58, 0, 58, 38, OFFSETX, OFFSETY, 5, 2, 4));
		
		sounds.put("Hit", new SoundImpl("/sound/hit-nuijaga.wav"));
	}
	
	/**
	 * Instantiates a new NuiJaga.
	 *
	 * @param x the X coordinate
	 * @param y the Y coordinate
	 * @param facing the direction the object is facing
	 * @param range the range of the path back and forth of this object
	 * @param solid whether the object is solid
	 * @param enabled whether the object is enabled
	 * @param width the width desired for the object. The X scale will be automatically calculated to fit this value.
	 * @param height the height desired for the object. The Y scale will be automatically calculated to fit this value.
	 * @param alpha the alpha value of the object
	 * @param handler the Handler
	 * @param layer the layer the object resides in
	 */
	public NuiJaga (
			Integer x, Integer y,
			Integer facing,
			Integer range,
			Boolean solid,
			Boolean enabled,
			Integer width, Integer height,
			Float alpha, 
			Handler handler,
			LevelLayer layer) {
		
		this (x, y, facing, range, solid, enabled, ((double) (width)/WIDTH), ((double) (height)/HEIGHT), alpha, handler, layer);
	}
	
	/**
	 * Instantiates a new NuiJaga.
	 *
	 * @param x the X coordinate of the object
	 * @param y the Y coordinate of the object
	 * @param solid whether the object is solid
	 * @param range the range of the path back and forth of this object
	 * @param handler the Handler
	 * @param layer the layer this object resides in
	 */
	public NuiJaga (
			Integer x, Integer y,
			boolean solid,
			Integer range,
			Handler handler,
			LevelLayer layer) {
		
		this (x, y, 1, range, solid, true, 1.0, 1.0, 1.0f, handler, layer);
	}

	/* (non-Javadoc)
	 * @see com.tq2.implementations.entity.active.actor.ActorImpl#tick()
	 */
	@Override
	public void tick() {
		super.tick();
		
		if (this.getHp() <= 0) {
			this.die();
		}
		
		else {
			
			this.resetMovementEnabled();
			
			//if the limit of the path is reached, turn
			if (this.getX()>= this.rightLimit) {
				this.setFacing(-1);
			}
			if (this.getX() <= this.leftLimit) {
				this.setFacing(1);
			}
			
			setRunning(true);
		}

	}
	
	/* (non-Javadoc)
	 * @see com.tq2.implementations.entity.EntityImpl#die()
	 */
	public void die() {
		this.stop = true;
		this.destroy();
	}

	/* (non-Javadoc)
	 * @see com.tq2.implementations.entity.active.actor.ActorImpl#isCloseTo(com.tq2.interfaces.Entity)
	 */
	@Override
	protected void isCloseTo(Entity en) {

		if (en.getId() == Id.player) {
			if ( en.getX() > this.leftLimit && en.getX() < this.rightLimit && ((TQPlayer)en).getHp() > 0 && en.getY() >= this.getY()) {
				if ((en.getX() - this.getX()) * this.getFacing() < 0) {
					this.setFacing(-this.getFacing());
				}
				
				if (Math.abs(en.getX() - this.getX()) < this.attackRange*2) {
					this.attack();
				}
			}
		}
	}
	
	/* (non-Javadoc)
	 * @see com.tq2.implementations.entity.active.ActiveImpl#collide(com.tq2.interfaces.Entity)
	 */
	@Override
	protected void collide (Entity en) {
		if (en.getId() == Id.attackRectangle) {
			if (((AttackRectangle)en).getTarget().getId() == Id.player) {
				if (this.getBounds().intersects(en.getBounds())) {
					en.destroy();
					this.addHp( -((AttackRectangle)en).getDamage() );
					this.resetMovementEnabled();
				}
			}
		}
		
		else if (en.getId() == Id.fireball) {
			//en.die();
			this.addHp( - ((Fireball)en).getDamage() );
			en.destroy();
			this.resetMovementEnabled();
		}
		
		else if (en.getId() == Id.lava && this.getHp()>0) {
			this.addHp(-this.getHp());
			this.setVelX(0);
		}
	}

	/* (non-Javadoc)
	 * @see com.tq2.implementations.entity.active.ActiveImpl#collideLeft(com.tq2.interfaces.Entity)
	 */
	@Override
	protected void collideLeft(Entity en) {
		//if the object collides with a tile on the left side, turn
		super.collideLeft(en);
		if (en instanceof Tile) {
			this.setFacing(-this.getFacing());
			this.setVelX(this.getVelX()*(-1));
		}
		
		//if the object collides with the player on the left side, turn towards him and attack. Also, subtract 1 from the player's health
		if (en instanceof TQPlayer) {
			if (((TQPlayer)en).getHp() > 0) {
				if (!this.isFalling()) {
					if (this.getFacing() == -1) {
						this.setFacing(1);
					}
					this.attack();
				}
			}
		}
	}

	/* (non-Javadoc)
	 * @see com.tq2.implementations.entity.active.ActiveImpl#collideRight(com.tq2.interfaces.Entity)
	 */
	@Override
	protected void collideRight(Entity en) {
		super.collideRight(en);
		//if the object collides with a tile on the right side, turn
		if (en instanceof Tile) {
			this.setFacing(-this.getFacing());
			this.setVelX(this.getVelX()*this.getFacing());
		}
		
		//if the object collides with the player on the right side, turn towards him and attack. Also, subtract 1 from the player's health
		if (en instanceof TQPlayer) {
			if (((TQPlayer)en).getHp() > 0) {
				if (!this.isFalling()) {
					if (this.getFacing() == -1) {
						this.setFacing(1);
					}
					this.attack();
				}
			}
		}
	}

	/* (non-Javadoc)
	 * @see com.tq2.implementations.entity.active.actor.ActorImpl#resetMovementEnabled()
	 */
	@Override
	public void resetMovementEnabled() {
		this.movementEnabled = !(this.isAttacking() || (this.getHp()<= 0) || this.isHit() || this.stop);
	}
	
	/**
	 * Sets whether the character can move.
	 *
	 * @param stop whether the character can move or not
	 */
	protected void setStop(Boolean stop) {
		if (this.stop != stop) {
			this.stop = stop;
			this.resetMovementEnabled();
		}
	}
}
