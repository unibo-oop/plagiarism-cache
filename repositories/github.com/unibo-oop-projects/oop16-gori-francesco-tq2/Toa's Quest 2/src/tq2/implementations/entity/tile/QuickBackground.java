package tq2.implementations.entity.tile;

import tq2.interfaces.Handler;
import tq2.interfaces.LevelLayer;

/**
 * The Class QuickBackground extends QuickTile and will create a tile that displays the image at the given path (if present).
 * The tile will automatically fit the size of the window. If the "KEEP_RATIO" mode is selected, the image will keep its original ratio
 * and expand to cover the whole window. Otherwise, if the "FILL" mode is selected width and height will automatically fit the width
 * and height of the window, stretching the image.
 * 
 * @author Francesco Gori
 */
public class QuickBackground extends QuickTile {

	/** The Constant KEEP_RATIO. Use this value in the constructor to make the object resize keeping its original ratio*/ 
	public static final Integer KEEP_RATIO = 0;
	
	/** The Constant FILL. Use this value in the constructor to make the object stretch to cover the window. */
	public static final Integer FILL = 1;
	
	/** The mode, either "FILL" or "KEEP_RATIO" */
	protected Integer mode;
	
	/**
	 * Instantiates a new QuickBackground.
	 *
	 * @param pathToImage the path to image
	 * @param enabled whether the object is enabled
	 * @param alpha the alpha value of the object
	 * @param mode the mode, either "FILL" or "KEEP_RATIO"
	 * @param handler the Handler
	 * @param layer the layer this object resides in
	 */
	public QuickBackground(String pathToImage,
			Boolean enabled, Float alpha, Integer mode, Handler handler, LevelLayer layer) {
		super(0, 0, pathToImage, false, enabled, 1.0, 1.0, alpha, handler, layer);

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
			this.setX((-this.getWidth()+this.getHandler().getGame().getGameWidth())/2);
		}
		
		if (scaleY != this.getScaleY()) {
			this.setScaleY(scaleY);
			this.setY((this.getHeight()+this.getHandler().getGame().getGameHeight())/2);
		}
	}
}
