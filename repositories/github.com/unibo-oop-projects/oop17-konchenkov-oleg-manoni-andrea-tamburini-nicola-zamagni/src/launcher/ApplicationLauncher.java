package launcher;

import controller.Controller;
import controller.ControllerImpl;
import controller.GameStatus;
import javafx.application.Application;
import javafx.stage.Stage;
import view.View;
import view.ViewImpl;

/**
 * Launch application.
 *
 * @author Oleg, Nicola Tamburini
 *
 */
public final class ApplicationLauncher extends Application {

    private static final double WIDTH = 1024;
    private static final double HEIGHT = 720;

    @Override
    public void start(final Stage primaryStage) throws Exception {
        primaryStage.setTitle("Tank Fury");
        primaryStage.setWidth(WIDTH);
        primaryStage.setHeight(HEIGHT);
        final Controller controller = ControllerImpl.getInstanceOf();
        final View view = new ViewImpl(controller);
        controller.setView(view);
        controller.update(GameStatus.LOAD);
        view.startSceneMainController(primaryStage);
    }

    /**
     * Main.
     *
     * @param args
     *            args
     */
    public static void main(final String... args) {
        launch(args);
    }

}
