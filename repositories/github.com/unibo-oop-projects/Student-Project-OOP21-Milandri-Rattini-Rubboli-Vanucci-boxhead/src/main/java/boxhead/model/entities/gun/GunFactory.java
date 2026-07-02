package boxhead.model.entities.gun;

import java.time.Duration;

import boxhead.model.entities.gun.Gun.GunType;
import javafx.geometry.Point2D;

/**
 * Factory template to create specific types of guns
 */
public class GunFactory {
	
	private static final int PISTOL_DAMAGE = 30;
    private static final long PISTOL_RATE_OF_FIRE = 800;
    private static final int PISTOL_MAGAZINE = 15;

    private static final int UZI_DAMAGE = 25;
    private static final long UZI_RATE_OF_FIRE = 350;
    private static final int UZI_MAGAZINE = 25;
    
    private static final int SHOTGUN_DAMAGE = 50;
    private static final long SHOTGUN_RATE_OF_FIRE = 1000;
    private static final int SHOTGUN_MAGAZINE = 10;
    
    /**
     * Main method to get a gun, specified by {@link GunType}
     * @param position
     * 			Starting position of the gun
     * @param type
     * 			Specific type of the gun
     * @return
     * 			The method to create the specific gun requested
     */
    public final Gun getGun(final Point2D position, final GunType type) {
    	switch (type) {
    	case PISTOL:
    		return this.getPistol(position);
    	case UZI:
    		return this.getUzi(position);
    	case SHOTGUN:
    		return this.getShotgun(position);
    	default:
    		return this.getPistol(position);
    	}
    }
    
    /**
     * Methods to get the specific {@link GunType}
     * @param position
     * 			Starting position of the gun
     * @return
     * 			The specific gun requested
     */
    private Gun getPistol(final Point2D position) {
		return new GunImpl.Builder(position, GunType.PISTOL, "Pistol")
						  .damage(PISTOL_DAMAGE)
						  .rateOfFire(Duration.ofMillis(PISTOL_RATE_OF_FIRE))
						  .magazineSize(PISTOL_MAGAZINE)
						  .build();
    }
    
    private Gun getUzi(final Point2D position) {
    	return new GunImpl.Builder(position, GunType.UZI, "Uzi")
				  		  .damage(UZI_DAMAGE)
				  		  .rateOfFire(Duration.ofMillis(UZI_RATE_OF_FIRE))
				  		  .magazineSize(UZI_MAGAZINE)
				  		  .build();
    }
    
    private Gun getShotgun(final Point2D position) {
    	return new GunImpl.Builder(position, GunType.SHOTGUN, "Shotgun")
				  		  .damage(SHOTGUN_DAMAGE)
				  		  .rateOfFire(Duration.ofMillis(SHOTGUN_RATE_OF_FIRE))
				  		  .magazineSize(SHOTGUN_MAGAZINE)
				  		  .build();
    }
}
