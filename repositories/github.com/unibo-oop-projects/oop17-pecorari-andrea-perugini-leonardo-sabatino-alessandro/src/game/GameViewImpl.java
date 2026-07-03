package game;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class GameViewImpl extends Application implements GameView {

	private static final double LIFE_LABEL_X_LAYOUT = 100.0;
	private static final double LIFE_LABEL_Y_LAYOUT = 20.0;
	private static final double WUMPA_LABEL_X_LAYOUT = 250.0;
	private static final double WUMPA_LABEL_Y_LAYOUT = 20.0;

	private final static GameViewImpl GAMESINGLETON = new GameViewImpl();

	private Scene scene;

	private Stage stage;

	@FXML
	private ImageView lifesImage;
	@FXML
	private Pane fieldLayer;
	@FXML
	private Label lifes = new Label();
	@FXML
	private Label wumpas = new Label();
	@FXML
	private Label title = new Label();

	// private GameViewImpl() {};

	public static GameViewImpl getGameView() {
		return GAMESINGLETON;
	}

	@Override
	public void start(Stage primaryStage) throws Exception {

		fieldLayer = new Pane();
		Group root = new Group();

		
		root = FXMLLoader.load(getClass().getResource("GameViewFX.fxml"));

		// root = FXMLLoader.load("GameViewFX.fxml");
		root.getChildren().add(fieldLayer);
		root.getChildren().add(lifes);
		root.getChildren().add(wumpas);
		scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("game.css").toExternalForm());

		lifes.setVisible(true);
		lifes.setLayoutX(GameViewImpl.LIFE_LABEL_X_LAYOUT);
		lifes.setLayoutY(GameViewImpl.LIFE_LABEL_Y_LAYOUT);
		lifes.setTextFill(Color.web("#fff600"));
		wumpas.setVisible(true);
		wumpas.setLayoutX(GameViewImpl.WUMPA_LABEL_X_LAYOUT);
		wumpas.setLayoutY(GameViewImpl.WUMPA_LABEL_Y_LAYOUT);
		wumpas.setTextFill(Color.web("#fff600"));

		title.setFont(new Font("Grinched", 40));

		primaryStage.setScene(scene);
		primaryStage.setFullScreen(false);
		primaryStage.setTitle("Dodge Bandicoot");
		primaryStage.show();

		this.stage = primaryStage;

	}
	
	public void updateLabel(final Integer lifes, final Integer wumpas) {
		this.lifes.setText(String.valueOf(lifes));
		this.wumpas.setText(String.valueOf(wumpas));
	}

	public Scene getScene() {
		return this.scene;
	}

	public Pane getPane() {
		return this.fieldLayer;
	}

	public Stage getStage() {
		return this.stage;
	}

	public void removeFromLayer(final ImageView imageView) {
		this.fieldLayer.getChildren().remove(imageView);
	}

	public void addToLayer(final ImageView imageView) {
		this.fieldLayer.getChildren().add(imageView);
	}

}