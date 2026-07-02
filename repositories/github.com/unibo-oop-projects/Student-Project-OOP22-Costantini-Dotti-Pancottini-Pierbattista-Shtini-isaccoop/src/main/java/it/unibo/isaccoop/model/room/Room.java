package it.unibo.isaccoop.model.room;

import java.util.List;
import java.util.Optional;

import it.unibo.isaccoop.model.ai.AIEnemy;
import it.unibo.isaccoop.model.collision.Event;
import it.unibo.isaccoop.model.common.MapElement;
import it.unibo.isaccoop.model.common.Removable;
import it.unibo.isaccoop.model.common.RoomType;
import it.unibo.isaccoop.model.enemy.Enemy;
import it.unibo.isaccoop.model.item.Item;
import it.unibo.isaccoop.model.player.Player;
import it.unibo.isaccoop.model.powerup.PowerUp;

/**
 * Interface to model the concept of Room inside the game.
 */
public interface Room extends MapElement, Removable {

    /**
     * Get the {@link RoomType} of the room, that is, the type of the room itself.
     * @return the type of this room
     */
    RoomType getRoomType();

    /**
     * Get the {@link AIEnemy} attached to this {@link Room}.
     * @return {@link AIEnemy} attached to this {@link Room}, or {@link Optional#empty()}
     * if there is not an {@link AIEnemy} in this room
     * */
    Optional<AIEnemy> getRoomAI();

    /**
     * Get the list of {@link Item}s in this room.
     * @return the list of items in this room, or {@link Optional#empty()}
     * if there is not a list of {@link Item}s in this room.
     */
    Optional<List<Item>> getItems();

    /**
     * Get the list of {@link PowerUp}s in this room.
     * @return the list of {@link PowerUp}s in this room, or {@link Optional#empty()}
     * if there is not a list of {@link PowerUp}s in this room.
     */
    Optional<List<PowerUp>> getPowerUps();

    /**
     * Get the {@link Player}.
     * @return the player, or {@link Optional#empty()}
     * if the {@link Player} is not in this room.
     */
    Optional<Player> getPlayer();

    /**
     * Get the list of {@link Enemy}.
     * @return the list of {@link Enemy}, or {@link Optional#empty()}
     * if there are no {@link Enemy} in this room.
     */
    Optional<List<Enemy>> getEnemies();

    /**
     * return width of this room.
     *
     * @return width of this room
     */
    int getWidth();

    /**
     * return height of this room.
     *
     * @return height of this room
     */
    int getHeight();

    /**
     * Check if this room is complete.
     * A room is complete if all enemies in there are defeated.
     * Rooms with neither enemies nor boss are already complete.
     * @return true if this room is complete, false otherwise
     */
    boolean isComplete();

    /**
     * Put the player inside this room. To be used together with {@link #removePlayer()}
     * to move a player from a Room to another.
     * @param player the player to be placed in this room
     * @return true if the player has been placed successfully in this room, false otherwise
     */
    boolean addPlayer(Player player);

    /**
     * Remove the player from this room. To be used together with {@link #addPlayer(Player)}
     * to move a player from a Room to another.
     * @return true if the player has been removed successfully from this room, false otherwise
     */
    boolean removePlayer();

    /**
     * Updates the enemies inside the room, if present. Otherwise, does nothing.
     */
    void updateRoom();

    /**
     * Receives an event and adds to the queue.
     * @param event the event to add to the queue
     */
    void notifyEvent(Event event);

    /**
     * Executes all events in queue.
     */
    void executeEvents();
}
