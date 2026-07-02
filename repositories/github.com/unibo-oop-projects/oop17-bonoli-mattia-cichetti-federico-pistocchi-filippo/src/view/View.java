package view;
/**
 * View interface.
 */
public interface View {
    /**
     * start the menu showing the main menu.
     */
    void startMenu();

    /**
     * change the current displayed scene.
     * @param s to be displayed.
     * @return current displayed scene after change
     */
    AbstractScene changeScene(ViewImpl.GameScreen s);

}
