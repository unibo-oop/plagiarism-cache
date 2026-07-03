package start;

import game.GameApp;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.event.ActionEvent;

public class StartViewImpl extends Application implements StartViewObserver {

	private final static StartViewImpl STARTSINGLETON = new StartViewImpl();

	@FXML
	public void doButtonStart(final ActionEvent event) {
		
		try {
 			GameApp gameApp = new GameApp();
 			gameApp.start();
 			(((Node) event.getSource())).getScene().getWindow().hide();
 		} catch (NumberFormatException e) {
 			e.printStackTrace();
 		}
		
	}

	// private StartViewImpl() {};

	public static StartViewImpl getStartView() {
		return STARTSINGLETON;
	}

	@Override
	public void start(Stage primaryStage) {
		try {
			
			
			Group root = FXMLLoader.load(getClass().getResource("StartViewFX.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			
			primaryStage.setScene(scene);
			primaryStage.setFullScreen(false);
			primaryStage.setTitle("Dodge Bandicoot");
			primaryStage.show();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}