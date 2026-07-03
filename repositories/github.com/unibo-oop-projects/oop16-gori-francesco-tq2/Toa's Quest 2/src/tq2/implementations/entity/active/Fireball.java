package tq2.implementations.entity.active;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.util.HashMap;

import tq2.implementations.Id;
import tq2.implementations.SoundImpl;
import tq2.implementations.entity.Light;
import tq2.implementations.entity.tile.Tile;
import tq2.implementations.graphics.Spritesheet;
import tq2.interfaces.Entity;
import tq2.interfaces.Handler;
import tq2.interfaces.LevelLayer;

/**
 * The Class Fireball represents a fireball object. A Light object is automatically created at the same coordinates of each
 * fireball object. The fireball will loop sound until it's destroyed.
 * 
 * @author Francesco Gori
 */
public class Fireball extends ActiveImpl {
		
	/** The width of the bounding box of the object. */
	protected static final Integer WIDTH = 6;
	
	/** The height of the bounding box of the object. */
	protected static final Integer HEIGHT = 5;
	
	/** The spritesheet containing the animations for this object. */
	protected static final String SPRITESHEET = "/fireball.png";

	/** The damage this object can do to Actors that collide with it. */
	public Integer damage;
	
	/**
	 * Instantiates a new Fireball.
	 *
	 * @param x the X coordinate
	 * @param y the Y coordinate
	 * @param facing the direction
	 * @param damage the damage this object will do to Actors that collide with it
	 * @param solid whether this object is solid
	 * @param enabled whether this object is enabled
	 * @param scaleX the scale of this object along the X axis
	 * @param scaleY the scale of this object along the Y axis
	 * @param velX the velocity of this object along the X axis
	 * @param alpha the alpha value of this object
	 * @param handler the Handler
	 * @param layer the layer this object resides in
	 */
	public Fireball (Integer x, Integer y, Integer facing, Integer damage, Boolean solid, Boolean enabled, Double scaleX, Double scaleY, Integer velX, float alpha, Handler handler, LevelLayer layer) {
		super(x, y, WIDTH, HEIGHT, facing, solid, enabled, scaleX, scaleY, velX.doubleValue(), 0.0, alpha, handler, layer, 0.0, velX, velX);
		

		this.setVelX(this.getVelX()*this.getFacing());
		
		this.id = Id.fireball;
		this.damage = damage;
		
		HashMap <String, Spritesheet> spritesheets = this.getHandler().getGame().getCurrentLevel().getSpritesheets();
		spritesheets.put(SPRITESHEET, new Spritesheet(SPRITESHEET));
		animations.put("Idle", spritesheets.get(SPRITESHEET).getAnim(0, 0, 11, 7, -4, -1, 3, 2, 0));
		
		sounds.put("Fire", new SoundImpl("/sound/fire.wav"));
		sounds.put("Die", new SoundImpl("/sound/fire hit.wav"));
		this.loopSound("Fire");
		
		this.getHandler().addEntity(new Light(this, this.getHandler()), this.layer().getIndex());
	}
	
	/**
	 * Instantiates a new Fireball.
	 *
	 * @param x the X coordinate
	 * @param y the Y coordinate
	 * @param facing the direction
	 * @param damage the damage this object will do to Actors that collide with it
	 * @param solid whether this object is solid
	 * @param enabled whether this object is enabled
	 * @param width the width the new object will have. The X scale will be automatically calculated to fit the desired width.
	 * @param height the height the new object will have. The Y scale will be automatically calculated to fit the desired height.
	 * @param velX the velocity of this object along the X axis
	 * @param alpha the alpha value of this object
	 * @param handler the Handler
	 * @param layer the layer this object resides in
	 */
	public Fireball (Integer x, Integer y, Integer facing, Integer damage, boolean solid, Boolean enabled, Integer width, Integer height, Integer velX, float alpha, Handler handler, LevelLayer layer) {
		this (x, y, facing, damage, solid, enabled, (double) (width/WIDTH), (double) (height/HEIGHT), velX, alpha, handler, layer);
	}
	
	/**
	 * Instantiates a new Fireball.
	 *
	 * @param x the X coordinate
	 * @param y the Y coordinate
	 * @param facing the direction
	 * @param damage the damage this object will do to Actors that collide with it
	 * @param velX the velocity of this object along the X axis
	 * @param handler the Handler
	 * @param layer the layer this object resides in
	 */
	public Fireball (Integer x, Integer y, Integer facing, Integer damage, Integer velX, Handler handler, LevelLayer layer) {
		this(x, y, facing, damage, true, true, 1.0, 1.0, velX, 1f, handler, layer);
	}

	/* (non-Javadoc)
	 * @see com.tq2.implementations.entity.EntityImpl#render(java.awt.Graphics2D)
	 */
	@Override
	public void render(Graphics2D g) {
		
        g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, this.alpha));
        this.playAnimation(g, "Idle");
        g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
        
	}

	/* (non-Javadoc)
	 * @see com.tq2.implementations.entity.active.ActiveImpl#tick()
	 */
	@Override
	public void tick() {
		super.tick();
		if (!(this.getBounds().intersects(this.getHandler().getGame().getWindowBounds(this.layer())))) {
			this.die();
		}
	}

	/* (non-Javadoc)
	 * @see com.tq2.implementations.entity.active.ActiveImpl#collide(com.tq2.interfaces.Entity)
	 */
	@Override
	public void collide(Entity en) {
		
		if (en instanceof Tile) {
			this.die();
		}
	}
		
	/**
	 * Returns the damage this object will do to Actors that collide with it.
	 *
	 * @return the damage this object will do to Actors that collide with it
	 */
	public Integer getDamage() {
		return this.damage;
	}
	
	/**
	 * Sets the damage this object will do to Actors that collide with it.
	 *
	 * @param damage the new damage this object will do to Actors that collide with it
	 */
	public void setDamage(Integer damage) {
		this.damage = damage;
	}
}
