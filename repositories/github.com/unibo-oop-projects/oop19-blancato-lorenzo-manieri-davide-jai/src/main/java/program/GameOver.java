package program;

import java.io.IOException;
import controller.BulletManager;
import controller.EnemyManager;
import controller.PowerUpManagerImpl;
import controller.ScreenManager;
import controller.SpawnManager;
import controller.TimeManagerImpl;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;


public class GameOver {
	
	private static StackPane root;
	private static Scene gameOverScene;
	private static int buttonWidth = 705;
	private static int buttonHeight = 80;
	

	
	/**
	 * reset every timer and create game over screen 
	 * @param gc graphic context used to draw on the window
	 */
	public static void gameOver(GraphicsContext gc) {
		
		TimeManagerImpl.getInstance().resetAllTimer();
		PowerUpManagerImpl.resetPowerUpManager();
		SpawnManager.resetSpawn();
		Game.animator.stop();
		
		root = new StackPane();
		
		try{
			ImageView img = new ImageView(ScreenManager.getImage("GameOverScreen.png"));
			img.setFitWidth(Game.root.getWidth());
			img.setFitHeight(Game.root.getHeight());
			root.getChildren().add(img);
		}
		catch(IOException e) {
			System.out.println("Couldn't load image");
		}
		

		Button restartButton = new Button("menu");
		restartButton.setMaxSize(buttonWidth, buttonHeight);
		restartButton.setTranslateX(-1);
		restartButton.setTranslateY(16);
		restartButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				returnToMenu();
			}
		});
		restartButton.setOpacity(0);
		
		
		Button quitButton = new Button("esci");
		quitButton.setMaxSize(buttonWidth, buttonHeight);
		quitButton.setTranslateX(-1);
		quitButton.setTranslateY(135);
		quitButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				Game.stage.close();
				System.exit(0);
			}
		});
		quitButton.setOpacity(0);
		
		ScreenManager.setRoot(root);
		gameOverScene = new Scene(root, ScreenManager.widthScreen,ScreenManager.heightScreen);
		Game.stage.setScene(gameOverScene);
		Game.stage.centerOnScreen();
		root.getChildren().addAll(restartButton, quitButton);
		
		
	}
	
	/**
	 * method to return to menu resetting menu scene
	 * 
	 */
	public static void returnToMenu() {
		
		GameMenu.stageWindow.setTitle("JALIEN INVASION");	
		Scene scene = new Scene(GameMenu.createMainPane());
		GameMenu.stageWindow.setScene(scene);
		GameMenu.stageWindow.centerOnScreen();
		Game.setMenuScene(scene);

		EnemyManager.getEnemyList().clear();
		BulletManager.getBulletList().clear();
		
	
	}
}
