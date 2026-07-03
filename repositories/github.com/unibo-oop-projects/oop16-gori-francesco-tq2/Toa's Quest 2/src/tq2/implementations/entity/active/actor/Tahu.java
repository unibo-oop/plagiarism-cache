package tq2.implementations.entity.active.actor;

import java.util.HashMap;

import tq2.implementations.Id;
import tq2.implementations.SoundImpl;
import tq2.implementations.entity.AttackRectangle;
import tq2.implementations.entity.active.Fireball;
import tq2.implementations.graphics.Spritesheet;
import tq2.interfaces.Entity;
import tq2.interfaces.Handler;
import tq2.interfaces.LevelLayer;
import tq2.interfaces.platform.tqgame.TQPlayer;

/**
 * The player for the first level of the game Toa's Quest 2. Tahu and BIONICLE belong to the LEGO Group.
 */
public class Tahu extends TQPlayerImpl implements TQPlayer {
	
	/** The width of the bounding box of this object. */
	protected static final Integer WIDTH = 16;
	
	/** The height of the bounding box of this object. */
	protected static final Integer HEIGHT = 36;
	
	/** The gravity acceleration the object is subject to. */
	protected static final Double GRAVITY_ACCELERATION = 0.27;
	
	/** The maximum velocity the object can reach along the X axis. */
	protected static final Integer MAXVELX = 7;
	
	/** The height of the object when crouched. */
	protected static final Integer CROUCHED_HEIGHT = 20;
	
	/** The speed the object reaches when running. */
	protected static final Integer RUNSPEED = 4;
	
	/** The speed the object reaches when walking. */
	protected static final Integer WALKSPEED = 1;
	
	/** The maximum velocity the object can reach along the Y axis. */
	protected static final Integer MAXVELY = 7;
	
	/** The strength of a jump of the object. */
	protected static final Double JUMPSTRENGTH = 6.5;
	
	/** The maximum number of jumps the object can perform mid-air. */
	protected static final Integer MAXJUMPS = 2;
	
	/** The the maximum amount of HP of the object. */
	protected static final Integer HP = 600;
	
	/** Themaximum amount of stamina of the object. */
	protected static final Integer STAMINA = 300;
	
	/** How much stamina the object recovers at each tick. */
	protected static final Double STAMINA_RECOVER = 0.3;
	
	/** The range of an attack of the object. */
	protected static final Integer ATTACK_RANGE = 45;
	
	/** The damage of an attack from the object. */
	protected static final Integer ATTACK_DAMAGE = 10;
	
	/** The width of the field of view of the object. If an Entity enters the area, a specific behavior can be triggered. */
	protected static final Integer AREA_WIDTH = 160;
	
	/** The height of the field of view of the object. If an Entity enters the area, a specific behavior can be triggered. */
	protected static final Integer AREA_HEIGHT= 60;
	
	/** The X offset of the animations in relation to the object. */
	protected static final Integer OFFSETX = -38;
	
	/** The Y offset of the animations in relation to the object. */
	protected static final Integer OFFSETY = -23;
	
	/** The cost of shooting a projectile for this object. */
	protected static final Integer FIREBALL_COST = 60;
	
	/** The damage a projectile of the object can do. */
	protected static final Integer FIREBALL_DAMAGE = 5;

	/** The spritesheet containing the frames of the animations of this object. */
	protected static final String SPRITESHEET = "/Tahu.png";
	
