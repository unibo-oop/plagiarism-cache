package tq2.implementations.entity.tile;

import tq2.interfaces.Handler;
import tq2.interfaces.LevelLayer;

/**
 * Descendants of this object will dynamically resize to fit the window of the game. The use can choose to keep the ratio of
 * the image or have it adjust its width and height to match those of the window.
 * 
 * @author Francesco Gori
 */
public abstract class Background extends Tile {

	/** The Constant KEEP_RATIO. */
	public static final Integer KEEP_RATIO = 0;
	
	/** The Constant FILL. */
	public static final Integer FILL = 1;
	
	/** The mode of the object (either "FILL" or "KEEP_RATIO". */
	protected Integer mode;
	
	/**
	 * Instantiates a new background.
	 *
	 * @param width the width of the object when unscaled
	 * @param height the height of the object when unscaled
	 * @param facing the direction of the object
	 * @param enabled whether the object is enabled
	 * @param alpha the alpha value of the object
	 * @param mode the mode of the background. Either "KEEP_RATIO" or "FILL".
	 * @param handler the Handler
	 * @param layer the layer the object resides in
	 */
	public Background(Integer width, Integer height, Integer facing, Boolean enabled, Float alpha,
			Integer mode, Handler handler, LevelLayer layer) {
		super(0, 0, width, height, facing, false, enabled, 1.0, 1.0, 0.0, 0.0,
				alpha, handler, layer);
		
		this.mode = mode;
	}
	
	
	/* (non-Javadoc)
	 * @see com.tq2.implementations.entity.tile.Tile#tick()
	 */
	@Override
	public void tick() {
		Double scaleX = new Double(this.getHandler().getGame().getGameWidth())/this.width;
		Double scaleY = new Double(this.getHandler().getGame().getGameHeight())/this.height;
		
		if (mode == KEEP_RATIO) {
			scaleX = Math.max (scaleY, scaleX);
			scaleY = scaleX;
		}
		
		if (scaleX != this.getScaleX()) {
			this.setScaleX(scaleX);

		}
		
		if (scaleY != this.getScaleY()) {
			this.setScaleY(scaleY);
			this.setY(this.getHeight());
		}
	}
}
