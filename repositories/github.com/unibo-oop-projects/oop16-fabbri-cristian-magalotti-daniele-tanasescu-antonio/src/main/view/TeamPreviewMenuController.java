package main.view;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import main.MainApp;
import managers.BattleLogManager;
import pokemon.Gender;
import team.Player;
import team.Team;


public class TeamPreviewMenuController implements Initializable{
    
    private static final int NUMOFMOVES = 4;
    
    @FXML
    ImageView teammer1;
    
    @FXML
    ImageView teammer2;
    
    @FXML
    ImageView teammer3;
    
    @FXML
    ImageView teammer4;
    
    @FXML
    ImageView teammer5;
    
    @FXML
    ImageView teammer6;
    
    @FXML
    ImageView gender1;
    
    @FXML
    ImageView gender2;
    
    @FXML
    ImageView gender3;
    
    @FXML
    ImageView gender4;
    
    @FXML
    ImageView gender5;
    
    @FXML
    ImageView gender6;
    
    @FXML
    Label pokemonName1;
    
    @FXML
    Label pokemonName2;
    
    @FXML
    Label pokemonName3;
    
    @FXML
    Label pokemonName4;
    
    @FXML
    Label pokemonName5;
    
    @FXML
    Label pokemonName6;
    
    @FXML
    Label pokemonMove11;
    
    @FXML
    Label pokemonMove12;
    
    @FXML
    Label pokemonMove13;
    
    @FXML
    Label pokemonMove14;
    
    @FXML
    Label pokemonMove21;
    
    @FXML
    Label pokemonMove22;
    
    @FXML
    Label pokemonMove23;
    
    @FXML
    Label pokemonMove24;
    
    @FXML
    Label pokemonMove31;
    
    @FXML
    Label pokemonMove32;
    
    @FXML
    Label pokemonMove33;
    
    @FXML
    Label pokemonMove34;
    
    @FXML
    Label pokemonMove41;
    
    @FXML
    Label pokemonMove42;
    
    @FXML
    Label pokemonMove43;
    
    @FXML
    Label pokemonMove44;
    
    @FXML
    Label pokemonMove51;
    
    @FXML
    Label pokemonMove52;
    
    @FXML
    Label pokemonMove53;
    
    @FXML
    Label pokemonMove54;
    
    @FXML
    Label pokemonMove61;
    
    @FXML
    Label pokemonMove62;
    
    @FXML
    Label pokemonMove63;
    
    @FXML
    Label pokemonMove64;
    
    Player player;

    public TeamPreviewMenuController() {
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        BattleLogManager battleLogManager = new BattleLogManager();
        this.player = battleLogManager.gameManagerInstance().getPlayer();
        this.drawTeamSprites();
        this.setPokemonNamesAndGenders();
        this.setMoves();
        
    }
    
    private void drawTeamSprites(){
        ImageView[] team = new ImageView[]{teammer1, teammer2, teammer3, teammer4, teammer5, teammer6};
        for(int i = 0; i < Team.MAX_POKEMON; i++){
            team[i].setImage(new Image (getClass().getResourceAsStream(player.getPokemon()[i].pokemonFrontImage())));
            if(player.getPokemon()[i].toString().startsWith("Tapu")){
                team[i].setScaleX(0.7);
                team[i].setScaleY(0.7);
            }
        }
    }
    
    private void setPokemonNamesAndGenders(){
        Label[] names = new Label[]{pokemonName1, pokemonName2, pokemonName3, pokemonName4, pokemonName5, pokemonName6};
        ImageView[] genders = new ImageView[]{gender1, gender2, gender3, gender4, gender5, gender6};
        for(int i = 0; i < Team.MAX_POKEMON; i++){
            names[i].setText(player.getPokemon()[i].toString());
            if(player.getPokemon()[i].getGender().equals(Gender.MALE)){
                genders[i].setImage(new Image (getClass().getResourceAsStream("resources/MaleSymbol.png")));
            }
            else if(player.getPokemon()[i].getGender().equals(Gender.FEMALE)){
                genders[i].setImage(new Image (getClass().getResourceAsStream("resources/FemaleSymbol.png")));
            }
            else{
                genders[i].setImage(new Image (getClass().getResourceAsStream("resources/Blank.png")));
            }
        }
    }
    
    private void setMoves(){
        Label[] moves = new Label[]{pokemonMove11, pokemonMove12, pokemonMove13, pokemonMove14,
                                    pokemonMove21, pokemonMove22, pokemonMove23, pokemonMove24,
                                    pokemonMove31, pokemonMove32, pokemonMove33, pokemonMove34,
                                    pokemonMove41, pokemonMove42, pokemonMove43, pokemonMove44,
                                    pokemonMove51, pokemonMove52, pokemonMove53, pokemonMove54,
                                    pokemonMove61, pokemonMove62, pokemonMove63, pokemonMove64};
        for(int i = 0; i < Team.MAX_POKEMON; i++){
            for(int j = 0; j < NUMOFMOVES; j++){
                int k = i*4 + j;
                moves[k].setText(player.getPokemon()[i].getMove(j).getMoveName());
                moves[k].setTextFill(Paint.valueOf(player.getPokemon()[i].getMove(j).getMoveType().getTypeColor()));
                moves[k].setEffect(new DropShadow(10, Color.BLACK));
            }
        }
    }
    
    public void requestBackToManagementMenu() throws IOException{
        MainApp.disableTeamPreview();
        MainApp.goToManagementMenu();
    }

}
