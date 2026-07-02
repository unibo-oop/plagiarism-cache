package boxhead.model.entities.gun;

import java.util.Optional;
import java.util.Set;

import boxhead.model.entities.Entity;
import boxhead.model.entities.utils.Direction;
import javafx.geometry.Point2D;

/**
 * Represents a generic gun that creates a {@link Shot}
 */

public interface Gun extends Entity{

	/**
	 * Used to separate Gun types
	 */
	enum GunType {
		/**
		 * Pistol has low ammos and low rateOfFire
		 */
		PISTOL("Pistol"),
		/**
		 * Uzi has more ammos and higher rateOfFire
		 */
		UZI("Uzi"),
		/**
		 * Shotgun has highdamage and different type of shot
		 */
		SHOTGUN("Shotgun");
		
		private String name;
		
		GunType(final String name) {
			this.name = name;
		}
		
		/**
		 * @return
		 * 		The name of the gun
		 */
		public String getName() {
			return this.name;
		}
	}
	
	/**
	 * @return Gun's name
	 */
	String getName();
	
	/**	
	 * @return
	 * 			The shot shot		
	 */
	Set<Optional<Shot>> attack(Point2D pos, Direction direction);
	
	/**
	 * @return
	 * 			The amount of damage of the shot
	 */
	int getDamage();
	
	/**
	 * Method to get the magazine size of the gun
	 * @return
	 */
	int getMagazineSize();
	
	/**
	 * @return
	 * 			The amount of ammo remaining
	 */
	int getCurrentAmmo();
	
	/**
	 * Method to recharge gun's ammos
	 * @param ammo
	 */
	void rechargeAmmo(int ammo);
	
	/**
	 * @return
	 * 			The type of the weapon
	 */
	GunType getGunType();
	
	/**
	 * Used to upgrade the gun's damage
	 * @param newDamage
	 */
	void updateDamage(int newDamage);
	
	/**
	 * Used to upgrade the gun's rate of fire
	 * @param newRate
	 */
	void updateRateOfFire(long newRate);
	
	/**
	 * Used to upgrade the gun's magazine size
	 * @param newSize
	 */
	void updateMagazine(int newSize);
}
