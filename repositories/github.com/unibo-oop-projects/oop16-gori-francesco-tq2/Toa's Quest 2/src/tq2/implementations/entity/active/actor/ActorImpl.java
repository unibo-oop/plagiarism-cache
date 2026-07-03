package tq2.implementations.entity.active.actor;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import tq2.implementations.entity.AttackRectangle;
import tq2.implementations.entity.active.ActiveImpl;
import tq2.implementations.entity.tile.Tile;
import tq2.interfaces.*;
import tq2.interfaces.platform.Actor;
import tq2.interfaces.platform.Attack;
import tq2.interfaces.platform.Crouch;
import tq2.interfaces.platform.HP;
import tq2.interfaces.platform.Jump;
import tq2.interfaces.platform.Stamina;
import tq2.interfaces.platform.Walk;

/**
 * The Class ActorImpl.
 */
public abstract class ActorImpl extends ActiveImpl implements Actor, Jump, Walk, Crouch, Attack, Stamina, HP {
	
	
	/** The default maximum amount of HP of the actor. */
	protected Integer defaultMaxHp;
	
	/** The default maximum amount of stamina of the actor. */
	protected Integer defaultMaxStamina;
	
	/** The default value for the recovery of stamina for the actor. At each tick, the stamina recover value is added to the amount of current stamina*/
	protected Double defaultStaminaRecover;
	
	/** The default speed the actor reaches running. */
	protected Integer defaultRunSpeed;
	
	/** The default speed the actor reaches walking. */
	protected Integer defaultWalkSpeed;
	
	/** The default strength of a jump of the actor. */
	protected Double defaultJumpStrength;
	
	/** The default maximum number of jumps the actor can perform mid-air. */
	protected Integer defaultMaxJumps;
	
	/** The default damage an attack from the actor will do. */
	protected Integer defaultAttackDamage;
	
	/** The current amount of HP of the actor. */
	protected Integer hp;
	
	/** The maximum amount of HP of the actor. */
	protected Integer maxHp;
	
	/** The current amount of stamina of the actor. */
	protected Double stamina;
	
	/** The maximum amount of stamina of the actor. */
	protected Integer maxStamina;
	
	/** The amount of stamina that is added to the current stamina at each update. */
	protected Double staminaRecover;
	
	/** The height of the actor when crouched. */
	protected Integer crouchedHeight;
	
	/** The speed the actor reaches running. */
	protected Integer runSpeed;
	
	/** The speed the actor reaches walking. */
	protected Integer walkSpeed;
	
	/** The strength of a jump of the actor. */
	protected Double jumpStrength;
	
	/** The maximum number of jumps the actor can perform mid-air. */
	protected Integer maxJumps;
	
	/** The damage an attack from the actor will do. */
	protected Integer attackDamage;
	
	/** The range of an attack of this actor. */
	protected Integer attackRange;
		
	/** Whether the actor is jumping. */
	protected Boolean jumping;
	
	/** The counter of jumps the actor has performed since last touching the ground. */
	protected Integer jumpCounter;
	
	/** Whether the actor is walking. */
	protected Boolean walking;
	
	/** Whether the actor is crouching. */
	protected Boolean crouching;
	
	/** Whether the actor is attacking. */
	protected Boolean attacking;
	
	/** Whether the actor has been hit. */
	protected Boolean hit;
	
	/** Whether the movement is enabled. While it's disabled, the actor can still attack and crouch */
	protected Boolean movementEnabled;
	
	/** Whether the actor is running. */
	protected Boolean running;
	
	/** The width of the field of view of the actor. If an Entity enters this area, specific behaviors can be triggered */
	protected Integer areaWidth;
	
