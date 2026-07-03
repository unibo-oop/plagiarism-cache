package reset;

import javafx.event.ActionEvent;
import javafx.stage.Stage;

public interface ResetPopupView {

	void tryAgain(ActionEvent event);
	
	void start(Stage primaryStage);
	
	
}
