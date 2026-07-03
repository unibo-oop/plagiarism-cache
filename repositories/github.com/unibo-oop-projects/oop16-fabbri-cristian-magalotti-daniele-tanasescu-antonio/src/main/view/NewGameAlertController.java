package main.view;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import main.MainApp;
import managers.SaveManager;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class NewGameAlertController implements Initializable{
    
    @FXML
    ImageView image1;
    
    @FXML
    ImageView image2;
    
    @FXML
    ImageView image3;
    
    @FXML
    ImageView image4;
    
    @FXML
    ImageView image5;
    
    @FXML
    ImageView image6;
    
    @FXML
    ImageView image7;
    
    @FXML
    ImageView image8;
    
    @FXML
    ImageView image9;
    
    @FXML
    ImageView image10;
    
    @FXML
    ImageView image11;
    
    @FXML
    ImageView image12;
    
    @FXML
    ImageView image13;
    
    public NewGameAlertController() {
        // TODO Auto-generated constructor stub
    }
    
    public void requestNewGame() throws IOException{
        SaveManager sm = new SaveManager();
        sm.newGame();
        MainApp.disableOverwritePreviousData();
        MainApp.showTutorial();
    }
    
    public void requestBackToMainMenu(){
        MainApp.disableOverwritePreviousData();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) { 
        ImageView[] images = new ImageView[]{image1,image2,image3,image4,image5,image6,image7,
                                             image8, image9, image10, image11, image12,image13}; 
        for(ImageView image : images){
            image.setImage(new Image (getClass().getResourceAsStream("resources/PokeBall.png"))); 
        }

    }

}
