package boxhead.model.level;

import boxhead.model.entities.Player;
import javafx.geometry.Point2D;

public class Camera {
	
	private double mapHeight;
	private double mapWidth;
	private double camHeight;
	private double camWidth;
	private double offsetX;
	private double offsetY;
	
	public Camera(final double mapHeight, final double mapWidth, final double camHeight, final double camWidth,
			final double offX, final double offY) {
		this.mapHeight=mapHeight;
		this.mapWidth=mapWidth;
		this.camHeight=camHeight;
		this.camWidth=camWidth;
		offsetX=offX;
		offsetY=offY;
	}
	
	/**
	 * Return the center of the camera
	 * @return Point2D
	 */
	public final Point2D getCenter() {
		return new Point2D(mapWidth / 2,mapHeight / 2);
	}
	
	/**
	 * Private method used internally to adjust the camera using the offset
	 */
	private void adjust() {
		offsetX = offsetX + camWidth <= mapWidth ? offsetX : mapWidth - camWidth;
		offsetX = offsetX >= 0 ? offsetX : 0;
		offsetY = offsetY + camHeight <= mapHeight ? offsetY: mapHeight - camHeight;
		offsetY = offsetY >= 0 ? offsetY : 0;
 	}	
	
	/**
	 * Center the camera on the given coordinates 
	 * @param x
	 * @param y
	 */
	public final void centerOn(final double x, final double y) {
        offsetX = x - camWidth / 2;
        offsetY = y - camHeight / 2;
        this.adjust();
    }
	
	/**
	 * Center the camera specifically on the Player's position
	 * @param Player player
	 */
	public final void centerOnPlayer(final Player player) {
		final Point2D playerPos=player.getPosition();
		this.centerOn(playerPos.getX()-player.getWidth() / 2, playerPos.getY() - player.getHeight()/2 );
	}
	
	/**
	 * Move the camera
	 * @param Point2D move vector
	 */
	public final void move(final Point2D move) {
		this.offsetX += move.getX();
		this.offsetY += move.getY();
		this.adjust();
	}
	
	/**
	 * Return the offset as a Point2D
	 * @return Point2D offset
	 */
	public final Point2D getOffset() {
        return new Point2D(this.offsetX, this.offsetY);
    }	
	
	/**
	 * Set scale of the camera 
	 * @param double scale
	 */
	public final void setScale(final double scale) {
        this.mapWidth *= scale;
        this.mapHeight *= scale;
    }
	
	/**
	 * Return the start of the camera
	 * @return
	 */
	public final Point2D start() {
        return this.getOffset();
    }
	
	/**
	 * Return the end of camera
	 * @return
	 */
	public final Point2D end() {
	        return this.getOffset().add(new Point2D(camWidth, camHeight));
	}
	
	/**
	 * Resize the camera by changing the width and height 
	 * @param double width
	 * @param double height
	 */
	public final void resize (final double width, final double height) {
		this.camHeight=height;
		this.camWidth=width;
	}
	
}
