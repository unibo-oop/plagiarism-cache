package tq2.implementations.entity.tile;

import java.util.HashMap;

import tq2.implementations.Id;
import tq2.implementations.graphics.Spritesheet;
import tq2.interfaces.Handler;
import tq2.interfaces.LevelLayer;

/**
 * The tile Sky1 shows a red sky.
 * 
 * @author Francesco Gori
 */
public class Sky1 extends Background {
	
	/** The width of the unscaled object. */
	protected static final Integer WIDTH = 1414;
	
	/** The height of the unscaled object. */
	protected static final Integer HEIGHT = 453;
	
	/** The spritesheet containing the image for this tile. */
	protected static final String SPRITESHEET = "/sky1.png";

	/**
	 * Instantiates a new Sky1 object.
	 *
	 * @param facing the direction
	 * @param enabled whether this object is enabled
	 * @param alpha the alpha value of this object
	 * @param mode how the image will fit to the screen. Either FILL or KEEP_SCALE
	 * @param handler the Handler
	 * @param layer the layer the object resides in
	 */
	public Sky1 (Integer facing, Boolean enabled, Float alpha, Integer mode, Handler handler, LevelLayer layer) {
		super(WIDTH, HEIGHT, facing, enabled, alpha, mode, handler, layer);
		
		this.id = Id.sky1;
		
		HashMap <String, Spritesheet> spritesheets = this.getHandler().getGame().getCurrentLevel().getSpritesheets();
		spritesheets.put(SPRITESHEET, new Spritesheet(SPRITESHEET));
		animations.put("Idle", spritesheets.get(SPRITESHEET).getAnim(WIDTH, HEIGHT, 0, 0, 1, 60, 0));
	}
	
	/**
	 * Instantiates a new Sky1 object.
	 *
	 * @param mode how the image will fit to the screen. Either FILL or KEEP_SCALE
	 * @param handler the Handler
	 * @param layer the layer the object resides in
	 */
	public Sky1 (Integer mode, Handler handler, LevelLayer layer) {
		this(1, true, 1f, mode, handler, layer);
	}

}
