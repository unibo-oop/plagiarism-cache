package tq2.implementations.entity.active.actor;

import java.util.Arrays;
import java.util.LinkedList;

import tq2.implementations.Id;
import tq2.implementations.entity.KanohiMask;
import tq2.implementations.entity.TrailEffect;
import tq2.implementations.entity.active.Fireball;
import tq2.interfaces.Entity;
import tq2.interfaces.Handler;
import tq2.interfaces.LevelLayer;
import tq2.interfaces.platform.tqgame.TQPlayer;

/**
 * The Class TQPlayerImpl, used to create the players of the game Toa's Quest 2.
 * 
 * @author Francesco Gori
 */
public  class TQPlayerImpl extends ActorImpl implements TQPlayer {
	
	/** How much the stamina decreases at each tick while the mask is active. */
	protected Double kanohiCost = 1.5;

	/** The list of masks. The first element of the list keeps the index of the mask that is active.
	 * If the player does not own a certain mask, the value of the associated cell is 0.
	 * If the player owns it but the mask is not active, the value is 1.
	 * If the player is using it, the value is 2.
	 * 
	 *  The masks are:
	 *  1 - Mask of shielding (Hau)
	 *  2 - Mask of levitation (Miru)
	 *  3 - Mask of speed (Kakama)*/
	protected LinkedList<Integer> kanohi;
	
	/** Whether the player is shooting. */
	protected Boolean shooting;
	
	/** The damage of a projectile shot by the player. */
	protected Integer shootDamage;
	
	/** The stamina cost of shooting a projectile. */
	protected Integer shootCost;
	
	/**
	 * Instantiates a new TQPlayerImpl.
	 *
	 * @param x the X coordinate
	 * @param y the Y coordinate
	 * @param width the width of the bounding box of the object
	 * @param height the height of the bounding box of the object
	 * @param facing the direction the object is facing
	 * @param solid whether the object is solid
	 * @param enabled whether the object is enabled
	 * @param scaleX the scale of the object along the X axis
	 * @param scaleY the scale of the object along the Y axis
	 * @param velX the velocity of the object along the X axis
	 * @param velY the velocity of the object along the Y axis
	 * @param alpha the alpha value of the object
	 * @param handler the Handler
	 * @param layer the layer the object resides in
	 * @param gravity the gravity the object is subject to
	 * @param maxVelX the maximum velocity the object can reach along the X axis
	 * @param maxVelY the maximum velocity the object can reach along the Y axis
	 * @param crouchedHeight the height of the object when crouched
	 * @param jumpStrength the strength of a jump of the object
	 * @param maxJumps the maximum number of jumps the object can perform mid-air
	 * @param hp the maximum amount of HP of the object
	 * @param stamina the maximum amount of stamina of the object
	 * @param staminaRecover how much stamina the object recovers at each tick
	 * @param runSpeed the speed the object reaches when running
	 * @param walkSpeed the the speed the object reaches when walking
	 * @param attackDamage the damage of an attack from the object
	 * @param attackRange the range of an attack of the object
	 * @param shootDamage the damage a projectile of the object can do
	 * @param shootCost the stamina cost of shooting a projectile
	 * @param areaWidth the width of the field of view of the object. If an Entity enters the area, a specific behavior can be triggered
	 * @param areaHeight the height of the field of view of the object. If an Entity enters the area, a specific behavior can be triggered
	 */
	public TQPlayerImpl (
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
			Integer shootDamage,
			Integer shootCost,
			Integer areaWidth,
			Integer areaHeight
			) {
		
		super(x, y, width, height, facing, solid, true, scaleX, scaleY, 0.0, 0.0, alpha, handler, layer, gravity, maxVelX, maxVelY, crouchedHeight, jumpStrength, maxJumps, hp, stamina, staminaRecover, runSpeed, walkSpeed, attackDamage, attackRange, areaWidth, areaHeight);

		this.id = Id.player;
		
		this.shootDamage = shootDamage;
		this.shooting = false;
		this.shootCost = shootCost;

		this.kanohi = new LinkedList<Integer>(Arrays.asList(0, 0, 0, 0, 0, 0, 0));
		}

