package boxhead.model.entities.gun;

import java.util.Set;

import javafx.geometry.BoundingBox;

/**
 * The manager of all the shots in the game.
 */
public interface ShotManager {

	/**
	 * @param s
	 * 			Used to add a shot to the manager.
	 */
	void addShot(Shot s);
	
	/**
	 * @param s
	 * 			Used to remove a shot from the manager.
	 */
	void removeShot(Shot s);
	
	/**
	 * @return
	 * 			A set with all the shots active in the game.
	 */
	Set<Shot> getShotsActive();
	
	/**
	 * Method to update the inner logic.
	 */
	void update();
	
	/**
	 * @return
	 * 			A set with all the shots ended in the last reading.
	 */
	Set<Shot> getShotsEnded();
	
	/**
	 * @param obstacles
	 * 			A set with all the obstacles in the world with which the shots can crash.
	 */
	void setWalls(Set<BoundingBox> walls);
}
