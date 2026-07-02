package controller;

import model.Entity;
import java.util.ArrayList;
import java.util.List;


/**
 * This class creates a number of entitis each time it is called, using an algorithm to call enemy and powerup
 * @author Roberto Di Lena
 */
public class ControllerSpawn {

	private static List<Entity> entitiesList = new ArrayList<>();
	private static int nspawnenemies = 0;
	private ControllerEnemies ce = new ControllerEnemies();
	private ControllerPowerUp cp = new ControllerPowerUp();

	/**
	 * Add enemy and powerup to entitiesList
	 * @return
	 */
	public List<Entity> spawnEntities() {

		entitiesList.clear();

		ce.spawnEnemies().forEach(enemy -> {
			entitiesList.add(enemy);
		});
		nspawnenemies++;
		if (nspawnenemies >= 2) {
			if ((int) (Math.random() * 10) < 3) {
				cp.spawnPowerUp().forEach(powerUp -> {
					entitiesList.add(powerUp);
				});
				nspawnenemies = 0;
			}
		}
		return entitiesList;
	}

	/**
	 * Start initial game
	 */
	public void start(){
		ce.start();
	}
}
