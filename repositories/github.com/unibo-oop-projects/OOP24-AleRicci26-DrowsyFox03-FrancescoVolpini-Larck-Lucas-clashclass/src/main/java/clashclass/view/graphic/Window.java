package clashclass.view.graphic;

/**
 * Interface that represents the concept of window.
 */
public interface Window {
    /**
     * Launches the window by creating and initializing a menu scene.
     */
    void launchWindow();

    /**
     * Method to create a menu scene for this window.
     *
     * @return a menu window as a BaseScene
     */
    BaseScene createMenuScene();
}
