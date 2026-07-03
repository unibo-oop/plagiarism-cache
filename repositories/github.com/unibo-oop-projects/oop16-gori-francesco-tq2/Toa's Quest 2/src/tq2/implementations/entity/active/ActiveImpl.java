package tq2.implementations.entity.active;

import java.awt.Rectangle;

import tq2.implementations.entity.EntityImpl;
import tq2.implementations.entity.tile.Tile;
import tq2.interfaces.Entity;
import tq2.interfaces.Handler;
import tq2.interfaces.LevelLayer;
import tq2.interfaces.platform.Active;

/**
 * The Class ActiveImpl.
 * 
 * @author Francesco Gori
 */
public abstract class ActiveImpl extends EntityImpl implements Active {
	
	/** The default gravity acceleration for this object. */
	protected Double defaultGravityAcceleration;
	
	/** The default maximum velocity this object can reach along the X axis. */
	protected Integer defaultMaxVelX;
	
	/** The default maximum velocity this object can reach along the Y axis. */
	protected Integer defaultMaxVelY;
	
	/** The gravity this object is subject to. */
	protected Double gravity;
	
	/** The gravity acceleration this object is subject to. */
	protected Double gravityAcceleration;
	
	/** The maximum velocity this object can reach along the X axis. */
	protected Integer maxVelX;
	
	/** The maximum velocity this object can reach along the Y axis. */
	protected Integer maxVelY;
	
	/** Whether the object is falling. */
	protected Boolean falling = true;

	
	/**
	 * Instantiates a new ActiveImpl.
	 *
	 * @param x the X coordinate
	 * @param y the Y coordinate
	 * @param width the width of the object
	 * @param height the height of the object
	 * @param facing the direction of the object. Either 1 (right) or -1 (left)
	 * @param solid whether the object is solid
	 * @param enabled whether the object is enabled
	 * @param scaleX the scale of the object along the X axis
	 * @param scaleY the scale of the object along the Y axis
	 * @param velX the velocity of this object along the X axis
	 * @param velY the velocity of this object along the Y axis
	 * @param alpha the alpha value of this object
	 * @param handler the Handler
	 * @param layer the layer this object resides in
	 * @param gravityAcceleration the gravity acceleration this object will be subject to
	 * @param maxVelX the maximum velocity this object can reach along the X axis
 	 * @param maxVelY the maximum velocity this object can reach along the Y axis
	 */
	public ActiveImpl (
			Integer x, Integer y,
			Integer width, Integer height,
			Integer facing,
			Boolean solid,
			Boolean enabled,
			Double scaleX, Double scaleY,
			Double velX, Double velY,
			Float alpha,
			Handler handler,
			LevelLayer layer,
			Double gravityAcceleration,
			Integer maxVelX, Integer maxVelY) {
		
		super(x, y, width, height, facing, solid, enabled, scaleX, scaleY, velX, velY, alpha, handler, layer);
		this.defaultGravityAcceleration = gravityAcceleration;
		this.defaultMaxVelX = maxVelX;
		this.defaultMaxVelY = maxVelY;
		
		this.gravity = gravityAcceleration;
		this.gravityAcceleration = gravityAcceleration;
		
		this.maxVelX = maxVelX;
		this.maxVelY = maxVelY;
		
		this.facing = facing;

	}

	/* (non-Javadoc)
	 * @see com.tq2.implementations.entity.EntityImpl#tick()
	 */
	@Override
	public void tick() {
			
			this.setY(this.getY() + this.getVelY() + this.getGravity());
			this.testCollision();
			
			//increment the X coordinate of one unit at a time
			for (int i=this.getVelX().intValue(); i!=0; i -= (Integer.signum(i))) {
				this.setX(this.getX()+(Integer.signum(i)));
				this.testCollision();
			}

			//increase gravity while falling
			if (this.isFalling()) {
				if ((this.getVelY() + getGravity()) <= this.getMaxVelY()) {
					this.setGravity(this.getGravity() + this.getGravityAcceleration());
				}
			}
	
		this.tickAnim();
	}
		
