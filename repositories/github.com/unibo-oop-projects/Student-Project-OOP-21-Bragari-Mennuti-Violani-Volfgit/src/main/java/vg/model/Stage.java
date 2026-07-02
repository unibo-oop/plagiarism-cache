package vg.model;

import vg.controller.entity.EntityManager;
import vg.controller.gameBoard.GameBoardController;
import vg.model.entity.ShapedEntity;
import vg.model.entity.Entity;
import vg.model.entity.dynamicEntity.DynamicEntity;
import vg.model.entity.dynamicEntity.enemy.Boss;
import vg.model.entity.dynamicEntity.player.Player;
import vg.model.entity.staticEntity.StaticEntity;
import vg.model.levels.LEVEL;
import vg.utils.Direction;
import vg.utils.MassTier;

import java.util.List;
import java.util.Set;

/**
 * The main model class that will control all the entities
 * on the map.
 * @param <T> V2D : the type which defines borders data structure.
 */
public interface Stage<T> {
    /**
     * Returns the value of the score of the player.
     * @return The current score of the player
     */
    int getCurrentScore();
    /**
     * Sets the value of the score of the player.
     * @param currentScore The value to set to
     */
    void setCurrentScore(int currentScore);
    /**
     * Return the current map state.
     * @return The current state of the map.
     * @see Map
     */
    Map<T> getMap();

    /**
     * Sets the map of the stage.
     * @param map The map to set to.
     * @see Map
     */
    void setMap(Map<T> map);

    /**
     * Return the current player state.
     * @return Player : the current player state.
     * @see Player
     */
    Player getPlayer();

    /**
     * Return current level boss.
     * @return Boss of level
     * @see Boss
     */
    Boss getBoss();

    /**
     * Get the current level played.
     * @return the current level played
     */
    int getLv();

    /**
     * Sets the current level.
     * @param lv the level
     */
    void setLv(int lv);
    /**
     * Sets the player in this stage.
     * @param player The player to set to.
     * @see Player
     */
    void setPlayer(Player player);
    /**
     * Return a Set of all static entities on the map.
     * @return The Set that contains all staticEntity on the map.
     * @see StaticEntity
     */
    Set<StaticEntity> getStaticEntitySet();

    /**
     * Return a Set of all dynamic entities on the map.
     * @return The Set that contains all staticEntity on the map.
     * @see DynamicEntity
     */
    Set<DynamicEntity> getDynamicEntitySet();

    /**
     * Return a Set of all entities that will be deleted from the map before the next iteration begins.
     * @return : Set of ShapedEntity that contains all entities on the map that will be deleted.
     * @see Entity
     * @see ShapedEntity
     */
    Set<ShapedEntity> getToDestroySet();

    /**
     * Return a data structure containing the current actual borders of the map.
     * This will not include Magic Boxes even if they are present on the map and
     * entities con collide with.
     * @return The Set of T, a data structure containing the borders of the map.
     */
    Set<T> getBorders();

    /**
     * Return all entities on the map.
     * @return The set that contains all entities on the map.
     * @see Entity
     * @see ShapedEntity
     */
    Set<Entity> getAllEntities();

    /**
     * For each dynamic entity not player on the map the method move will be called.
     * @see DynamicEntity
     */
    void moveAll();

    /**
     * Add an entity to a list of entities that will be destroyed
     * at the end of current cycle. Usually after moving entities,
     * if they collide with entities of opposite faction then this
     * method will be called.
     * @param toDestroy ShapedEntity : the entity to destroy.
     */
    void addToDestroy(ShapedEntity toDestroy);

    /**
     * After moving all the dynamic entities of the map this checks
     * which one are colliding and call the proper method on the
     * entity to manage the collision.
     * @see DynamicEntity
     * @see DynamicEntity#afterCollisionAction(MassTier)
     */
    void checkCollisions();

    /**
     * Similar to checkCollisions but instead checks for all entities
     * if there are some that are in invalid positions and will manage them
     * directly.
     */
    void checkAllOutOfBounds();

    /**
     * At the end of a cycle this method will be called and delete all
     * the entities that needs to be destroyed.
     * @see #addToDestroy
     */
    void destroyAll();

    /**
     * For each entity on the map, computes the cycle and apply all the changes.
     * @see #moveAll()
     * @see #checkCollisions()
     * @see #checkAllOutOfBounds()
     * @see #destroyAll()
     */
    void doCycle();
    void setEntityManagerController(EntityManager e, GameBoardController g);
    /**
     * Creates a {@link Map}, must use {@link LEVEL} or better
     * {@link MapFactoryImpl}.
     */
    void createNextLevel();

    /**
     * Method that will tell if the border or the {@link Map}
     * were updated from the last time this method was called.
     * @return true if the {@link #getBorders()} changed, false otherwise
     */
    boolean isBorderUpdated();

    /**
     * Method that will "consume" the state of {@link #isBorderUpdated()}, so
     * if it was true, then it will become false.
     */
    void consumeBorderUpdatedState();

    EntityManager getEntityManager();
}
