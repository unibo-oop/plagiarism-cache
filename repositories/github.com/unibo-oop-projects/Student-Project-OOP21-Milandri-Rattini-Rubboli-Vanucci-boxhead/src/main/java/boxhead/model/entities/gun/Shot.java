package boxhead.model.entities.gun;

import boxhead.model.entities.Entity;

public interface Shot extends Entity {
	
	/**
	 * @return True if the shot has ended his trajectory.
	 */
	boolean hasEnded();
	
	/**
	 * @return The amount of damage the shot does.
	 */
	int getDamage();
	
	/**
	 * @return The trajectory of the shot.
	 */
	Trajectory getTrajectory();
	
	/**
	 * Method to update the shot.
	 */
	void update();
}
