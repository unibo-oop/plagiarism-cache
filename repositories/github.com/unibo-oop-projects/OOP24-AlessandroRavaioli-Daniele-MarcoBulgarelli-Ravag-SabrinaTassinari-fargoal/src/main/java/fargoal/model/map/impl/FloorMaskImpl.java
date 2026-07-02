package fargoal.model.map.impl;

import java.util.List;
import java.util.HashMap;
import java.util.Map;

import fargoal.commons.api.Position;
import fargoal.model.manager.api.FloorManager;
import fargoal.model.map.api.FloorMask;

/**
 * A class that implements the map covering effect.
 */
public class FloorMaskImpl implements FloorMask {

    private static final int FLOOR_HEIGTH = 25;
    private static final int FLOOR_LENGTH = 40;

    private final Map<Position, Boolean> mask;

    /**
     * Constrctor that inizializes the mask as complete darkness and defines the renderers.
     */
    public FloorMaskImpl() {
        this.mask = new HashMap<>();
        this.resetMask();
    }

    /**
     * Constructor that sets the field {@link #mask} to the given one.
     * Used mainly for testing.
     * @param mask - the initial value of the mask
     */
    public FloorMaskImpl(final Map<Position, Boolean> mask) {
        this.mask = Map.copyOf(mask);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void resetMask() {
        for (int i = 0; i < FLOOR_LENGTH; i++) {
            for (int j = 0; j < FLOOR_HEIGTH; j++) {
                mask.put(new Position(i, j), false);
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update(final FloorManager manager) {
        final int lightRange = manager.getPlayer().hasLight() ? 2 : 1;
        final List<Position> lightUp = this.mask.keySet().stream()
                .filter(e -> Math.abs(e.x() - manager.getPlayer().getPosition().x()) <= lightRange
                        && Math.abs(e.y() - manager.getPlayer().getPosition().y()) <= lightRange)
                .toList();
        lightUp.forEach(p -> this.mask.replace(p, true));
        for (int x = 0; x < FLOOR_LENGTH; x++) {
            for (int y = 0; y < FLOOR_HEIGTH; y++) {
                final var pos = new Position(x, y);
                if (this.mask.get(pos)) {
                    manager.getFloorMap().render(pos);
                }
            }
        }

        for (final var element : manager.getAllElements()) {
            if (this.mask.get(element.getPosition())) {
                element.render();
            }
        }

        manager.getPlayer().render();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void clearMask() {
        for (int i = 0; i < FLOOR_LENGTH; i++) {
            for (int j = 0; j < FLOOR_HEIGTH; j++) {
                mask.replace(new Position(i, j), true);
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((mask == null) ? 0 : mask.hashCode());
        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final FloorMaskImpl other = (FloorMaskImpl) obj;
        if (mask == null) {
            if (other.mask != null) {
                return false;
            }
        } else {
           return this.mask.entrySet().containsAll(other.mask.entrySet());
        }
        return false;
    }

}
