package main.view;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import main.MainApp;
import managers.SaveManager;
import pokemon.Pokemon;

public class AdieuTeamMemberSelectController implements Initializable {
    
    @FXML
    ImageView pokemon1;
    
    @FXML
    ImageView pokemon2;
    
    @FXML
    ImageView pokemon3;
    
    @FXML
    ImageView pokemon4;
    
    @FXML
    ImageView pokemon5;
    
    @FXML
    ImageView pokemon6;
    
    public static Pokemon adieuPokemon;
    
    private Pokemon[] team;
    private SaveManager saveManager;

    public AdieuTeamMemberSelectController() {
        // TODO Auto-generated constructor stub
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.saveManager = new SaveManager();
        this.team = this.saveManager.gameManagerInstance().getPlayer().getPokemon();
        ImageView[] pokemons = new ImageView[]{pokemon1, pokemon2, pokemon3, pokemon4, pokemon5, pokemon6};
        for(int i = 0; i < this.saveManager.gameManagerInstance().getPlayer().getNumOfPokemon(); i++){
            pokemons[i].setImage(new Image(
                                 this.getClass().getResourceAsStream(this.team[i].pokemonFrontImage())));
            if(this.team[i].toString().startsWith("Tapu")){
                pokemons[i].setScaleX(0.7);
                pokemons[i].setScaleY(0.7);
            }
        }
    }
    
    public void choose1(){
        this.adieuExMember(0);
    }
    
    public void choose2(){
        this.adieuExMember(1);
    }
    
    public void choose3(){
        this.adieuExMember(2);
    }
    
    public void choose4(){
        this.adieuExMember(3);
    }
    
    public void choose5(){
        this.adieuExMember(4);
    }
    
    public void choose6(){
        this.adieuExMember(5);
    }
       
    
    private void adieuExMember(int index){
        adieuPokemon = this.saveManager.gameManagerInstance().getPlayer().getPokemon()[index];
        this.saveManager.gameManagerInstance().getPlayer().trade(index, ShopMenuController.getSoldPokemon());
        try {
            MainApp.goToGoodbyeAndWelcome();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
