package tmw.controller.world;

import java.util.List;
import java.util.Observer;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;

import javafx.scene.Scene;
import tmw.common.Dim2D;
import tmw.common.EntityFactory;
import tmw.controller.entities.AbstarctBulletController;
import tmw.controller.entities.EntityController;
import tmw.controller.entities.MilkController;
import tmw.controller.hud.HudController;
import tmw.controller.item.AbstractItemController;
import tmw.view.level.RoomView;
import tmw.model.entities.Enemy;
import tmw.model.objects.BaseGameObject;
import tmw.model.objects.EscapeDoor;
import tmw.model.objects.Trigger;
import tmw.model.world.AbstractWorld;

/**
 * This controller manages the whole world and it's view counterpart. Basically
 * provides fundamentals methods to execute the game; if fact allows to call
 * update and render methods according to gameLoop speed. To achieve these
 * functionalities it has references of all entities/gameObjects present in a
 * specific world.
 * 
 * It allows to
 * 
 * @version 1.3
 */
public interface WorldController extends Observer {

    /**
     * This method should be called to initialize controllers.
     */
    void init();

    /**
     * Method to load a specific room.
     * 
     * @param room {@link WorldDispenser} represents a room to load
     */
    void loadRoom(WorldDispenser room);

    /**
     * Method to call to set on which action world controller should change room.
     * <p>
     * An optional {@link RoomSwitcherPolicy} parameter is passed, if this is an
     * empty parameter default behavior'll be implemented so when all enemies are
     * dead room is changed. Instead, if switcher parameter is not null, it'll be
     * set as switcher controller. Note that switcher can be scripted as needed.
     * 
     * @param switcher {@link RoomSwitcherPolicy} scripted switcher controller
     */
    void initializeRoomSwitcher(Optional<RoomSwitcherPolicy> switcher);

    /**
     * Allows to insert a playerController in world. If an empty playerController is
     * passed this method should create a new one.
     * 
     * @param playerController {@link Optional<MilkController>} milk controller
     *                         reference.
     */
    void createPlayer(Optional<MilkController> playerController);

    /**
     * Allows to update game entities.
     * 
     * @param dt time since last update
     */
    void updateEntities(double dt);

    /**
     * Allows to render entities.
     */
    void renderEntites();

    /**
     * This method should be called when game changes its resolution, because hitBox
     * is in proportion with game resolution has to be recalculated.
     * 
     * @param dim {@link Dim2D} new game resolution
     */
    void updateEntitiesHitBox(Dim2D dim);

    /**
     * This method can remove an entity in world by removing first his controller
     * and then his GameEntity instance.
     * 
     * @param en {@link EntityController} controller to remove
     */
    void removeEntityController(EntityController<? extends Enemy> en);

    /**
     * Insert a bulletController in world in order to manage bullets.
     * 
     * @param blt {@link AbstarctBulletController} bullet controller to add
     */
    void addBullet(AbstarctBulletController blt);

    /**
     * Destroys a bullet in world by removing first his controller and then his
     * model in world.
     * 
     * @param blt {@link AbstarctBulletController} controller to remove
     */
    void removeBullet(AbstarctBulletController blt);

    /**
     * Removes an item from world.
     * 
     * @param item {@link AbstractItemController} controller of this item
     */
    void removeItem(AbstractItemController item);

    /**
     * Method that clear all entities loaded in world.
     */
    void resetEntities();

    /**
     * This method deletes the player in world causing room exit and switch to
     * gameOver.
     */
    void killPlayer();

    /**
     * Getter for the level view.
     * 
     * @return {@link RoomView} level view
     */
    RoomView getView();

    /**
     * Increments score of specific value.
     * 
     * @param value value to add
     */
    void incrementScore(int value);

    /**
     * Getter for current score.
     * 
     * @return integer value of current score
     */
    int getActualScore();

    /**
     * Getter for hud controller.
     * 
     * @return {@link HudController} the hud controller
     */
    HudController getHud();

    /**
     * Getter for the player controller.
     * 
     * @return {@link MilkController} the player controller
     */
    MilkController getPlayer();

    /**
     * Getter for gameWorld.
     * 
     * @return {@link AbstractWorld} current world
     */
    AbstractWorld getGameWorld();

    /**
     * Getter for level Scene.
     * 
     * @return {@link Scene} represents the level scene
     */
    Scene getScene();

    /**
     * Getter for entities loaded.
     * 
     * @return list of entities
     */
    List<? extends EntityController<? extends Enemy>> getEntitiesLoaded();

    /**
     * Getter for obstacles loaded.
     * 
     * @return list of obstacles
     */
    List<BaseGameObject> getObstacleLoaded();

    /**
     * Getter for game triggers.
     * 
     * @return list of triggers
     */
    CopyOnWriteArrayList<Trigger> getTriggers();

    /**
     * Getter for items loaded.
     * 
     * @return list of items
     */
    CopyOnWriteArrayList<AbstractItemController> getItemLoaded();

    /**
     * Getter for the bullets loaded.
     * 
     * @return list of bullets
     */
    CopyOnWriteArrayList<AbstarctBulletController> getBulletLoaded();

    /**
     * Getter for the current Room.
     * 
     * @return current room
     */
    WorldDispenser getRoom();

    /**
     * Getter for the escape door.
     * 
     * @return the escape door
     */
    EscapeDoor getEscapeDoor();

    /**
     * Getter for the entity factory.
     * 
     * @return the entity factory
     */
    EntityFactory getFactory();

}
