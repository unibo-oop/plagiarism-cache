package starcatraz.application;

import javafx.stage.Stage;
import starcatraz.model.Achievements;
import starcatraz.model.Settings;
import starcatraz.model.Statistics;

/**
 * Interface for the Starcatraz application.
 */
public interface StarcatrazApp {

    /**
     * Application title.
     */
    String APP_TITLE = "Starcatraz";

    /**
     * Start method.
     * @param primaryStage
     */
    void start(Stage primaryStage);

    /**
     * Sets up the Stage.
     * @param width: window width
     * @param height: window height
     */
    void setupPrimaryStage(int width, int height);

    /**
     * Initializes the root layout.
     */
    void initRootLayout();

    /**
     * Shows a view.
     * @param path: path of the view to show
     */
    void showView(String path);

    /**
     * Shows GameView inside the root layout.
     */
    void showGameView();

    /**
     * @return the main stage
     */
    Stage getPrimaryStage();

    /**
     * @return the game settings object
     */
    Settings getSettings();

    /**
     * @return the game statistics object
     */
    Statistics getStatistics();

    /**
     * @return the game achievements object
     */
    Achievements getAchievements();

}
