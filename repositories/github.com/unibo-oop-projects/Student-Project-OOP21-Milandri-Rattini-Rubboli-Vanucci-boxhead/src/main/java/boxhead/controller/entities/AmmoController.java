package boxhead.controller.entities;

import java.util.Map;
import java.util.Set;

import boxhead.model.entities.gun.Ammo;
import boxhead.view.entities.AmmoView;
import javafx.geometry.BoundingBox;

/**
 * Interface fot the AmmoController.
 */
public interface AmmoController {
	
	/**
	 * Method to retrieve the BoundingBox of the Active Ammos.
	 * @return Set of ammo's bounding box.
	 */
	Set<BoundingBox> getAmmos();
	
	/**
	 * Method to get all the Ammo's associated to their AmmoView.
	 * @return Map of Ammo and AmmoView.
	 */
	Map<Ammo, AmmoView> getAmmoView();
	
	/**
	 * Method to remove a single Ammo box when hit by the Player.
	 * @param ammo
	 */
	void removeAmmo(BoundingBox ammo);
	
	/**
	 * Method to update the inner logic.
	 */
	void update();
}
