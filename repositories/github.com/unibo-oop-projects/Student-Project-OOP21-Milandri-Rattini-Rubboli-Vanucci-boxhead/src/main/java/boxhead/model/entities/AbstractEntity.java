package boxhead.model.entities;

import javafx.geometry.BoundingBox;
import javafx.geometry.Point2D;

public abstract class AbstractEntity implements Entity {
	
	private Point2D position;
	private EntityType entityType;
	private double height;
	private double width;
	
	public AbstractEntity(Point2D position,EntityType entityType) {
		this.position=position;
		this.entityType=entityType;
	}
	
	/** 
     * Set entity position
     * @param position entity's position
     */
	public void setPosition(Point2D position) {
		this.position=position;
	}
	
	/** 
     * Get entity position
     * @return position entity's position
     */
	public Point2D getPosition() {
		return this.position;
	}
	
	/** 
     * Set the height and the width 
     * @param height height of the box
     * @param width width of the box
     */
	public void setBoundingBox(double width, double height) {
		this.height=height;
		this.width=width;
	}
	
	/** 
     * Get the bounding box by creating a new BoundingBox object
     * @return BoundingBox 
     */
	public BoundingBox getBoundingBox() {
		return new BoundingBox(this.position.getX()-20,this.position.getY()-34,this.width,this.height);
	}
	
	/** 
     * Get the height
     * @return height
     */
	public double getHeight() {
		return this.height;
	}
	
	/** 
     * Get the width
     * @return width
     */
	public double getWidth() {
		return this.width;
	}
	
	/** 
     * Get the entity type
     * @return EntityType     
     */
	public EntityType getEntityType() {
		return this.entityType;
	}
}
