package launcher;

import controller.gameloop.GameController;
import controller.gameloop.GameControllerImpl;
import javafx.application.Application;
import javafx.stage.Stage;
import model.ModelImpl;
import view.ViewImpl;

/**
 * Class to start the game.
 */
public class Launcher extends Application {

    /**
     * The main method.
     * 
     * @param args input arguments
     */
    public static void main(final String[] args) {
        Application.launch(args);
    }

    /**
     * Start the game.
     */
    @Override
    public void start(final Stage stage) {
        GameController gameController = null;
        try {
            gameController = new GameControllerImpl(new ViewImpl(stage), new ModelImpl());
        } catch (Exception e) {
            e.printStackTrace();
            gameController.handleError(e.getMessage() + "\n\n" + e.getStackTrace(), true);
        }
    }
}
