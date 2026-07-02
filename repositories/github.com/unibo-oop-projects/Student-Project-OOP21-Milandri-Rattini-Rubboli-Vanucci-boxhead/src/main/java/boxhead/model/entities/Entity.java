package boxhead.model.entities;

import javafx.geometry.BoundingBox;
import javafx.geometry.Point2D;

public interface Entity {
	
	/** 
     * Set entity position
     * @param position entity's position
     */
	void setPosition(Point2D position);
	
	 /** 
     * @return entity position
     */
	Point2D getPosition();
	
	 /** 
     * Set the size of the bounding box
     * @param height of the bounding box 
     * @param widht of the bounding box 
     */
	void setBoundingBox(double height, double width);
	
	 /** 
     * @return bounding box
     */
    BoundingBox getBoundingBox();
    
    /** 
     * @return eight
     */
    double getHeight();
    
    /** 
     * @return width
     */
    double getWidth();
    
    /** 
     * @return entity type
     */
    EntityType getEntityType();
    
}
