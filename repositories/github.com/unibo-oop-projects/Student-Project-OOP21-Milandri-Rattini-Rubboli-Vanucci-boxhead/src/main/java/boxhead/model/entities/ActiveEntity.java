package boxhead.model.entities;
import boxhead.model.entities.utils.*;
import javafx.geometry.Point2D;


public interface ActiveEntity extends Entity{
	
	/** 
     * Set the speed
     * @param Point2D speed 
     */ 
	void setSpeed(Point2D speed);
	
	/** 
     * Get the speed
     * @return Point2D speed
     */
	Point2D getSpeed();
	
	/** 
     * Set the facing direction 
     * @param Direction direction enum
     */
	void setDirection(Direction direction);
	
	/** 
     * Get the facing direction 
     * @return Direction direction enum
     */
	Direction getDirection();
	
	/** 
     * Update the entity's position 
     */
	void update();
	
}
