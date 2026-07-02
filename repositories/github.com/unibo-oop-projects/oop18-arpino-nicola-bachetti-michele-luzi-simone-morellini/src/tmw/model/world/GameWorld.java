package tmw.model.world;

import java.util.List;
import java.util.Optional;

import javafx.geometry.Point2D;
import javafx.scene.shape.Rectangle;
import tmw.common.P2d;
import tmw.controller.entities.EntityController;
import tmw.controller.item.AbstractItemController;
import tmw.model.entities.BulletEntity;
import tmw.model.entities.Enemy;
import tmw.model.entities.MilkEntity;
import tmw.model.item.Item;
import tmw.model.objects.GameObject;

/**
 * This class represents a gameWorld concept. World contains enemies, player,
 * items and bullets shoot by both enemies and player. All these entities are
 * closed in a gameWorldArea which represents the world size. There are multiple
 * methods which allows to interact with game entities. Allows also to be
 * observed by observers that'll be aware of changes.
 * 
 * @version 1.3
 */
public interface GameWorld {

    /**
     * This method check if entity it's placed in a valid position in world.
     * 
     * @param entity {@link GameEntity} entity reference
     * @return boolean response
     */
    default boolean checkInBounds(GameObject entity) {
        return (this.getWorldArea()
                .contains(new Point2D(entity.getCurrentPos().getX(), entity.getCurrentPos().getY())));
    }

    /**
     * Allows to populate world with entities.
     * 
     * @param entities {@link EntityController} entities with their positions
     *                 encapsulated
     * @param items    items
     */
    void populateWorld(List<? extends EntityController<?>> entities, List<AbstractItemController> items);

    /**
     * Insert item in world. Items have fixed position and must be specified because
     * items does not have any position value inside their classes.
     * 
     * @param obj {@link GameObject} item to insert
     */
    void insertItem(Item obj);

    /**
     * Removes item in world.
     * 
     * @param obj {@link GameObject} item to remove
     */
    void removeItem(Item obj);

    /**
     * Insert enemy in world.
     * 
     * @param enemy {@link Enemy} generic enemy
     */
    void insertEnemy(Enemy enemy);

    /**
     * Removes enemy in world.
     * 
     * @param enemy {@link Enemy} generic enemy
     */
    void removeEnemy(Enemy enemy);

    /**
     * Insert a bullet in world.
     * 
     * @param bullet {@link BulletEntity} bullet entity
     */
    void insertBullet(BulletEntity bullet);

    /**
     * Removes bullet in world.
     * 
     * @param bullet {@link BulletEntity} bullet entity
     */
    void removeBullet(BulletEntity bullet);

    /**
     * Insert player in world.
     * 
     * @param player {@link MilkEntity} player entity
     */
    void insertPlayer(MilkEntity player);

    /**
     * Removes the player. Represents player's death.
     * 
     * @param player {@link MilkEntity} player entity
     */
    void killPlayer(MilkEntity player);

    /**
     * Insert an obstacle (such as a simple wall) in the world.
     * 
     * @param obstacle {@link GameObject} obstacle entity to insert
     */
    void insertObstacle(GameObject obstacle);

    /**
     * Removes an obstacle from the world.
     *
     * @param obstacle {@link GameObject} obstacle to remove.
     */
    void removeObstacle(GameObject obstacle);

    /**
     * Gets enemy position.
     * 
     * @param enemy {@link Enemy} enemy reference
     * @return {@link Optional<P2d>} position of that enemy
     */
    Optional<P2d> getEnemyPosition(Enemy enemy);

    /**
     * Gets item position in world.
     * 
     * @param item {@link GameObject} item reference
     * @return {@link P2d} item position
     */
    Optional<P2d> getItemPosition(Item item);

    /**
     * Gets player position in world.
     * 
     * @return {@link Optional<P2d>} player position
     */
    Optional<P2d> getPlayerPosition();

    /**
     * Getter for world items.
     * 
     * @return {@link List} list of items
     */
    List<Item> getItems();

    /**
     * Getter for enemies in world.
     * 
     * @return {@link List} list of enemies
     */
    List<Enemy> getEnemies();

    /**
     * Getter for all obstacles in world.
     * 
     * @return {@link List} list of obstacles
     */
    List<GameObject> getObstacles();

    /**
     * Getter for current player in world.
     * 
     * @return {@link Optional<MilkEntity>} player
     */
    Optional<MilkEntity> getPlayer();

    /**
     * Returns the worldArea.
     * 
     * @return area {@link Rectangle} represents world area
     */
    Rectangle getWorldArea();

    /**
     * Sets the new world area.
     * 
     * @param area {@link Rectangle} new area
     */
    void setWorldArea(Rectangle area);
}