	/**
	 * Instantiates a new Tahu object.
	 *
	 * @param x the X coordinate
	 * @param y the Y coordinate
	 * @param facing the direction the object is facing
	 * @param solid whether the object is solid
	 * @param scaleX the scale of the object along the X axis
	 * @param scaleY the scale of the object along the Y axis
	 * @param alpha the alpha value of the object
	 * @param handler the Handler
	 * @param layer the layer the object resides in
	 */
	public Tahu (
			Integer x, Integer y,
			Integer facing,
			boolean solid, 
			Double scaleX, Double scaleY,
			float alpha,
			Handler handler,
			LevelLayer layer) {
		
		super (x, y, WIDTH, HEIGHT, facing, solid, true, scaleX, scaleY, 0.0, 0.0, alpha, handler, layer, GRAVITY_ACCELERATION, MAXVELX, MAXVELY, CROUCHED_HEIGHT, JUMPSTRENGTH, MAXJUMPS, HP, STAMINA, STAMINA_RECOVER, RUNSPEED, WALKSPEED, ATTACK_DAMAGE, ATTACK_RANGE, FIREBALL_DAMAGE, FIREBALL_COST, AREA_WIDTH, AREA_HEIGHT);

		this.id = Id.player;
		this.addKanohi(1);
		
		this.shooting = false;
		
		HashMap <String, Spritesheet> animationsheets = this.getHandler().getGame().getCurrentLevel().getSpritesheets();
		animationsheets.put(SPRITESHEET, new Spritesheet(SPRITESHEET));

		//Stopped
		animations.put("Idle", animationsheets.get(SPRITESHEET).getAnim(0, 0, 85, 60, OFFSETX, OFFSETY, 1, 15, 0));
		//Falling
		animations.put("Fall", animationsheets.get(SPRITESHEET).getAnim(16*85, 0, 85, 60, OFFSETX, OFFSETY, 1, 2, 0));
		//Running
		animations.put("Run", animationsheets.get(SPRITESHEET).getAnim(1*85, 0, 85, 60, OFFSETX, OFFSETY, 11, 2, 2));
		//Jumping
		animations.put("Jump", animationsheets.get(SPRITESHEET).getAnim(12*85, 0, 85, 60, OFFSETX, OFFSETY, 1, 2, 0));
		//Double Jump
		animations.put("Double Jump", animationsheets.get(SPRITESHEET).getAnim(13*85, 0, 85, 60, OFFSETX, OFFSETY, 4, 2, 3));
		//Walking
		animations.put("Walk", animationsheets.get(SPRITESHEET).getAnim(17*85, 0, 85, 60, OFFSETX, OFFSETY, 8, 2, 0));
		//Crouching
		animations.put("Crouch", animationsheets.get(SPRITESHEET).getAnim(25*85, 0, 85, 60, OFFSETX, OFFSETY, 1, 2, 0));
		//Hit
		animations.put("Hit", animationsheets.get(SPRITESHEET).getAnim(26*85, 0, 85, 60, OFFSETX, OFFSETY, 2, 4, 0));
		//Die
		animations.put("Die", animationsheets.get(SPRITESHEET).getAnim(28*85, 0, 85, 60, OFFSETX, OFFSETY, 5, 2, 4));
		//Attack
		animations.put("Attack", animationsheets.get(SPRITESHEET).getAnim(33*85, 0, 85, 60, OFFSETX, OFFSETY, 4, 2, 3));
		//Attack Jump
		animations.put("Attack Jump", animationsheets.get(SPRITESHEET).getAnim(56*85, 0, 85, 60, OFFSETX, OFFSETY, 4, 2, 3));
		//Shoot
		animations.put("Shoot", animationsheets.get(SPRITESHEET).getAnim(40*85, 0, 85, 60, OFFSETX, OFFSETY, 5, 1, 4));
		//Shoot Jump
		animations.put("Shoot Jump", animationsheets.get(SPRITESHEET).getAnim(48*85, 0, 85, 60, OFFSETX, OFFSETY, 5, 1, 4));
		
		//Hau
		animations.put("Hau", animationsheets.get(SPRITESHEET).getAnim(62*85, 0, 85, 60, OFFSETX, OFFSETY, 8, 2, 0));
		//Miru
		animations.put("Miru", animationsheets.get(SPRITESHEET).getAnim(71*85, 0, 85, 60, OFFSETX, OFFSETY, 4, 2, 3));
		//Kakama
		animations.put("Kakama", animationsheets.get(SPRITESHEET).getAnim(1*85, 0, 85, 60, OFFSETX, OFFSETY, 11, 1, 2));

		this.sounds.put("Fireball", new SoundImpl("/sound/fireball.wav"));
		this.sounds.put("Run", new SoundImpl("/sound/run.wav"));
		this.sounds.put("Walk", new SoundImpl("/sound/walk.wav"));
		this.sounds.put("Hit ground", new SoundImpl ("/sound/hit ground.wav"));
		this.sounds.put("Hit", new SoundImpl("/sound/hit.wav"));
		this.sounds.put("Attack", new SoundImpl("/sound/fire attack.wav"));
		this.sounds.put("Hau", new SoundImpl("/sound/forcefield.wav"));
		this.sounds.put("Kakama", new SoundImpl("/sound/kakama.wav"));
	}
	
