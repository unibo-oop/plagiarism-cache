package it.unibo.isaccoop.model.room;

import java.util.List;
import java.util.Optional;

import it.unibo.isaccoop.model.ai.AIEnemy;
import it.unibo.isaccoop.model.ai.ConcreteAIEnemy;
import it.unibo.isaccoop.model.common.Point2D;
import it.unibo.isaccoop.model.common.RoomType;
import it.unibo.isaccoop.model.enemy.Enemy;
import it.unibo.isaccoop.model.item.Item;
import it.unibo.isaccoop.model.player.Player;
import it.unibo.isaccoop.model.powerup.PowerUp;

/**
 * Class to model the Builder pattern, to build a {@link Room} using the "fluent" style.
 */
public class RoomBuilder {

    // messages used when an exception is thrown
    private static final String ITEMS_IN_STANDARD_ROOM = "only STANDARD rooms can have items";
    private static final String ENEMIES_BOSS_STANDARD_ROOM = "only STANDARD and BOSS rooms can have enemies";
    private static final String POWERUPS_SHOP_TREASURE_ROOM = "only SHOP and TREASURE room can have powerups";
    private static final String INCORRECT_ROOM_CONFIG = "the room cannot be built: some required fields are not set";

    /**
     * Static class to actually implement the {@link RoomBuilder}.
     */
    public static class Builder {
        //basic and minimal fields (set with constructor)
        private final int width;
        private final int height;

        // other basic field (set with their dedicated methods)
        private Optional<Point2D> coord = Optional.empty();
        private Optional<RoomType> roomType = Optional.empty();

        // optional fields (to set with their dedicated methods)
        private Optional<List<Item>> items = Optional.empty();
        private Optional<List<PowerUp>> powerups = Optional.empty();
        private Optional<List<Enemy>> enemies = Optional.empty();
        private final Optional<Player> player = Optional.empty();
        private Optional<AIEnemy> roomAI = Optional.empty();

        // to access utility methods
        private RoomBuilderUtils builderUtils;

        /**
         * To build a Room, use {@link RoomFactory} instead. <br>
         * It is required to call this constructor first, then the REQUIRED methods.
         * <br> At the end, call the method build().
         *
         * @param width the horizontal dimension of this room
         * @param height the vertical dimension of this room
         */
        public Builder(final int width, final int height) {
            this.width = width;
            this.height = height;
        }

        /**
         * Method to set the coordinate of this room inside the level, REQUIRED for ALL rooms.
         *
         * @param coord the coordinate of this room inside the level
         * @return this builder
         */
        public Builder putCoord(final Point2D coord) {
            this.coord = Optional.of(coord);
            return this;
        }

        /**
         * Method to set the room type, REQUIRED for ALL rooms.
         *
         * @param roomType the type of room to be created
         * @return this builder
         */
        public Builder roomType(final RoomType roomType) {
            this.roomType = Optional.of(roomType);
            this.builderUtils = new RoomBuilderUtils(roomType);
            return this;
        }

        /**
         * Method to put items inside this room. ONLY for STANDARD rooms.
         * 
         * @return this builder
         */
        public Builder putItems() {
            if (this.builderUtils.canRoomHaveItems()) {
                this.items = this.builderUtils.generateItems();
                this.builderUtils.randomSpawn(this.items.get(), this.width, this.height);
                return this;
            }
            throw new IllegalStateException(ITEMS_IN_STANDARD_ROOM);
        }

        /**
         * Method to put the enemies inside this room (according to roomType). 
         * REQUIRED ONLY for STANDARD and BOSS rooms.
         * 
         * @return this builder
         * @throws IllegalStateException if called on NON STANDARD or NON BOSS rooms
         */
        public Builder putEnemies() {
            if (this.builderUtils.canRoomHaveEnemies()) {
                this.enemies = this.builderUtils.generateEnemies();
                this.builderUtils.randomSpawn(this.enemies.get(), width, height);
                this.roomAI = Optional.of(new ConcreteAIEnemy(this.enemies.get()));
                return this;
            }
            throw new IllegalStateException(ENEMIES_BOSS_STANDARD_ROOM);
        }

        /**
         * Method to put the powerups inside this room. ONLY for the SHOP and TREASURE rooms.
         * 
         * @return this builder
         * @throws IllegalStateException if called on NON SHOP or NON TREASURE rooms
         */
        public Builder putPowerUps() {
            if (this.builderUtils.canRoomHavePowerUps()) {
                this.powerups = this.builderUtils.generatePowerups();
                this.builderUtils.orderedSpawn(this.powerups.get(), width, height);
                return this;
            }
            throw new IllegalStateException(POWERUPS_SHOP_TREASURE_ROOM);
        }

        /**
         * Method to build the Room. First, call the constructor, then the REQUIRED methods to set this Room's fields.
         * <br>After that, you can call this method.
         *
         * @throws IllegalStateException if current Room has some REQUIRED fields unset.
         * @return the built Room
         */
        public Room build() {
            if (areThereMinimumRequirements() && canBuildRoom()) {
                return new RoomImpl(this.width, this.height, this.coord.get(),
                        this.roomType.get(), this.roomAI, this.items, this.powerups, 
                        this.player);
            }
            throw new IllegalStateException(INCORRECT_ROOM_CONFIG);
        }

        /**
         * Method to check if the minimum requirements are satisfied.
         * @return true if the minimum requirements are satisfied
         */
        private boolean areThereMinimumRequirements() {
            return this.coord.isPresent() && this.roomType.isPresent();
        }

        /**
         * Check if this room can be built. A room can be built only if
         * all required fields are set, depending on its {@link RoomType}.
         * @return true if the room can be built, i.e. if all required fields are set,
         * false otherwise
         */
        private boolean canBuildRoom() {
            return this.builderUtils.canBuildRoom(this.items, this.powerups, this.enemies, 
                    this.player, this.roomAI);
        }
    }
}
