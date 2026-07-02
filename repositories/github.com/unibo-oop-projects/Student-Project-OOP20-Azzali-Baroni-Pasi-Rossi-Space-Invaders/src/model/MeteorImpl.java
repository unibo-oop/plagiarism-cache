package model;

import utility.Pair;

/**
 * The Class MeteorImpl manages the behaviors of each meteor
 */
public class MeteorImpl extends AbstractMeteor{
	
	/** The meteor life. */
	private int meteor_life=3;
	
	
	/**
	 * Instantiates a new meteor impl.
	 *
	 * @param position the position
	 * @param velocityX the velocity X
	 * @param velocityY the velocity Y
	 * @param Length the length
	 * @param id the id
	 */
	public MeteorImpl(Pair<Integer, Integer> position, int velocityX, int velocityY, int Length, ID id) {
		super(position, velocityX, velocityY, Length, id);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Update entity.
	 */
	@Override
	protected void updateEntity() {
		// TODO Auto-generated method stub
		if(((MeteorHitBox) (this.getHitbox())).isOutBorder()) {
			this.setDead();
		}
		
	}

	/**
	 * Collide.
	 *
	 * @param entity the entity
	 */
	@Override
	public void collide(Entity entity) {
		// TODO Auto-generated method stub
		this.meteor_life--;
		if(this.meteor_life <= 0) {
			this.setDead();
		}
	}
	


}
