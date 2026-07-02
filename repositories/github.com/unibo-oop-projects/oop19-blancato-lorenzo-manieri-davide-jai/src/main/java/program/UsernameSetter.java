package program;

import controller.ScoreManagerImpl;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;


public class UsernameSetter{

	/**
	 * create a window to set player name, register it in score tab and to start game
	 * @param title title of the window
	 * 
	 */
	public static void display(String title) {
		
		Stage window = new Stage();
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle(title);
		window.setWidth(300);
		window.setHeight(150);
		TextField textField = new TextField("Insert Username");
		textField.setMaxWidth(150);
		
		textField.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (newValue.booleanValue()) {
                    focusGained(textField);
                } 
            } 
        	private void focusGained(TextField textField){
    		    textField.setText("");
    		}
            });
		
		
		Button buttonSet = new Button("Start Game");
		buttonSet.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				String userName = textField.getText();
				userName =userName.replaceAll(" ", "");
				ScoreManagerImpl sm =ScoreManagerImpl.getInstance();
				sm.getCurrentScore().setUser(userName);
				window.close();
				Game.startGame();
			}
			
		});
		
		VBox layout = new VBox(10);
		layout.getChildren().add(buttonSet);
		layout.getChildren().add(textField);
		layout.setAlignment(Pos.CENTER);
		Scene scene = new Scene(layout);
		window.setScene(scene);
		Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
		window.setX((screenBounds.getWidth() - window.getWidth()) / 2); 
	    window.setY((screenBounds.getHeight() - window.getHeight()) / 2 - 100);  
		window.show();
	}
}
