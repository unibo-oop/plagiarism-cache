package it.unibo.oop.crossline.launcher;

import java.awt.Dimension;
import java.awt.event.ActionListener;

/**
 * View of the launcher.
 */
public interface LauncherView {

    /**
     * Set the number of available screens.
     * @param screenCount the number of available screens
     */
    void setAvailableScreens(int screenCount);

    /**
     * Get the currently selected screen.
     * @return the selected screen index
     */
    int getSelectedScreen();

    /**
     * Set the selected screen.
     * @param index the index of the screen
     */
    void setSelectedScreen(int index);

    /**
     * Get the selected resolution.
     * @return the resolution
     */
    Dimension getResolution();

    /**
     * Set the resolution field.
     * @param resolution the resolution to apply
     */
    void setResolution(Dimension resolution);

    /**
     * Get the current fullscreen checkbox status.
     * @return the checkbox status
     */
    boolean isFullscreen();

    /**
     * Set the fullscreen checkbox.
     * @param fullscreen the fullscreen status
     */
    void setFullscreen(boolean fullscreen);

    /**
     * Get the current selected volume.
     * @return volume level
     */
    int getVolume();

    /**
     * Set the the volume slider level.
     * @param volume the volume level
     */
    void setVolume(int volume);

    /**
     * Set the the Play button listener.
     * @param listener the actions listener
     */
    void setPlayListener(ActionListener listener);

    /**
     * Set the launcher visibility.
     * @param visibility the visibility status
     */
    void setVisibility(boolean visibility);
}
