package boxhead.view.world.tile;

import javafx.geometry.Point2D;
import javafx.scene.image.Image;

/**
 * Interface to model a tile.
 */
public interface Tile {

	/**
	 * Method to get the image of the tile.
	 *
	 * @return
	 */
	Image getTile();

	/**
	 * Method to get the size of the tile.
	 *
	 * @return
	 */
	double getSize();

	/**
	 * Method to set the render scale of the tile.
	 *
	 * @param scale
	 */
	void setRenderScale(double scale);

	/**
	 * Method to get the render scale of the tile.
	 *
	 * @return
	 */
	double getRenderScale();

	/**
	 * Method to get the relative position.
	 *
	 * @return
	 */
	Point2D getRelativePos();

	/**
	 * Method to get the absolute position.
	 *
	 * @return
	 */
	Point2D getPos();

	/**
	 * Method to get the x.
	 *
	 * @return
	 */
	double getX();

	/**
	 * Method to get the y.
	 *
	 * @return
	 */
	double getY();
}
