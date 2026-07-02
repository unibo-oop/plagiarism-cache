package jwhale.view;


import javafx.application.Application;
import javafx.stage.Stage;
import jwhale.controller.Controller;
import jwhale.controller.ControllerImpl;
import jwhale.model.Model;
import jwhale.model.ModelImpl;
/**
 * JavaFX launcher.
 */
public final class JWhale extends Application {

    public static void main(final String[] args) {
        launch(args);
    }

    @Override
    public void start(final Stage stage) throws Exception {
        final Model model = new ModelImpl();
        final Controller controller = new ControllerImpl(model);
        final View view = new ViewImpl(stage, controller);
        view.launch();
    }
}
