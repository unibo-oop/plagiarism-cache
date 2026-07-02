package model;

import controller.ScreenManager;


public abstract class BulletImpl extends GameObjectImpl implements Bullet{

	private final double SPEED = 1.2;
	private Target target; 
	private double damage;
	
	/**
	 * 
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 * @param target
	 * @param damage
	 */
	public BulletImpl(double x, double y, double width, double height, Target target, double damage) {
		super(x, y, width, height);
		this.damage = damage;
		this.target = target;
	}
	
	public double getDamage() {
		return this.damage;
	}
	
	public void setDamage(int newDamage) {
		damage = newDamage;
	}

	
	public Target getTarget() {
		return this.target;  
	}
	
	public void setTarget(Target target) {
		this.target = target; 
	}
	
	public boolean updateMovement() {
		if(this.getPositionY() > ScreenManager.heightScreen || this.getPositionY() + this.getHeight() < 0) {
			this.setVisible(false);
			return false;
		}else {
			if (this.target == Target.ENEMY)
			{
				this.moveY(-DELTA_Y * SPEED);
			}else {
				this.moveY(DELTA_Y * SPEED);
			}
			return true;
		}
		
	}


	
	public  enum Target{
		ENEMY,
		PLAYER
	}
}
