package virtualworld;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import com.google.common.collect.ImmutableMap;

import entity.Actor;
import entity.CollisionBox;
import entity.CollisionBoxInt;
import entity.EntitySpawner;
import entity.Faction;
import entity.Projectile;
import entity.UUIDActor;
import entity.UUIDEntity;
import entity.UUIDProjectile;
import javafx.util.Pair;

public class VirtualMapPrototype<A extends UUIDActor, P extends UUIDProjectile> implements VirtualMap<A, P> {

    private final int heigth;
    private final int width;
    private Object lock = new Object();
    private EntitySpawner spawner = new EntitySpawner() {

        @Override
        public void spwanProjectile(final Projectile p) {
        }

        @Override
        public void spwanActor(final Actor a) {
        }

        @Override
        public Set<Projectile> getDespawnedProjectiles() {
            return Set.of();
        }

        @Override
        public Set<Actor> getDespawnedActors() {
            return Set.of();
        }
    };
    private final EntityVirtualMap<A> actors;
    private final EntityVirtualMap<P> projectiles;
    private final Map<UUIDEntity, Movment> entities = new HashMap<>();

    /**
     * @param heigth
     * @param width
     */
    public VirtualMapPrototype(final int heigth, final int width) {
        this.heigth = heigth;
        this.width = width;
        this.actors = new EntityVirtualMap<>(heigth, width);
        this.projectiles = new EntityVirtualMap<>(heigth, width);
    }

    public final void setSpawner(final EntitySpawner spawner) {
        synchronized (lock) {
        synchronized (spawner) {
            this.spawner = spawner;
            }
        }
    }

    public final void setLock(final Object lock) {
        synchronized (lock) {
            this.lock = lock;
        }
    }

    @Override
    public final Map<A, CollisionBox<Integer>> getActors(final Faction faction) {
    return getActors().entrySet().stream()
            .filter(x -> x.getKey().getFaction().equals(faction))
            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    @Override
    public final Map<A, CollisionBox<Integer>> getActors() {
        return getEntities(actors);
    }

    @Override
    public final Map<P, CollisionBox<Integer>> getProjectiles() {
        return getEntities(projectiles);
    }

    private <T extends UUIDEntity> Map<T, CollisionBox<Integer>> getEntities(final EntityVirtualMap<T> emap) {
        synchronized (lock) {
            return ImmutableMap.copyOf(emap.getEntities().stream()
                    .map(x -> new Pair<>(x, entities.get(x).getCollisionBox()))
                    .collect(Collectors.toMap(Pair::getKey, Pair::getValue))
                    );
        }
    }

    @Override
    public final Movment addProjectile(final P projectile, final int x, final int y) throws OutofBoundariesException {
        final CollisionBox<Integer> box = projectile.getBody().getCollisionBox();
        return addProjectile(projectile, new CollisionBoxInt(x, y, box));
    }

    @Override
    public final Movment addProjectile(final P projectile, final CollisionBox<Integer> collisionbox) throws OutofBoundariesException {
        return addEntity(projectile, projectiles, collisionbox, x -> spawner.spwanProjectile(x));
    }

    @Override
    public final Movment addActor(final A actor, final int x, final int y) throws OutofBoundariesException {
        final CollisionBox<Integer> box = actor.getBody().getCollisionBox();
        return addActor(actor, new CollisionBoxInt(x, y, box));
    }

    @Override
    public final Movment addActor(final A actor, final CollisionBox<Integer> collisionbox) throws OutofBoundariesException {
        return addEntity(actor, actors, collisionbox, x -> spawner.spwanActor(x));
    }

    private  <T extends UUIDEntity> Movment addEntity(final T entity, final EntityVirtualMap<T> emap, final CollisionBox<Integer> collisionbox, final Consumer<T> spawner) throws OutofBoundariesException {
        synchronized (lock) {
            if (emap.getEntities().contains(entity)) {
                return this.entities.get(entity);
            }
            if (emap.addEntity(entity, collisionbox)) {
                final Movment mv = new MapMovment<>(this, emap, entity, collisionbox);
                entities.put(entity, mv);
                entity.getBody().setMotion(mv);
                spawner.accept(entity);
                return mv;
            }
        }
        throw new OutofBoundariesException();        
    }

    @Override
    public final void removeActor(final A actor) {
       removeEntity(actor, actors);
    }

    @Override
    public final void removeProjectile(final P projectile) {
       removeEntity(projectile, projectiles);
    }

    private  <T extends UUIDEntity> void removeEntity(final T entity, final EntityVirtualMap<T> emap) {
        synchronized (lock) {
            if (emap.getEntities().contains(entity)) {
                emap.removeEntity(entity);
                entities.remove(entity);
            }
        }
    }

    private boolean isInside(final int x, final int y) {
        return x < this.width && x > 0 && y < this.heigth && y > 0;
    }

    private boolean isInside(final CollisionBox<Integer> collisionbox) {
        return isInside(collisionbox.getX(), collisionbox.getY())
                && isInside(collisionbox.getX() + collisionbox.getWidth(),
                        collisionbox.getY() + collisionbox.getHeight());
    }

    @Override
    public final synchronized int getHeigth() {
        return heigth;
    }

    @Override
    public final synchronized int getWidth() {
        return width;
    }


    public class MapMovment<T extends UUIDEntity> implements Movment {

        private final VirtualMapPrototype map;
        private final EntityVirtualMap<T> emap;
        private final T entity;
        private CollisionBox<Integer> hitbox;

        /**
         * @param map
         * @param emap
         * @param entity
         * @param hitbox
         */
        public MapMovment(final VirtualMapPrototype map, final EntityVirtualMap<T> emap, final T entity, final CollisionBox<Integer> hitbox) {
            this.map = map;
            this.emap = emap;
            this.entity = entity;
            this.hitbox = hitbox;
        }

        @Override
        public final VirtualMap getMap() {
                return map;
        }

        @Override
        public final boolean moveTo(final int x, final int y) {
            if(emap.canMove(entity, x, y)) {
            	this.hitbox = new CollisionBoxInt(x,y,hitbox);
            	return true;
            }
            return false;
        }

        @Override
        public final boolean isInside(final int x, final int y) {
            synchronized (lock) {
                return map.isInside(new CollisionBoxInt(x, y, hitbox)) && emap.isInside(new CollisionBoxInt(x, y, hitbox));
            }
        }

        @Override
        public final CollisionBox<Integer> getCollisionBox() {
            return hitbox;
        }
    }
}
