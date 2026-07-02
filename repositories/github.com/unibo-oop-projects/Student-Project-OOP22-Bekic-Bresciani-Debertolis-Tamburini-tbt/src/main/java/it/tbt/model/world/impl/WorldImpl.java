package it.tbt.model.world.impl;

import it.tbt.model.world.api.Room;
import it.tbt.model.world.api.World;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * Default implementation for World interface.
 */
public class WorldImpl implements World {
    private final Set<Room> rooms;
    private Optional<Room> start;
    /**
     * Default constructor of the World object.
     */
    public WorldImpl() {
        this.rooms = new HashSet<>();
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void addRoom(final Room room) {
        this.rooms.add(room);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Room> getListRoom() {
        return this.rooms.stream().toList();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Room> getStartRoom() {
        return this.start;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setStartRoom(final Room room) {
        this.start = Optional.of(room);
    }
}
