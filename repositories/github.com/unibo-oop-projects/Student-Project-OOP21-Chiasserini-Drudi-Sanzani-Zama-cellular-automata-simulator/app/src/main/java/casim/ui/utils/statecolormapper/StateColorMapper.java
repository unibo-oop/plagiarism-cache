package casim.ui.utils.statecolormapper;

import javafx.scene.paint.Color;

/**
 * A mapper which map to a state a specific {@link Color}.
 *
 * @param <T> the type of the states take as input.
 */
public interface StateColorMapper<T> {

    /**
     * Map a {@link Color} to the state take as input.
     * 
     * @param state the state to map to a {@link Color}.
     * @return the {@link Color} linked to the specified state.
     */
    Color toColor(T state);
}