	/* (non-Javadoc)
	 * @see platform.entity.active.ActiveInterface#testCollision()
	 */
	@Override
	public void testCollision() {
		if (this.solid) {
			Boolean falling = true;
			
			for (int i=0; i< this.layer().size(); i++) {
				Entity en = this.layer().get(i);
				
				if (en.isSolid()) {
					if (en instanceof Tile) {
						if (this.getBounds().intersects(en.getBounds())) {
							this.collide(en);
						}
						
						if (this.getBoundsTop().intersects(en.getBounds())) {
							this.collideTop(en);
						}
						
						else if (this.getBoundsBottom().intersects(en.getBounds())) {
							falling = false;
							this.collideBottom(en);
						}
						
						else if (this.getBoundsLeft().intersects(en.getBounds())) {
							this.collideLeft(en);
						}
						
						else if (this.getBoundsRight().intersects(en.getBounds())) {
							this.collideRight(en);
						}

					}
				}
			
			this.setFalling(falling);
			}
		}
	}
	
	/**
	 * Trigger the behavior this object has when it collides with an Entity of the kind of the specified one.
	 *
	 * @param en the Entity this object is colliding with
	 */
	protected void collide(Entity en) {
		
	}
	
	/**
	 * Trigger the behavior this object has when it collides from the top with an Entity of the kind of the specified one.
	 *
	 * @param en the Entity this object is colliding with
	 */
	protected void collideTop(Entity en) {

		//if collides with a tile on the upper side, set gravity to 0 (start falling)
		if (en instanceof Tile) {
			this.setY(en.getY() + this.getHeight());
			this.setGravity(this.getGravityAcceleration());
		}

	}
	
	/**
	 * Trigger the behavior this object has when it collides from the bottom with an Entity of the kind of the specified one.
	 *
	 * @param en the Entity this object is colliding with
	 */
	protected void collideBottom(Entity en) {

		//if collides with a tile on the lower side, stand on it
		if (en instanceof Tile) {
			this.setY(en.getY() - en.getHeight());
			this.setGravity(this.getGravityAcceleration());
					
			if (this.isFalling()) {
				this.setFalling(false);
			}
		}
	}
	
	/**
	 * Trigger the behavior this object has when it collides from the left with an Entity of the kind of the specified one.
	 *
	 * @param en the Entity this object is colliding with
	 */
	protected void collideLeft(Entity en) {

		//if collides with a tile on the left side, set velX to 0 (stop)
		if (en instanceof Tile) {
			setVelX(0);
			this.setX(en.getX() + en.getWidth());
		}

	}
	
	/**
	 * Trigger the behavior this object has when it collides from the right with an Entity of the kind of the specified one.
	 *
	 * @param en the Entity this object is colliding with
	 */
	protected void collideRight(Entity en) {

		//if collides with a tile on the right side, set velX to 0 (stop)
		if (en instanceof Tile) {
			setVelX(0);
			this.setX(en.getX() - this.getWidth());
		}
	}
	
	/**
	 * Returns the upper side of the bounding box of this object.
	 *
	 * @return a rectangle as wide as the object, with a minimum height value
	 */
	public Rectangle getBoundsTop() {
		return new Rectangle(this.getX() + 1, this.getY() - this.getHeight(), this.getWidth() - 2, 1);	
	}
	
	/**
	 * Returns the lower side of the bounding box of this object.
	 *
	 * @return a rectangle as wide as the object, with a minimum height value
	 */
	public Rectangle getBoundsBottom() {	
		return new Rectangle(this.getX() + 1, this.getY(), this.getWidth() - 2, 1);
	}
	
	/**
	 * Returns the left side of the bounding box of this object.
	 *
	 * @return a rectangle as high as the object, with a minimum width value
	 */
	public Rectangle getBoundsLeft() {
		return new Rectangle(this.getX(), this.getY() - this.getHeight() + 5, 1, this.getHeight() - 10);
	}
	
	/**
	 * Returns the right side of the bounding box of this object.
	 *
	 * @return a rectangle as high as the object, with a minimum width value
	 */
	public Rectangle getBoundsRight() {
		return new Rectangle(this.getX() + this.getWidth() - 1,  this.getY() - this.getHeight() + 5, 1, this.getHeight() - 10);
	}
	
