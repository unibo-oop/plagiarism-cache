package game;

import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public interface GameView {

	void updateLabel(Integer lifes, Integer wumpas);

	Pane getPane();

	Scene getScene();

	Stage getStage();

	void start(Stage stage) throws Exception;

	void removeFromLayer(ImageView imageView);
	
	void addToLayer(ImageView imageView);
}
