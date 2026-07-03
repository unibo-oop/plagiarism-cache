package main.view;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import main.MainApp;
import managers.SaveManager;

public class TeamOverwriteAlertController implements Initializable{
    
    @FXML
    Label message;
    
    @FXML
    ImageView ball1;
    
    @FXML
    ImageView ball2;
    
    @FXML
    ImageView ball3;
    
    @FXML
    ImageView ball4;
    
    private static final String QUESTION = "?";
    private static final String RETURN = "in return for enemy's ";
    private static final String SURE = "Are you sure you want to trade\n your ";

    public TeamOverwriteAlertController() {
        // TODO Auto-generated constructor stub
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        message.setText(SURE + MainApp.tradePanelController.pokemonYouOffer.toString() + RETURN + 
                        MainApp.tradePanelController.pokemonChoice.toString() + QUESTION);
        this.setBalls();
    }
    
    private void setBalls(){
        ImageView[] balls = {ball1, ball2, ball3, ball4};
        for(ImageView ball : balls){
            ball.setImage(new Image 
                          (getClass().getResourceAsStream("resources/PokeBall.png")));
        }
    }
    
    public void answerNo(){
        MainApp.tradePanelController.resetChoices();
        MainApp.disableTeamOverwrite();
    }
    
    public void answerYes() throws IOException{
        SaveManager saveManager = new SaveManager();
        saveManager.gameManagerInstance().getPlayer().trade(MainApp.tradePanelController.playerIndex, 
                                                            MainApp.tradePanelController.pokemonChoice);
        int enemyIndex = saveManager.gameManagerInstance().getPlayer().getCurrentPosition();
        saveManager.gameManagerInstance().getAllEnemies()[enemyIndex].trade(MainApp.tradePanelController.enemyIndex, 
                                                                            MainApp.tradePanelController.pokemonYouOffer);
        saveManager.gameManagerInstance().getAllEnemies()[enemyIndex].setHasTraded(true);
        MainApp.disableTradePanel();
        MainApp.disableTeamOverwrite();
        MainApp.goToGameMenu();
    }

}
