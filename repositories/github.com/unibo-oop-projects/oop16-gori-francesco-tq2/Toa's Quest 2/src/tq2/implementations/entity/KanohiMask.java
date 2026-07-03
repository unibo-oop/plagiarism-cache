package tq2.implementations.entity;

import java.awt.Graphics2D;
import java.util.HashMap;

import tq2.implementations.Id;
import tq2.implementations.SoundImpl;
import tq2.implementations.entity.Light;
import tq2.implementations.graphics.Spritesheet;
import tq2.interfaces.Handler;
import tq2.interfaces.LevelLayer;
/**
 * The Class KanohiMask.
 * 
 * @author Francesco Gori
 */
public class KanohiMask extends EntityImpl{

	/** The width of the bounding box of the object. */
	protected static final Integer WIDTH = 12;
	
	/** The height of the bounding box of the object. */
	protected static final Integer HEIGHT = 12;
	
	/** The length of the path the object will follow up and down. */
	protected static final Integer RANGE = 4;
	
	/** The speed at which the mask will float up and down. */
	protected static final Double SPEED = 0.3;
	
	/** The first spritesheet, used for mask "Kakama". */
	protected static final String SPRITESHEET2 = "/mask_kakama.png";
	
	/** The second spritesheet, used for mask "Miru". */
	protected static final String SPRITESHEET3 = "/mask_miru.png";
	
	/** This value will identify which mask this object represents. 1 is for Kakama, 2 is for Miru. */
	protected Integer mask;
	
	/** The upper limit of the path of the object. */
	protected Integer upperLimit;
	
	/** The lower limit of the path of the object. */
	protected Integer lowerLimit;
	
	/**
	 * Instantiates a new Kanohi mask.
	 *
	 * @param x the X coordinate
	 * @param y the Y coordinate
	 * @param mask which mask this is
	 * @param enabled if the objects is enabled
	 * @param alpha the alpha value of the object
	 * @param handler the Handler
	 * @param layer the layer this object resides in
	 */
	public KanohiMask(
			Integer x, Integer y,
			Integer mask,
			Boolean enabled,
			Float alpha,
			Handler handler,
			LevelLayer layer) {
		
		super(x, y, WIDTH, HEIGHT, 1, true, enabled, 1.0, 1.0, 0.0, 0.0, alpha, handler, layer);
		this.mask = mask;
		this.upperLimit = this.getY() + RANGE;
		this.lowerLimit = this.getY() - RANGE;
		this.setVelY(SPEED);
		
		this.id = Id.kanohiMask;
		
		HashMap <String, Spritesheet> spritesheets = this.getHandler().getGame().getCurrentLevel().getSpritesheets();
		
		switch(mask) {
		case 2:
			spritesheets.put(SPRITESHEET2, new Spritesheet(SPRITESHEET2));
			animations.put("Idle", spritesheets.get(SPRITESHEET2).getAnim(0, 0, 14, 16, -1, -2, 1, 180, 0));
			break;
		case 3:
			spritesheets.put(SPRITESHEET3, new Spritesheet(SPRITESHEET3));
			animations.put("Idle", spritesheets.get(SPRITESHEET3).getAnim(0, 0, 14, 16, -1, -2, 1, 180, 0));
			break;
		}
		
		this.sounds.put("Die", new SoundImpl("/sound/get mask.wav"));
		this.getHandler().addEntity(new Light(this, handler), this.layer().getIndex());
	}
	
	/**
	 * Instantiates a new Kanohi mask.
	 *
	 * @param x the X coordinate
	 * @param y the Y coordinate
	 * @param mask which mask this is
	 * @param handler the Handler
	 * @param layer the layer this object resides in
	 */
	public KanohiMask(
			Integer x, Integer y,
			Integer mask,
			Handler handler,
			LevelLayer layer) {
		this(x, y, mask, true, 1.0f, handler, layer);
	}

	/* (non-Javadoc)
	 * @see com.tq2.implementations.entity.EntityImpl#render(java.awt.Graphics2D)
	 */
	@Override
	public void render(Graphics2D g) {
		this.playAnimation(g, "Idle");
		
	}

	/* (non-Javadoc)
	 * @see com.tq2.implementations.entity.EntityImpl#tick()
	 */
	@Override
	public void tick() {
		this.setY(this.getYd() + this.getVelY());

		//when the lower limit is reached, make the object go up and vice versa
		if (this.getY() >= this.upperLimit || this.getY() <= this.lowerLimit) {
			this.setVelY(-this.getVelY());
		}
	}
	
	/**
	 * Returns a number that identifies which mask is this (1 is for Kakama, 2 is for Miru).
	 *
	 * @return the mask
	 */
	public Integer getMask() {
		return this.mask;
	}
	
	/* (non-Javadoc)
	 * @see com.tq2.implementations.entity.EntityImpl#die()
	 */
	public void die() {
		this.playSoundInstance("Die");
		this.destroy();
	}
}
