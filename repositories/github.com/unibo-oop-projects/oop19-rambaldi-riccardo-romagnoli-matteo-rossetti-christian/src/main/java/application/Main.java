package application;

import javafx.application.Application;
import javafx.stage.Stage;
import view.base.BaseViewImpl;

/**
 * This class represent the Main class of the JavaFX-based application.
 */
public final class Main extends Application {

    @Override
    public void start(final Stage stage) throws Exception {
        new BaseViewImpl(stage);
    }

    /**
     * @param args unused
     */
    public static void main(final String[] args) {
        launch();
    }

}