	/** The height of the field of view of the actor. If an Entity enters this area, specific behaviors can be triggered */
	protected Integer areaHeight;
	

	
	/**
	 * Instantiates a new actor impl.
	 *
	 * @param x the X coordinate
	 * @param y the Y coordinate
	 * @param width the width of the bounding box of the actor
	 * @param height the height of the bounding box of the actor
	 * @param facing the direction this actor is facing
	 * @param solid whether the actor collides with other solid Entities
	 * @param enabled whether the actor is enabled
	 * @param scaleX the scale of the actor along the X axis
	 * @param scaleY the scale of the actor along the Y axis
	 * @param velX the velocity of the actor along the X axis
	 * @param velY the velocity of the actor along the Y axis
	 * @param alpha the alpha value of the actor
	 * @param handler the Handler
	 * @param layer the layer the actor resides in
	 * @param gravity the gravity this actor is subject to
	 * @param maxVelX the maximum velocity of the actor along the X axis
	 * @param maxVelY the maximum velocity of the actor along the Y axis
	 * @param crouchedHeight the height of the actor when crouched
	 * @param jumpStrength the strength of a jump of the actor
	 * @param maxJumps the maximum number of jumps the actor can perform mid-air
	 * @param hp the amount of HP
	 * @param stamina the amount of stamina
	 * @param staminaRecover amount of stamina that is added to the current stamina at each update.
	 * @param runSpeed the speed the actor reaches when running
	 * @param walkSpeed the speed the actor reaches when walking
	 * @param attackDamage the damage an attack from the actor will do
	 * @param attackRange the range of an attack of the actor
	 * @param areaWidth the width of the field of view of the actor. If an Entity enters this area, specific behaviors can be triggered
	 * @param areaHeight the height of the field of view of the actor. If an Entity enters this area, specific behaviors can be triggered
	 */
	public ActorImpl (
			Integer x, Integer y,
			Integer width, Integer height,
			Integer facing,
			Boolean solid,
			Boolean enabled,
			Double scaleX, Double scaleY,
			Double velX, Double velY,
			Float alpha, Handler handler, LevelLayer layer,
			Double gravity,
			Integer maxVelX, Integer maxVelY,
			Integer crouchedHeight,
			Double jumpStrength, Integer maxJumps,
			Integer hp,
			Integer stamina,
			Double staminaRecover,
			Integer runSpeed,
			Integer walkSpeed,
			Integer attackDamage,
			Integer attackRange,
			Integer areaWidth,
			Integer areaHeight
			) {
			
		
		super(x, y, width, height, facing, solid, enabled, scaleX, scaleY, velX, velY, alpha, handler, layer, gravity, maxVelX, maxVelY);
		
		
		this.defaultMaxHp = maxHp;
		this.defaultMaxStamina = maxStamina;
		this.defaultStaminaRecover = staminaRecover;
		this.defaultRunSpeed = runSpeed;
		this.defaultWalkSpeed = walkSpeed;
		this.defaultJumpStrength = jumpStrength;
		this.defaultMaxJumps = maxJumps;
		this.defaultAttackDamage = attackDamage;
		
		
		this.hp = hp;
		this.maxHp = hp;
		this.stamina = stamina.doubleValue();
		this.maxStamina = stamina;
		this.staminaRecover = staminaRecover;
		
		this.crouchedHeight = crouchedHeight;
		this.runSpeed = runSpeed;
		this.walkSpeed = walkSpeed;
		this.jumpStrength = jumpStrength;
		this.maxJumps = maxJumps;
		this.attackDamage = attackDamage;
		this.attackRange = attackRange;
		
		this.jumping = false;
		this.jumpCounter = 1;
		this.walking = false;
		this.crouching = false;
		this.attacking = false;
		this.hit = false;
		this.movementEnabled = true;
		
		this.running = false;
		
		this.areaWidth = areaWidth;
		this.areaHeight = areaHeight;
	}
	
