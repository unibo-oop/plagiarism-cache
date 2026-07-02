package theMilkWay;

import javafx.application.Application;
import javafx.geometry.Dimension2D;
import javafx.stage.Stage;
import tmw.controller.MainControllerImpl;
import tmw.view.MainViewImpl;

/**
 * Main class of whole application. This represents the starting point from
 * which launch the game.
 */
public class TheMilkWay extends Application {

    /**
     * Main method to launch javaFx application.
     * 
     * @param args {@link String[]} parameter to pass
     */
    public static void main(final String[] args) {
        launch(args);
    }

    /**
     * Starts whole application by initialize {@link MainView} and
     * {@link MainController}.
     */
    @Override
    public void start(final Stage primaryStage) throws Exception {

        final MainViewImpl view = new MainViewImpl(primaryStage, new Dimension2D(800, 600));
        new MainControllerImpl(view).init();
    }

}
