package com.biaren;

import java.io.IOException;

import com.biaren.sportclubmanager.corebundle.controller.MainController;
import javafx.application.Application;
import javafx.stage.Stage;

public class App extends Application {
    
    public static void main( String[] args ) {        
        Application.launch(args);
    }

    @Override
    public void start(Stage stage) throws IOException {   
        new MainController(stage);
    }
    
}
