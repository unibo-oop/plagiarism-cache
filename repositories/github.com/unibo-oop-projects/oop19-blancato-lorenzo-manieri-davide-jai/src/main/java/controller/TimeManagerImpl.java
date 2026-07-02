package controller;

import java.util.Timer;
import java.util.TimerTask;
import model.PlayerImpl;

/**
 *
 */
public class TimeManagerImpl implements TimeManager{

	private static long SHIELD_TIME = 10000;
	
	private Timer fireTimer;
	private Timer shieldTimer;
	private Timer blinkTimer;
	private Timer enemyFireTimer;
	private int countBlink = 0; 
	private long starShieldTime =0;
	private long stopShieldTime = 0;
	
	

	private static TimeManagerImpl timeManager = null;

	/**
	 * 
	 * @return
	 * 		Returns a single instance of this class to manage all timer for entities fire and 
	 * 		player upgrades
	 */
	public synchronized static TimeManagerImpl getInstance() {
		if(timeManager == null) {
			timeManager = new TimeManagerImpl();
		}
		return timeManager;
	}

	public TimeManagerImpl() {
		this.fireTimer = new Timer();
		this.shieldTimer = new Timer();
		this.blinkTimer = new Timer();
		this.enemyFireTimer = new Timer();
	}
	
	/**
	 * Set player fire speed timer
	 * 
	 * @param period
	 * 		Period when player can fire
	 */
	public void setFireTimer(long period) {
		this.fireTimer.schedule(new FireTask(), 0, period*50);
	}
	
	/**
	 * Set enemy fire speed
	 * 
	 * @param period
	 * 		Period when enemy can fire
	 */
	public void setEnemyFireTimer(long period) {
		this.enemyFireTimer.schedule(new EnemyFireTask(), 0, period*50);
	}
	
	/**
	 * Set player shield duration
	 */
	public void setShieldTimer() {
		this.shieldTimer.schedule(new ShieldTask(), SHIELD_TIME, 10000);
		this.starShieldTime = System.currentTimeMillis();
	}
	
	/**
	 * Set period of blinking
	 */
	public void setBlinkingTimer() {
		this.blinkTimer.schedule(new BlinkTask(), 0, 500);
		
	}
	
	/**
	 * Reset fire timer
	 */
	public void resetFireTimer() {
		this.fireTimer.cancel();
		this.fireTimer = new Timer();
		
	}
	
	/**
	 * Reset enemy fire timer
	 */
	public void resetEnemyFireTimer() {
		this.enemyFireTimer.cancel();
		this.enemyFireTimer = new Timer();
	}
	
	/**
	 * Reset shield timer 
	 */
	public void resetShield() {
		this.shieldTimer.cancel();
		this.shieldTimer = new Timer();
	
	}
	
	/** 
	 * Reset blinking timer
	 */
	public void resetBlinking() {
		this.blinkTimer.cancel();
		this.blinkTimer = new Timer();
		
	}
	
	public void pause() {
		this.stopShieldTime = System.currentTimeMillis();
		this.resetShield();
		this.resetBlinking();
		
	}
	
	public void resume() {
		long timeElapsed = this.stopShieldTime - this.starShieldTime;
		if (timeElapsed < 10000) {
			this.shieldTimer.schedule(new ShieldTask(), SHIELD_TIME-timeElapsed, 10000);
		}
		if (this.countBlink > 0) {
			this.setBlinkingTimer();
		}
		
	}
	
	public void resetAllTimer() {
		this.resetBlinking();
		this.resetEnemyFireTimer();
		this.resetFireTimer();
		this.resetShield();
	}
	/**
	 * Timer task called by fire timer which set if player can fire
	 * 
	 */
	class FireTask extends TimerTask{

		@Override
		public void run() {
			PlayerImpl.getInstance().setCanFire(true);
		}
		
	}
	
	/**
	 *	Timer task called by shield timer which remove player shield
	 * 
	 */
	class ShieldTask extends TimerTask{

		@Override
		public void run() {
			PlayerImpl.getInstance().setShield(false);	
			resetShield();
		}
		
	}
	
	/**
	 *	Timer task called by blinkTimer which make blink hit living entities
	 */
	class BlinkTask extends TimerTask{

		@Override
		public void run() {
			if (countBlink<3) {
				PlayerImpl.getInstance().setVisible(!PlayerImpl.getInstance().isVisible());
				countBlink++;
			}else {
				countBlink =0;
				PlayerImpl.getInstance().setVisible(true);
				PlayerImpl.getInstance().setBlinking(false);
				resetBlinking();
			}
		}
	}
	
	/**
	 * 	Timer task called by enemyFireTimer which set if enemies can fire
	 *
	 */
	class EnemyFireTask extends TimerTask{

		@Override
		public void run() {
			EnemyManager.setCanFire(true);
		}
		
	}
}




