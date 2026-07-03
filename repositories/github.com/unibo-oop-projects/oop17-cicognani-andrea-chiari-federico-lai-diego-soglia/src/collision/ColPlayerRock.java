package collision;

import static com.almasb.fxgl.app.DSLKt.inc;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.physics.CollisionHandler;
import factory.MowerType;

/**
*
* @author Nicola
*
* This class generates a collision handler between player and rocks
*
*/
public class ColPlayerRock extends CollisionHandler {

	/**
	 * empty constructor
	 */
	public ColPlayerRock() {
		super(MowerType.PLAYER, MowerType.ROCK);


	}


	/**
	 * @param player **Entity player**
	 * @param gravel  **Entity rock**
	 */
	@Override
	protected void onCollisionBegin(Entity player, Entity rock) {
		inc("gas", -5);
		inc("score", -150);
	}




}

