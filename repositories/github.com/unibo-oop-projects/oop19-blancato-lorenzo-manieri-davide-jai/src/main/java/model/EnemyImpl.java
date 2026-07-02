package model;

import controller.EnemyManager;
import controller.ScreenManager;
import javafx.scene.canvas.GraphicsContext;

public abstract class EnemyImpl extends LivingEntityImpl implements Enemy {

	private HealthBarImpl healthBar;
	private final static double ENEMY_INITIAL_DAMAGE = 25;

			
	public EnemyImpl(double width, double height, double positionX, double positionY) {
		super(width, height, positionX, positionY);
		this.setDamage(ENEMY_INITIAL_DAMAGE);
		this.healthBar = new HealthBarImpl();
		this.setVisible(true);	
	}
		
	public void moveUp() {
		move(0, -1);
	}
	
	public void moveDown() {
		move(0, 1);
	}
	
	public void moveRight() {
		move(1, 0); 
	}
	
	public void moveLeft() {
		move(-1, 0); 
	}
	
	@Override
	public void hit(double damage) {
		super.hit(damage);
		this.healthBar.setCurrentHealth(this.getRelativeHealth());
	}
	
	public abstract boolean updateMovement();
	

	public void move(int deltaX, int deltaY) {
		checkMoveX(deltaX);
		moveY(deltaY);	
	}
	
	@Override
	public void draw(GraphicsContext gc) {
		healthBar.drawRectangle(gc, x, y - 2*healthBar.getHealthHeight());
		super.draw(gc);
	}
	
	public boolean checkBottomBorder() {
		if (this.getPositionY() > ScreenManager.heightScreen) {
			EnemyManager.removeEnemy(this);
			return false;
		}else {
			return true;
		}
	}
	
	
}
				

