package main.view;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Paint;
import main.MainApp;
import managers.GameManager;
import managers.MenuMenager;
import team.Team;
import team.enemies.Enemy;

public class GameMenuController implements Initializable{

    @FXML
    ImageView leftArrow;

    @FXML
    ImageView rightArrow;

    @FXML
    ImageView trainerSprite;

    @FXML
    ImageView firstOfTeam;

    @FXML
    ImageView secondOfTeam;

    @FXML
    ImageView thirdOfTeam;

    @FXML
    ImageView fourthOfTeam;

    @FXML
    ImageView fifthOfTeam;

    @FXML
    ImageView sixthOfTeam;

    @FXML
    Label label1;

    @FXML
    Label label2;

    @FXML
    Label label3;

    @FXML
    Label label4;

    @FXML
    Label label5;

    @FXML
    Label label6;

    @FXML
    Button battleButton;

    @FXML
    Button tradeButton;

    private int currentPosition;
    private boolean canBattle;                              //userful to decide whether to be able to battle or not
    private MenuMenager menuMenager;
    private Enemy enemy;

    public GameMenuController() {
        // TODO Auto-generated constructor stub
    }

    //TODO DOVRA' ANCHE IMPOSTARE L'IMMAGINE E IL TEAM VISUALIZZATI DEL RIVALE A SECONDA DELLA POSIZIONE DI PARTENZA (sav)
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        this.menuMenager = new MenuMenager();
        this.currentPosition = menuMenager.gameManagerInstance().getPlayer().getCurrentPosition();
        this.setEnemy();

        leftArrow.setImage(new Image (getClass().getResourceAsStream("resources/LeftArrow.png"))); 
        rightArrow.setImage(new Image (getClass().getResourceAsStream("resources/RightArrow.png")));     

