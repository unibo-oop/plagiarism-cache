package model.entities;

import java.util.List;

/**
 * This interface is implemented by entity who can shoot.
 */
public interface Shooter {

    /**
     * @return list of the new bullets.
     */
    List<Bullet> shoot();

    /**
     * Getter for the rate of fire.
     * 
     * @return fire rate
     * 
     */
    int getFireRate();

    /**
     * Setter for the rate of fire.
     * 
     * @param fireRate to set.
     */
    void setFireRate(int fireRate);

    /**
     * @return true if the entity can shoot, otherwise false.
     */
    boolean canShoot();

}
