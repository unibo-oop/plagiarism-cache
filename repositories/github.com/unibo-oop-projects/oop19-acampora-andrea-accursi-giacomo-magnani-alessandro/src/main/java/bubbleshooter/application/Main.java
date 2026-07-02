package bubbleshooter.application;

import bubbleshooter.controller.Controller;
import bubbleshooter.controller.ControllerImpl;
import bubbleshooter.model.Model;
import bubbleshooter.model.ModelImpl;
import bubbleshooter.view.View;
import bubbleshooter.view.ViewImpl;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * This class represent the Main class of the Application.
 */
public final class Main extends Application {

    @Override
    public void start(final Stage windowStage) throws Exception {
        final View view = new ViewImpl(windowStage);
        final Model model = new ModelImpl();
        final Controller controller = new ControllerImpl(model, view);
        view.launch(controller);
    }

    /**
     * 
     * @param args unused
     */
    public static void main(final String[] args) {
        launch();
    }

}
