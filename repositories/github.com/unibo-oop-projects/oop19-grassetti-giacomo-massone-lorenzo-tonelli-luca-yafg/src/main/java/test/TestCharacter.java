package test;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import model.Character;

public class TestCharacter extends Application {
    
   public Character c1;

    @Override
    public void start(Stage stage) throws Exception {
        
        c1 = new Character("src/main/resources/character/prova.png", 0, 1280, 0, 800);
        
        //Creating a Group object  
        HBox root = new HBox(c1.getImageView());
        
        //Creating a scene object 
        Scene scene = new Scene(root, 600, 500);  
        
        //Setting title to the Stage 
        stage.setTitle("Loading an image");  
        
        //Adding scene to the stage 
        stage.setScene(scene);
        
        //Displaying the contents of the stage 
        stage.show(); 
        
    }
    
    public static void main(String[] args) {
        launch();
    }

}
