package model.map;

import java.util.Collection;
import java.util.List;

import model.entities.Enemy;
import model.entities.ItemImpl;
import model.hitbox.HitboxRectangle;
import model.utils.Direction;

/**
 * 
 * Represents all the rooms of the game.
 *
 */
public interface Room {

    /**
     * Add all the Items provided to this room.
     * 
     * @param items
     *            A collection of Items to add to this room.
     */
    void addAllItems(Collection<ItemImpl> items);

    /**
     * Get the items contained in this room.
     * 
     * @return A collection of Item.
     */
    Collection<ItemImpl> getAllItems();

    /**
     * Get the enemies contained in this room..
     * 
     * @return A list of Enemy.
     */
    List<Enemy> getEnemies();

    /**
     * 
     * @param door
     *            The door the player has passed through.
     * @return Return an index representing the room connected with the door
     *         given as parameter. The index represents the position of the room
     *         in the collection of the MapImpl class.
     */
    int getConnection(Direction door);

    /**
     * Add a connection between this room and another room.
     * 
     * @param i
     *            The index representing the room that has to be connected with
     *            this room. The index represents the position of the room in
     *            the collection of the MapImpl class.
     */
    void addConnection(int i);

    /**
     * Add a door to this room.
     * 
     * @param d
     *            A direction that represents the door to add.
     */
    void addDoors(Direction d);

    /**
     * Add enemies to this room.
     * 
     * @param e
     *            A list of Enemy.
     */
    void addEnemies(List<Enemy> e);

    /**
     * Add all the doors provided to this room.
     * 
     * @param d
     *            A list of doors
     */
    void addAllDoors(List<Direction> d);

    /**
     * Get all the doors of this Room.
     * 
     * @return A list of Direction representing the doors of this room.
     */
    List<Direction> getDoors();

    /**
     * Get the walls of this room.
     * 
     * @return The walls of this room.
     */
    List<HitboxRectangle> getWalls();

}