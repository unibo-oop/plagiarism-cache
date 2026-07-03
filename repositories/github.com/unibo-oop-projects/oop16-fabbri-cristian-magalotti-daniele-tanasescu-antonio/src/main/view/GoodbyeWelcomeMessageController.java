package main.view;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import main.MainApp;

public class GoodbyeWelcomeMessageController implements Initializable{
    
    @FXML
    Label goodbyePokemon;
    
    @FXML
    Label welcomePokemon;

    public GoodbyeWelcomeMessageController() {
        // TODO Auto-generated constructor stub
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.goodbyePokemon.setText(AdieuTeamMemberSelectController.adieuPokemon.toString() + "...");
        this.welcomePokemon.setText(ShopMenuController.getSoldPokemon().toString() + "!");        
    }
    
    public void requestBackToGameMenu() throws IOException{
        MainApp.disableAdieu();
        MainApp.disableGoodbyeAndWelcome();
        MainApp.goToGameMenu();
    }

}
