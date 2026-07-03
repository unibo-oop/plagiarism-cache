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
import managers.EnemyManager;
import managers.GameManager;
import managers.SaveManager;
import team.enemies.Enemy;

public class LoseScreenController implements Initializable{

    @FXML
    ImageView leftFace = new ImageView();

    @FXML
    ImageView rightFace = new ImageView();    

    SaveManager saveManager = new SaveManager();

    public LoseScreenController() {
        // TODO Auto-generated constructor stub
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.leftFace.setImage(new Image (getClass().getResourceAsStream("resources/SadFace.png")));
        this.rightFace.setImage(new Image (getClass().getResourceAsStream("resources/SadFace.png")));
        this.saveManager = new SaveManager();
    }

    public void requestGoToGameMenu() throws IOException{
        //raise player's points
        this.saveManager = new SaveManager();
        Enemy enemy = this.saveManager.gameManagerInstance().getAllEnemies()[this.saveManager.gameManagerInstance().getLastEnemyCreatedIndex()];
        enemy.incrementNumOfLosses();
        MainApp.getBattleArena().restoreEnemyAndPlayer();
        int decrement = this.saveManager.gameManagerInstance().getPlayer().getPlayerPoints()/2;
        this.saveManager.gameManagerInstance().getPlayer().decrementPlayerPoints(decrement);
        
        
        MainApp.disableWinScreen();
        MainApp.goToGameMenu();
    }

}
