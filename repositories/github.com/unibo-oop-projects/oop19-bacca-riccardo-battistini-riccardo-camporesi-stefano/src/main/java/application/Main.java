package application;

import controller.Controller;
import controller.ControllerImpl;
import javafx.application.Application;
import javafx.stage.Stage;
import model.Scenario;
import model.ScenarioImpl;
import view.View;
import view.ViewImpl;

/**
 * This class represent the Main class of TrafficSimulator.
 */
public final class Main extends Application {

    @Override
    public void start(final Stage stage) throws Exception {
        final View view = new ViewImpl(stage);
        final Scenario scenario = new ScenarioImpl();
        final Controller controller = new ControllerImpl(view, scenario);
        view.launch(controller);
    }

    /**
     * @param args unused
     */
    public static void main(final String[] args) {
        launch();
    }

}
