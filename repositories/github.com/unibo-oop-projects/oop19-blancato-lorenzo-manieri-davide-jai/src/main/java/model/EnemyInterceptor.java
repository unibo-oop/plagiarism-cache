package model;

import java.util.Random;

import controller.BulletManager;
import controller.ScreenManager;
import model.BulletImpl.Target;

public class EnemyInterceptor extends EnemyImpl{
	
	private int randomBound = 100;
	private Random rand = new Random();
	
	/**
     * 
     * @param width width of enemyInterceptor
     * @param height height of enemyInterceptor
     * @param positionX position x of enemyInterceptor
     * @param positionY position y of enemyInterceptor
     */
	public EnemyInterceptor(double width, double height, double positionX, double positionY) {
		super(width, height, positionX, positionY);
		
		try {
			this.setImage(ScreenManager.getImage("EnemySpaceshipInterceptor.png"));
        }catch(Exception e) {
            System.out.println("errore immagine");
        }
	}	
	
	public boolean updateMovement() {
			int selector = rand.nextInt(randomBound);
			
			if(selector % 2 == 0) {
				moveDown();
				moveRight();
				
			}else {
				moveDown();
				moveLeft();
			}
		return this.checkBottomBorder();
	}
	
	@Override
	public void fire() {
		super.fire();
		fireWithGreenBullet();
	}
	
	/**
	 * fire with green bullet
	 */
	private void fireWithGreenBullet() {
		GreenBullet b = new GreenBullet(1,6, 
				this.getPositionX()+(this.width/2), this.getPositionY() + (this.height), 
				Target.PLAYER, this.getDamage());
		BulletManager.addBullet(b);
	}
}