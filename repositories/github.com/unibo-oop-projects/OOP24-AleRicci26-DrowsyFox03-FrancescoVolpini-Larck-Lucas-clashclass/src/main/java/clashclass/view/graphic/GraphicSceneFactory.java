package clashclass.view.graphic;

/**
 * Factory interface for creating scenes in the application.
 */
public interface GraphicSceneFactory {
    /**
     * Creates and returns a new BaseScene representing the menu.
     *
     * @param window the window context in which the menu scene will be used
     *
     * @return a BaseScene instance representing the menu
     */
    BaseScene createMenuScene(Window window);

    /**
     * Creates the player village scene.
     *
     * @param window the window reference
     *
     * @return the player village scene
     */
    BaseScene createPlayerVillageScene(Window window);
}
