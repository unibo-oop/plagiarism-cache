package entity;

public interface Projectile extends Entity {

    /**
    * @return the direction in of the Projectile
    */
    double getDirection();

    /**
     * Notify the Projectile that as hit.
     * need to update the aliveness
     */
    void hit();

    /**
     * @return the amount of damage
     */
    int getDamage();

    /**
     * @return the Actor that generated him.
     */
    Actor getFather();
}
