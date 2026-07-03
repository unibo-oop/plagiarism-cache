package main.view;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;
import main.MainApp;
import managers.SaveManager;

public class SaveOverwriteController implements Initializable{

    public SaveOverwriteController() {
        // TODO Auto-generated constructor stub
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // TODO Auto-generated method stub  
    }
    
    public void requestSaveGame() throws IOException{
        SaveManager saveManager = new SaveManager();
        saveManager.saveGame();
        MainApp.goToSaveDone();
    }
    
    public void requestBackToManagementMenu() throws IOException{
        MainApp.disableSaveOverwrite();
        MainApp.goToManagementMenu();
    }

}
