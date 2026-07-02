package model;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.World;

import com.google.common.collect.Maps;

import common.Log;
import controller.entities.Coin;
import controller.entities.Enemy;
import controller.entities.Entity;
import controller.entities.Platform;
import controller.entities.Player;
import enumerators.Level;
import enumerators.PlayerCharacter;
import factories.AbstractFactory;
import view.Camera;

/**
 * Contains all the world entities and the JBox2D world and bodies. It handles
 * the world steps.
 */
public final class GameModel {

    private static final int VELOCITY_ITERATION = 10;
    private static final int POSITION_ITERATIONS = 3;
    private float fps;

    private static World world;
    private Map<Body, Entity> entitiesMap;
    private Map<Body, Entity> toBeDeletedMap;
    private Player player;
    private Platform topPlatform;
    private boolean playerDead;
    private double maxHeight;

    private static final Vec2 GRAVITY = new Vec2(0, 10f);

    /**
     * Instantiates the game entities and the JBox2D world.
     * 
     * @param fps   - the framerate the game should update
     * @param level - the level to generate
     */
    public GameModel(final long fps) {
        this.fps = (float) fps;
        world = new World(GRAVITY);
        world.setContactListener(new CollisionHandler(this));
        entitiesMap = Maps.newConcurrentMap();
        toBeDeletedMap = Maps.newConcurrentMap();
    }

    public void init(final Level level) {
        world.setGravity(GRAVITY.mul((float) level.getDifficulty()));
    }

    /**
     * Does a JBox2D step and updates the model.
     */
    public void updateWorld() {
        world.step(1.0f / fps, VELOCITY_ITERATION, POSITION_ITERATIONS);
        if (Math.round(player.getPhysicPosition().y) > maxHeight) {
            maxHeight = player.getPhysicPosition().y;
        }
    }

    /**
     * Updates all entities.
     */
    public void updateEntities() {
        if (!entitiesMap.isEmpty()) {
            entitiesMap.values().stream().forEach(Entity::updateEntity);
        }
    }

    /**
     * Returns the JBox2D world.
     * 
     * @return the JBox2D world.
     */
    public static World getWorld() {
        return world;
    }

    /**
     * Returns all spawned enemies.
     * 
     * @return enemies set
     */
    public Set<Enemy> getEnemiesSet() {
        return entitiesMap.values().stream().filter(en -> en instanceof Enemy).map(en -> (Enemy) en)
                .collect(Collectors.toSet());
    }
    
    public Set<Coin> getCoinSet() {
        return entitiesMap.values().stream().filter(co -> co instanceof Coin).map(co -> (Coin) co)
                .collect(Collectors.toSet());
    }

    /**
     * Returns the set with platforms.
     * 
     * @return the platform set
     */
    public Set<Platform> getPlatformSet() {
        return entitiesMap.values().stream().filter(pl -> pl instanceof Platform).map(pl -> (Platform) pl)
                .collect(Collectors.toSet());
    }

    /**
     * @return the topPlatform
     */
    public Platform getTopPlatform() {
        return topPlatform;
    }

    /**
     * @param topPlatform the topPlatform to set
     */
    public void setTopPlatform(final Platform topPlatform) {
        this.topPlatform = topPlatform;
    }

    /**
     * Returns all spawned entities.
     * 
     * @return set with all entities
     */
    public Set<Entity> getAllEntities() {
        return entitiesMap.values().stream().filter(ent -> ent instanceof Entity).map(ent -> (Entity) ent)
                .collect(Collectors.toSet());
    }

    /**
     * Removes the entity at the end of the GameController tick.
     * 
     * @param entity to be removed
     */
    public void removeEntityFromMap(final Entity entity) {
        if (!entitiesMap.isEmpty() && entitiesMap.containsKey(entity.getBody())) {
            toBeDeletedMap.put(entity.getBody(), entity);
        }
    }

    /**
     * Add the entity to the entities map.
     * 
     * @param entity to be added
     * @param body   of the entity
     */
    public void addEntityToMap(final Entity entity, final Body body) {
        entitiesMap.put(body, entity);
    }

