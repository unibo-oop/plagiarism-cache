package tq2.implementations.entity.tile;

import java.util.HashMap;

import tq2.implementations.Id;
import tq2.implementations.graphics.Spritesheet;
import tq2.interfaces.Handler;
import tq2.interfaces.LevelLayer;

/**
 * A tile of a tree.
 * 
 * @author Francesco Gori
 */
public class TreeSmall extends Tile {
		
	/** The width of the object. */
	protected static final Integer WIDTH = 149;
	
	/** The height of the object. */
	protected static final Integer HEIGHT = 145;
	
	/** The path to the spritesheet containing the sprite. */
	protected static final String SPRITESHEET = "/trees.png";

	/**
	 * Instantiates a new TreeSmall.
	 *
	 * @param x the X coordinate of the object
	 * @param y the Y coordinate of the object
	 * @param facing the direction of the object
	 * @param solid whether the object is solid
	 * @param enabled whether the object is enabled
	 * @param scaleX the X scale of the object
	 * @param scaleY the Y scale of the object
	 * @param alpha the alpha value of the object
	 * @param handler the Handler
	 * @param layer the layer the object resides in
	 */
	public TreeSmall (Integer x, Integer y, Integer facing, Boolean solid, Boolean enabled, Double scaleX, Double scaleY, Float alpha, Handler handler, LevelLayer layer) {
		super(x, y, WIDTH, HEIGHT, facing, solid, enabled, scaleX, scaleY, 0.0, 0.0, alpha, handler, layer);
		
		this.id = Id.treeSmall;
		
		HashMap <String, Spritesheet> spritesheets = this.getHandler().getGame().getCurrentLevel().getSpritesheets();
		spritesheets.put(SPRITESHEET, new Spritesheet(SPRITESHEET));
		animations.put("Idle", spritesheets.get(SPRITESHEET).getAnim(0, 0, 149, 145, 0, 0, 1, 60, 0));
	}
	
	/**
	 * Instantiates a new TreeSmall.
	 *
	 * @param x the X coordinate of the object
	 * @param y the Y coordinate of the object
	 * @param facing the direction of the object
	 * @param solid whether the object is solid
	 * @param enabled whether the object is enabled
	 * @param width the width of the object the X scale will be automatically calculated to fit this size.
	 * @param height the height of the object. The Y scale will be automatically calculated to fit this size.
	 * @param alpha the alpha value of the object
	 * @param handler the Handler
	 * @param layer the layer the object resides in
	 */
	public TreeSmall (Integer x, Integer y, Integer facing, Boolean solid, Boolean enabled, Integer width, Integer height, Float alpha, Handler handler, LevelLayer layer) {
		this(x, y, facing, solid, enabled, (double) (width/WIDTH), (double) (height/HEIGHT), alpha, handler, layer);
	}
	
	/**
	 * Instantiates a new TreeSmall.
	 *
	 * @param x the X coordinate of the object
	 * @param y the Y coordinate of the object
	 * @param solid whether the object is solid
	 * @param handler the Handler
	 * @param layer the layer the object resides in
	 */
	public TreeSmall (Integer x, Integer y, Boolean solid, Handler handler, LevelLayer layer) {
		this(x, y, 1, solid, true, 1.0, 1.0, 1f, handler, layer);
	}
}
