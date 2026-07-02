package it.tbt.model.world.impl;

import it.tbt.model.entities.KillableEntity;
import it.tbt.model.world.api.KillObserver;
import it.tbt.model.world.api.Room;
import it.tbt.model.entities.SpatialEntity;

import java.util.HashSet;
import java.util.Set;

/**
 * Default implementation of the Room interface.
 */

public class RoomImpl implements Room, KillObserver {

    private final int roomWidth;
    private final int roomHeight;
    private final Set<SpatialEntity> entities;
    /**
     * default Height of this RoomImpl as integer.
     */
    public static final int DEFAULT_HEIGHT_ROOM = 300;
    /**
     * default Width of this RoomImpl as integer.
     */
    public static final int DEFAULT_WIDTH_ROOM = 300;
    /**
     * @param roomWidth the room's width
     * @param roomHeight the room's height
     */
    public RoomImpl(final int roomWidth, final int roomHeight) {
        this.roomHeight = roomHeight;
        this.roomWidth = roomWidth;
        entities = new HashSet<>();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addEntity(final SpatialEntity entity) {
        if (isValidCoordinates(entity.getX(), entity.getY(), entity.getWidth(), entity.getHeight())) {
            entities.add(entity);
            if (entity instanceof KillableEntity) {
                ((KillableEntity) entity).setKillObserver(this);
            }

        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Set<SpatialEntity> getEntities() {
        return Set.copyOf(entities);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getHeight() {
        return this.roomHeight;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getWidth() {
        return this.roomWidth;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Boolean isValidCoordinates(final int xCenter, final int yCenter, final int width, final int height) {
        final int left = xCenter - (width / 2);
        final int right = xCenter + (width / 2);
        final int top = yCenter - (height / 2);
        final int bottom = yCenter + (height / 2);
        return !(left < 0 || right > this.roomWidth || top < 0 || bottom > this.roomHeight);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onKill(final SpatialEntity spatialEntity) {
        this.entities.remove(spatialEntity);
    }
}
