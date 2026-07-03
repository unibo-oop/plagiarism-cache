package collision;

import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.components.CollidableComponent;
import com.almasb.fxgl.physics.CollisionHandler;
import factory.MowerType;

/**
*
* @author Nicola
*
* This class generates a collision handler between player and blocks
*
*/
public class ColPlayerBlock extends CollisionHandler {


	/**
	 * empty constructor
	 */
	public ColPlayerBlock() {
		super(MowerType.PLAYER, MowerType.BLOCK);


	}

	/**
	 * @param player **Entity player**
	 * @param block  **Entity block**
	 */
	@Override
	protected void onCollisionBegin(Entity player, Entity block) {
		block.addComponent(new CollidableComponent(false));
	}



}

