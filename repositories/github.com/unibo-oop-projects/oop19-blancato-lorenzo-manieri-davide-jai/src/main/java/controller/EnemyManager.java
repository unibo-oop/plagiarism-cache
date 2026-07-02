package controller;


import java.util.List;

import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;
import javafx.scene.canvas.GraphicsContext;
import model.Enemy;
import model.EnemyCruiser;
import model.PlayerImpl;

/**
 * 
 * Class for managing enemy movement, firing and explosion
 *
 */
public class EnemyManager implements Controller {
	
	Random rand = new Random();
	
	protected static CopyOnWriteArrayList<Enemy> enemyManager = null;
	private static int ENEMY_COLLISION_DAMAGE = 40;
	private static int MAX_ENEMY = 6;
	public static int enemyKilled = 0;
	public static Enemy lastEnemy;
	public static boolean canFire = true;
	public static int speedFireEnemy = 20;
	public TimeManager fireManager;
	public static int lastEnemyKilled = 0;

	/**
	 * initialize timer for managing enemies
	 */
	public EnemyManager() {
		SpawnManager.SpawnTimer();
		fireManager = TimeManagerImpl.getInstance();
		fireManager.setEnemyFireTimer(speedFireEnemy);
	}
	
	/**
	 * get the enemy list
	 * @return return enemy list
	 * 
	 *
	 */
	public static List<Enemy> getEnemyList() {
		if (enemyManager == null) {
			enemyManager = new CopyOnWriteArrayList<Enemy>();
		}
		return enemyManager;
	}
	
	/**
	 * add enemy e to the list
	 * @param e enemy to be added
	 */
	public static void addEnemy(Enemy e){
		EnemyManager.getEnemyList().add(e);
	}
	
	/**
	 * remove enemy e to the list
	 * @param e enemy to be removed
	 */
	public static void removeEnemy(Enemy e){ 
		EnemyManager.getEnemyList().remove(e);
	}
	
	/**
	 * add enemy e to counter and add score points based on which enemy is killed
	 * @param e enemy killed and added to score point
	 */
	private void addEnemyKilled(Enemy e) {
		lastEnemy = e;
		ScoreManager scoreManager = ScoreManagerImpl.getInstance();
		enemyKilled++;
		if(e instanceof EnemyCruiser) {
			scoreManager.getCurrentScore().setScore(scoreManager.getCurrentScore().getScore() + 10);
		}else {
			scoreManager.getCurrentScore().setScore(scoreManager.getCurrentScore().getScore() + 20);
		}
		scoreManager.getCurrentScore().setEnemyKilled(enemyKilled);
		removeEnemy(e);
	}
	
	
	/**
	 * method to set if enemy can shoot
	 * @param canFire boolean to set fire possibility
	 */
	public static void setCanFire(boolean canFire) {
		EnemyManager.canFire = canFire ;
	}
	
	/**
	 * update enemy movement in the list and draw them, managing also collision damage
	 * and which enemy is firing
	 * @param gc graphic context used to draw on the window
	 */
	public void update(GraphicsContext gc) {
		if(getEnemyList().size() > 0) {
			for(Enemy e: getEnemyList()) {
				if (e.getRemovable()) {
					addEnemyKilled(e);
				}else {
					boolean updated = e.updateMovement();
					if (updated) {
						e.draw(gc);
					}
					if(e.checkCollision(PlayerImpl.getInstance().getBounds()) && 
						!PlayerImpl.getInstance().isBlinking()) {
						if (PlayerImpl.getInstance().getShield()) {
							e.hit(PlayerImpl.getInstance().getCollisionDamage());
						}else {
							PlayerImpl.getInstance().hit(ENEMY_COLLISION_DAMAGE);
							e.hit(PlayerImpl.getInstance().getCollisionDamage());
						}
					}
				}	
			}
			int firingEnemies = 0;
			for(Enemy e : getEnemyList()) {
				if(canFire) {
					int fireChance = rand.nextInt(100);
					if(firingEnemies < MAX_ENEMY /2 && fireChance < 50) {
					firingEnemies++;
					e.fire();
					}	
				}
			}
			EnemyManager.setCanFire(false); 
			firingEnemies = 0;
			
		}	
		
	}
	
}