        this.trainerSprite.setImage(new Image(this.getClass().getResourceAsStream("resources/Blank.png")));        
        this.drawEnemyInformations();
    }

    public void drawEnemyInformations(){
        this.setEnemy();
        Label[] labels = new Label[] {label1, label2, label3, label4, label5, label6};
        //se il nemico è affrontabile quindi
        if(currentPosition <= menuMenager.gameManagerInstance().getLastEnemyCreatedIndex() && enemy != null){
            this.setAvailableEnemy(enemy, labels);
            this.canBattle = true;
        }
        else{
            this.setUnavailableEnemy(enemy, labels);
            this.canBattle = false;
        }
    }

    private void setAvailableEnemy(Enemy enemy, Label[] labels){
        String[] messages = new String[] {enemy.getEnemyName(), enemy.getEnemySurname(), enemy.getEnemyClass(),
                Integer.toString(enemy.getEnemyLevel()), Integer.toString(enemy.getNumOfWins()),
                Integer.toString(enemy.getNumOfLosses())};
        for(int i = 0; i < labels.length; i++){                 //labels.length == messages.length
            labels[i].setText(messages[i]);
        }

        trainerSprite.setImage(new Image (getClass().getResourceAsStream(enemy.getEnemyImage())));
        ImageView[] pokemonImages = new ImageView[]{firstOfTeam, secondOfTeam, thirdOfTeam, fourthOfTeam,
                fifthOfTeam, sixthOfTeam};
        int i = 0;                                                              //mi servirà anche esternamente
        for(i = 0; i < enemy.getPokemon().length; i++){
            if(enemy.hasBeenDefeated()){
                pokemonImages[i].setImage(new Image (
                        getClass().getResourceAsStream(enemy.getPokemon()[i].pokemonFrontImage())));
                if(!enemy.getPokemon()[i].toString().startsWith("Tapu")){
                    pokemonImages[i].setScaleX(1.5);
                    pokemonImages[i].setScaleY(1.5);
                }
                else{
                    pokemonImages[i].setScaleX(0.8);
                    pokemonImages[i].setScaleY(0.8);
                }
            }
            else{
                pokemonImages[i].setImage(new Image (getClass().getResourceAsStream("resources/PokeBall.png")));      
                pokemonImages[i].setScaleX(0.75);
                pokemonImages[i].setScaleY(0.75);
            }
        }
        //se il team ha meno di MAX_POKEMON pokemon
        while(i < Team.MAX_POKEMON){
            pokemonImages[i].setImage(null);
            i++;
        }

        battleButton.setText("Battle");

        if(enemy.hasBeenDefeated()){                                            //se il nemico è stato sconfitto
            if(!enemy.hasTraded()){                                             //e ancora lo scambio non è stato fatto
                tradeButton.setText("Trade");
                tradeButton.setTextFill(Paint.valueOf("Green"));                    //invita di più a fare lo scambio così
            }
            else{
                tradeButton.setText("Already\n traded!");
                tradeButton.setTextFill(Paint.valueOf("Red"));                 //accesso negato!
            }
        }
        else{
            tradeButton.setText("-");
        }
    }

    private void setUnavailableEnemy(Enemy enemy, Label[] labels){
        for(int i = 0; i < labels.length; i++){                 
            labels[i].setText("-");
        }
        trainerSprite.setImage(new Image (getClass().getResourceAsStream("resources/CharacterNotUnlocked.png")));
        ImageView[] pokemonImages = new ImageView[]{firstOfTeam, secondOfTeam, thirdOfTeam, fourthOfTeam,
                fifthOfTeam, sixthOfTeam};
        //uso il riferimento a tutti i pokemon così non sarà conosciuto (e non si hanno errori se enemy è null)
        for(int i = 0; i < Team.MAX_POKEMON; i++){
            pokemonImages[i].setImage(new Image (getClass().getResourceAsStream("resources/CharacterNotUnlocked.png")));  
            pokemonImages[i].setScaleX(1);
            pokemonImages[i].setScaleY(1);
        }

        battleButton.setText("-");
        tradeButton.setText("-");
    } 
    
    private void setEnemy() {
        this.enemy = menuMenager.gameManagerInstance().getAllEnemies()[currentPosition];
    }

    public void goToRight(){
        MenuMenager menuMenager = new MenuMenager();
        //controllo di non essere arrivato all'ultimo enemy da visualizzare (nel caso torno al primo!)
        if(menuMenager.gameManagerInstance().getPlayer().getCurrentPosition() < (GameManager.NUMENEMIES-1)){
            menuMenager.gameManagerInstance().getPlayer().changeCurrentPosition(+1);
            this.currentPosition++;
        }
        //altrimenti torno al primo enemy
        else{
            menuMenager.gameManagerInstance().getPlayer().setCurrentPosition(0);
            this.currentPosition = 0;
        }

        this.drawEnemyInformations();
    }

    public void goToLeft(){
        MenuMenager menuMenager = new MenuMenager();
        //controllo di non essere arrivato al primo enemy da visualizzare (nel caso vado all'ultimo!)
        if(menuMenager.gameManagerInstance().getPlayer().getCurrentPosition() > 0){
            menuMenager.gameManagerInstance().getPlayer().changeCurrentPosition(-1);
            this.currentPosition--;
        }
        //altrimenti finisco all'ultimo enemy
        else{
            menuMenager.gameManagerInstance().getPlayer().setCurrentPosition(GameManager.NUMENEMIES-1);
            this.currentPosition = GameManager.NUMENEMIES-1;
        }

        this.drawEnemyInformations();

    }

    public void requestToBattle() throws IOException{
        if(this.canBattle){
            MainApp.goToBattleMenu();
        }
    }

    public void requestGoToManagementMenu() throws IOException{
        MainApp.goToManagementMenu();
    }

    public void requestToTrade() throws IOException{
        if(enemy != null){
            if(enemy.hasBeenDefeated() && !enemy.hasTraded()){
                MainApp.goToTradePanel();
            }
        }
    }
    
    public void requestToShop() throws IOException{
        MainApp.goToShopMenu();
    }

}