	/* (non-Javadoc)
	 * @see com.tq2.implementations.entity.active.actor.ActorImpl#tick()
	 */
	@Override
	public void tick() {
		super.tick();
		
		//if the mask of shielding (Hau) is active, decrement the stamina value
		if (this.getKanohiList().get(1) == 2) {
			if (this.getStamina() >= kanohiCost) {
				this.addStamina(-kanohiCost);
			}
			else {
				this.deactivateKanohi();
			}
		}
		
		//if the mask of speed (Kakama) is active and the player is moving, decrement the stamina value,
		//add the trail effect and set a higher running speed
		if (this.getKanohiList().get(2) == 2) {

			if (this.getStamina() >= kanohiCost) {
				if (this.getVelX()!=0 && !this.isWalking()) {
					this.addStamina(-kanohiCost);
					this.getHandler().addEntity(new TrailEffect (this, this.getHandler()), this.layer().getIndex());
				}
				this.setRunSpeed(12);
				this.setMaxVelX(12);

			}
			else {
				this.deactivateKanohi();
			}
		}

		//if the mask of levitation (Miru) is active, decrement the stamina value.
		//if the stamina runs out, make the player slowly fall to the ground and prevent the stamina from regenerating.
		//prevent the mask from being activated again until the player touches the ground again
		if (this.getKanohiList().get(3) == 2) {
			if (this.getStamina() >= kanohiCost) {
				this.addStamina(-kanohiCost);
				
				if (this.getGravity() > 0) {
					this.setVelY(0);
					this.setGravityAcceleration(0);
					this.setGravity(0);
				}
			}
			else {
				if (this.isFalling()) {
					this.setMaxVelY(1);
					this.resetGravityAcceleration();
				}
				else {
					this.deactivateKanohi();


				}
			}
		}

		if (this.isShooting()) {
			if (this.animLoop != 0) {
				this.setShooting(false);

				//shoot a fireball in the direction of the player
				this.getHandler().addEntity(new Fireball (this.getX() + ((new Double(36*this.getScaleX()).intValue()) * this.getFacing()), this.getY() - ( new Double(22 * this.getScaleY()).intValue() ), this.getFacing(), shootDamage, 5, this.getHandler(), this.layer()), this.layer().getIndex());
			}
		}
	}

	/* (non-Javadoc)
	 * @see com.tq2.implementations.entity.active.actor.ActorImpl#tickAnim()
	 */
	@Override
	protected void tickAnim() {
		String anim;
		
		//if the HP is equal to 0, die
		if (this.getHp() <= 0) {
			anim = "Die";
		}
		
		//if the player is being hit
		else if (this.isHit()) {
			anim = "Hit";
		}
		
		//if the player is shooting mid-air
		else if (this.isShooting() && this.isFalling()) {
			anim = "Shoot Jump";
		}
		
		//if the player is attacking mid-air
		else if (this.isAttacking() && this.isFalling()) {			
			anim = "Attack Jump";
		}

		//if the player is using the mask of levitation (Miru)
		else if (this.getKanohiList().get(3) > 1 && this.isFalling()) {
			anim = "Miru";
		}
		
		//if the player is jumping mid-air
		else if (this.isJumping()) {
			if (this.getJumpCounter() > 1) {
		
				anim = "Double Jump";
			}
			//if the player is jumping
			else {
				anim = "Jump";
			}
		}

		//if the player is falling
		else if (this.isFalling()) {			
			anim = "Fall";
		}
		
		//if the player is attacking
		else if (this.isAttacking()) {			
			anim = "Attack";
		}
		
		//if the player is shooting
		else if (this.isShooting()) {
			anim = "Shoot";
		}
				
		//if the player is using the mask of shielding (Hau)
		else if (this.getKanohiList().get(1) == 2) {
			anim = "Hau";
		}
		
		//if the player is crouching
		else if (this.isCrouching()) {
			anim = "Crouch";
		}
		
		else if (this.getVelX() == 0) {
		
		//if the player is standing on the ground
			anim = "Idle";
		}
		else if (this.isWalking()) {

		//if the player is walking
			anim = "Walk";
		}
		
		//if the player is using the mask of speed (Kakama)
		else if (this.getKanohiList().get(2)==2) {
			
			anim = "Kakama";
		}

		//if the player is running
		else {
			anim = "Run";
		}
		
		if (this.getCurrentAnimation() != anim) {
			this.setCurrentAnimation(anim);
			this.frameCounter = 0;
		}
	}

	/* (non-Javadoc)
	 * @see com.tq2.implementations.entity.active.actor.ActorImpl#tickSound()
	 */
	@Override
	protected void tickSound() {
		
		//if the player is using the mask of speed (Kakama)
		if (this.getKanohiList().get(2) == 2 && !this.isFalling() && !this.isWalking() && this.getVelX() != 0) {
			this.loopSound("Kakama");
		}
		else {
			super.tickSound();
			this.stopSound("Kakama");
		}

		//if the player is using the mask of shielding (Hau)
		if (this.getKanohiList().get(1) == 2) {
			this.loopSound("Hau");
		}
		else {
			this.stopSound("Hau");
		}
	}

	/* (non-Javadoc)
	 * @see com.tq2.implementations.entity.active.ActiveImpl#collide(com.tq2.interfaces.Entity)
	 */
	protected void collide(Entity en) {
		if (en.getId() ==  Id.kanohiMask) {
			this.addKanohi(((KanohiMask)en).getMask());
			en.die();
		}
	}
	
	/* (non-Javadoc)
	 * @see com.tq2.implementations.entity.EntityImpl#die()
	 */
	@Override
	public void die() {
		this.resetMovementEnabled();
	}

