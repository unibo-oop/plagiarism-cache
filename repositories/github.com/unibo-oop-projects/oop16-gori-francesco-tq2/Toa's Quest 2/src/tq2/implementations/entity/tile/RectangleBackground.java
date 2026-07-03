package tq2.implementations.entity.tile;

import java.awt.Color;

import tq2.interfaces.Handler;
import tq2.interfaces.LevelLayer;

/**
 * The Class RectangleBackground creates a rectangle of a solid color that will automatically fit the size of the window.
 * 
 * @author Francesco Gori
 */
public class RectangleBackground extends RectangleTile {

	/**
	 * Instantiates a new RectangleBackground.
	 *
	 * @param color the color of the rectangle
	 * @param alpha the alpha value of the object
	 * @param handler the Handler
	 * @param layer the layer this object resides in
	 */
	
	public RectangleBackground(Color color, Float alpha,
			Handler handler, LevelLayer layer) {
		super(0, 0, 10, 10, color, false, alpha, handler, layer);
	}
	
	/* (non-Javadoc)
	 * @see com.tq2.implementations.entity.tile.Tile#tick()
	 */
	@Override
	public void tick() {
		super.tick();
		Integer width = this.getHandler().getGame().getGameWidth();
		Integer height = this.getHandler().getGame().getGameHeight();
		
		if (this.width != width) {
			this.width = width;
		}
		
		if (this.height != height) {
			this.height = height;
			this.setY(0);
		}
	}
}

