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
import managers.BattleLogManager;
import pokemon.Pokemon;
import team.Team;
import team.enemies.Enemy;

public class TradePanelController implements Initializable{

    @FXML
    Label enemyTeamName;

    @FXML
    Label playerName1;

    @FXML
    Label playerName2;

    @FXML
    Label playerName3;

    @FXML
    Label playerName4;

    @FXML
    Label playerName5;

    @FXML
    Label playerName6;

    @FXML
    Label enemyName1;

    @FXML
    Label enemyName2;

    @FXML
    Label enemyName3;

    @FXML
    Label enemyName4;

    @FXML
    Label enemyName5;

    @FXML
    Label enemyName6;

    @FXML
    ImageView player1;

    @FXML
    ImageView player2;

    @FXML
    ImageView player3;

    @FXML
    ImageView player4;

    @FXML
    ImageView player5;

    @FXML
    ImageView player6;

    @FXML
    ImageView enemy1;

    @FXML
    ImageView enemy2;

    @FXML
    ImageView enemy3;

    @FXML
    ImageView enemy4;

    @FXML
    ImageView enemy5;

    @FXML
    ImageView enemy6;

    @FXML
    ImageView arrows;
    
    @FXML
    ImageView yourOffer = new ImageView();

    @FXML
    ImageView choice = new ImageView();



    Enemy enemy;
    BattleLogManager battleLogManager;

    int playerIndex;
    int enemyIndex;

    Pokemon pokemonYouOffer;
    Pokemon pokemonChoice;

    boolean playerChosen;
    boolean enemyChosen;
    boolean firstTime;

    Image blank = new Image(this.getClass().getResourceAsStream("resources/Blank.png"));

    public TradePanelController() {
        // TODO Auto-generated constructor stub
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.battleLogManager = new BattleLogManager();
        int enemyIndex = battleLogManager.gameManagerInstance().getPlayer().getCurrentPosition();
        this.enemy = battleLogManager.gameManagerInstance().getAllEnemies()[enemyIndex];
        enemyTeamName.setText(enemy.getEnemyName() + " " + enemy.getEnemySurname() + "'s Team");
        this.setTeamImagesAndNames();
        arrows.setImage(new Image 
                (getClass().getResourceAsStream("resources/DoubleArrows.png")));
        this.setReferences();
        resetChoices();
    }
    
    private void setReferences(){
        if(MainApp.tradePanelController == null){
            MainApp.tradePanelController = this;
        }
    }

    private void setTeamImagesAndNames() {
        ImageView[] players = {player1, player2, player3, player4, player5, player6};
        Label[] playerNames = {playerName1, playerName2, playerName3, playerName4, playerName5, playerName6};
        ImageView[] enemies = {enemy1, enemy2, enemy3, enemy4, enemy5, enemy6};
        Label[] enemyNames = {enemyName1, enemyName2, enemyName3, enemyName4, enemyName5, enemyName6};

        int index = 0;
        for(Pokemon pokemon : this.battleLogManager.gameManagerInstance().getPlayer().getPokemon()){
            players[index].setImage(new Image 
                    (getClass().getResourceAsStream(pokemon.pokemonFrontImage())));
            if(pokemon.toString().startsWith("Tapu")){
                players[index].setScaleX(0.7);
                players[index].setScaleY(0.7);
            }
            playerNames[index].setText(pokemon.toString());
            index++;
        }

        index = 0;
        for(Pokemon pokemon : enemy.getPokemon()){
            enemies[index].setImage(new Image 
                    (getClass().getResourceAsStream(pokemon.pokemonFrontImage())));
            if(pokemon.toString().startsWith("Tapu")){
                enemies[index].setScaleX(0.7);
                enemies[index].setScaleY(0.7);
            }
            enemyNames[index].setText(pokemon.toString());
            index++;
        }
        while(index < Team.MAX_POKEMON){
            enemies[index].setImage(null);
            enemyNames[index].setText("-");
            index++;
        }
    }

    public void player1(){
        this.setPokemonYouOffer(0);
    }

    public void player2(){
        this.setPokemonYouOffer(1);
    }

    public void player3(){
        this.setPokemonYouOffer(2);
    }

    public void player4(){
        this.setPokemonYouOffer(3);
    }

    public void player5(){
        this.setPokemonYouOffer(4);
    }

    public void player6(){
        this.setPokemonYouOffer(5);
    }

    public void enemy1(){
        this.setChoice(0);
    }

    public void enemy2(){
        this.setChoice(1);
    }

    public void enemy3(){
        this.setChoice(2);
    }

    public void enemy4(){
        this.setChoice(3);
    }

    public void enemy5(){
        this.setChoice(4);
    }

    public void enemy6(){
        this.setChoice(5);
    }

    private void setPokemonYouOffer(int index){
        if(firstTime){
            this.yourOffer.setImage(new Image 
                                   (getClass().getResourceAsStream("resources/Blank.png")));
            this.firstTime = false;
        }
        else{
            playerIndex = index;
            pokemonYouOffer = this.battleLogManager.gameManagerInstance().getPlayer().getPokemon()[index];
            this.yourOffer.setImage(new Image 
                                   (getClass().getResourceAsStream(pokemonYouOffer.pokemonFrontImage())));
            this.yourOffer.setScaleX(1.5);
            this.yourOffer.setScaleY(1.5);
            playerChosen = true;
            this.checkTrade();
        }
    }

    private void setChoice(int index){
       if(firstTime){
           this.choice.setImage(new Image 
                                (getClass().getResourceAsStream("resources/Blank.png")));
           this.firstTime = false;
       }                        
       else{
           if(index < enemy.getNumOfPokemon()){
               enemyIndex = index;
               pokemonChoice = enemy.getPokemon()[index];
               this.choice.setImage(new Image 
                       (getClass().getResourceAsStream(pokemonChoice.pokemonFrontImage())));
               this.choice.setScaleX(1.5);
               this.choice.setScaleY(1.5);
               enemyChosen = true;
               this.checkTrade();
           }
       }
    }

    private void checkTrade(){
        if(enemyChosen && playerChosen){
            try {
                MainApp.goToTeamOverwriteAlter();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    public void resetChoices(){
        firstTime = true;
        playerIndex = 0;
        pokemonYouOffer = null;
        this.setPokemonYouOffer(0);
        firstTime = true;
        this.playerChosen = false;
        enemyIndex = 0;
        pokemonChoice = null;
        this.setChoice(0);
        enemyChosen = false;
    }

}
