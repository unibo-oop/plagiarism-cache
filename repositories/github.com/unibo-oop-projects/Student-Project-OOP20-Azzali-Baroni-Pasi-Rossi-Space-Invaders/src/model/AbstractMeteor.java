package model;

import utility.Pair;

/**
 * The Class AbstractMeteor defines the general behaviors of meteors
 */
public abstract class AbstractMeteor extends EntityImpl implements Meteor{

	/**
	 * Instantiates a new abstract meteor.
	 *
	 * @param position the position
	 * @param velocityX the velocity X
	 * @param velocityY the velocity Y
	 * @param Length the length
	 * @param id the id
	 */
	public AbstractMeteor(final Pair<Integer,Integer> position,final int velocityX,final int velocityY,final int Length,final ID id) {
		super(position,velocityX,velocityY,id);
		this.setHitbox(new MeteorHitBox(position,Length));
	}
	
	/**
	 * Update.
	 */
	public void update() {
		this.getPosition().setY(this.getPosition().getY() + this.getSpeed().getY());
	    ((MeteorHitBox) (this.getHitbox())).updteHitBox(this.getPosition());
		this.updateEntity();
	}
	
	/**
	 * Update entity.
	 */
	protected abstract void updateEntity();

}
