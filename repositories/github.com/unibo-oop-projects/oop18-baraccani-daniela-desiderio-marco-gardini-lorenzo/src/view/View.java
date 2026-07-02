package view;

import controller.gameloop.GameController;
import javafx.scene.Parent;
import view.viewmanager.ViewManager;

/**
 * 
 * View is the interface for controlling the main feature and aspects of the
 * view.
 *
 */
public interface View {

    /**
     * Set the controller the application is managed by.
     * 
     * @param controller is the {@link GameController}
     */
    void setController(GameController controller);

    /**
     * Show the user interface.
     */
    void showUI();

    /**
     * @return the controller the application is managed by
     */
    GameController getController();

    /**
     * @throws NullPointerException
     * @param scene Set scene as the current scene in the view. If scene is null
     *              throws {@link NullPointerException}
     */
    void setScene(Parent scene);

    /**
     * Close the application.
     */
    void close();

    /**
     * Open a {@link Dialog} that specify an error.
     * 
     * @param message  to be shown in the dialog.
     * @param critical true if, after the error, the application closes.
     */
    void showError(String message, boolean critical);

    /**
     * @return the ViewManager
     */
    ViewManager getViewManager();
}
