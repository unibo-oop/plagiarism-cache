package controller;

import java.io.IOException;

import javafx.application.Application;
import javafx.stage.Stage;
import view.View;
import view.ViewGuiImpl;

/**
 * 
 */
public class F1Fantarace extends Application {


    private class Main extends Thread {

        private final View view;

        Main(final View view) {
            this.view = view;
            this.setDaemon(true);
        }

        public void run() {
            new ControllerImpl(view);
        }
    }

    @Override
    public void start(final Stage stage) throws IOException {
        final View view = new ViewGuiImpl();
        view.startMenu(stage);
        new Main(view).start();
    }

    /**
     * Main.
     * @param args parameters
     */
    public static void main(final String[] args) {
        //new ControllerImpl(new ViewImpl());
        launch(new String[0]); // Block the execution until GUI stop
    }

}
