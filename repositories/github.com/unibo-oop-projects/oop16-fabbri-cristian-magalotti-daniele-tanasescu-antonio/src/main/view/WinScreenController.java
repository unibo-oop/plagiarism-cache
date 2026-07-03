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

public class WinScreenController implements Initializable{

    private static final String WIN = "You win!\nYou earn ";
    private static final String POINTS = " points!";

    @FXML
    ImageView leftCup = new ImageView();

    @FXML
    ImageView rightCup = new ImageView();    

    @FXML
    Label victoryDeclaration;

    SaveManager saveManager = new SaveManager();

    public WinScreenController() {
        // TODO Auto-generated constructor stub
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.leftCup.setImage(new Image (getClass().getResourceAsStream("resources/Win.png")));
        this.rightCup.setImage(new Image (getClass().getResourceAsStream("resources/Win.png")));
        this.saveManager = new SaveManager();
        Enemy enemy = saveManager.gameManagerInstance().getAllEnemies()[saveManager.gameManagerInstance().getLastEnemyCreatedIndex()];
        victoryDeclaration.setText(WIN + enemy.getWinningPoints() + POINTS);
    }

    public void requestGoToGameMenu() throws IOException{
        //raise player's points
        this.saveManager = new SaveManager();
        Enemy enemy = this.saveManager.gameManagerInstance().getAllEnemies()[this.saveManager.gameManagerInstance().getLastEnemyCreatedIndex()];
        if(!enemy.hasBeenDefeated()){
            MainApp.initializeNewEnemy();
            this.saveManager.gameManagerInstance().getPlayer().incrementNumOfEnemiesDefeated();
        }
        enemy.incrementNumOfWins();
        MainApp.getBattleArena().restoreEnemyAndPlayer();
        this.saveManager.gameManagerInstance().getPlayer().incrementPlayerPoints(enemy.getWinningPoints());
        
        
        MainApp.disableWinScreen();
        MainApp.goToGameMenu();
    }

}
