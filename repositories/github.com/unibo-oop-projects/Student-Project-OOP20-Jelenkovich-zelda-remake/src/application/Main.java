package application;

import javafx.application.Application;
import javafx.stage.Stage;
import view.ViewManager;

/**
 * Main class dell'aplicazione Javafx
 * @author Angela
 *
 */
public final class Main extends Application {

    @Override
    public void start(Stage stage) {
    	try {
    		ViewManager manager = new ViewManager();
    		stage = manager.getMainStage();
    		stage.setTitle("Zelda");
    		stage.setResizable(false);
    		stage.show();
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    }

    /**
     * Main metodo
     */
    public static void main(final String[] args) {
        launch(args);
    }

}