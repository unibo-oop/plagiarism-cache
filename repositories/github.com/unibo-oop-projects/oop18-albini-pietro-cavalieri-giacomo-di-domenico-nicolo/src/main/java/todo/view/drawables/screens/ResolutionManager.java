package todo.view.drawables.screens;

import java.util.List;

import com.badlogic.gdx.Graphics.DisplayMode;
import com.badlogic.gdx.utils.viewport.Viewport;

/**
 * This interface models a resolution manager. There can be only one instance at
 * a time through the entire game. Its task is to manage the current display
 * mode and apply any modifications.
 */
public interface ResolutionManager {
    /**
     * @return a list of all the supported display modes
     */
    List<DisplayMode> getSupportedDisplayModes();

    /**
     * @return the current display mode
     */
    DisplayMode getCurrentDisplayMode();

    /**
     * @return the current aspect ratio
     */
    AspectRatio getCurrentAspectRatio();

    /**
     * @return the current viewport
     */
    Viewport getCurrentViewport();

    /**
     * @return true if the display mode is fullscreen
     */
    boolean isFullscreen();

    /**
     * @return a builder to update the resolution
     */
    ResolutionUpdater update();

    /**
     * Get the scale factor for the {@link RoomAspectRatio}. It's the ratio between
     * the current display size and the original size of the room according to the
     * selected aspect ratio.
     *
     * @return the scale factor
     */
    float getScaleFactor();
}