	/* (non-Javadoc)
	 * @see com.tq2.implementations.entity.active.actor.ActorImpl#jump()
	 */
	@Override
	public void jump() {
		
		//if the mask of levitation (Miru) is not active
		if (this.getKanohiList().get(3) < 2 ) {
			super.jump();
		}
	}
	
	/* (non-Javadoc)
	 * @see platform.entity.active.actor.ShootInterface#shoot()
	 */
	@Override
	public void shoot() {
		if (this.getStamina() >= shootCost) {
			this.setShooting(true);
			this.addStamina(- shootCost);
			
			this.playSoundInstance("Fireball");
		}
	}

	/* (non-Javadoc)
	 * @see com.tq2.interfaces.platform.tqgame.KanohiUser#getActiveKanohi()
	 */
	@Override
	public Integer getActiveKanohi() {
		return this.getKanohiList().get(0);
	}
	

	/* (non-Javadoc)
	 * @see com.tq2.interfaces.platform.tqgame.KanohiUser#addKanohi(java.lang.Integer)
	 */
	@Override
	public void addKanohi(Integer mask) {
		this.getKanohiList().set(mask, 1);
	}
	

	/* (non-Javadoc)
	 * @see com.tq2.interfaces.platform.tqgame.KanohiUser#hasKanohi(java.lang.Integer)
	 */
	@Override
	public Boolean hasKanohi(Integer mask) {
		if (this.getKanohiList().size() > mask) {
			return (this.getKanohiList().get(mask) == 1);
		}
		else return false;
	}
	
	/* (non-Javadoc)
	 * @see com.tq2.interfaces.platform.tqgame.KanohiUser#setActiveKanohi(java.lang.Integer)
	 */
	@Override
	public void setActiveKanohi (Integer mask) {
		
		if (mask == 0) {
			this.deactivateKanohi();
		}
		else 
			if (this.getKanohiList().size() > mask) {
				if (this.getKanohiList().get(mask) == 1) {
				
				if (this.getStamina() >= kanohiCost * 15) {
					
					this.setStaminaRecover(0.0);
					
					switch(mask) {
					
					//if the actor is standing on the ground, activate the
					//Mask of shielding (Hau)
					case 1:
						if (!(this.isFalling() && velX==0)) {
							this.getKanohiList().set(0, mask);
							this.getKanohiList().set(mask, 2);
						}
						break;
					
					//if the actor is not falling, activate the
					//Mask of speed (Kakama)
					case 2:
						if (!(this.isFalling() )) {
							this.getKanohiList().set(0, mask);
							this.getKanohiList().set(mask, 2);
							this.resetMaxVelX();
							this.resetRunSpeed();
						}
						break;
					
					//if the actor is in the air, activate the
					//Mask of levitation (Miru)
					case 3:
						if (this.isFalling()) {
							this.getKanohiList().set(0, mask);
							this.getKanohiList().set(mask, 2);
						}
					}
				}
			}
			this.resetMovementEnabled();
		}
	}

	/* (non-Javadoc)
	 * @see com.tq2.interfaces.platform.tqgame.KanohiUser#deactivateKanohi()
	 */
	@Override
	public void deactivateKanohi () {
		
		//mask of shielding (Hau)
		if (this.getActiveKanohi() == 2) {
			this.resetRunSpeed();
		}
		
		//mask of levitation (Miru)
		if (this.getActiveKanohi() == 3) {
			this.resetMaxVelY();
			this.resetGravityAcceleration();
		}
		
		this.getKanohiList().set(this.getActiveKanohi(), 1);
		this.getKanohiList().set(0, 0);
		this.resetMovementEnabled();
		this.resetStaminaRecover();
	}
	
	/* (non-Javadoc)
	 * @see com.tq2.implementations.entity.active.actor.ActorImpl#resetMovementEnabled()
	 */
	@Override
	public void resetMovementEnabled() {
		this.movementEnabled = !(this.isCrouching() || ( (this.isAttacking() || this.isShooting() || (this.getKanohiList().get(0)==1)) && !this.isFalling() ) || (this.getHp()<= 0));
	}
	
	/* (non-Javadoc)
	 * @see com.tq2.interfaces.platform.Shoot#isShooting()
	 */
	@Override
	public Boolean isShooting() {
		return this.shooting;
	}
	
	/* (non-Javadoc)
	 * @see platform.entity.active.actor.ShootInterface#setShooting(java.lang.Boolean)
	 */
	@Override
	public void setShooting(Boolean shooting) {
		this.shooting = shooting;
		this.resetMovementEnabled();
	}

	/* (non-Javadoc)
	 * @see com.tq2.interfaces.platform.tqgame.KanohiUser#getKanohiList()
	 */
	@Override
	public LinkedList<Integer> getKanohiList() {
		return this.kanohi;
	}
}
