package program;


import java.io.IOException;
import controller.InputController;
import controller.ScoreManagerImpl;
import controller.ScreenManager;
import controller.SoundManager;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import model.PlayerImpl;
import view.Animator;
import view.AnimatorImpl;


public class Game {

	public static StackPane root;
	public static GraphicsContext gc; 
	public static Canvas canvas;
	public static Scene scene;
	public static InputController inputController; 
	public static Animator animator;
	public static ScreenManager screenManager;
	public static Stage stage; 
	private static Scene menuScene;	
	public static boolean onRun = true;
	
	public static void setMenuScene(Scene scene) {
		Game.menuScene = scene;
	}
	
	public static void setStage(Stage stage) {
		Game.stage = stage;
	}
	
	public static void startGame() {
		
		root = new StackPane(); 		
		canvas = new Canvas(); 
		ScreenManager.setCanvas(canvas);
		ScreenManager.setRoot(root);
		ScreenManager.setupScreenManager();
		SoundManager.initSoundManager();
		stage.setX((ScreenManager.PREF_WIDTH - stage.getWidth()) / 2); 
	    stage.setY((ScreenManager.PREF_HEIGHT - stage.getHeight()) / 2);  
		ScoreManagerImpl.getInstance();
		gc = canvas.getGraphicsContext2D();
		PlayerImpl.resetPlayer();
		PlayerImpl.getInstance();

		scene = new Scene(root, ScreenManager.widthScreen,ScreenManager.heightScreen);
		inputController = new InputController(scene);
		
		root.getChildren().add(canvas);
		
		stage.setResizable(false);
		Image background = null;
		
		try {
			background = ScreenManager.getImage("sfondo.jpg");
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
		
		BackgroundSize bgSize = new BackgroundSize(ScreenManager.widthScreen, ScreenManager.heightScreen, true, true, true, true);
		BackgroundImage bgImage = new BackgroundImage(background, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT, bgSize);
		Background bg = new Background(bgImage);
		
		
		root.setBackground(bg);
		
		
		animator = new AnimatorImpl(gc); 
		animator.start();
		
		stage.setScene(scene);
		stage.setTitle("JALIEN INVASION");
		stage.centerOnScreen();
		stage.show();
		
		stage.setOnCloseRequest(new EventHandler<WindowEvent>(){
			  public void handle(WindowEvent we) {
			    stage.close();
			    System.exit(0);
			  }
			});
	}			
	
}
