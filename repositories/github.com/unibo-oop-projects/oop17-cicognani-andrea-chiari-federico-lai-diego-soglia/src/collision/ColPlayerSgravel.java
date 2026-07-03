package collision;

import static com.almasb.fxgl.app.DSLKt.inc;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.physics.CollisionHandler;
import factory.MowerType;

/**
*
* @author Nicola
*
* This class generates a collision handler between player and sgravel
*
*/
public class ColPlayerSgravel extends CollisionHandler {


	/**
	 * empty constructor
	 */
	public ColPlayerSgravel() {
		super(MowerType.PLAYER, MowerType.SGRAVEL);

	}



	/**
	 * @param player **Entity player**
	 * @param gravel  **Entity sgravel**
	 */
	@Override
	protected void onCollisionBegin(Entity player, Entity sgravel) {

		inc("gas", -3);
		inc("score", -50);
		sgravel.removeFromWorld();

	}


}

