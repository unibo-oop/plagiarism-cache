package main.view;

import main.MainApp;


public class NoSaveFileErrorScreenController {

    public NoSaveFileErrorScreenController() {
        // TODO Auto-generated constructor stub
    }
    
    public void requestBackToMainMenu(){
        MainApp.disableNoSaveFileErrorScreen();
    }

}
