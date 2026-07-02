package model;

import controller.BulletManager;
import controller.ScreenManager;
import model.BulletImpl.Target;


public class EnemyCruiser extends EnemyImpl{

	private int stepMotion = 0;
	private int stepMovement = 0;
	private boolean changeDir = false;
	
	/**
	 * 
	 * @param width width of enemyCruiser
	 * @param height height of enemyCruiser
	 * @param positionX position x of enemyCruiser
	 * @param positionY position y of enemyCruiser
	 */
	public EnemyCruiser(double width, double height, double positionX, double positionY) {
		super(width, height, positionX, positionY);
		
		try {
			this.setImage(ScreenManager.getImage("EnemySpaceshipCruiser.png"));
        }catch(Exception e) {
            System.out.println("errore immagine");
        }
	}
			
	 public boolean updateMovement() {
		
		if(stepMotion >=15) {
			changeDir = !changeDir;
			stepMotion = 0 ;
		}else{
			stepMovement++;
		}
		if(stepMovement >=5) {
			stepMotion++;
			stepMovement = 0;
			if(changeDir) {
				moveDown();
				moveRight();
				moveLeft();
				moveLeft();
				moveUp();
				moveDown();
			}else {
				moveDown();
				moveLeft();
				moveRight();
				moveRight();
				moveUp();
				moveDown();
			}
		}
		return this.checkBottomBorder();
	}	

	@Override
	public void fire() {
		super.fire();
		fireWithYellowBullet();	
	}
	
	/**
	 * fire with yellow bullet
	 */
	private void fireWithYellowBullet(){
		YellowBullet b = new YellowBullet(1,6, 
				this.getPositionX()+(this.width/2), this.getPositionY()+ (this.height), 
				Target.PLAYER, this.getDamage());
		BulletManager.addBullet(b);
	}
}
	
