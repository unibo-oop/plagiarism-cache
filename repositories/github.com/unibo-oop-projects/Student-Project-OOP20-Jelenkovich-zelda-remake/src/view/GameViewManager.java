package view;

import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
/**
 * Classe contenente gli Event handler per la ricezione degli eventi
 * 					e l'inizializzazione dei nuovi livelli
 * @author Angela
 *
 */
public class GameViewManager {
	
	private static final int WIDTH = 900;
	private static final int HEIGHT = 650;
	
	private AnchorPane gamePane;
	private Scene gameScene;
	private Stage gameStage;
	private ViewObserver obs;
	private final Label life;
	
	
	public GameViewManager(final ViewObserver observer, Stage stage) {
		this.gameStage = stage;
		this.obs=observer;
		this.life = new Label();
	}
	
	public void initStage(int life) {
		gamePane = new AnchorPane();
		gameScene = new Scene(gamePane, WIDTH, HEIGHT);
		final ImageView background = new ImageView(new Image(ClassLoader.getSystemResource("res/img/lv.jpg").toString()));
        this.gamePane.getChildren().add(background);
        gameScene.getStylesheets().add("res/style/life.css");
        this.obs.newGame();
		gameStage.setScene(gameScene);
		this.setLife(life);
		this.addEvent();
	}
	

	public void secondStage(int life) {
		gamePane = new AnchorPane();
		gameScene = new Scene(gamePane, WIDTH, HEIGHT);
		final ImageView background = new ImageView(new Image(ClassLoader.getSystemResource("res/img/lv2.jpg").toString()));
        this.gamePane.getChildren().add(background);
        gameScene.getStylesheets().add("res/style/life.css");
        this.obs.newGame();
		gameStage.setScene(gameScene);
		this.addEvent();
		this.setLife(life);
	}
	
	public void thirdStage(int life) {
		gamePane = new AnchorPane();
		gameScene = new Scene(gamePane, WIDTH, HEIGHT);
		final ImageView background = new ImageView(new Image(ClassLoader.getSystemResource("res/img/lv3.jpg").toString()));
        this.gamePane.getChildren().add(background);
        gameScene.getStylesheets().add("res/style/life.css");
        this.obs.newGame();
		gameStage.setScene(gameScene);
		this.addEvent();
		this.setLife(life);
	}

	
	private void addEvent() {
		this.gameScene.addEventHandler(KeyEvent.KEY_PRESSED, event -> {
            if (event.getCode() == KeyCode.W) {
                this.obs.upPressed();
            } else if(event.getCode() == KeyCode.D) {
            	this.obs.rightPressed();
			}else if(event.getCode() == KeyCode.A) {
				this.obs.leftPressed();
			}else if(event.getCode() == KeyCode.S) {
				this.obs.downPressed();
			}	
        });
		
		this.gameScene.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				obs.click(e.getX(), e.getY());
			}
		});
	}

	private void setLife(int life) {
        this.life.setText("LIFE: " + life);
        this.life.setAlignment(Pos.CENTER_LEFT);
        this.life.getStyleClass().add("life");
        this.gamePane.getChildren().add(this.life);
	}

	public ViewObserver getObs() {
		return obs;
	}

	public void setObs(ViewObserver obs) {
		this.obs = obs;
	}
	
	public void addChildren(final Node n) {
        this.gamePane.getChildren().add(n);
    }
	
	public int getWidth() {
		return WIDTH;
	}
	
	public int getHeight() {
		return HEIGHT;
	}
	
	public void deleteElement(Object o) {
		this.gamePane.getChildren().remove(o);
	}

	public void updateLife(int life) {
		this.life.setText("LIFE: " + life);
	}
}
