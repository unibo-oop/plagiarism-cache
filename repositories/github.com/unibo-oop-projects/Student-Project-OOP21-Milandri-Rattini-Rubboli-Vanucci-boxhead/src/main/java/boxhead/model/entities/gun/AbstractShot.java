package boxhead.model.entities.gun;

import boxhead.model.entities.AbstractEntity;
import boxhead.model.entities.EntityType;
import javafx.geometry.Point2D;

/**
 * Abstract shot that implements shot and that will be extended by Bullet
 */
public abstract class AbstractShot extends AbstractEntity implements Shot {
	
	private final int damage;

	public AbstractShot(Point2D position, EntityType entityType, final int damage) {
		super(position, entityType);
		this.damage = damage;
	}
	
	/**
	 * @return
	 * 			The trajectory of the shot
	 */
	public abstract Trajectory getTrajectory();

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void update() {
		this.getTrajectory().update();
		this.setPosition(this.getTrajectory().getCurrentPosition());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final boolean hasEnded() {
		return this.getTrajectory().hasEnded();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final int getDamage() {
		return this.damage;
	}
}
