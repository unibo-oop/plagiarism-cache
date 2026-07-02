package bzzbomber.controller.collision;

import java.util.Optional;
import java.util.Set;

import bzzbomber.model.entities.Door;
import bzzbomber.model.entities.Entity;
import bzzbomber.model.entities.HealthPoint;
import bzzbomber.model.entities.Insects;

/**
 * Implementation of @HeroCollision .
 * 
 * @author Enrico Gnagnarella
 */

public class BomberCollisionImpl extends CollisionImpl implements BomberCollision {

    /**
     * Constructor of HeroCollisionImpl.
     * 
     * @param entity
     *            the entity of hero.
     */
    public BomberCollisionImpl(final Entity entity) {
        super(entity);
    }

    @Override
    public final boolean openDoorCollision(final Optional<Door> door) {
        if (door.isPresent()) {
            return super.getCollisionBox().intersects(door.get().getCollisionBox());
        } else {
            return false;
        }
    }

    @Override
    public final boolean insectsCollision(final Set<Insects> insects) {
        return insects.stream().filter(elem -> elem.isAlive())
                .anyMatch(rec -> super.getCollisionBox().intersects(rec.getCollisionBox()));
    }

    @Override
    public final boolean healthCollision(final Set<HealthPoint> health) {

        final boolean temp = health.stream().anyMatch(rec -> super.getCollisionBox().intersects(rec.getCollisionBox()));
        if (temp) {
            health.stream().filter(rec -> super.getCollisionBox().intersects(rec.getCollisionBox()))
                    .forEach(e -> e.setTaken(true));
        }
        return temp;

    }
}
