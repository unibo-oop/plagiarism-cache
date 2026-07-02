package program;

import controller.ScoreManager;
import controller.ScoreManagerImpl;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.PersonalScore;

public class ScoreHistory {

	public static void start() throws Exception {

		
		Stage stage = new Stage();
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.setTitle("SCORE HISTORY");
		Pane root = new Pane();
		root = createContent(stage);
		stage.show();
	}

	/**
	 * Create the score history table and update it with scores
	 * @param stage
	 * @return
	 */
	private static Pane createContent(Stage stage) {
		Pane root = new Pane();
		TableView<PersonalScore> scoreTable = new TableView<PersonalScore>();
		
		TableColumn<PersonalScore, Integer> positionColumn = new TableColumn<>("Position");
		positionColumn.setCellValueFactory(new PropertyValueFactory<>("position"));
		
		
		TableColumn<PersonalScore, String> userColumn = new TableColumn<>("User");
		userColumn.setCellValueFactory(new PropertyValueFactory<>("user"));
		
		TableColumn<PersonalScore, Integer> scoreColumn = new TableColumn<>("Score");
		scoreColumn.setCellValueFactory(new PropertyValueFactory<>("score"));
		
		TableColumn<PersonalScore, Integer> enemyKilledColumn = new TableColumn<>("EnemyKilled");
		enemyKilledColumn.setCellValueFactory(new PropertyValueFactory<>("enemyKilled"));
		
	
		
		scoreTable.getColumns().add(positionColumn);
		scoreTable.getColumns().add(userColumn);
		scoreTable.getColumns().add(scoreColumn);
		scoreTable.getColumns().add(enemyKilledColumn);
		
		ScoreManager sm = ScoreManagerImpl.getInstance();
		ScoreManagerImpl.getListFromFile();
		scoreTable.setItems(sm.getScoreList());
	

		
		VBox vbox = new VBox(scoreTable);
		Scene scene = new Scene(vbox);
		stage.setScene(scene);
		

		return root;
	}
	
	
	
}
