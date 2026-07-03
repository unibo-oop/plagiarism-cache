package application;

import controller.Controller;
import model.ReadWriteData;
import javafx.application.Application;
import javafx.stage.Stage;
import view.ViewUtils;
import model.DataImpl;

public class NewClubSportman extends Application {

	@Override
	public void start(Stage primaryStage) {
		ViewUtils.openWindow();
	}

	@Override
	public void stop() throws Exception {
		ReadWriteData.write(DataImpl.getInstance());
	}

	public static void main(String[] args) {
		Controller.getInstance();
		launch(args);
	}
}
