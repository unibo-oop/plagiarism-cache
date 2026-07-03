package main.view;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;
import main.MainApp;
import managers.SaveManager;

public class ManagementMenuController implements Initializable{

    public ManagementMenuController() {
        // TODO Auto-generated constructor stub
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // TODO Auto-generated method stub
        
    }
    
    public void requestSaveGame() throws IOException{
        SaveManager saveManager = new SaveManager();
        if(saveManager.existsNotEmptySaveFile()){
            MainApp.goToSaveOverwrite();
        }
        else{
            saveManager.saveGame();
            MainApp.goToSaveDone();
        }
    }
    
    public void requestTeamPreview() throws IOException{
        MainApp.goToTeamPreviewMenu();
    }
    
    public void backToGameMenu() throws IOException{
        MainApp.disableManagementMenu();
        MainApp.goToGameMenu();
    }

}
