package model;

import java.io.IOException;
import controller.ScreenManager;

public class GreenBullet extends BulletImpl{
	
	private final static String GREEN_BULLET_IMG = "GreenBullet.png";


	public GreenBullet(double x, double y, double width, double height, Target target, double damage) {
		super(x, y, width, height, target, damage);
		try {
			this.setImage(ScreenManager.getImage(GREEN_BULLET_IMG));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