	/**
	 * Updates the animation the object is playing depending on its status.
	 */
	protected void tickAnim() {
		String anim;
		//Falling
		if (this.isFalling()) {
			anim = "Fall";
		}
		//Idle
		else if (this.getVelX() == 0) {
			anim = "Idle";
		}
		//Running
		else {
			anim = "Run";
		}
		
		if (this.getCurrentAnimation() != anim) {
			this.setCurrentAnimation(anim);
			this.frameCounter = 0;
		}
		
	}

	/* (non-Javadoc)
	 * @see platform.entity.active.ActiveInterface#isFalling()
	 */
	@Override
	public Boolean isFalling() {
		return this.falling;
	}
	
	/* (non-Javadoc)
	 * @see com.tq2.interfaces.platform.Fall#getGravity()
	 */
	@Override
	public Double getGravity() {
		return this.gravity;
	}
	
	/* (non-Javadoc)
	 * @see com.tq2.interfaces.platform.Fall#getGravityAcceleration()
	 */
	@Override
	public Double getGravityAcceleration() {
		return this.gravityAcceleration;
	}


	/**
	 * Returns the maximum velocity this object can reach along the X axis.
	 *
	 * @return the maximum velocity this object can reach along the X axis.
	 */
	public Integer getMaxVelX() {
		return this.maxVelX;
	}

	/* (non-Javadoc)
	 * @see com.tq2.interfaces.platform.Fall#getMaxVelY()
	 */
	public Integer getMaxVelY() {
		return this.maxVelY;
	}

	/* (non-Javadoc)
	 * @see com.tq2.interfaces.platform.Fall#setFalling(java.lang.Boolean)
	 */
	//setter
	@Override
	public void setFalling(Boolean falling) {
		this.falling = falling;
	}

	/* (non-Javadoc)
	 * @see com.tq2.interfaces.platform.Fall#setGravity(java.lang.Double)
	 */
	@Override
	public void setGravity(Double gravity) {
		this.gravity = gravity;
	}

	/* (non-Javadoc)
	 * @see com.tq2.interfaces.platform.Fall#setGravity(java.lang.Integer)
	 */
	@Override
	public void setGravity(Integer gravity) {
		this.gravity = gravity.doubleValue();
	}

	/* (non-Javadoc)
	 * @see com.tq2.interfaces.platform.Fall#setGravityAcceleration(java.lang.Double)
	 */
	@Override
	public void setGravityAcceleration(Double gravityAcceleration) {
		this.gravityAcceleration = gravityAcceleration;
	}
	
	/* (non-Javadoc)
	 * @see com.tq2.interfaces.platform.Fall#setGravityAcceleration(java.lang.Integer)
	 */
	@Override
	public void setGravityAcceleration(Integer gravityAcceleration) {
		this.gravityAcceleration = gravityAcceleration.doubleValue();
	}

	/**
	 * Sets the maximum velocity this object can reach along the X axis.
	 *
	 * @param maxVelX the new maximum velocity this object can reach along the X axis.
	 */
	public void setMaxVelX(Integer maxVelX) {
		this.maxVelX = maxVelX;
	}

	/* (non-Javadoc)
	 * @see com.tq2.interfaces.platform.Fall#setMaxVelY(java.lang.Integer)
	 */
	@Override
	public void setMaxVelY(Integer maxVelY) {
		this.maxVelY = maxVelY;
	}
	
	/* (non-Javadoc)
	 * @see com.tq2.interfaces.platform.Fall#resetGravityAcceleration()
	 */
	@Override
	public void resetGravityAcceleration() {
		this.setGravityAcceleration(this.defaultGravityAcceleration); 
	}
	
	
	/**
	 * Reset the maximum velocity this object can reach along the X axis to its original value.
	 */
	public void resetMaxVelX() {
		this.setMaxVelX(this.defaultMaxVelX);
	}
	
	/* (non-Javadoc)
	 * @see platform.entity.active.ActiveInterface#resetMaxVelY()
	 */
	@Override
	public void resetMaxVelY() {
		this.setMaxVelY(this.defaultMaxVelY);
	}
}
