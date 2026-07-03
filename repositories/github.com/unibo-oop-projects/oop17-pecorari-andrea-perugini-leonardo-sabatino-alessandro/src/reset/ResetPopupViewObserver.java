package reset;

import javafx.event.ActionEvent;
import javafx.stage.Stage;


public interface ResetPopupViewObserver {

	/**
	 * close current window and start a new game
	 */
	void tryAgain(ActionEvent event);
	
	/**
	 * initializes the view
	 */
	void start(Stage primaryStage);
	
}
