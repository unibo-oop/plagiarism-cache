package clashclass;

import clashclass.view.graphic.WindowJavaFX;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * JavaFX Application.
 */
public final class JavaFXApp extends Application {

    @Override
    public void start(final Stage primaryStage) {
        new WindowJavaFX().launchWindow();
    }

    /**
     * Entry point's class.
     */
    public static final class Main {
        private Main() {
            // the constructor will never be called directly.
        }

        /**
         * Program's entry point.
         *
         * @param args ignored
         */
        public static void main(final String... args) {
            launch(JavaFXApp.class, args);
        }
    }
}
