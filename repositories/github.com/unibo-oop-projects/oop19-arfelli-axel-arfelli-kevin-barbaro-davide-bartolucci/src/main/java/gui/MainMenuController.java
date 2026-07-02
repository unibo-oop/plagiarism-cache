package gui;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.control.Button;

public class MainMenuController implements Initializable{
    
    @FXML
    private Button PLAY = new Button();
    
    @FXML
    private Button CONTROLS = new Button();
    
    @FXML
    private Button QUIT = new Button();
    
    @FXML
    private TextField usernameField = new TextField();
    
    @FXML
    private StackPane sp = new StackPane();
    
    @FXML
    private Button LEADERBOARD = new Button();
    
    private static Optional<String> username;
    
    // play button handler, checks if a username is selected and warns the user if it is not; then changes scene to play mode selection
    public void handlePlayButton() throws IOException{
        Stage mainStage = (Stage) this.sp.getScene().getWindow();
        if(this.usernameField.getText().trim().isEmpty()) {
            UsernameAlertController.display();
            if(UsernameAlertController.getFlag()) {
                Utilities.load("GameScene.fxml", mainStage);
            }
        }
        else {
            username = Optional.of(this.usernameField.getText());
            Utilities.load("GameScene.fxml", mainStage);
        }
    }
    
    // changes scene to show the player the controls for the game
    public void handleControlsButton() throws IOException{
        Stage mainStage = (Stage) this.sp.getScene().getWindow();
        Utilities.load("Controls.fxml", mainStage);
    }
    
    
    // closes the application
    public void handleQuitButton() {
    	System.exit(0);
    }
    
    public void handleLeaderboard() {
        Utilities.load("Leaderboard.fxml", (javafx.stage.Stage) this.LEADERBOARD.getScene().getWindow());
    }

    public static Optional<String> getUsername() {
        return username;
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		for(Node n : this.sp.getChildren()) {
			
		}
	}
    
}
