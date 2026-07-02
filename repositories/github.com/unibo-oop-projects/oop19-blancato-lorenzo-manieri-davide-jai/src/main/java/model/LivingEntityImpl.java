package model;


import java.io.IOException;
import controller.ScreenManager;
import controller.SoundManager;
import javafx.scene.canvas.GraphicsContext;


public abstract class LivingEntityImpl extends BorderGameObjectImpl implements LivingEntity{

	private final static String EXPLOSION1 = "Explosion1.png";
	private final static String EXPLOSION2 = "Explosion2.png";
	private final static String EXPLOSION3 = "Explosion3.png";
	private final static double INITIAL_HEALTH = 100;
	private final static long INITIAL_FIRE_SPEED = 20;
	
	public double health = INITIAL_HEALTH; 
	public double maxHealth = INITIAL_HEALTH;
	private int explosionStep = 0;
	private boolean exploding = false;
	private boolean removable = false;
	private long fireSpeed = INITIAL_FIRE_SPEED;
	private double damage;
	
	
	
	public LivingEntityImpl(double width, double height, double positionX, double positionY) {
		super(width, height, positionX, positionY);
	}
	
	public boolean getRemovable() {
		return this.removable;
	}
	
	public void setRemovable(boolean remove) {
		this.removable = remove;
	}
	
	public void setFireSpeed(long newSpeed) {
		this.fireSpeed = newSpeed;
	}
	
	public long getFireSpeed() {
		return this.fireSpeed;
	}
	
	public double getHealth() {
		return this.health;
	}
	
	public double getMaxHealth() {
		return this.maxHealth;
	}
	
	public void setHealth(double newHealth) {
		if (newHealth > this.maxHealth) {
			this.health = newHealth;
			this.maxHealth = this.health;
		}else {
			this.health = newHealth;
		}
	}
	
	public void setDamage(double newDamage) {
		this.damage = newDamage;
	}
	
	public double getDamage() {
		return this.damage;
	}
	
	public void hit(double damage) {
		this.setHealth(this.getHealth()-damage);
		if (this.getHealth() <= 0.0) {
			this.setExplode(true);
		}
	}

	public void setExplode(boolean explode) {
		this.exploding = explode;
	}
	
	public boolean isExploding() {
		return this.exploding;
	}

	
	public void checkExplode() {
		if (this.exploding) {
			this.explode();
		}
	}
	
	
	@Override
	public void draw(GraphicsContext gc) {
		checkExplode();
		super.draw(gc);
	}
	
	public void explode() {
		if (this.exploding) {
			if (explosionStep == 0) {
				SoundManager.playExplosionClip();
			}
			explosionStep++;
			try {
				if (explosionStep <= 20) {
					this.setImage(ScreenManager.getImage(EXPLOSION1));
				}
				if (explosionStep > 20 && explosionStep <=40) {
					this.setImage(ScreenManager.getImage(EXPLOSION2));
				}
				if (explosionStep >40 && explosionStep <= 60) {
					this.setImage(ScreenManager.getImage(EXPLOSION3));
				}
				if (explosionStep > 60) {
					explosionStep = 0;
					this.setRemovable(true);
					this.setVisible(false);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public double getRelativeHealth() {
		return this.health/this.maxHealth;
	}
	
	public void fire() {
		SoundManager.playFireClip();
	}
	

}
