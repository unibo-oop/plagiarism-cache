package tq2.implementations.entity.tile;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;

import tq2.implementations.Id;
import tq2.interfaces.Handler;
import tq2.interfaces.LevelLayer;

/**
 * The Class RectangleTile is useful to create quick obstacles for testing. It will create a rectangle of the desired solid color.
 * 
 * @author Francesco Gori
 */
public class RectangleTile extends Tile {

	/** The color of the rectangle. */
	private Color color;
	
	/**
	 * Instantiates a new rectangular tile of the specified dimensions at the specified position.
	 *
	 * @param x the X coordinate of the object
	 * @param y the y coordinate of the object
	 * @param width the width of the rectangle
	 * @param height the height of the rectangle
	 * @param color the color of the rectangle
	 * @param solid whether the rectangle is solid
	 * @param alpha the alpha value of the rectangle
	 * @param handler the Handler
	 * @param layer the layer the object resides in
	 */
	public RectangleTile (Integer x, Integer y, Integer width, Integer height, Color color, Boolean solid, Float alpha, Handler handler, LevelLayer layer) {
		super(x, y, width, height, 1, solid, true, 1.0, 1.0, 0.0, 0.0, alpha, handler, layer);
		
		this.id = Id.rectangleTile;
		this.color = color;
	}
	
	/**
	 * Instantiates a new rectangle tile.
	 *
	 * @param x the x
	 * @param y the y
	 * @param width the width
	 * @param height the height
	 * @param color the color
	 * @param handler the handler
	 * @param layer the layer
	 */
	public RectangleTile (Integer x, Integer y, Integer width, Integer height, Color color, Handler handler, LevelLayer layer) {
		this(x, y, width, height, color, true, 1.0f, handler, layer);
	}

	/* (non-Javadoc)
	 * @see com.tq2.implementations.entity.tile.Tile#render(java.awt.Graphics2D)
	 */
	@Override
	public void render(Graphics2D g) {
		
        g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
		
        g.setColor(this.color);
		g.fillRect(this.getX(), this.getY(), this.getWidth(), this.getHeight());
        
        g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
	}
	
	/**
	 * Changes the color of the rectangle.
	 *
	 * @param color the new color
	 */
	public void setColor(Color color) {
		this.color = color;
	}
	
}
