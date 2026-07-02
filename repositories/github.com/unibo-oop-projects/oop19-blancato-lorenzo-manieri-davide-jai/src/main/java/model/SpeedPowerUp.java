package model;

import java.io.IOException;

import controller.ScreenManager;

public class SpeedPowerUp extends PowerUpImpl {

	public SpeedPowerUp(double width, double height, double positionX, double positionY) {
		super(width, height, positionX, positionY);
		try {
			this.setImage(ScreenManager.getImage("SpeedPowerUp.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void upgradePlayer() {
		PlayerImpl.getInstance().setFireSpeed((long)(PlayerImpl.getInstance().getFireSpeed()*0.90));
	}
	

}
