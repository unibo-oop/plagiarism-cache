package controller;

import java.util.LinkedList;


import java.util.List;
import java.util.Random;
import javafx.scene.canvas.GraphicsContext;
import model.DamagePowerUp;
import model.HealthPowerUp;
import model.PlayerImpl;
import model.PowerUp;
import model.ShieldPowerUp;
import model.SpeedPowerUp;

/**
 * 
 * Class for powerUp movement and spawn management
 *
 */
public class PowerUpManagerImpl implements PowerUpManager{
	
	private final static int POWER_UP_WIDTH = 10; 
	private final static int POWER_UP_HEIGHT = 10; 
	private final static int SPAWN_POWER_UP = 10;
	private static int lastPowerUp =0;
	
	private static List<PowerUp> powerUpManager = null;
	

	/**
	 * Get the powerUp list
	 * @return
	 * 		Return the power up list
	 */
	public static List<PowerUp> getPowerUpList(){
		if(powerUpManager == null) {
			powerUpManager = new LinkedList<PowerUp>();
		}
		return powerUpManager;
	}
	
	/**
	 * 
	 */
	public void addPowerUp(PowerUp p) {
		PowerUpManagerImpl.getPowerUpList().add(p);
		
	}
	
	/**
	 * 
	 */
	public void removePowerUp(PowerUp p) {
		PowerUpManagerImpl.getPowerUpList().remove(p);
	}
	
	/**
	 * 
	 */
	public void update(GraphicsContext gc) {
		if (PowerUpManagerImpl.getPowerUpList().size() > 0 ) {
			for (PowerUp p : powerUpManager) {
				if (p.updateMovement()) {
					p.draw(gc);
					if (p.checkCollision(PlayerImpl.getInstance().getBounds())) {
						p.upgradePlayer();
						this.removePowerUp(p);
					}
				}else {
					this.removePowerUp(p);
				}
			}
		}else {
			if (EnemyManager.enemyKilled % SPAWN_POWER_UP == 0 && EnemyManager.enemyKilled> 0 && EnemyManager.enemyKilled > lastPowerUp ) {
				double tmpX, tmpY;
				tmpX = EnemyManager.lastEnemy.getPositionX();
				tmpY = EnemyManager.lastEnemy.getPositionY();
				this.spawnPowerUp(tmpX, tmpY);
				lastPowerUp = EnemyManager.enemyKilled;
			}
		}
	}
	
	/**
	 * Spawn a random powerUp at (x, y) position
	 * @param x
	 * @param y
	 */
	private void spawnPowerUp(double x, double y) {
		Random rand = new Random();
		int tmp = rand.nextInt(100);
		if (PlayerImpl.getInstance().getFireSpeed()>1) {
			tmp = tmp % 4; 
		}else {
			tmp = tmp % 3;
		}
		
		switch (tmp) {
		
		case 0: PowerUp p3 = new HealthPowerUp(POWER_UP_WIDTH, POWER_UP_HEIGHT, x, y);
				this.addPowerUp(p3);
				break;
				
		case 1: PowerUp p1 = new DamagePowerUp(POWER_UP_WIDTH, POWER_UP_HEIGHT, x, y);
				this.addPowerUp(p1);
				break;
		
		case 2: PowerUp p2 = new ShieldPowerUp(POWER_UP_WIDTH, POWER_UP_HEIGHT, x, y);
				this.addPowerUp(p2);
				break;
		case 3: PowerUp p = new SpeedPowerUp(POWER_UP_WIDTH, POWER_UP_HEIGHT, x, y);
				this.addPowerUp(p);
				break;
		
		default: break;				
		}
	}
	
	/**
	 * Clear the powerUp manager list
	 */
	public static void resetPowerUpManager() {
		powerUpManager.clear();
	}
}
	

