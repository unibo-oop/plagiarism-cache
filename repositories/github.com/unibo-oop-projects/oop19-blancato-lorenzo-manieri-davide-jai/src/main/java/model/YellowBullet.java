package model;

import java.io.IOException;

import controller.ScreenManager;


public class YellowBullet extends BulletImpl{
	private final static String YELLOW_BULLET_IMG = "YellowBullet.png";


	public YellowBullet(double x, double y, double width, double height, Target target, double damage) {
		super(x, y, width, height, target, damage);
		try {
			this.setImage(ScreenManager.getImage(YELLOW_BULLET_IMG));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
