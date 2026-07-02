package javarogue.launcher;

import javafx.application.Application;
import javafx.stage.Stage;
import javarogue.ui.config.ConfigController;
import javarogue.ui.config.ConfigControllerImpl;
import javarogue.ui.config.ConfigModel;
import javarogue.ui.config.ConfigModelImpl;
import javarogue.ui.config.ConfigView;
import javarogue.ui.config.ConfigViewImpl;

/**
 * 
 * The entry point of the application. Launches a JavaFX application and handles
 * window to window messages.
 *
 */
public class Launcher extends Application {

	/**
	 * Entry point of the application
	 * 
	 * @param arguments
	 */
	public static void main(String... args) {
		// Launch JavaFX application
		launch();
	}

	@Override
	public void start(Stage PrimaryStage) throws Exception {
		// Launch the configuration window
		launchConfig();
	}

	/**
	 * Launches Configuration
	 */
	private void launchConfig() {
		// init MVC
		ConfigController controller = new ConfigControllerImpl();
		ConfigModel model = new ConfigModelImpl();
		ConfigView view = new ConfigViewImpl();
		controller.setModel(model);
		view.setController(controller);
		// Show view
		view.open();
	}

}
