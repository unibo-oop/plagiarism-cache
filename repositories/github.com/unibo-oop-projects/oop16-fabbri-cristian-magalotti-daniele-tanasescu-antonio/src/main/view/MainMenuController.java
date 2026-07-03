package main.view;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import main.MainApp;
import managers.EnemyManager;
import managers.SaveManager;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class MainMenuController implements Initializable{
    
    @FXML
    ImageView imageView;
    
    public MainMenuController() {
        // TODO Auto-generated constructor stub
    }

    public void requestNewGame() throws IOException{
        SaveManager sm = new SaveManager();
        sm.createSaveFile();
        if(sm.existsNotEmptySaveFile()){
            MainApp.showOverwritePreviousData();
        }
        else{
            sm.gameManagerInstance().newPlayer();
            sm.gameManagerInstance().setAllEnemies();
            MainApp.initializeNewEnemy();
            MainApp.showTutorial();
        }
    }
    
    public void requestLoadGame() throws IOException, ClassNotFoundException{
        SaveManager sm = new SaveManager();
        if(sm.existsNotEmptySaveFile()){
            sm.loadGame();
            MainApp.goToGameMenu();
        }
        else{
            MainApp.showNoSaveDataErrorScreen();
        }
    }
    
    public void requestQuit(){
        MainApp.quitGame();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        imageView.setImage(new Image (getClass().getResourceAsStream("resources/PokeBall.png")));        
    }

}