	/**
	 * Instantiates a new Tahu object.
	 *
	 * @param x the X coordinate
	 * @param y the Y coordinate
	 * @param facing the direction the object is facing
	 * @param solid whether the object is solid
	 * @param width the width desired for the object. The X scale will be automatically calculated to fit this value.
	 * @param height the height desired for the object. The Y scale will be automatically calculated to fit this value.
	 * @param alpha the alpha value of the object
	 * @param handler the Handler
	 * @param layer the layer the object resides in
	 */
	public Tahu (
			Integer x, Integer y,
			Integer facing,
			boolean solid,
			Integer width, Integer height,
			float alpha, 
			Handler handler,
			LevelLayer layer) {
		
		this(x, y, facing, solid, ((double) (width)/WIDTH), ((double) (height)/HEIGHT), alpha, handler, layer);
	}
	
	/**
	 * Instantiates a new Tahu object.
	 *
	 * @param x the X coordinate
	 * @param y the Y coordinate
	 * @param solid whether the object is solid
	 * @param handler the Handler
	 * @param layer the layer the object resides in
	 */
	public Tahu (
			Integer x, Integer y,
			boolean solid,
			Handler handler,
			LevelLayer layer) {
		
		this(x, y, 1, solid, 1.0, 1.0, 1.0f, handler, layer);
	}

	/* (non-Javadoc)
	 * @see com.tq2.implementations.entity.active.actor.TQPlayerImpl#tick()
	 */
	@Override
	public void tick() {
		super.tick();
		
		//once the animation has played, shoot a fireball
		if (this.isShooting()) {
			if (this.animLoop != 0) {
				this.setShooting(false);
				this.getHandler().addEntity(new Fireball (this.getX() + ((new Double(36*this.getScaleX()).intValue()) * this.getFacing()), this.getY() - ( new Double(22 * this.getScaleY()).intValue() ), this.getFacing(), FIREBALL_DAMAGE, 5, this.getHandler(), this.layer()), this.layer().getIndex());
			}
		}
	}
	
	/* (non-Javadoc)
	 * @see com.tq2.implementations.entity.active.actor.TQPlayerImpl#tickSound()
	 */
	@Override
	protected void tickSound() {
		super.tickSound();
	}
	
	/* (non-Javadoc)
	 * @see com.tq2.implementations.entity.active.actor.TQPlayerImpl#collide(com.tq2.interfaces.Entity)
	 */
	@Override
	protected void collide(Entity en) {
		super.collide(en);
		//if there's collision with a Nui Jaga, decrease the health by 5
		if (this.getKanohiList().get(0) != 1 && !this.isHit()) {
			if (en.getId() == Id.nuiJaga) {
					this.addHp(-5);
			}

			//if there's collision with the attack rectangle of a Nui Jaga, decrease the health of the damage of the attack
			if (en.getId() == Id.attackRectangle) {
				if (((AttackRectangle)en).getTarget().getId() == Id.nuiJaga) {
					if (this.getBounds().intersects(en.getBounds())) {
						en.destroy();
						this.addHp( -((AttackRectangle)en).getDamage() );
					}
				}
			}
		}
		
		//If there's collision with lava set health to 0 and make the player do a small jump before falling
		if (en.getId() == Id.lava && this.getHp()>0) {
			this.setGravity(-5);
			this.addHp(-this.getHp());
			this.setVelX(0);
		}
	}
}
