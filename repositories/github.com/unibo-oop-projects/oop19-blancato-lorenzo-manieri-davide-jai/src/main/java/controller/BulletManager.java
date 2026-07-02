package controller;


import java.util.concurrent.CopyOnWriteArrayList;
import javafx.scene.canvas.GraphicsContext;
import model.Bullet;
import model.PlayerImpl;
import model.BulletImpl.Target;
import model.Enemy;

/**
 * 
 * 
 * Class for bullet collision and damage application
 *
 */
public class BulletManager implements Controller {

	protected static CopyOnWriteArrayList<Bullet> bulletManager = null;


	
	/**
	 * get the bullet list
	 * @return return bullet list
	 * 
	 *
	 */
	public static CopyOnWriteArrayList<Bullet> getBulletList(){

		if(bulletManager == null) {
			bulletManager = new CopyOnWriteArrayList<Bullet>();

		}
		return bulletManager;
	}
	

	/**
	 * add bullet b to the list
	 * @param b bullet that has to be added
	 * 
	 */
	public static void addBullet(Bullet b) {

		BulletManager.getBulletList().add(b);
		
	}
	

	/**
	 * remove bullet b to the list
	 * @param b bullet that has to be removed
	 * 
	 */
	public static void removeBullet(Bullet b) {
		BulletManager.getBulletList().remove(b);
	}
	
	/**
	 * update bullet movement in the list and draw them, managing also damage dealt for player and enemy
	 * @param gc graphic context used to draw on the window
	 * 
	 */
	public void update(GraphicsContext gc) {
		for(Bullet b : BulletManager.getBulletList()) {
			if(b.updateMovement()) {
				b.draw(gc);
				if(b.getTarget() == Target.PLAYER) {
					if(b.checkCollision(PlayerImpl.getInstance().getBounds()) && 
							!PlayerImpl.getInstance().isBlinking()) {
						if (!PlayerImpl.getInstance().getShield()) {
							PlayerImpl.getInstance().hit(b.getDamage());
							b.setVisible(false);
							removeBullet(b);
						}
					}
				}else {
					if(b.getTarget() == Target.ENEMY) {
						for(Enemy e : EnemyManager.getEnemyList()) {
							if(e.checkCollision(b.getBounds())) {
								e.hit(b.getDamage());
								b.setVisible(false);
								removeBullet(b);
							}
						}
					}
				}
			}
		}
	}
}

