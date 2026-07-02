package todo.view.menu;

import com.badlogic.gdx.Graphics.DisplayMode;

import todo.view.screens.RoomScreen;

public interface MenuObserver {
    /**
     * Set the {@link RoomScreen}.
     *
     * @param title the title of the level to be uploaded
     */
    void openLevelScreen(String title);

    /**
     * Set the {@link SettingsScreen}.
     */
    void openSettingsScreen();

    /**
     * Set the {@link MenuScreen}.
     */
    void openMenuScreen();

    /**
     * Change the current {@link DisplayMode}.
     *
     * @param mode the new {@link DisplayMode}
     */
    void changeDisplayMode(DisplayMode mode);

    /**
     * Set the game in window mode or fullscreen depending on {@link setWindowMode}.
     *
     * @param setFullscreen if true it sets the game in fullscreen mode
     */
    void changeWindowOrFullscreen(boolean setFullscreen);

    /**
     * Close the application.
     */
    void close();
}
