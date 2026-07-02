package boxhead.model.entities;

import boxhead.model.entities.utils.Direction;
import javafx.geometry.Point2D;

public abstract class AbstractHealthEntity extends AbstractActiveEntity{
	
	int health;
	
	public AbstractHealthEntity(Point2D speed, Direction direction, Point2D position, EntityType entityType, int health) {
		super(speed,direction,position,entityType);
		this.health=health;
	}

	/** 
     * Set the entity's health
     * @param double health
     */
	public void setHealth(int health) {
		this.health=health;
	}
	
	/** 
     * Get the entity's health
     * @return double health
     */
	public int getHealth() {
		return this.health;
	}
	
	/** 
     * Deal damage to the entity
     * @param double damage done do the entity
     */
	public void takeDamage(int damage) {
		this.health-=damage;
	}
	
	/** 
     * Check if the entity is alive
     * @return boolean true if entity is alive
     */
	public boolean isAlive() {
		return this.health>0;
	}
}
