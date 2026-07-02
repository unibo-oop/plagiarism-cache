package model;

import java.io.IOException;

import controller.ScreenManager;

public class HealthPowerUp extends PowerUpImpl {

	public HealthPowerUp(double width, double height, double positionX, double positionY) {
		super(width, height, positionX, positionY);
		try {
			this.setImage(ScreenManager.getImage("HealthPowerUp.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void upgradePlayer() {
		PlayerImpl.getInstance().setHealth(PlayerImpl.getInstance().getHealth()*1.25);
	}

}
