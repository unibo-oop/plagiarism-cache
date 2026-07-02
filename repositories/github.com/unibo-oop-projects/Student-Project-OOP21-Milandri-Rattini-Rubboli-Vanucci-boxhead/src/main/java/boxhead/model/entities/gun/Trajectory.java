package boxhead.model.entities.gun;

import javafx.geometry.Point2D;

/**
 * Represents the trajectory of the shot
 */
public interface Trajectory {
	
	/**
	 * 
	 * @return The position at the current moment
	 */
	Point2D getCurrentPosition();
	
	/**
	 * 
	 * @return The angle at the current moment
	 */
	double getAngle();
	
	/**
	 * 
	 * @return The speed of the movement
	 */
	double getSpeed();
	
	/**
	 * 
	 * @return The variation of the position to move the trajectory
	 */
	Point2D getPositionVariation();
	
	/**
	 * 
	 * @return True if the movement has ended
	 */
	boolean hasEnded();
	
	/**
	 * Used to update the movement
	 */
	void update();
}
