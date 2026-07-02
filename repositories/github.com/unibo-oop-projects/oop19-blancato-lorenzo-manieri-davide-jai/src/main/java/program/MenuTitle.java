package program;

import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;


public class MenuTitle extends StackPane {
    private Text text;
    
    /**
     * Constructor use to write title onto the menu and to set spacing between characters
     * @param name title to write
     */
    public MenuTitle(String name) {
    	String spread = "";
        for (char c : name.toCharArray()) {
        	spread += c + " ";
        }  
        
        text = new Text(spread);
		text.setFill(Color.WHITE);
		
        final Font f = Font.loadFont(ClassLoader.getSystemResourceAsStream("Nebullium.ttf"), 30);
		text.setFont(f);  
        
        getChildren().addAll(text);
    }
    
}