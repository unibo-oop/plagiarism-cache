package model;

import java.io.IOException;

import controller.ScreenManager;

public class ShieldPowerUp extends PowerUpImpl {

	/**
	 * 
	 * @param width
	 * @param height
	 * @param positionX
	 * @param positionY
	 */
	public ShieldPowerUp(double width, double height, double positionX, double positionY) {
		super(width, height, positionX, positionY);
		try {
			this.setImage(ScreenManager.getImage("ShieldPowerUp.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void upgradePlayer() {
		PlayerImpl.getInstance().setShield(true);
	}
}
