package it.tbt.model.entities.npc.impl;

import it.tbt.model.entities.EntityImpl;
import it.tbt.model.entities.npc.api.NPC;

/**
 * The {@code AbstractNPCImpl} class is an abstract implementation of the {@link NPC} interface.
 * It extends the {@link EntityImpl} class and provides basic functionality for NPCs.
 */
public abstract class AbstractNPCImpl extends EntityImpl implements NPC {

    private final int x;
    private final int y;
    private final int height;
    private final int width;

    /**
     * Constructs a new instance of the AbstractNPCImpl class with the specified name, position, and dimensions.
     *
     * @param name   the name of the NPC
     * @param x      the X coordinate of the NPC's position
     * @param y      the Y coordinate of the NPC's position
     * @param height the height of the NPC
     * @param width  the width of the NPC
     * @throws IllegalArgumentException if name is null or empty, or if height or width is negative
     */
    public AbstractNPCImpl(final String name, final int x, final int y, final int height, final int width) {
        super(name);
        if (height < 0) {
            throw new IllegalArgumentException("Height cannot be negative");
        }
        if (width < 0) {
            throw new IllegalArgumentException("Width cannot be negative");
        }
        this.x = x;
        this.y = y;
        this.height = height;
        this.width = width;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getX() {
        return x;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getY() {
        return y;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getHeight() {
        return height;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getWidth() {
        return width;
    }
}
