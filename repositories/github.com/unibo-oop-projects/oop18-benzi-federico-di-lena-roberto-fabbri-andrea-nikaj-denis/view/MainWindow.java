package view;

import java.io.IOException;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * This class start and manage the main scene of the application
 * 
 *
 */
public class MainWindow extends Application {

	private final static double WINDOWS_WIDTH = 400;
	private final static double WINDOWS_HEIGHT = 650;
	private Stage windowStage;
	private Scene scene;

	/**
	 * This method create the basic stage of the game
	 * 
	 * @param windowStage
	 */
	public void start(Stage windowStage) throws Exception {
		this.windowStage = new Stage();
		this.windowStage.setHeight(WINDOWS_HEIGHT);
		this.windowStage.setWidth(WINDOWS_WIDTH);
		this.windowStage.setTitle("Race Traffic");
		this.windowStage.centerOnScreen();
		this.windowStage.setResizable(false);
		this.windowStage.setOnCloseRequest(e -> Platform.exit());
		this.showGame();
	}

	/**
	 * This method is used to load the fxml file for the menu
	 * 
	 * @throws IOException
	 */
	public void showGame() throws IOException {
		Parent root;
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation((getClass().getResource(FXMLPath.MENU.getPath())));
		root = loader.load();
		windowStage.setScene(new Scene(root));
		windowStage.show();
	}

	/**
	 * 
	 * @param scene
	 */
	public void setScene(final Scene scene) {
		this.scene = scene;
	}

	/**
	 * 
	 * @return this.scene
	 */
	public Scene getScene() {
		return this.scene;
	}

	public void showStage() {
		this.windowStage.setScene(this.getScene());
		this.windowStage.show();

	}

}
