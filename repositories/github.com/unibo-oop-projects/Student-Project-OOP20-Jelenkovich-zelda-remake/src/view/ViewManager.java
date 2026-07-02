package view;

import java.util.ArrayList;
import java.util.List;
import controller.HeroGoController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene; 
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.stage.Stage;
import model.GameButton;
/**
 * Classe per la creazione del menu iniziale
 * @author Angela
 *
 */
public class ViewManager {
	
	private static final int WIDTH = 900;
	private static final int HEIGHT = 650;
	private static final int BUTTON_X = 200;
	private static final int BUTTON_Y = 150;
	
	
	private AnchorPane mainPane;
	private Stage mainStage;
	private Scene mainScene;
	private List<GameButton> btList;
	
	public ViewManager () {
		btList = new ArrayList<>();
		mainPane = new AnchorPane();
		mainScene = new Scene(mainPane, WIDTH, HEIGHT);
		mainStage = new Stage();
		mainStage.setScene(mainScene);
		createBackground();
		createButtons();
	}

	public Stage getMainStage() {
		return mainStage;
	}
	
	private void addMenuBt(GameButton bt) {
		bt.setLayoutX(BUTTON_X);
		bt.setLayoutY(BUTTON_Y + btList.size() * 100);
		btList.add(bt);
		mainPane.getChildren().add(bt);
	}
	
	private void createButtons() {
		startBt();
		exitBt();
	}
	
	private void startBt() {
		GameButton startBt = new GameButton("PLAY");
		addMenuBt(startBt);
		startBt.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent e) {
				new HeroGoController(mainStage);
			}
			
		});
	}

	
	private void exitBt() {
		GameButton exitBt = new GameButton("EXIT");
		addMenuBt(exitBt);
		exitBt.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				mainStage.close();
			}
			
		});
	}	
	
	
	private void createBackground() {
		Image backgroundImg = new Image("/res/img/mainBackground.jpg", WIDTH, HEIGHT, false, true);
		BackgroundImage background = new BackgroundImage(backgroundImg, null, null, BackgroundPosition.DEFAULT, null);
		mainPane.setBackground(new Background(background));
	}


}
