package boxhead.model.entities;

import boxhead.model.entities.utils.Direction;
import javafx.geometry.Point2D;

public abstract class AbstractActiveEntity extends AbstractEntity implements ActiveEntity {
	
	Point2D speed;
	Direction direction;
	
	public AbstractActiveEntity(Point2D speed, Direction direction, Point2D position, EntityType entityType) {
		super(position,entityType);
		this.speed=speed;
		this.direction=direction;
	}
	
/** 
 * Set the speed
 * @param Point2D speed 
 */ 
	public void setSpeed(Point2D speed) {
		this.speed=speed;
	
}

/** 
 * Get the speed
 * @return Point2D speed
 */
	public Point2D getSpeed() {
		return this.speed;
}
/** 
 * Set the facing direction 
 * @param Direction direction enum
 */
	public void setDirection(Direction direction) {
		this.direction=direction;
}

/** 
 * Get the facing direction 
 * @return Direction direction enum
 */
	public Direction getDirection() {
		return this.direction;
}

/** 
 * Update the entity's position 
 */
	public void update() {
		this.setPosition(this.getPosition().add(this.speed));
}
	
}
