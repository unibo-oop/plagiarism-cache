package it.unibo.isaccoop.model.room;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import it.unibo.isaccoop.core.GameEngine;
import it.unibo.isaccoop.graphics.factory.ConcretePlayerGraphicsComponentFactory;
import it.unibo.isaccoop.model.common.Direction;
import it.unibo.isaccoop.model.common.RoomType;
import it.unibo.isaccoop.model.player.Player;

/**
 * Implementation of {@link Level}.
 */
public final class LevelImpl implements Level {

    private final List<Room> rooms = new LinkedList<>();
    private final Optional<Player> player;
    private final Minimap minimap;

    /**
     * Empty Constructor.
     * @param engine
     */
    public LevelImpl(final GameEngine engine) {
        this.minimap = new MinimapImpl(this);
        this.player = Optional.of(new Player(engine.getController("keyMove"), engine.getController("keyShot"),
                                    new ConcretePlayerGraphicsComponentFactory().getPlayerGraphicsComponent()));
    }

    @Override
    public void putRooms(final List<Room> roomList) {
        if (roomList.isEmpty() || roomList.size() < RoomType.values().length) {
            throw new IllegalArgumentException("This level must have at least " + RoomType.values().length
                    + " rooms");
        }
        if (this.rooms.isEmpty()) {
            this.rooms.addAll(roomList);
            getStartRoom().addPlayer(this.player.get());
        } else {
            throw new IllegalStateException("This level already has a room list");
        }
    }

    @Override
    public List<Room> getRooms() {
        return List.copyOf(this.rooms);
    }

    @Override
    public boolean isLevelComplete() {
        return this.rooms.stream().allMatch(r -> r.isComplete());
    }

    @Override
    public Room getStartRoom() {
        return this.rooms.stream().filter(r -> r.getRoomType() == RoomType.START)
                .findFirst().get();
    }

    @Override
    public Room getCurrentRoom() {
        return this.rooms.stream().filter(r -> r.getPlayer().isPresent())
                .findFirst().get();
    }

    @Override
    public Map<Direction, Room> getNearRooms() {
        return new LevelUtils().getNearAccessibleRooms(getCurrentRoom(), List.copyOf(this.rooms));
    }

    @Override
    public Player getPlayer() {
        return getCurrentRoom().getPlayer().get();
    }

    @Override
    public boolean isCurrentRoomComplete() {
        return getCurrentRoom().isComplete();
    }

    @Override
    public Map<Direction, Room> getAccessibleRooms() {
        return getNearRooms();
    }

    @Override
    public void moveToPreviousRoom() {
        getPrevNextRoom(Direction.LEFT).ifPresent(r -> moveToRoom(r));
    }

    @Override
    public void moveToNextRoom() {
        getPrevNextRoom(Direction.RIGHT).ifPresent(r -> moveToRoom(r));
    }

    @Override
    public Minimap getMinimap() {
        return this.minimap;
    }

    /**
     * Utility method to check if the player can move to the specified room.
     * @param room the room to move to
     * @return true if the player can move to the given room, false otherwise
     */
    private boolean moveToRoom(final Room room) {
        final Player player = getPlayer();
        if (isValidNewRoom(room) && getCurrentRoom().isComplete() && getCurrentRoom().removePlayer()) {
            room.addPlayer(player);
            return true;
        }
        return false;
    }

    /**
     * Get the previous or next room of current room.
     * @param dir {@link Direction#RIGHT} to get the next room,<br>
     * {@link Direction#LEFT} to get the previous one
     * @return the previous or next room of current room, or Optional.empty if not available
     */
    private Optional<Room> getPrevNextRoom(final Direction dir) {
        final var prevNextRoom = getAccessibleRooms().entrySet().stream()
                .filter(e -> e.getKey() == dir).findFirst();
        if (prevNextRoom.isPresent()) {
            return Optional.of(prevNextRoom.get().getValue());
        }
        return Optional.empty();
    }

    private boolean isValidNewRoom(final Room destRoom) {
        return getCurrentRoom().getCoords().getX() - destRoom.getCoords().getX() <= 1.0
                && getCurrentRoom().getCoords().getY() - destRoom.getCoords().getY() <= 1.0;
    }
}
