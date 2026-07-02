package boxhead.model.entities.gun;

import boxhead.model.entities.EntityType;
import javafx.geometry.Point2D;

/**
 * Normal shot of {@link GunImpl}
 */
public class Bullet extends AbstractShot {
	
	private static final double HEIGHT = 2;
	private static final double WIDTH = 1;
	private final Trajectory trajectory;
	
	/**
	 * Constructor where the Bullet has a starting point and a target point.
	 * @param from
	 * 			The starting point.
	 * @param towards
	 * 			The target point
	 * @param damage
	 * 			The damage of the Bullet.
	 */
	public Bullet(final Point2D from, final Point2D towards, final int damage) {
		super(from, EntityType.BULLET, damage);
		this.setBoundingBox(HEIGHT, WIDTH);
		this.trajectory = new StraightTrajectory(from, towards);
	}
		
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Trajectory getTrajectory() {
		return this.trajectory;
	}
}
