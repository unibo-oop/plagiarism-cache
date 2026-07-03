package tq2.implementations.entity;

import java.awt.Graphics2D;

import tq2.implementations.Id;
import tq2.interfaces.Handler;
import tq2.interfaces.LevelLayer;
import tq2.interfaces.platform.Attack;

/**
 * The Class AttackRectangle.
 * 
 * @author Francesco Gori
 */
public class AttackRectangle extends EntityImpl {
	
	/** The target which is attacking. */
	protected Attack target;
	
	/** The offset along the X axis from the target. */
	protected Integer offsetX;
	
	/** The offset along the Y axis from the target. */
	protected Integer offsetY;
	
	/** A counter that starts when the object is created to keep track of its lifetime. */
	protected Integer duration;
	
	/** How much the Attack rectangle will persist before disappearing. */
	protected Integer maxDuration;
	
	/** The damage the characters will get if they collide with this object. */
	protected Integer damage;
	
	/**
	 * Instantiates a new attack rectangle.
	 *
	 * @param target the target who did the attack
	 * @param width the width of the rectangle
	 * @param duration the duration the rectangle will persist
	 * @param handler the Handler
	 * @param layer the layer the rectangle resides in
	 */
	public AttackRectangle (Attack target, Integer width, Integer duration, Handler handler, LevelLayer layer) {
		super(target.getX(),
				target.getY(),
				width,
				target.getHeight(),
				1, true, target.isEnabled(),
				target.getScaleX(),
				target.getScaleY(),
				target.getVelX(),
				target.getVelY(), 
				1.0f, handler, layer);
	
		this.id = Id.attackRectangle;
		this.target = target;
		
		this.duration = 0;
		this.maxDuration = duration;
		
		this.damage = target.getAttackDamage();

	}

	/* (non-Javadoc)
	 * @see com.tq2.implementations.entity.EntityImpl#tick()
	 */
	public void tick() {
	
		//the rectangle is always in the direction of the target
		Integer offset = this.getWidth()*target.getFacing();
		if (target.getFacing() == -1) {
			offset = this.getWidth()*target.getFacing();
		}
		else {
			offset = target.getWidth()*target.getFacing();
		}
		this.setX(target.getX() + offset);
		this.setY(target.getY() );
		this.duration++;
		
		if (this.getHandler().getLayerOf(this.target) == null || !this.target.isEnabled() || this.duration >= this.maxDuration || !this.target.isAttacking() ) {
			this.destroy();
		}
	}
	
	/**
	 * Returns the target who did the attack.
	 *
	 * @return the target
	 */
	public Attack getTarget() {
		return this.target;
	}
	
	/**
	 * Returns the damage a character will get if he overlaps the rectangle.
	 *
	 * @return the damage
	 */
	public Integer getDamage() {
		return this.damage;
	}
	
	/**
	 * Sets the damage a character will get if he overlaps the rectangle.
	 *
	 * @param damage the new damage
	 */
	public void setDamage(Integer damage) {
		this.damage = damage;
	}

	/* (non-Javadoc)
	 * @see com.tq2.implementations.entity.EntityImpl#render(java.awt.Graphics2D)
	 */
	@Override
	public void render(Graphics2D g) {
		//Not used
	}
	
}
