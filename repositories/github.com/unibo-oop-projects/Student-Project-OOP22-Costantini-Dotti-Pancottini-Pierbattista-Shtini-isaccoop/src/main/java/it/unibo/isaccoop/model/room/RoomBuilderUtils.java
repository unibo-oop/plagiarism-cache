package it.unibo.isaccoop.model.room;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import it.unibo.isaccoop.model.ai.AIEnemy;
import it.unibo.isaccoop.model.common.MapElement;
import it.unibo.isaccoop.model.common.RoomType;
import it.unibo.isaccoop.model.creator.ConcreteCreatorFactory;
import it.unibo.isaccoop.model.creator.CreatorFactory;
import it.unibo.isaccoop.model.enemy.Enemy;
import it.unibo.isaccoop.model.item.Item;
import it.unibo.isaccoop.model.player.Player;
import it.unibo.isaccoop.model.powerup.PowerUp;
import it.unibo.isaccoop.model.spawn.SpawnOrdered;
import it.unibo.isaccoop.model.spawn.SpawnRandom;

/**
 * Delegated class that contains utility methods to help {@link RoomBuilder} creating 
 * a room.
 */
public final class RoomBuilderUtils {

    // factory that creates enemies, items and powerups
    private final CreatorFactory creatorFactory = new ConcreteCreatorFactory();
    private final RoomType roomType;

    /**
     * Create a RoomBuilderUtil object, to access utility methods.
     * @param roomType the type of the room
     */
    public RoomBuilderUtils(final RoomType roomType) {
        this.roomType = roomType;
    }

    /**
     * Check if the current room to build can have the Player object.
     * @return true if the room can have the Player object, false otherwise
     */
    public boolean canRoomHavePlayer() {
        return this.roomType == RoomType.START;
    }

    /**
     * Check if the current room to build can have enemies.
     * @return true if the room can have enemies, false otherwise
     */
    public boolean canRoomHaveEnemies() {
        return this.roomType == RoomType.STANDARD || this.roomType == RoomType.BOSS;
    }

    /**
     * Check if the current room to build can have powerups.
     * @return true if the room can have powerups, false otherwise
     */
    public boolean canRoomHavePowerUps() {
        return this.roomType == RoomType.SHOP || this.roomType == RoomType.TREASURE;
    }

    /**
     * Check if the current room to build can have items.
     * @return true if the room can have items, false otherwise
     */
    public boolean canRoomHaveItems() {
        return this.roomType == RoomType.STANDARD;
    }

    /**
     * Method that generates enemies, according to the {@link RoomType}.
     * @return the list of enemies
     */
    public Optional<List<Enemy>> generateEnemies() {
        if (this.roomType == RoomType.BOSS) { //for BOSS room
            return Optional.of(creatorFactory.createBoss().create());
        } else { // for STANDARD room
            return Optional.of(creatorFactory.createEnemies().create());
        }
    }

    /**
     * Method that generates items, according to the {@link RoomType}.
     * @return the list of items
     */
    public Optional<List<Item>> generateItems() {
        return Optional.of(creatorFactory.createItems().create());
    }

    /**
     * Method that generates powerups, according to the {@link RoomType}.
     * @return the list of powerups
     */
    public Optional<List<PowerUp>> generatePowerups() {
        if (this.roomType == RoomType.SHOP) { // for SHOP room
            return Optional.of(creatorFactory.createShopPowerUps().create());
        } else { // for TREASURE ROOM
            return Optional.of(creatorFactory.createTreasurePowerUps().create());
        }
    }

    /**
     * Method to randomly spawn a list of {@link MapElement}.
     * @param list the list of MapElements to spawn
     * @param width width of the room
     * @param height height of the room
     */
    public void randomSpawn(final List<? extends MapElement> list, final int width, final int height) {
        new SpawnRandom().setPosition(new ArrayList<>(list), width, height);
    }

    /**
     * Method to orderly spawn a list of {@link MapElement}.
     * @param list the list of MapElements to spawn
     * @param width width of the room
     * @param height height of the room
     */
    public void orderedSpawn(final List<? extends MapElement> list, final int width, final int height) {
        new SpawnOrdered().setPosition(new ArrayList<>(list), width, height);
    }

    /**
     * Check if this room can be built. A room can be built only if
     * all required fields are set, depending on the {@link RoomType}.
     * @param items the list of items
     * @param powerups the list of powerups
     * @param enemies the list of enemies
     * @param player the player
     * @param roomAI the roomAI
     * @return true if the room can be built (all required fields are set),
     * false otherwise
     */
    public boolean canBuildRoom(final Optional<List<Item>> items, final Optional<List<PowerUp>> powerups,
            final Optional<List<Enemy>> enemies, final Optional<Player> player, final Optional<AIEnemy> roomAI) {
        switch (this.roomType) {
        case START:
            return items.isEmpty() && player.isEmpty() && enemies.isEmpty()
                    && powerups.isEmpty() && roomAI.isEmpty();
        case SHOP:
        case TREASURE:
            return items.isEmpty() && player.isEmpty() && enemies.isEmpty()
                    && powerups.isPresent() && roomAI.isEmpty();
        case STANDARD:
            return items.isPresent() && player.isEmpty() && enemies.isPresent()
                    && powerups.isEmpty() && roomAI.isPresent();
        case BOSS:
            return items.isEmpty() && player.isEmpty() && enemies.isPresent()
                    && powerups.isEmpty() && roomAI.isPresent();
        default:
            return false;
        }
    }
}
