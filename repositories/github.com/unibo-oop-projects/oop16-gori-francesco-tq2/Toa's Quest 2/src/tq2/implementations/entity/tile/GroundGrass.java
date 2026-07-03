package tq2.implementations.entity.tile;

import java.util.HashMap;

import tq2.implementations.Id;
import tq2.implementations.graphics.Spritesheet;
import tq2.interfaces.Handler;
import tq2.interfaces.LevelLayer;

/**
 * A tile with grass
 * 
 * @author Francesco Gori
 */
public class GroundGrass extends Tile {
		
	/** The width of this object. */
	protected static final Integer WIDTH = 128;
	
	/** The height of this object. */
	protected static final Integer HEIGHT = 32;
	
	/** The spritesheet containing the image for this object. */
	protected static final String SPRITESHEET = "/tiles.png";

	/**
	 * Instantiates a new GroundGrass object.
	 *
	 * @param x the X coordinate
	 * @param y the Y coordinate
	 * @param facing the direction of the object
	 * @param solid whether the object is solid
	 * @param enabled whether the object is enabled
	 * @param scaleX the scale of the object along the X axis
	 * @param scaleY the scale of the object along the Y axis
	 * @param alpha the alpha value of this object
	 * @param handler the Handler
	 * @param layer the layer this object resides in
	 */
	public GroundGrass (Integer x, Integer y, Integer facing, Boolean solid, Boolean enabled, Double scaleX, Double scaleY, Float alpha, Handler handler, LevelLayer layer) {
		super(x, y, WIDTH, HEIGHT, facing, solid, enabled, scaleX, scaleY, 0.0, 0.0, alpha, handler, layer);
		
		this.id = Id.groundGrass;
		
		HashMap <String, Spritesheet> spritesheets = this.getHandler().getGame().getCurrentLevel().getSpritesheets();
		spritesheets.put(SPRITESHEET, new Spritesheet(SPRITESHEET));
		animations.put("Idle", spritesheets.get(SPRITESHEET).getAnim(0, 0, 128, 32, 0, 0, 1, 60, 0));
	}
	
	/**
	 * Instantiates a new GroundGrass object.
	 *
	 * @param x the X coordinate
	 * @param y the Y coordinate
	 * @param facing the direction of the object
	 * @param solid whether the object is solid
	 * @param enabled whether the object is enabled
	 * @param width the width desired for the object. The X scale will be automatically calculated to fit this value.
	 * @param height the height desired for the object. The Y scale will be automatically calculated to fit this value.
	 * @param alpha the alpha value of this object
	 * @param handler the Handler
	 * @param layer the layer this object resides in
	 */
	public GroundGrass (Integer x, Integer y, Integer facing, Boolean solid, Boolean enabled, Integer width, Integer height, Float alpha, Handler handler, LevelLayer layer) {
		this(x, y, facing, solid, enabled, (double) (width/WIDTH), (double) (height/HEIGHT), alpha, handler, layer);
	}
	
	/**
	 * Instantiates a new GroundGrass object.
	 *
	 * @param x the X coordinate
	 * @param y the Y coordinate
	 * @param solid whether the object is solid
	 * @param handler the Handler
	 * @param layer the layer this object resides in
	 */
	public GroundGrass (Integer x, Integer y, Boolean solid, Handler handler, LevelLayer layer) {
		this(x, y, 1, solid, true, 1.0, 1.0, 1f, handler, layer);
	}
}
