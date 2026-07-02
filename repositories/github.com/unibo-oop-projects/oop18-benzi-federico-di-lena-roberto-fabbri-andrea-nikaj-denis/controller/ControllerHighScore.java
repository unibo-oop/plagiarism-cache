package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import model.HighScoreManager;
import model.Score;
import view.FXMLPath;


/**
 * This class is used to control the scene for the High score
 * @author Andrea
 *
 */
public class ControllerHighScore implements Initializable {
	public ListView<String> highScoreList = new ListView<String>();
	public List<Label> NameList= new ArrayList<>();
	public List<Label> ScoreList= new ArrayList<>();
	
	public Label Name1=new Label();
	public Label Name2=new Label();
	public Label Name3=new Label();
	public Label Name4=new Label();
	public Label Name5=new Label();
	
	public Label Score1=new Label();
	public Label Score2=new Label();
	public Label Score3=new Label();
	public Label Score4=new Label();
	public Label Score5=new Label();
	
	private ArrayList<Score> list=new ArrayList<>();
	private HighScoreManager hm;
	private int size;
	
	/**
	 * Initialize the lists used to write names and score
	 */
	public void initialize() {
		hm=new HighScoreManager();
		NameList.clear();
		NameList.add(Name1);
		NameList.add(Name2);
		NameList.add(Name3);
		NameList.add(Name4);
		NameList.add(Name5);
		ScoreList.add(Score1);
		ScoreList.add(Score2);
		ScoreList.add(Score3);
		ScoreList.add(Score4);
		ScoreList.add(Score5);       
	}
	
	/**
	 * This method show in the scene the high score of the first five players
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		initialize();
		list.addAll(hm.getScores());
		if(list.size()<5) {
			size=list.size();
		}else {
			size=5;
		}
		for (int j = 0; j <size; j++) {
			NameList.get(j).setText(list.get(j).getName());
			ScoreList.get(j).setText(Integer.toString(list.get(j).getScore()));
		}
	}

	/**
	 * This method is used to go back to the menu scene
	 * @param event
	 * @throws IOException
	 */
	public void goBack(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource(FXMLPath.MENU.getPath()));
		Scene loginScene = new Scene(root);
		Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
		window.setScene(loginScene);
		window.show();
	}

}
