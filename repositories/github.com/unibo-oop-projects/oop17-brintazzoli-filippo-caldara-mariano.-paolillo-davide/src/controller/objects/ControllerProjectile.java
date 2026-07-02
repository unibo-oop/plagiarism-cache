package controller.objects;

/**
 * Interface to control {@link Projectile}.
 */
public interface ControllerProjectile {

    /**
     * Add to a map a new {@link Projectile} when the mouse is clicked.
     */
    void playerShot();

    /**
     * Update all the {@link Projectile} and check the collision.
     */
    void updateProjectiles();

}