    /**
     * Returns the entity, given the body.
     * 
     * @param body to get the entity from
     * @return the entity's body
     */
    public Entity getEntityFromBody(final Body body) {
        if (!entitiesMap.isEmpty()) {
            if (entitiesMap.containsKey(body)) {
                return entitiesMap.get(body);
            }
            throw new IllegalArgumentException("This body doesn't exist inside the entity collection!");
        } else {
            throw new IllegalArgumentException("Entity collection is empty");
        }
    }

    /**
     * Sets an entity to be destroyed.
     * 
     * @param entity to be destroyed
     */
    public void addEntityToDestroy(final Entity entity) {
        toBeDeletedMap.put(entity.getBody(), entity);
    }

    /**
     * Returns the player entity.
     * 
     * @return player entity.
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * @return <code>true</code> if the player is dead
     */
    public boolean isPlayerDead() {
        return playerDead;
    }
    /**
     * Create the player entity and put it into the entities set.
     * 
     * @param character the player character to create
     * @param position  the position where to create the player
     */
    public void createPlayer(final PlayerCharacter character, final Vec2 position) {
        player = AbstractFactory.createPlayer(character, position);
        addEntityToMap(player, player.getBody());
        this.maxHeight = position.y;
        this.playerDead = false;

    }

    /**
     * Check the entities position and remove them. Also checks if the
     * player is dead.
     */
    public void checkEntitiesToDelete() {
//        final Iterator<Entity> entitiesIterator = GameController.getInstance().getGameModel().getAllEntities()
//                .iterator();
//        while (entitiesIterator.hasNext()) {
//            final Entity entity = entitiesIterator.next();
//            if (Camera.isYPastPlayer(entity.getPhysicPosition().y)) {
//                this.addEntityToDestroy(entity);
//                if (entity.getEntityType().equals(EntityType.PLAYER)) {
//                    this.playerDead = true;
//                }
//            }
//        }
//    }
        entitiesMap.values()
                    .stream()
                    .filter(e -> (!e.getModel().isAlive() || Camera.isYPastPlayer(e.getPhysicPosition().y)))
                    .forEach(e -> {
                        e.destroy();
                        toBeDeletedMap.put(e.getBody(), e);
                    });
        if (toBeDeletedMap.containsValue(player)) {
            playerDead = true;
        }
        deleteEntities();
    }

    /**
     * Put all entities to the collection to be destroyed.
     */
    public void cleanAllEntities() {
        entitiesMap.values()
                    .stream()
                    .forEach(e -> {
                        e.destroy();
                        toBeDeletedMap.put(e.getBody(), e);
                    });
        deleteEntities();
    }

    /**
     * Effectively destroy all the entities that are set to be deleted.
     */
    public void deleteEntities() {
        if (!world.isLocked()) {
            if (!toBeDeletedMap.isEmpty()) {
//                for (Map.Entry<Body, Entity> entry : toBeDeletedMap.entrySet()) {
//                    if (entitiesMap.containsKey(entry.getKey())) {
//                        entitiesMap.get(entry.getKey()).destroy();
//                        entitiesMap.remove(entry.getKey());
//                        // Log.add("removed entity, left " + entitiesMap.size());
//                    }
//                }
//                toBeDeletedMap.clear();

                toBeDeletedMap.keySet()
                                .stream()
                                .forEach(b -> {
                                    world.destroyBody(b);
                                    if (entitiesMap.containsKey(b)) {
                                        entitiesMap.remove(b);
                                    }
                                });
                toBeDeletedMap.clear();
            }
        } else {
            Log.add("World is locked");
        }
    }

    /**
     * Clears all the entities maps.
     */
    public void resetWorld() {
        this.cleanAllEntities();
        Log.add("ENTITIES MAP: " + String.valueOf(entitiesMap.size()));
        Log.add("DELETE MAP: " + String.valueOf(toBeDeletedMap.size()));
    }

    /**
     * Returns the max height the player has reached.
     * 
     * @return max height
     */
    public double getMaxHeight() {
        return maxHeight;
    }
}
