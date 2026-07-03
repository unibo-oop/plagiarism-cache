package tq2.implementations.entity;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.util.HashMap;

import tq2.implementations.HandlerImpl;
import tq2.implementations.Id;
import tq2.implementations.graphics.Spritesheet;
import tq2.interfaces.Entity;
import tq2.interfaces.Handler;

/**
 * The Class Light will create a light that follows the target until either the target is destroyed, disabled, or the light is explicitly destroyed.
 * 
 * @author Francesco Gori
 */
public class Light extends EntityImpl {
		
	/** The width of the bounding box of this object. */
	protected static final Integer WIDTH = 2;
	
	/** The height of the bounding box of this object. */
	protected static final Integer HEIGHT = 2;
	
	/** The path of the spritesheet to use for the animations.*/
	protected static final String SPRITESHEET = "/light.png";
	
	/** The target to follow. */
	protected Entity target;
	
	/** The offset along the X axis of the animation from the bounding box. */
	protected Integer offsetX;
	
	/** The offset along the Y axis of the animation from the bounding box. */
	protected Integer offsetY;
	
	/**
	 * Instantiates a new light that will follow the target.
	 *
	 * @param target the target to follow
	 * @param scaleX the X scale of this object
	 * @param scaleY the Y scale of this object
	 * @param alpha the alpha value of this object
	 * @param handler the Handler
	 */
	public Light (Entity target, Double scaleX, Double scaleY, Float alpha, Handler handler) {
		super(target.getX() + target.getWidth()/2, target.getY() + target.getHeight()/2, WIDTH, HEIGHT, 1, false, true, scaleX, scaleY, target.getVelX(), target.getVelY(), alpha, handler, target.layer());
	
		this.id = Id.light;
		this.target = target;
		
		HashMap <String, Spritesheet> spritesheets = this.getHandler().getGame().getCurrentLevel().getSpritesheets();
		spritesheets.put(SPRITESHEET, new Spritesheet(SPRITESHEET));
		animations.put("Idle", spritesheets.get(SPRITESHEET).getAnim(0, 0, 50, 50, -24, -24, 4, 4, 0));
	}
	
	/**
	 * Instantiates a new light that will follow the target. The scale will be set automatically so that the object fits the desired width and height.
	 *
	 * @param target the target to follow
	 * @param width the width this object will assume
	 * @param height the height this object will assume
	 * @param alpha the alpha value of this object
	 * @param handler the Handler
	 */
	public Light (Entity target, Integer width, Integer height, Float alpha, HandlerImpl handler) {
		this(target, (double) (width/WIDTH), (double) (height/HEIGHT), alpha, handler);
	}
	
	/**
	 * Instantiates a new light that will follow the target. The alpha value will be 0.5.
	 *
	 * @param target the target to follow
	 * @param handler the Handler
	 */
	public Light (Entity target, Handler handler) {
		this(target, 1.0, 1.0, 0.5f, handler);
	}

	/* (non-Javadoc)
	 * @see com.tq2.implementations.entity.EntityImpl#render(java.awt.Graphics2D)
	 */
	public void render(Graphics2D g) {
        g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
		
		this.playAnimation(g, "Idle");
        
        g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
	}

	/* (non-Javadoc)
	 * @see com.tq2.implementations.entity.EntityImpl#tick()
	 */
	public void tick() {
		//follow the target
		this.setX(this.target.getX() + (this.target.getWidth() - this.getWidth())/2 + this.target.getVelX());
		this.setY(this.target.getY() - (this.target.getHeight() - this.getHeight())/2 + this.target.getVelY());
		
		if (!this.target.isEnabled()) {
			this.destroy();
		}
	}
	
}
