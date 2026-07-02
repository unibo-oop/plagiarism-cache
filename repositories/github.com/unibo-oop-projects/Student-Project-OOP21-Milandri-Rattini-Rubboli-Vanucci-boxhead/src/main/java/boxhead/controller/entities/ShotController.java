package boxhead.controller.entities;

import java.util.Map;
import java.util.Optional;
import java.util.Set;

import boxhead.model.entities.gun.Shot;
import boxhead.model.entities.gun.ShotManager;
import boxhead.view.entities.ShotView;
import javafx.geometry.BoundingBox;

/**
 * Controller that manages the shots with {@link ShotManager} and {@link ShotView}.
 */
public interface ShotController {

	/**
	 * @return
	 * 			A set with all the active shots.
	 */
	Set<Shot> getShotsActive();
	
	/**
	 * @param obstacles
	 * 			A set with all the obstacles in the world with which the shots can crash.
	 */
	void setWalls(Set<BoundingBox> walls);
	
	/**
	 * @return
	 * 			A map with all the active shots mapped with their shotView.
	 */
	Map<Shot, ShotView> getShots();
	
	/**
	 * Used to add a new shot to the model and view.
	 * @param shot
	 */
	void addShot(Optional<Shot> shot);
	
	/**
	 * Method to update the deleted shots in {@link ShotManagerImpl} and update the map in {@link ShotControllerImpl}.
	 */
	void update();
}
