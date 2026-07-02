package virtualworld;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import entity.CollisionBox;
import entity.CollisionBoxInt;
import entity.Entity;

public class EntityVirtualMap<T extends Entity> {

    private final int heigth;
    private final int width;

    private final List<T> entities = new LinkedList<>();

    /**
     * @param heigth
     * @param width
     */
    public EntityVirtualMap(final int heigth, final int width) {
        this.heigth = heigth;
        this.width = width;
    }


    public final Set<T> getEntities() {
        synchronized (entities) {
            return Set.copyOf(entities);
        }
    }

    public final boolean addEntity(final T actor, final CollisionBox<Integer> hitbox, final int x, final int y) {
        return addEntity(actor, new CollisionBoxInt(x, y, hitbox.getWidth(), hitbox.getHeight()));
    }

    public final boolean addEntity(final T entity, final CollisionBox<Integer> hitbox) {
        if (!isInside(hitbox)) {
            return false;
        }
        synchronized (entities) {
            if (!entities.contains(entity)) {
                entities.add(entity);
            }
            return true;
        }        
    }

    public final boolean removeEntity(final T entity) {
        synchronized (entities) {
            return this.entities.remove(entity);
        }        
    }

    public final boolean isInside(final int x, final int y) {
        return x < this.width && x >= 0 && y < this.heigth && y >= 0;
    }

    public final boolean isInside(final CollisionBox<Integer> collisionbox) {
        return isInside(collisionbox.getX(), collisionbox.getY()) 
                && isInside(collisionbox.getX() + collisionbox.getWidth(),
                        collisionbox.getY() + collisionbox.getHeight());
    }

    public final boolean canMove(final T e, final int x, final int y) {
        return isInside(new CollisionBoxInt(x, y, e.getBody().getCollisionBox()));
    }

    public final synchronized int getHeigth() {
        return heigth;
    }

    public final synchronized int getWidth() {
        return width;
    }
}
