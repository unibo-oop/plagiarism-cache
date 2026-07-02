package model;

import java.io.IOException;

import controller.ScreenManager;

public class RedBullet extends BulletImpl{
	private final static String RED_BULLET_IMG = "RedBullet.png";


	public RedBullet(double x, double y, double width, double height, Target target, double damage) {
		super(x, y, width, height, target, damage);
		try {
			this.setImage(ScreenManager.getImage(RED_BULLET_IMG));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
