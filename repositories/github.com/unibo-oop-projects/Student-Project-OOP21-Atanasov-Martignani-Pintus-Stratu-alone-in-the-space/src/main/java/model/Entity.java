
package model;

import com.almasb.fxgl.core.math.Vec2;
import javafx.scene.Node;
import javafx.scene.image.Image;

public interface Entity {
	
    /**
	 * get the current angle of this ship.
	 * @return angle in degrees
	 */
	double getAngle();
	
	 /**
	  * Get the position of the entity
	  * @return vec2 position of the entity
	  */
	Vec2 getPosition();
	
	 /**
	  * Get the direction of the entity
	  * @return vec2 direction of the entity
	  */
	Vec2 getDirection();
	
	 /**
	  * boolean statement if the entity is still alive
	  * @return true or false
	  */
	Boolean isAlive();
	
	 /**
	  * destroy all item attached to the object
	  */
	void destroy();

	 /**
	  * move the entity for the interval specified
	  * @param deltaTime tic update
	  */
	void move(long deltaTime);

	 /**
	  * return the node (of javaFX) associate at the object
	  * @return javafx node
	  */
	Node getNode();

	 /**
	  * Set the sprite of the entity
	  * @param img sprite of the entity
	  */
	void setSprite(Image img);
	
	 /**
	  * Set the position.
	  * @param newPos new vec2 pos of the entity
	  */
	void setPosition(Vec2 newPos);
}
