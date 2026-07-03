package collision;

import static com.almasb.fxgl.app.DSLKt.inc;
import java.util.concurrent.ThreadLocalRandom;
import com.almasb.fxgl.app.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.physics.CollisionHandler;
import factory.MowerType;
import launcher.MowerApp;

/**
*
* @author Nicola
*
* This class generates a collision handler between player and gravel
*
*/
public class ColPlayerGravel extends CollisionHandler {

	private MowerApp app;

	/**
	 * empty constructor
	 */
	public ColPlayerGravel() {
		super(MowerType.PLAYER, MowerType.GRAVEL);

		 app = (MowerApp) FXGL.getApp();

	}


	/**
	 * @param player **Entity player**
	 * @param gravel  **Entity gravel**
	 */
	@Override
	protected void onCollisionBegin(Entity player, Entity gravel) {

		inc("gas", -5);
		inc("score", -50);
		gravel.removeFromWorld();
		for(int x = 0; x<4; x++){
		int xrandpos = (ThreadLocalRandom.current().nextInt(MowerApp.getMin(),MowerApp.getMax() + 10))*MowerApp.BLOCK_SIZE;
		int yrandpos = (ThreadLocalRandom.current().nextInt(MowerApp.getMin(), MowerApp.getMax()))*MowerApp.BLOCK_SIZE;
		app.getGameWorld().spawn("sgravel", xrandpos, yrandpos);
		app.getGameWorld().spawn("grass", gravel.getX(), gravel.getY());

		}



}

}