package reset;

import start.StartViewImpl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class ResetPopupViewImpl implements ResetPopupViewObserver {

	private Pane pane;
	private Group root;
	private Scene scene;
	private Stage stage;

	private final static ResetPopupViewImpl POPUPSINGLETON = new ResetPopupViewImpl();
	
	public static ResetPopupViewImpl getGameView() {
		return POPUPSINGLETON;
	}
	
	/**
	 * initializes the view
	 */
	@Override
	public void start(final Stage primaryStage) {
		try {
			this.pane = new Pane();
			this.root = new Group();
			this.stage = primaryStage;

			this.root = FXMLLoader.load(getClass().getResource("ResetPopupViewFX.fxml"));
			this.root.getChildren().add(this.pane);
			this.scene = new Scene(this.root);

			this.stage.setScene(this.scene);
			this.stage.setFullScreen(false);
			this.stage.setTitle("GAME OVER");
			this.stage.show();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	
	@FXML
	public void tryAgain(final ActionEvent event) {
		try {
			(((Node) event.getSource())).getScene().getWindow().hide();

			Stage astage = new Stage();
			StartViewImpl.getStartView();
			StartViewImpl.getStartView().start(astage);

		} catch (NumberFormatException e) {
			e.printStackTrace();
		}

	}


}
