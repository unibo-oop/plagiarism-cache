package view.mainmenu;

import java.io.IOException;

import constants.GameConstants;
import controller.gamecontroller.GameController;
import controller.gamecontroller.GameControllerImpl;
import view.gameview.GameScreen;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Glow;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class PlayerName extends Region{

	GameScreen game;
	private GameController gc = new GameControllerImpl();
	public Parent createContent() throws IOException {
		
		Pane root = new Pane();
			
		TextField box = new TextField();
		    box.setPrefSize(GameConstants.buttonSize*7, GameConstants.buttonSize);
		    box.setTranslateX(GameConstants.buttonSize*16);
		    box.setTranslateY(GameConstants.buttonSize*13);
		
		Rectangle bg = new Rectangle(GameConstants.gameWidth+GameConstants.buttonSize,GameConstants.gameHeight+GameConstants.buttonSize);
        bg.setFill(Color.GREY);
        bg.setOpacity(0.5);
        
        Text tx = new Text("Inserisci il\ntuo nome!");
        tx.setFont(Font.loadFont("file:res/JOJO____.ttf", GameConstants.buttonSize));
        tx.setFill(Color.INDIGO);
        tx.setTranslateY(GameConstants.buttonSize*5);
        tx.setTranslateX(GameConstants.buttonSize*2);
         
        ImageLoader im = new ImageLoader("res/player.png");
     	im.getImage().prefHeight(GameConstants.height);
     	im.getImage().prefWidth(GameConstants.width);
     	//root.setPrefSize(GameConstants.gameWidth, GameConstants.gameHeight);
        
        VBox mn = new VBox(GameConstants.buttonSize);
        mn.setPrefSize(GameConstants.width, GameConstants.height);
        mn.getChildren().add(im.getImage());
     	root.getChildren().add(mn);
		
		ButtonD d = new ButtonD("start");
		d.setTranslateX(GameConstants.buttonSize*16.5);
	     d.setTranslateY(GameConstants.buttonSize*16);
		d.setOnMouseClicked(event -> {
			gc.init();
			game = new GameScreen(gc);
			gc.getModel().getPlayer().setName(box.getText());
				root.getChildren().setAll(game.createContent());
		});
		
		
		root.getChildren().addAll(bg,d,box,tx);
		
		return root;
	}
	
	public class ButtonD extends StackPane{

		private Text text;
		
		public ButtonD(String name) {
			
			text = new Text(name);
			text.setFont(Font.loadFont("file:res/JOJO____.ttf", GameConstants.buttonSize));
			text.setFill(Color.INDIGO);
			
			Rectangle bg = new Rectangle(GameConstants.buttonSize*6,GameConstants.buttonSize*3);
			bg.setOpacity(0.8);
			bg.setFill(Color.CYAN);
			
			  setAlignment(Pos.CENTER);
	            setRotate(-0.5);
	            getChildren().addAll(bg, text);

	            setOnMouseEntered(event -> {
	                bg.setFill(Color.WHITE);
	                text.setFill(Color.BLACK);
	            });

	            setOnMouseExited(event -> {
	                bg.setFill(Color.CYAN);
	                text.setFill(Color.INDIGO);
	            });

	            DropShadow drop = new DropShadow(50, Color.WHITE);
	            drop.setInput(new Glow());

	            setOnMousePressed(event -> setEffect(drop));
	            setOnMouseReleased(event -> setEffect(null));  
		}
	}
	
	public GameController getgc() {
		return this.gc;
	}
	
}
