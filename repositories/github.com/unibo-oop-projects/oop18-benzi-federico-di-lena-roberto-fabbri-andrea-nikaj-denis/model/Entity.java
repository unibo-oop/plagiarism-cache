package model;

import javafx.scene.shape.Rectangle;
import utility.Pair;


/**
 * The interface of the Template Method pattern.
 *This is the main interface of the model of entities. 
 */
public interface Entity {
	
	/**
	 * Update the fields of the entities during the game
	 */
	public void update();
	
	/**
	 * @return the position of the entity
	 */
	public Pair<Double,Double> getPosition();
	
	/**
	 * @return the hitbox of the entity
	 */
	public Rectangle getHitbox();
	
	
	/**
	 * 
	 * @return the type of the entity
	 */
	public EntityType getType();
	
	/**
	 * 
	 * @return the id util for the image loader
	 */
	public int getId();

	
	
}
