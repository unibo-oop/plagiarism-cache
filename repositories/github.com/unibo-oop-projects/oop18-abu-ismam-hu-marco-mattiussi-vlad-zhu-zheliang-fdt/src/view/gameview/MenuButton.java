package view.gameview;

import constants.GameConstants;
import javafx.geometry.Pos;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Glow;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class MenuButton extends StackPane{

	private Text text;
	
	public MenuButton(String name) {
		
		text = new Text(name);
		text.setFont(Font.loadFont("file:res/JOJO____.ttf", GameConstants.buttonSize/2));
		text.setFill(Color.BLACK);
		
		Rectangle bg = new Rectangle(GameConstants.buttonSize*3.75,GameConstants.buttonSize*3.75);
		bg.setOpacity(0.8);
		bg.setFill(Color.CRIMSON);
		
		  setAlignment(Pos.CENTER);
            setRotate(-0.5);
            getChildren().addAll(bg, text);

            setOnMouseEntered(event -> {
                bg.setFill(Color.WHITE);
                text.setFill(Color.BLACK);
            });

            setOnMouseExited(event -> {
                bg.setFill(Color.CRIMSON);
                text.setFill(Color.BLACK);
            });

            DropShadow drop = new DropShadow(50, Color.WHITE);
            drop.setInput(new Glow());

            setOnMousePressed(event -> setEffect(drop));
            setOnMouseReleased(event -> setEffect(null));  
	}
}
