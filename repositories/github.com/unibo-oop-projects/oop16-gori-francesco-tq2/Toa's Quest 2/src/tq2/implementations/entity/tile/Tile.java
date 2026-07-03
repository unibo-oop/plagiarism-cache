package tq2.implementations.entity.tile;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;

import tq2.implementations.entity.EntityImpl;
import tq2.interfaces.Handler;
import tq2.interfaces.LevelLayer;

/**
 * The Class Tile is an abstract class to be used for the creation of new tiles.
 * By default, the animation under the name "Idle" is played.
 * 
 * @author Francesco Gori
 */
public abstract class Tile extends EntityImpl {

	/**
	 * Instantiates a new Tile.
	 *
	 * @param x the X coordinate of the object
	 * @param y the Y coordinate of the object
	 * @param width the width of the bounding box of the object
	 * @param height the height of the bounding box of the object
	 * @param facing the direction of the object
	 * @param solid whether the object is solid
	 * @param enabled whether the object is enabled
	 * @param scaleX the scale of the object along the X axis
	 * @param scaleY the scale of the object along the Y axis
	 * @param velX the velocity of the object along the X axis
	 * @param velY the velocity of the object along the Y axis
	 * @param alpha the alpha value of the object
	 * @param handler the Handler
	 * @param layer the layer the object resides in
	 */
	public Tile (
			Integer x, Integer y,
			Integer width, Integer height,
			Integer facing,
			Boolean solid,
			Boolean enabled,
			Double scaleX, Double scaleY,
			Double velX, Double velY,
			Float alpha,
			Handler handler,
			LevelLayer layer) {
		
		super(x, y, width, height, facing, solid, enabled, scaleX, scaleY, velX, velY, alpha, handler, layer);
	}
	
	/* (non-Javadoc)
	 * @see com.tq2.interfaces.Entity#render(java.awt.Graphics2D)
	 */
	@Override
	public void render(Graphics2D g) {
        g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
		
		this.playAnimation(g, "Idle");
        
        g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
	}
	
	/* (non-Javadoc)
	 * @see com.tq2.interfaces.Entity#tick()
	 */
	@Override
	public void tick() {
		//default behavior: empty
	}
}
