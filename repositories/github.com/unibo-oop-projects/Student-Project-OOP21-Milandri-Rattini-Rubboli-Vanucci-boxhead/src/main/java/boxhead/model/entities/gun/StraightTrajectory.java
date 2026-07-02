package boxhead.model.entities.gun;

import javafx.geometry.Point2D;

/**
 * Represent the straight trajectory of the bullet
 */

public class StraightTrajectory implements Trajectory {

	private static final double DEFAULT_SPEED = 20;
	
	private double speed;
	private double angle;
	private Point2D position;
	private Point2D positionVariation;
		
	/**
	 * @param from
	 * 			The starting point of the trajectory
	 * @param towards
	 * 			The point where the trajectory is moving
	 */
	public StraightTrajectory(final Point2D from, final Point2D towards) {
		this.position = from;
		this.positionVariation = towards;
		this.setSpeed(DEFAULT_SPEED);
		this.calculateStep();
	}
	
	/**
	 * Used to calculate the next step
	 */
	private void calculateStep() {
		final double xStep, yStep;
		final double distX = this.positionVariation.getX() - this.position.getX();
		final double distY = this.positionVariation.getY() - this.position.getY();
		this.angle = Math.atan2(distY, distX);
		xStep = Math.cos(this.angle) * this.speed;
		yStep = Math.sin(this.angle) * this.speed;
		this.positionVariation = new Point2D(xStep, yStep);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final Point2D getCurrentPosition() {
		return this.position;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final double getAngle() {
		return this.angle;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final double getSpeed() {
		return this.speed;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final boolean hasEnded() {
		return false;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void update() {
		this.position = this.position.add(this.positionVariation);
	}
	
	/**
	 * Used to set the speed
	 * @param speed
	 * 			The speed of the trajectory
	 */
	public final void setSpeed(final double speed) {
		this.speed = speed;
	}
	
	public final Point2D getPositionVariation() {
		return this.positionVariation;
	}

}
