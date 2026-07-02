package controller.collision;

import controller.utility.CheckIntersection;
import java.util.List;
import java.util.stream.Collectors;

import model.object.Projectile;
import model.object.Tank;

/**
 * Concrete implementation of {@link Collision} interface.
 */
public class TankWithProjectile implements Collision {

    private static final int DAMAGE = 1;
    private final Tank playerTank;
    private final Tank enemyTank;
    private final List<Projectile> projectiles;

    /**
     * Constructor.
     * 
     * @param playerTank
     *            the player {@link Tank}.
     * @param enemyTank
     *            the enemy {@link Tank}.
     * @param projectiles
     *            the list of {@link Projectile}.
     */
    public TankWithProjectile(final Tank playerTank, final Tank enemyTank, final List<Projectile> projectiles) {
        this.playerTank = playerTank;
        this.enemyTank = enemyTank;
        this.projectiles = projectiles;
    }

    @Override
    public final void manageCollision() {
        updateTankLifeAndProjectiles(this.playerTank,
                projectiles.stream()
                        .filter(p -> CheckIntersection.intersects(p.getPosition(), p.getBounds(),
                                this.playerTank.getPosition(), Tank.getDimension()))
                        .collect(Collectors.toList()));
        updateTankLifeAndProjectiles(this.enemyTank,
                projectiles.stream()
                        .filter(p -> CheckIntersection.intersects(p.getPosition(), p.getBounds(),
                                this.enemyTank.getPosition(), Tank.getDimension()))
                        .collect(Collectors.toList()));

    }

    private static void updateTankLifeAndProjectiles(final Tank tank, final List<Projectile> hitProjectiles) {
        if (!hitProjectiles.isEmpty()) {
            tank.damage(DAMAGE * hitProjectiles.size());
            hitProjectiles.forEach(p -> p.setDead());
        }
    }
}
