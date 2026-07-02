package application;

import javafx.application.Application;
import javafx.stage.Stage;
import view.View;
import view.ViewImpl;

/**
 * This class represent the Main class of the JavaFX-based application.
 */
public final class Main extends Application {

    @Override
    public void start(final Stage stage) throws Exception {
        final View gui = new ViewImpl(stage);
        gui.init();
    }

    /**
     * 
     * @param args unused
     */
    public static void main(final String[] args) {
        launch();
    }

}
