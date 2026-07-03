package collision;

import static com.almasb.fxgl.app.DSLKt.geti;
import static com.almasb.fxgl.app.DSLKt.inc;

import com.almasb.fxgl.app.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.physics.CollisionHandler;
import factory.MowerType;
import launcher.MowerApp;

/**
*
* @author Nicola
*
* This class generates a collision handler between player and weed
*
*/
public class ColPlayerWeed extends CollisionHandler {

		private MowerApp app;

		/**
		 * empty constructor
		 */
		public ColPlayerWeed() {
			super(MowerType.PLAYER, MowerType.WEED);

			 app = (MowerApp) FXGL.getApp();

		}


		/**
		 * @param player **Entity player**
		 * @param gravel  **Entity weed**
		 */
		@Override
		protected void onCollisionBegin(Entity player, Entity weed) {

			inc("weedtiles", -1);
			inc("score", +50);

			if (geti("weedtiles") == 0) {
				app.gameOver();
			}
			app.getGameWorld().spawn("grass", weed.getX(), weed.getY());
			weed.removeFromWorld();
			
			FXGL.getAudioPlayer().playSound("run.wav");
		}




	}