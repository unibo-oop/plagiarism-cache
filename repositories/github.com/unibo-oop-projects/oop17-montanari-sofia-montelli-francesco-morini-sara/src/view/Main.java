package view;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Entry point of the program.
 */
public final class Main extends Application {

        @Override
        public void start(final Stage primaryStage) throws Exception {
                MasterViewImpl.getInstance().setPrimaryStage(primaryStage);
                MasterViewImpl.getInstance().getPrimaryStage().setTitle("OOP17-BATTLESHIP");
                MasterViewImpl.getInstance().initialSetup();
        }

        /**
         * @param args not used.
         */
        public static void main(final String[] args) {
               launch(args);
        }

}
