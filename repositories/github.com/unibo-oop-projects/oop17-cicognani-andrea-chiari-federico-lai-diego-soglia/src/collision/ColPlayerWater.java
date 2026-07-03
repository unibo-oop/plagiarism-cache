package collision;


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
* This class generates a collision handler between player and water
*
*/
public class ColPlayerWater extends CollisionHandler {

	private MowerApp app;

	/**
	 * empty constructor
	 */
	public ColPlayerWater() {
		super(MowerType.PLAYER, MowerType.WATER);

		 app = (MowerApp) FXGL.getApp();

	}



	/**
	 * @param player **Entity player**
	 * @param gravel  **Entity water**
	 */
	@Override
	protected void onCollisionBegin(Entity player, Entity water) {
		FXGL.getAudioPlayer().playSound("lose.wav");
		app.getDisplay().showMessageBox("Oh no, you fell into the water. Game over!", () -> {
			app.setGameOver(true);
		});
	}
}

