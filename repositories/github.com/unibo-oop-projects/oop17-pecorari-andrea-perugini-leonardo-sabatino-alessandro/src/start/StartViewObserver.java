package start;

import javafx.event.ActionEvent;
import javafx.stage.Stage;

public interface StartViewObserver {

	void start(Stage primaryStage);

	void doButtonStart(ActionEvent event);

}
