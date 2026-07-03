package tq2.implementations.entity.tile;

import tq2.interfaces.Handler;
import tq2.interfaces.LevelLayer;

/**
 * The Class QuickTileResponsive.
 */
public class QuickTileResponsive extends QuickTile {

	/** The relative position of the tile in the screen. */
	Double xPercent;
	
	/** The relative position of the tile in the screen. */
	Double yPercent;
	
	/** The relative scale of the tile. */
	Double relativeScale;
	
	/** The starting width of the tile (in relation to the screen). */
	Double startingWidth;
	
	/** The starting height of the tile (in relation to the screen). */
	Double startingHeight;
	
	/**
	 * Instantiates a new QuickTileResponsive.
	 *
	 * @param xPercent the relative position of the tile in the screen
	 * @param yPercent the relative position of the tile in the screen
	 * @param relativeScale the relative scale of the tile
	 * @param pathToImage the path to image
	 * @param solid whether the object is solid
	 * @param enabled whether the object is enabled
	 * @param alpha the alpha value of the object
	 * @param handler the Handler
	 * @param layer the layer the object resides in
	 */
	public QuickTileResponsive(
			Double xPercent, Double yPercent,
			Double relativeScale,
			String pathToImage,
			Boolean solid, Boolean enabled,
			Float alpha,
			Handler handler, LevelLayer layer) {
		super(0, 0, pathToImage, solid, enabled, 1.0, 1.0, alpha, handler, layer);
		this.xPercent = xPercent;
		this.yPercent = yPercent;
		this.relativeScale = relativeScale;
	}
	
	/**
	 * Instantiates a new QuickTileResponsive.
	 *
	 * @param xPercent the relative position of the tile in the screen
	 * @param yPercent the relative position of the tile in the screen
	 * @param relativeScale the relative scale of the tile
	 * @param pathToImage the path to image
	 * @param handler the Handler
	 * @param layer the layer the object resides in
	 */
	public QuickTileResponsive(
			Double xPercent, Double yPercent,
			Double relativeScale,
			String pathToImage,
			Handler handler, LevelLayer layer) {
		this(xPercent, yPercent, relativeScale, pathToImage, false, true, 1.0f, handler, layer);
	}

	/* (non-Javadoc)
	 * @see com.tq2.implementations.entity.tile.Tile#tick()
	 */
	@Override
	public void tick() {
		this.setX(-this.getWidth()/2 + this.getHandler().getGame().getGameWidth()*xPercent);
		this.setY(this.getHandler().getGame().getGameHeight()*yPercent);

		Double scale = Math.min(((this.getHandler().getGame().getGameWidth())*relativeScale/this.width),((this.getHandler().getGame().getGameHeight())*relativeScale/this.height));

		this.setScaleX(scale);
		this.setScaleY(scale);
	}
}
