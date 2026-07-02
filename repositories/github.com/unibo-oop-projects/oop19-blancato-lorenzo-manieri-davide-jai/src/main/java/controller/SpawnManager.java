package controller;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import model.EnemyImpl;
import model.EnemyCruiser;
import model.EnemyInterceptor;

/**
 * 
 * 
 * Class for spawning enemy and upgrade them through the game
 *
 */
public class SpawnManager {
	
	private static Random rand = new Random();
	private static int ENEMY_WIDTH = 15;
	private static int ENEMY_HEIGHT = 15;
	public static Timer spawnTimer = new Timer();
	private static int MAX_ENEMY = 6;
	private static double deltaHealth = 0;
	private static double deltaDamage = 0;
	private static long fireSpeed = 20;

		
	/**
	 * create a timer to spawn enemy with chosen intervals
	 * 
	 */
	public static void SpawnTimer() {
		
		spawnTimer.scheduleAtFixedRate(new TimerTask() {
			
			@Override
			public void run() {
				if(EnemyManager.getEnemyList().size() < MAX_ENEMY){
					upgradeEnemy();
					spawnEnemy();
					
				}
			}
			
		}, 0, 1950);
	}
	
	/**
	 * method to reset spawn timer
	 * 
	 */
	public static void resetSpawn() {
		spawnTimer.cancel();
		spawnTimer = new Timer();
		
	}
	
	/**
	 * method to spawn a random enemy in a random position X 
	 * 
	 */
	public static void spawnEnemy() {
		int randomX = rand.nextInt((ScreenManager.widthScreen - ENEMY_WIDTH));
		int randomEnemy = rand.nextInt();
		
		
		if(randomEnemy % 2 == 0) {
			EnemyImpl enemyCruiser = new EnemyCruiser(ENEMY_WIDTH, ENEMY_HEIGHT, randomX, 0);
			enemyCruiser.setHealth(enemyCruiser.getMaxHealth() + deltaHealth);
			enemyCruiser.setDamage(enemyCruiser.getDamage() + deltaDamage);
			EnemyManager.addEnemy(enemyCruiser);
			
		}else {
			EnemyImpl enemyInterceptor = new EnemyInterceptor(ENEMY_WIDTH, ENEMY_HEIGHT, randomX, 0);
			enemyInterceptor.setHealth(enemyInterceptor.getMaxHealth() + deltaHealth);
			enemyInterceptor.setDamage(enemyInterceptor.getDamage() + deltaDamage);
			EnemyManager.addEnemy(enemyInterceptor);
	 }
	}
	
	/**
	 * method to upgrade enemy during play time
	 * 
	 */
	public static void upgradeEnemy() {
		if(EnemyManager.enemyKilled % 10 == 0 && EnemyManager.enemyKilled> 0 && EnemyManager.enemyKilled > EnemyManager.lastEnemyKilled) {
			deltaHealth += 20;
			EnemyManager.lastEnemyKilled = EnemyManager.enemyKilled;
		}
		
		if(EnemyManager.enemyKilled % 20 == 0 && EnemyManager.enemyKilled> 0 && EnemyManager.enemyKilled > EnemyManager.lastEnemyKilled) {
			deltaDamage += 20;
			EnemyManager.lastEnemyKilled = EnemyManager.enemyKilled;
		}
		
		if(EnemyManager.enemyKilled % 30  == 0 && EnemyManager.enemyKilled> 0 && EnemyManager.enemyKilled > EnemyManager.lastEnemyKilled ) {
			fireSpeed--;
			if(fireSpeed > 1) {
				TimeManagerImpl.getInstance().setEnemyFireTimer(fireSpeed);
				EnemyManager.lastEnemyKilled = EnemyManager.enemyKilled;	
			}
		}
		
	}	
	
}
	

