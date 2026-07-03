package start;

import start.StartViewImpl;
import javafx.application.Application;
import javafx.stage.Stage;

public class StartApp extends Application implements StartAppObserver {

	@Override
	public void start(Stage primaryStage){
		init(primaryStage);
	}

	@Override
	public void init(Stage primaryStage) {
		StartViewImpl.getStartView().start(primaryStage);
	}
	
	public static void main(String[] args) {
		launch(args);
	}

}
