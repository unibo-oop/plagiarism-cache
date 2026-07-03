package collision;


import static com.almasb.fxgl.app.DSLKt.inc;
import com.almasb.fxgl.app.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.physics.CollisionHandler;
import factory.MowerType;
import launcher.MowerApp;


/**
*
* @author Nicola
* @author Andrea
*
* This class generates a collision handler between player and flower
*
*/
public class ColPlayerFlower extends CollisionHandler {

	private MowerApp app;


	/**
	 * empty constructor
	 */
	public ColPlayerFlower() {
		super(MowerType.PLAYER, MowerType.FLOWER);

		 app = (MowerApp) FXGL.getApp();

	}


	/**
	 * @param player **Entity player**
	 * @param gravel  **Entity flower**
	 */
	@Override
	protected void onCollisionBegin(Entity player, Entity flower) {
		inc("gas", -3);
		inc("score", -50);
		app.getGameWorld().spawn("grass", flower.getX(), flower.getY());
		flower.removeFromWorld();
		
		FXGL.getAudioPlayer().playSound("run.wav");
	}

}

