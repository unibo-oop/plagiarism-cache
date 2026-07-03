package main.view;

import java.io.IOException;

import main.MainApp;

public class SaveDoneController {

    public SaveDoneController() {
        // TODO Auto-generated constructor stub
    }
    
    public void requestBackToManagementMenu() throws IOException{
        MainApp.disableAlreadySaved();
        MainApp.disableSaveOverwrite();
    }

}
