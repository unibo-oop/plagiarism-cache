package tmw.controller.world;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import tmw.controller.entities.EntityController;
import tmw.controller.item.AbstractItemController;
import tmw.model.entities.Enemy;
import tmw.model.objects.BaseGameObject;
import tmw.model.objects.EscapeDoor;
import tmw.model.objects.Trigger;
import tmw.model.world.GameWorld;
import utils.Rooms;

/**
 * This class represents a tool to populate a gameWorld in order to build
 * different levels/gameRooms. It's also the strategy pattern interface.
 * 
 * @version 1.2
 */

public interface WorldDispenser {

    /**
     * Method to insert entities in world.
     * 
     * @param world {@link GameWorld} world reference
     */
    void populateWorld(GameWorld world);

    /**
     * Getter for entities in world.
     * 
     * @return list of entities
     */
    CopyOnWriteArrayList<EntityController<? extends Enemy>> getEntities();

    /**
     * Getter for items in world.
     * 
     * @return list of items
     */
    CopyOnWriteArrayList<AbstractItemController> getItems();

    /**
     * Getter for the triggers in the world.
     * 
     * @return list of trigger
     */
    CopyOnWriteArrayList<Trigger> getTriggers();

    /**
     * Getters for the obstacles in the world.
     * 
     * @return list of obstacles
     */
    List<BaseGameObject> getObstacles();

    /**
     * Getter for escape door in game.
     * 
     * @return {@link EscapeDoor} escape door.
     */
    EscapeDoor getEscapeDoor();

    /**
     * Getter for room type.
     * 
     * @return {@link Rooms} actual type
     */
    Rooms getRoomType();

}