	/* (non-Javadoc)
	 * @see com.tq2.interfaces.Entity#render(java.awt.Graphics2D)
	 */
	@Override
	public void render(Graphics2D g) {

        g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
        
		this.playAnimation(g, this.getCurrentAnimation());
		
        g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));

	
	}
	
	/* (non-Javadoc)
	 * @see com.tq2.implementations.entity.active.ActiveImpl#tick()
	 */
	@Override
	public void tick() {
		
		super.tick();
		
		if(this.isRunning()) {
			if(this.isWalking()) {
				this.setVelX(this.getWalkSpeed()*this.getFacing());
			}
			else {
				this.setVelX(this.getRunSpeed()*this.getFacing());
			}
		}
		
		//The actor can't cross window borders
		if (this.getX() <= 0) this.setX(0);
		if (this.getY() - this.getHeight() <= 0) this.setY(this.getHeight());

		if (this.getX() + this.getWidth() >= this.getHandler().getGame().getCurrentLevel().getWidth()) {
			this.setX(this.getHandler().getGame().getCurrentLevel().getWidth() - this.getWidth());
		}
		if (this.getY() >= this.getHandler().getGame().getCurrentLevel().getHeight()) {
			this.setY(this.getHandler().getGame().getCurrentLevel().getHeight());
		}
		
		if (!this.movementEnabled && !this.isFalling()) {
			this.setRunning(false);
		}
		
		//if the actor is jumping but it's starting to fall, set the flag "jumping" to false
		if (this.isJumping()) {
			if ((this.getVelY() + this.getGravity()) > 0.5) {
				this.setJumping(false);
			}
		}
		
		//if the actor is standing on ground, reset the jump counter
		if (!this.isFalling() && !this.isJumping()) {
			this.setJumpCounter(0);
		}
		
		//if the animation "Attack" is over, set the flag "attacking" to false
		if (this.isAttacking() && (this.animLoop > 0)) {
			this.setAttacking(false);
		}
		
		//if the animation "Hit" is over, set the flag "hit" to false
		if (this.isHit()) {
			if (this.animLoop >= 2) {
				this.setHit(false);
			}
		}
		
		//if the current amount of HP is equal to 0, make the actor die
		if (this.getHp() <= 0) {
			this.die();
		}
		
		//at each update, add the "staminaRecover" value to that of the current stamina
		this.addStamina(this.staminaRecover);
		
		//make the actor play the sounds he's supposed to, given its current status
		tickSound();
		
	}
	
	/**
	 * Gets the width of the field of view of the actor. If an Entity enters this area, specific behaviors can be triggered.
	 *
	 * @return the width of the area
	 */
	protected Integer getAreaWidth() {
		return this.areaWidth;
	}
	
	/**
	 * Gets the height of the field of view of the actor. If an Entity enters this area, specific behaviors can be triggered.
	 *
	 * @return the height of the area
	 */
	protected Integer getAreaHeight() {
		return this.areaHeight;
	}
	
	
	/**
	 * Returns the rectangle of the field of view of the actor. If an Entity enters this area, specific behaviors can be triggered.
	 *
	 * @return the area
	 */
	protected Rectangle getArea() {
		return new Rectangle (this.getX() + (this.getWidth() - this.getAreaWidth())/2 + this.getVelX().intValue(), this.getY() + this.getVelY().intValue() - this.getAreaHeight(), this.getAreaWidth(), this.getAreaHeight());
	}
	
	/* (non-Javadoc)
	 * @see com.tq2.implementations.entity.EntityImpl#getBounds()
	 */
	@Override
	public Rectangle getBounds() {
		
		//if the object is crouching, the height of the bounding box of the actor will be equal to "crouchedHeight"
		Rectangle bounds;
		if (this.isCrouching()) {
			bounds = new Rectangle(this.getX(), this.getY() - this.getCrouchedHeight(), this.getWidth(), this.getCrouchedHeight());
		}
		//otherwise, the height will be the normal one
		else {
			bounds = super.getBounds();
		}
		
		return bounds;
	}
	
	/* (non-Javadoc)
	 * @see com.tq2.implementations.entity.active.ActiveImpl#getBoundsTop()
	 */
	@Override
	public Rectangle getBoundsTop() {
		
		//if the object is crouching, the height of the bounding box's top side will be equal to "crouchedHeight"
		Rectangle bounds;
		if (this.isCrouching()) {
			bounds = new Rectangle(this.getX() + 5, this.getY() - this.getCrouchedHeight(), this.getWidth() - 10, 1);	
		}
		//otherwise, the height will be the normal one
		else {
			bounds = super.getBoundsTop();
		}
		
		return bounds;
	}
	
	/* (non-Javadoc)
	 * @see com.tq2.implementations.entity.active.ActiveImpl#getBoundsLeft()
	 */
	@Override
	public Rectangle getBoundsLeft() {
		//if the object is crouching, the height of the bounding box's left side will be equal to "crouchedHeight"
		Rectangle bounds;
		if (this.isCrouching()) {
			bounds = new Rectangle(this.getX(), this.getY() - this.getCrouchedHeight() + 5, 1, this.getCrouchedHeight() - 10);
		}
		//otherwise, the height will be the normal one
		else {
			bounds = super.getBoundsLeft();
		}
		
		return bounds;
	}
	
	/* (non-Javadoc)
	 * @see com.tq2.implementations.entity.active.ActiveImpl#getBoundsRight()
	 */
	@Override
	public Rectangle getBoundsRight() {
		//if the object is crouching, the height of the bounding box's right side will be equal to "crouchedHeight"
		Rectangle bounds;
		if (this.isCrouching()) {
			bounds = new Rectangle(this.getX() + this.getWidth() - 1,  this.getY() - this.getCrouchedHeight() + 5, 1, this.getCrouchedHeight() - 10);
		}
		//otherwise, the height will be the normal one
		else {
			bounds = super.getBoundsRight();
		}
		
		return bounds;
	}
	
	/**
	 * Triggers the behavior of the actor when an Entity of that type enters his field of view.
	 *
	 * @param en the Entity
	 */
	protected void isCloseTo(Entity en) {
	}
	
	/* (non-Javadoc)
	 * @see com.tq2.implementations.entity.active.ActiveImpl#collideTop(com.tq2.interfaces.Entity)
	 */
	@Override
	public void collideTop (Entity en) {
		super.collideTop(en);
		//if the actor is jumping and hits an obstacle with its top side, the actor will start falling
		if (en instanceof Tile) {
			if (this.isJumping()) {
				this.setJumping(false);
			}
		}

	}

	/**
	 * Make the actor run.
	 */
	public void run() {
		this.setRunning(true);
	}
	
	/**
	 * Checks if the actor is running.
	 *
	 * @return Whether the actor is running
	 */
	public Boolean isRunning() {
		return this.running;
	}
	
	/**
	 * Sets if the actor is running.
	 *
	 * @param running Whether the actor is running
	 */
	public void setRunning(Boolean running) {
		this.running = running && this.isMovementEnabled();
		if (!running) {
			this.setVelX(0);
		}
	}
	
	/* (non-Javadoc)
	 * @see platform.entity.active.actor.ActorInterface#jump()
	 */
	@Override
	public void jump() {
				
		//if the actor hasn't jumped enough times already
		if (this.getJumpCounter() < this.getMaxJumps()) {

			this.setJumpCounter(this.getJumpCounter() + 1);
			this.setGravity(- this.getJumpStrength());
			this.setJumping(true);
		}
	}

	/* (non-Javadoc)
	 * @see platform.entity.active.actor.ActorInterface#attack()
	 */
	@Override
	public void attack() {
		if (!(this.isHit() && this.animLoop <= 1) && !this.isAttacking() ) {
			//if the object is already attacking and the animation is over, restart it
			if (this.currentAnimation == "Attack" && this.animLoop > 1) {
				this.frameCounter = 0;
				this.animLoop = 0;
			}
			this.setAttacking(true);
			//add the invisible rectangle that will hurt enemies that collide with it
			this.getHandler().addEntity(new AttackRectangle (this, this.attackRange, 20, this.getHandler(), this.layer()), this.layer().getIndex());
			this.playSound("Attack");
		}
	}
	
	/* (non-Javadoc)
	 * @see com.tq2.implementations.entity.active.ActiveImpl#tickAnim()
	 */
	@Override
	protected void tickAnim() {
		String anim;
		
		if (this.getHp() <= 0) {
		//It's dying
			anim = "Die";
		}
		
		else if (this.isHit()) {
		//It's been hit
			anim = "Hit";
		}
		
		else if (this.isAttacking() && this.isFalling()) {			
		//It's attacking in the air
			anim = "Attack Jump";
		}
		
		else if (this.isJumping()) {
			if (this.getJumpCounter() > 1) {
		//It's jumping in the air
				anim = "Double Jump";
			}
			else {
		//It's jump
				anim = "Jump";
			}
		}
		
		else if (this.isFalling()) {
		//It's falling
			anim = "Fall";
		}
		
		else if (this.isAttacking()) {			
		//It's attacking
			anim = "Attack";
		}
		
		else if (this.isCrouching()) {
		//It's crouching
			anim = "Crouch";
		}
		
		else if (!this.isRunning()) {
		//It's standing on the ground
			anim = "Idle";
		}
		else if (this.isWalking()) {
		//It's walking
			anim = "Walk";
		}
		
		else {
		//It's running
			anim = "Run";
		}
		
		if (this.getCurrentAnimation() != anim) {
			this.setCurrentAnimation(anim);
			this.frameCounter = 0;
		}
		
	}
	
	/**
	 * Make the actor play the sound he's supposed to given his current status.
	 */
	protected void tickSound() {
		//the actor is running
		if (!this.isFalling() && this.getVelX() != 0 && !this.isWalking()) {
			this.loopSound("Run");
		}
		//the actor is not running
		else {
			this.stopSound("Run");
		}
		
		//the actor is walking
		if (!this.isFalling() && this.getVelX() != 0 && this.isWalking()) {
			this.loopSound("Walk");
		}
		//the actor is not walking
		else {
			this.stopSound("Walk");
		}
	}
	
	/* (non-Javadoc)
	 * @see com.tq2.implementations.entity.active.ActiveImpl#testCollision()
	 */
	@Override
	public void testCollision() {
		//if this actor is solid
		if (this.solid) {
			Boolean falling = true;
			
			//for each other Entity on this layer
			for (int i=0; i< this.layer().size(); i++) {
				Entity en = this.layer().get(i);
				
				if (en.isSolid()) {
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
						
						if (this.getArea().intersects(en.getBounds())) {
							this.isCloseTo(en);
						}
					}
				}
			
			//if the object collided with any solid Entity from the bottom, it will stop falling 
			this.setFalling(falling);
		}
	}
	
	/* (non-Javadoc)
	 * @see platform.entity.active.actor.ActorInterface#getHp()
	 */
	@Override
	public Integer getHp() {
		return this.hp;
	}
	
	/* (non-Javadoc)
	 * @see platform.entity.active.actor.ActorInterface#getMaxHp()
	 */
	@Override
	public Integer getMaxHp() {
		return this.maxHp;
	}
	
	/* (non-Javadoc)
	 * @see platform.entity.active.actor.ActorInterface#getStamina()
	 */
	@Override
	public Integer getStamina() {
		return this.stamina.intValue();
	}
	
	/* (non-Javadoc)
	 * @see platform.entity.active.actor.ActorInterface#getMaxStamina()
	 */
	@Override
	public Integer getMaxStamina() {
		return this.maxStamina;
	}
	
	/* (non-Javadoc)
	 * @see platform.entity.active.actor.ActorInterface#getRunSpeed()
	 */
	@Override
	public Integer getRunSpeed() {
		return this.runSpeed;
	}
	
	/* (non-Javadoc)
	 * @see platform.entity.active.actor.ActorInterface#getWalkSpeed()
	 */
	@Override
	public Integer getWalkSpeed() {
		return this.walkSpeed;
	}
	
	/* (non-Javadoc)
	 * @see platform.entity.active.actor.ActorInterface#getAttackDamage()
	 */
	@Override
	public Integer getAttackDamage() {
		return this.attackDamage;
	}
	
	/* (non-Javadoc)
	 * @see platform.entity.active.actor.ActorInterface#getCrouchedHeight()
	 */
	@Override
	public Integer getCrouchedHeight() {
		return new Double(this.crouchedHeight * this.getScaleY()).intValue();
	}
		
	/* (non-Javadoc)
	 * @see platform.entity.active.actor.ActorInterface#isJumping()
	 */
	@Override
	public Boolean isJumping() {
		return jumping;
	}
	
	/* (non-Javadoc)
	 * @see platform.entity.active.actor.ActorInterface#getJumpStrength()
	 */
	@Override
	public Double getJumpStrength() {
		return this.jumpStrength;
	}

	/* (non-Javadoc)
	 * @see platform.entity.active.actor.ActorInterface#getMaxJumps()
	 */
	@Override
	public Integer getMaxJumps() {
		return maxJumps;
	}

	/* (non-Javadoc)
	 * @see platform.entity.active.actor.ActorInterface#getJumpCounter()
	 */
	@Override
	public Integer getJumpCounter() {
		return jumpCounter;
	}
	
	/* (non-Javadoc)
	 * @see platform.entity.active.actor.ActorInterface#isWalking()
	 */
	@Override
	public Boolean isWalking() {
		return this.walking;
	}
	
	/* (non-Javadoc)
	 * @see platform.entity.active.actor.ActorInterface#isCrouching()
	 */
	@Override
	public Boolean isCrouching() {
		return this.crouching;
	}
	
	/* (non-Javadoc)
	 * @see platform.entity.active.actor.ActorInterface#isAttacking()
	 */
	@Override
	public Boolean isAttacking() {
		return this.attacking;
	}
	
	/* (non-Javadoc)
	 * @see platform.entity.active.actor.ActorInterface#isHit()
	 */
	@Override
	public Boolean isHit() {
		return this.hit;
	}
	
	/* (non-Javadoc)
	 * @see platform.entity.active.actor.ActorInterface#isMovementEnabled()
	 */
	@Override
	public Boolean isMovementEnabled() {
		return this.movementEnabled;
	}

	/* (non-Javadoc)
	 * @see platform.entity.active.actor.ActorInterface#setStamina(java.lang.Integer)
	 */
	@Override
	public void setStamina(Integer stamina) {
		this.stamina = stamina.doubleValue();
	}
	
	/* (non-Javadoc)
	 * @see platform.entity.active.actor.ActorInterface#setStamina(java.lang.Double)
	 */
	@Override
	public void setStamina(Double stamina) {
		this.stamina = stamina;
	}
	
	/* (non-Javadoc)
	 * @see platform.entity.active.actor.ActorInterface#setMaxStamina(java.lang.Integer)
	 */
	@Override
	public void setMaxStamina(Integer maxStamina) {
		this.maxStamina = maxStamina;
	}
	
	/* (non-Javadoc)
	 * @see platform.entity.active.actor.ActorInterface#addStamina(java.lang.Integer)
	 */
	@Override
	public void addStamina(Integer points) {
		this.stamina += points;
	}
	
	/* (non-Javadoc)
	 * @see platform.entity.active.actor.ActorInterface#addStamina(java.lang.Double)
	 */
	@Override
	public void addStamina(Double points) {
		this.setStamina( Math.min(this.getMaxStamina(),  Math.max(0, this.stamina += points)) );
	}
	
	/* (non-Javadoc)
	 * @see platform.entity.active.actor.ActorInterface#setStaminaRecover(java.lang.Double)
	 */
	@Override
	public void setStaminaRecover (Double staminaRecover) {
		this.staminaRecover = staminaRecover;
	}
	
	/* (non-Javadoc)
	 * @see platform.entity.active.actor.ActorInterface#setHp(java.lang.Integer)
	 */
	@Override
	public void setHp(Integer hp) {
		this.hp = Math.min(this.getMaxHp(), Math.max(0, hp));
	}
	
	/* (non-Javadoc)
	 * @see platform.entity.active.actor.ActorInterface#setMaxHp(java.lang.Integer)
	 */
	@Override
	public void setMaxHp (Integer maxHp) {
		this.maxHp = maxHp;
		//if the current amount of HP is greater than the new maximum value, it will be reduced
		this.setHp(this.getHp());
	}
	
	/* (non-Javadoc)
	 * @see platform.entity.active.actor.ActorInterface#addHp(java.lang.Integer)
	 */
	@Override
	public void addHp(Integer points) {
		if (points < 0 && this.getHp() > 0) {
			this.setHit(true);
		}
		this.setHp(this.getHp() + points);

	}
	
	/* (non-Javadoc)
	 * @see platform.entity.active.actor.ActorInterface#setRunSpeed(java.lang.Integer)
	 */
	@Override
	public void setRunSpeed(Integer runSpeed) {
		this.runSpeed = Math.min(runSpeed, this.getMaxVelX());
//		this.runSpeed = runSpeed;
	}
	
	/* (non-Javadoc)
	 * @see platform.entity.active.actor.ActorInterface#setWalkSpeed(java.lang.Integer)
	 */
	@Override
	public void setWalkSpeed(Integer walkSpeed) {
		this.walkSpeed = Math.min(walkSpeed, this.getMaxVelX());
//		this.walkSpeed = walkSpeed;
	}
	
	/* (non-Javadoc)
	 * @see platform.entity.active.actor.ActorInterface#setJumpStrength(java.lang.Double)
	 */
	@Override
	public void setJumpStrength(Double jumpStrength) {
		this.jumpStrength = jumpStrength;
	}
		
	/* (non-Javadoc)
	 * @see platform.entity.active.actor.ActorInterface#setJumpStrength(java.lang.Integer)
	 */
	@Override
	public void setJumpStrength(Integer jumpStrength) {
		this.jumpStrength = jumpStrength.doubleValue();
	}
	
	/* (non-Javadoc)
	 * @see platform.entity.active.actor.ActorInterface#setMaxJumps(java.lang.Integer)
	 */
	@Override
	public void setMaxJumps(Integer maxJumps) {
		this.maxJumps = maxJumps;
	}
	
	/* (non-Javadoc)
	 * @see platform.entity.active.actor.ActorInterface#setAttackDamage(java.lang.Integer)
	 */
	@Override
	public void setAttackDamage(Integer attackDamage) {
		this.attackDamage = attackDamage;
	}
	
	/* (non-Javadoc)
	 * @see platform.entity.active.actor.ActorInterface#setCrouchedHeight(java.lang.Integer)
	 */
	@Override
	public void setCrouchedHeight (Integer crouchedHeight) {
		this.crouchedHeight = crouchedHeight;
	}
	
	/* (non-Javadoc)
	 * @see platform.entity.active.actor.ActorInterface#setJumping(java.lang.Boolean)
	 */
	@Override
	public void setJumping(Boolean jumping) {
		this.jumping = jumping;
	}
	
	/* (non-Javadoc)
	 * @see platform.entity.active.actor.ActorInterface#setWalking(java.lang.Boolean)
	 */
	@Override
	public void setWalking (Boolean walking) {
		this.walking = walking;
	}
	
	/* (non-Javadoc)
	 * @see platform.entity.active.actor.ActorInterface#setCrouching(java.lang.Boolean)
	 */
	@Override
	public void setCrouching(Boolean crouching) {
		if ( (crouching == true && !this.isFalling()) || crouching == false) {
			this.crouching = crouching;
		}
		//if the actor is crouching, the movement will be disabled. Otherwise, they will be enabled again (unless there's another reason for them to be disabled)
		this.resetMovementEnabled();
	}
	
	/* (non-Javadoc)
	 * @see platform.entity.active.actor.ActorInterface#setAttacking(java.lang.Boolean)
	 */
	@Override
	public void setAttacking(Boolean attacking) {
		this.attacking = attacking;
		//if the actor is crouching, the movement will be disabled. Otherwise, they will be enabled again (unless there's another reason for them to be disabled)
		resetMovementEnabled();
	}
	
	/* (non-Javadoc)
	 * @see platform.entity.active.actor.ActorInterface#setHit(java.lang.Boolean)
	 */
	@Override
	public void setHit(Boolean hit) {
		this.hit = hit;
		
		//if the actor has been hit, play the sound
		if (hit) {
			this.playSound("Hit");
		}
	}

	/* (non-Javadoc)
	 * @see platform.entity.active.actor.ActorInterface#resetMovementEnabled()
	 */
	@Override
	public void resetMovementEnabled() {
		this.movementEnabled = !(this.isCrouching() || ( (this.isAttacking() ) && !this.isFalling()  || (this.getHp()<= 0) ) );
	}

	/* (non-Javadoc)
	 * @see platform.entity.active.actor.ActorInterface#setJumpCounter(java.lang.Integer)
	 */
	@Override
	public void setJumpCounter(Integer jumpCounter) {

		if (this.getJumpCounter()!=jumpCounter) {
			this.jumpCounter = jumpCounter;
			//if the actor is hitting the ground, play the sound
			if (jumpCounter == 0) {
				this.playSoundInstance("Hit ground");
			}
		}
	}
	
	/* (non-Javadoc)
	 * @see platform.entity.active.actor.ActorInterface#resetMaxHp()
	 */
	@Override
	public void resetMaxHp() {
		this.setMaxHp(this.defaultMaxHp); 
	}
	
	/* (non-Javadoc)
	 * @see platform.entity.active.actor.ActorInterface#resetMaxStamina()
	 */
	@Override
	public void resetMaxStamina() {
		this.setMaxStamina(this.defaultMaxStamina); 
	}
	
	/* (non-Javadoc)
	 * @see platform.entity.active.actor.ActorInterface#resetStaminaRecover()
	 */
	@Override
	public void resetStaminaRecover() {
		this.setStaminaRecover(this.defaultStaminaRecover); 
	}
	
	/* (non-Javadoc)
	 * @see platform.entity.active.actor.ActorInterface#resetRunSpeed()
	 */
	@Override
	public void resetRunSpeed() {
		this.setRunSpeed(this.defaultRunSpeed);
	}
	
	/* (non-Javadoc)
	 * @see platform.entity.active.actor.ActorInterface#resetWalkSpeed()
	 */
	@Override
	public void resetWalkSpeed() {
		this.setWalkSpeed(this.defaultWalkSpeed);
	}
	
	/* (non-Javadoc)
	 * @see platform.entity.active.actor.ActorInterface#resetJumpStrength()
	 */
	@Override
	public void resetJumpStrength() {
		this.setJumpStrength(this.defaultJumpStrength);
	}
	
	/* (non-Javadoc)
	 * @see platform.entity.active.actor.ActorInterface#resetMaxJumps()
	 */
	@Override
	public void resetMaxJumps() {
		this.setMaxJumps(this.defaultMaxJumps);
	}
	
	/* (non-Javadoc)
	 * @see platform.entity.active.actor.ActorInterface#resetAttackDamage()
	 */
	@Override
	public void resetAttackDamage() {
		this.setAttackDamage(this.defaultAttackDamage);
	}
	
}