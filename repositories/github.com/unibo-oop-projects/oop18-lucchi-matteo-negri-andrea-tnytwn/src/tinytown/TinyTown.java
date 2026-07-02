package tinytown;

import javafx.application.Application;
import javafx.stage.Stage;
import view.scenecreator.MainMenu;

/**
 * The main class that start the application.
 */
public final class TinyTown extends Application {

    /**
     * 
     * @param args
     *          arguments
     */
    public static void main(final String[] args) {
        launch(args);
    }

    @Override
    public void start(final Stage primaryStage) throws Exception {
        new MainMenu(primaryStage);
    }
}
