package collision;


import static com.almasb.fxgl.app.DSLKt.inc;
import com.almasb.fxgl.app.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.physics.CollisionHandler;
import factory.MowerType;
import javafx.util.Duration;
import launcher.MowerApp;



/**
*
* @author Nicola
* @author Andrea
*
* This class generates a collision handler between player and fuel
*
*/
public class ColPlayerFuel extends CollisionHandler {

	private MowerApp app;


	/**
	 * empty constructor
	 */
	public ColPlayerFuel() {
		super(MowerType.PLAYER, MowerType.FUEL);

		 app = (MowerApp) FXGL.getApp();

	}



	/**
	 * @param player **Entity player**
	 * @param gravel  **Entity fuel**
	 */
	@Override
	protected void onCollisionBegin(Entity player, Entity fuel) {

		app.getGameState().setValue("gas", MowerApp.getTimePerLevel());
		inc("score", +75);
		app.getMasterTimer().runOnceAfter(() -> {
			app.fuelSpawnPosition();
		}, Duration.seconds(MowerApp.getFuelSpawnTime()));

		fuel.removeFromWorld();

		FXGL.getAudioPlayer().playSound("fuel.wav");
	}



}

