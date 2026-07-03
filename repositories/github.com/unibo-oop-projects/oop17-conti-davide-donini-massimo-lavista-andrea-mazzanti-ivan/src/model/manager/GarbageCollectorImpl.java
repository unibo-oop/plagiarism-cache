package model.manager;

import java.util.List;

import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import model.entities.Bullet;
import model.entities.Enemy;
import model.entities.Entity;
import model.entities.powerup.PowerUp;

/**
 * Implement method to delete from model object not necessary.
 */
public final class GarbageCollectorImpl implements GarbageCollector {

    private static final int EDGE_WIDTH = 150;

    private final Shape world;
    private final Shape enemiesWorld;

    /**
     * Initialize assuming information about world.
     * 
     * @param worldWidth
     *            world's width
     * @param worldHeight
     *            world's height
     */
    public GarbageCollectorImpl(final int worldWidth, final int worldHeight) {
        world = new Rectangle(0, 0, worldWidth, worldHeight);
        enemiesWorld = new Rectangle(-EDGE_WIDTH, -EDGE_WIDTH, worldWidth + (EDGE_WIDTH * 2),
                worldHeight + (EDGE_WIDTH * 2));
    }

    @Override
    public void deleteUnnecessaryEntities(final List<Enemy> enemies, final List<PowerUp> powerUps,
            final List<Bullet> bullets) {
        powerUps.removeIf(p -> this.isEntitiesOutOfTheWorld(p));
        bullets.removeIf(b -> this.isEntitiesOutOfTheWorld(b));
        enemies.removeIf(e -> this.isEntitiesOutOfTheWorld(e));
    }

    private boolean isEntitiesOutOfTheWorld(final Enemy entity) {
        return isThereIntersection(entity.getShape(), this.enemiesWorld);
    }

    private boolean isEntitiesOutOfTheWorld(final Entity entity) {
        return isThereIntersection(entity.getShape(), this.world);
    }

    private boolean isThereIntersection(final Shape s1, final Shape s2) {
        return !s1.getBoundsInLocal().intersects(s2.getBoundsInLocal());
    }

}
