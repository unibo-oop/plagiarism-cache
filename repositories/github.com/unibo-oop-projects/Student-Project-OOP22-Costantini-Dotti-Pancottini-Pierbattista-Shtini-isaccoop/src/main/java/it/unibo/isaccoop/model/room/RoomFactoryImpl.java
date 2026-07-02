package it.unibo.isaccoop.model.room;

import it.unibo.isaccoop.model.common.Point2D;
import it.unibo.isaccoop.model.common.RoomType;

/**
 * Implementation of {@link RoomFactory}.
 */
public final class RoomFactoryImpl implements RoomFactory {

    // messages when an exception is thrown
    private static final int ROOM_WIDTH = 300;
    private static final int ROOM_HEIGHT = 200;
    private static final String ALREADY_GENERATED_ALL_ROOMS = "you have already generated all the required rooms";
    private static final String START_ROOM_MUST_BE_FIRST = "the START room must be the FIRST to be generated";
    private static final String BOSS_ROOM_MUST_BE_LAST = "the BOSS room must be the LAST to be generated";
    private static final String CANNOT_CREATE_MORE_ROOMS = "cannot create more rooms";

    private final int width;
    private final int height;
    private int roomCount;
    private final RoomFactoryLogics rFactoryLogics;

    /**
     * Constructor. Requires the total number of rooms to be created.
     * @param totalNumberOfRooms the total number of rooms to be created
     */
    public RoomFactoryImpl(final int totalNumberOfRooms) {
        this.width = ROOM_WIDTH;
        this.height = ROOM_HEIGHT;
        this.roomCount = 0;
        this.rFactoryLogics = new RoomFactoryLogics(totalNumberOfRooms);
    }

    @Override
    public Room buildStartRoom(final Point2D coordInsideLevel) {
        if (this.rFactoryLogics.canBuildStartRoom(this.roomCount)
                && !this.rFactoryLogics.hasAlreadyBuiltStartRoom()) {
            incrementRoomCount();
            this.rFactoryLogics.setAlreadyBuiltStartRoom();
            return new RoomBuilder.Builder(this.width, this.height)
                    .roomType(RoomType.START)
                    .putCoord(coordInsideLevel)
                    .build();
        }
        throw new IllegalStateException(START_ROOM_MUST_BE_FIRST);
    }

    @Override
    public Room buildStandardRoom(final Point2D coordInsideLevel) {
        if (this.rFactoryLogics.canBuildNonBossNonStartRoom(roomCount)) {
            incrementRoomCount();
            return new RoomBuilder.Builder(this.width, this.height)
                    .roomType(RoomType.STANDARD)
                    .putCoord(coordInsideLevel)
                    .putEnemies()
                    .putItems()
                    .build();
        }
        throw new IllegalStateException(ALREADY_GENERATED_ALL_ROOMS);
    }

    @Override
    public Room buildShopRoom(final Point2D coordInsideLevel) {
        if (this.rFactoryLogics.canBuildNonBossNonStartRoom(roomCount)
                && !this.rFactoryLogics.hasAlreadyBuiltShopRoom()) {
            incrementRoomCount();
            this.rFactoryLogics.setAlreadyBuiltShopRoom();
            return new RoomBuilder.Builder(this.width, this.height)
                    .roomType(RoomType.SHOP)
                    .putCoord(coordInsideLevel)
                    .putPowerUps()
                    .build();
        }
        throw new IllegalStateException(ALREADY_GENERATED_ALL_ROOMS);
    }

    @Override
    public Room buildBossRoom(final Point2D coordInsideLevel) {
        if (this.rFactoryLogics.canBuildBossRoom(roomCount)
                && !this.rFactoryLogics.hasAlreadyBuiltBossRoom()) {
            incrementRoomCount();
            this.rFactoryLogics.setAlreadyBuiltBossRoom();
            return new RoomBuilder.Builder(this.width, this.height)
                    .roomType(RoomType.BOSS)
                    .putCoord(coordInsideLevel)
                    .putEnemies()
                    .build();
        }
        throw new IllegalStateException(BOSS_ROOM_MUST_BE_LAST);
    }

    @Override
    public Room buildTreasureRoom(final Point2D coordInsideLevel) {
        if (this.rFactoryLogics.canBuildNonBossNonStartRoom(roomCount)
                && !this.rFactoryLogics.hasAlreadyBuiltTreasureRoom()) {
            incrementRoomCount();
            this.rFactoryLogics.setAlreadyBuiltTreasuretRoom();
            return new RoomBuilder.Builder(this.width, this.height)
                    .roomType(RoomType.TREASURE)
                    .putCoord(coordInsideLevel)
                    .putPowerUps()
                    .build();
        }
        throw new IllegalStateException(ALREADY_GENERATED_ALL_ROOMS);
    }

    @Override
    public Room buildRoomInProperOrder(final Point2D coordInsideLevel) {
        if (this.rFactoryLogics.canBuildStartRoom(roomCount)
                && !this.rFactoryLogics.hasAlreadyBuiltStartRoom()) {
            return buildStartRoom(coordInsideLevel);
        }
        if (this.rFactoryLogics.canBuildBossRoom(roomCount)
                && !this.rFactoryLogics.hasAlreadyBuiltBossRoom()) {
            return buildBossRoom(coordInsideLevel);
        }
        if (this.rFactoryLogics.canBuildNonBossNonStartRoom(roomCount)
                && !this.rFactoryLogics.hasAlreadyBuiltShopRoom()) {
            return buildShopRoom(coordInsideLevel);
        }
        if (this.rFactoryLogics.canBuildNonBossNonStartRoom(roomCount)
                && !this.rFactoryLogics.hasAlreadyBuiltTreasureRoom()) {
            return buildTreasureRoom(coordInsideLevel);
        }
        if (this.rFactoryLogics.canBuildNonBossNonStartRoom(roomCount)) {
            return buildStandardRoom(coordInsideLevel);
        }
        throw new IllegalStateException(CANNOT_CREATE_MORE_ROOMS);
    }

    /**
     * Increments the room count. Useful to check if a specified room can be generated in that index.
     * (START room must alway be the first, BOSS room the last)
     */
    private void incrementRoomCount() {
        this.roomCount++;
    }
}
