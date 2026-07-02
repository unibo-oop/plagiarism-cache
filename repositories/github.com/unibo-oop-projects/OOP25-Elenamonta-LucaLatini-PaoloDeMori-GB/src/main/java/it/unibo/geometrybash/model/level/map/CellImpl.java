package it.unibo.geometrybash.model.level.map;

import java.util.Optional;

import it.unibo.geometrybash.model.core.GameObject;

/**
 * Implementation of the {@link Cell} interface.
 */
public final class CellImpl implements Cell {

    private final Optional<GameObject<?>> gameOptional;

    /**
     * Create new cell implementation containing the specified game object.
     *
     * @param gameObject the object to be placed in this cell.
     */
    public CellImpl(final GameObject<?> gameObject) {
        this.gameOptional = Optional.of(gameObject);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<GameObject<?>> getGameObject() {
        return this.gameOptional;
    }

}
