package gui;

import java.net.URL;
import java.util.ResourceBundle;

import filetypes.Leaderboard;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import resourcemanager.ResourceManagerAlpha;

public class LeaderboardController implements Initializable{
	
	private Leaderboard leaderboard = ResourceManagerAlpha.getIstance().getLeaderboardAsObject();
	
	@FXML
	private Button back = new Button();
	
	@FXML
	private VBox vBox = new VBox();

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		System.out.println(this.leaderboard.getList());
		for(int i=0; i<this.leaderboard.getList().size(); i++) {
			if(i==4) {
				break;
			}
			Label label = new Label();
			label.setText(Integer.toString(i+1) + "- " + this.leaderboard.getList().get(i).getKey() + ": " + this.leaderboard.getList().get(i).getValue());
			label.setFont(new Font(30));
			label.setPrefHeight(35);
			label.setPrefWidth(300);
			this.vBox.getChildren().add(label);
		}
	}
	
	public void handleBack() {
		Utilities.load("MainMenu.fxml", (javafx.stage.Stage) this.back.getScene().getWindow());
	}

}
