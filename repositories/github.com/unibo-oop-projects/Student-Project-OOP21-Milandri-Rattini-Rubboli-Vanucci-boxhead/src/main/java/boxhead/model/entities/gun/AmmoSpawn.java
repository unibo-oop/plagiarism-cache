package boxhead.model.entities.gun;

import java.util.Set;

import javafx.geometry.Point2D;

/**
 * Interface to model a spawn of {@link Ammo}
 */
public interface AmmoSpawn {
	
	/**
	 * Method to get all ammos active.
	 * @return Set of all the ammos active.
	 */
	Set<Ammo> getAmmoActive();
	
	/**
	 * Method to set all the ammo spawn points.
	 * @param ammoSpawnPoints
	 */
	void setAmmoSpawnPoints(Set<Point2D> ammoSpawnPoints);
	
	/**
	 * Method to remove an ammo when is picked up.
	 * @param ammo
	 */
	void removeAmmo(Ammo ammo);
	
	/**
	 * Method to update the inner logic.
	 */
	void update();
}
