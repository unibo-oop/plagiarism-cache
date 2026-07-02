package todo.view.drawables.screens;

import com.badlogic.gdx.Graphics.DisplayMode;

/**
 * The resolution updater is a builder to update the internal state of a
 * {@link ResolutionManager}. Implementations must not update the internal
 * {@link ResolutionManager} state until the {@link #apply} method is called,
 * and atomically change the state when the method is called.
 */
public interface ResolutionUpdater {
    /**
     * Set the display mode to the specified one. The display mode must be present
     * in {@link #getSupportedDisplayModes()}.
     *
     * @param mode is the display mode to use
     * @return the updater
     */
    ResolutionUpdater setDisplayMode(DisplayMode mode);

    /**
     * Set the fullscreen flag to the specified value.
     *
     * @param active is true if the display mode should be fullscreen
     * @return the updater
     */
    ResolutionUpdater setFullscreen(boolean active);

    /**
     * Apply the changes to the resolution/aspect ratio.
     */
    void apply();
}
