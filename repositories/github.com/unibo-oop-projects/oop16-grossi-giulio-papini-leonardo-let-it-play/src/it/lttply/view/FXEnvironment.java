package it.lttply.view;

import it.lttply.view.fxmlscreens.FXMLScreens;
import javafx.scene.Node;
import javafx.stage.Stage;

/**
 * Interface that allows to load the view.
 */
public interface FXEnvironment {

    /**
     * Shows the view.
     *
     * @param primaryStage
     *            the stage of the view
     */
    void start(Stage primaryStage);

    /**
     * @return The main application window
     */
    Stage getMainStage();

    /**
     *
     * @param screen
     *            the fxml of the view
     * @return the Node loaded
     *
     */
    Node getNode(FXMLScreens screen);

    /**
     * Display the main window.
     */
    void show();

    /**
     * @param screen
     *            the screen to display
     */
    void displayScreen(FXMLScreens screen);

    /**
     * Loads a screen ad sets its controller.
     *
     * @param screen
     *            the fxml of the view
     * @param controller
     *            the controller of the view
     */
    void loadScreen(FXMLScreens screen, Object controller);

}