package model;

import controller.ScreenManager;

public abstract class PowerUpImpl extends GameObjectImpl implements PowerUp{
	
	
	private final static double SPEED_Y = 0.5;

	/**
	 * 
	 * @param width
	 * @param height
	 * @param positionX
	 * @param positionY
	 */
	public PowerUpImpl(double width, double height, double positionX, double positionY) {
		super(width, height, positionX, positionY);
	}
	
	public boolean updateMovement() {
		 moveY(DELTA_Y * SPEED_Y);
		 if (this.getPositionY() > ScreenManager.heightScreen) {
			 return false;
		 }else {
			 return true; 
		 }
	}
	
	public abstract void upgradePlayer();
	
	
	

}
