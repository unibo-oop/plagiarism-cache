package model;

import java.io.IOException;

import controller.ScreenManager;

public class DamagePowerUp extends PowerUpImpl{
	
	/**
	 * 
	 * @param width
	 * @param height
	 * @param positionX
	 * @param positionY
	 */
	public DamagePowerUp(double width, double height, double positionX, double positionY) {
		super(width, height, positionX, positionY);
		try {
			this.setImage(ScreenManager.getImage("DamagePowerUp.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void upgradePlayer() {
		PlayerImpl.getInstance().setDamage(PlayerImpl.getInstance().getDamage()* 1.10);
	}

}
