package main.view;

import java.io.IOException;
import java.io.Serializable;
import java.net.URL;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import main.MainApp;
import managers.PokemonListManager;
import managers.SaveManager;
import pokemon.Pokemon;

public class ShopMenuController implements Initializable, Serializable{

    private static final long serialVersionUID = 1L;

    private static final int MAXMOVES = 4;
    private static final int POKEMONPERPAGE = 3;
    private static final int MAXPAGES = 19;

    @FXML
    Label playerPoints;

    @FXML
    Label upPokemonName;

    @FXML
    Label upPokemonAbility;

    @FXML
    Label middlePokemonName;

    @FXML
    Label middlePokemonAbility;

    @FXML 
    Label downPokemonName;

    @FXML
    Label downPokemonAbility;

    @FXML
    Label upPokemonMove1;

    @FXML
    Label upPokemonMove2;

    @FXML
    Label upPokemonMove3;

    @FXML
    Label upPokemonMove4;

    @FXML
    Label middlePokemonMove1;

    @FXML
    Label middlePokemonMove2;

    @FXML
    Label middlePokemonMove3;

    @FXML
    Label middlePokemonMove4;

    @FXML
    Label downPokemonMove1;

    @FXML
    Label downPokemonMove2;

    @FXML
    Label downPokemonMove3;

    @FXML
    Label downPokemonMove4;

    @FXML       
    Label upPokemonPrice;

    @FXML       
    Label middlePokemonPrice;

    @FXML
    Label downPokemonPrice;

    @FXML
    ImageView leftArrow;

    @FXML
    ImageView rightArrow;

    @FXML
    ImageView upPokemonSprite;

    @FXML
    ImageView middlePokemonSprite;

    @FXML 
    ImageView downPokemonSprite;

    @FXML
    Button upButton;

    @FXML
    Button middleButton;

    @FXML
    Button downButton;

    private static Pokemon soldPokemon;
    private static Pokemon upPokemon;
    private static Pokemon middlePokemon;
    private static Pokemon downPokemon;

    private Map<Pokemon, Integer> shop = new LinkedHashMap<Pokemon, Integer>();
    private int currentPosition;     
    private boolean shopCreated;
    private SaveManager saveManager;

    public ShopMenuController() {
        this.shopCreated = false;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.currentPosition = 0;
        this.saveManager = new SaveManager();
        if(!this.shopCreated){
            this.createShop();
            this.shopCreated = true;
        }

        this.refreshScreen();
        this.setArrows();
    }

    private void setPlayerPoints() {
        this.playerPoints.setText(Integer.toString(saveManager.gameManagerInstance().getPlayer().getPlayerPoints()));
    }

    private void setArrows() {
        this.leftArrow.setImage(new Image (getClass().getResourceAsStream("resources/LeftArrow.png"))); 
        this.rightArrow.setImage(new Image (getClass().getResourceAsStream("resources/RightArrow.png")));  
    }

    private void setPokemonOnSell() {
        Iterator<Pokemon> iterator = this.shop.keySet().iterator();
        Pokemon pokemon = null;
        for(int i = 0; i < this.currentPosition*3 && iterator.hasNext(); i++){
            pokemon = iterator.next();
        }
        if(iterator.hasNext()){
            upPokemon = iterator.next();
        }
        if(iterator.hasNext()){
            middlePokemon = iterator.next();
        }
        else{
            middlePokemon =  null;
        }
        if(iterator.hasNext()){
            downPokemon = iterator.next();
        }
        else{
            downPokemon = null;
        }
        Pokemon[] pokemons = new Pokemon[]{upPokemon, middlePokemon, downPokemon};
        ImageView[] sprites = new ImageView[]{upPokemonSprite, middlePokemonSprite, downPokemonSprite};
        Label[] names = new Label[]{upPokemonName, middlePokemonName, downPokemonName};
        Label[] abilities = new Label[]{upPokemonAbility, middlePokemonAbility, downPokemonAbility};
        Label[] moves = new Label[]{upPokemonMove1, upPokemonMove2, upPokemonMove3, upPokemonMove4,
                middlePokemonMove1, middlePokemonMove2, middlePokemonMove3, middlePokemonMove4,
                downPokemonMove1, downPokemonMove2, downPokemonMove3, downPokemonMove4};
        Label[] prices = {upPokemonPrice, middlePokemonPrice, downPokemonPrice};
        Button[] shopButtons = new Button[]{upButton, middleButton, downButton};
        for(int i = 0; i < POKEMONPERPAGE; i++){
            if(pokemons[i] != null){
                sprites[i].setImage(new Image(
                                    this.getClass().getResourceAsStream(pokemons[i].pokemonFrontImage())));
                if(!pokemons[i].toString().startsWith("Tapu")){
                    sprites[i].setScaleX(1.5);
                    sprites[i].setScaleY(1.5);
                }
                else{
                    sprites[i].setScaleX(0.9);
                    sprites[i].setScaleY(0.9);
                }
                names[i].setText(pokemons[i].toString());
                abilities[i].setText("(" + pokemons[i].getAbility().getName() + ")");
                for(int j = i*MAXMOVES; j < (i+1)*MAXMOVES; j++){
                    int moveIndex = j - i*MAXMOVES;
                    moves[j].setText(pokemons[i].getMove(moveIndex).getMoveName());
                    moves[j].setTextFill(Paint.valueOf(pokemons[i].getMove(moveIndex).getMoveType().getTypeColor()));
                    moves[j].setEffect(new DropShadow(10, Color.BLACK));  
                }
                prices[i].setText(Integer.toString(this.shop.get(pokemons[i])));
                if(this.saveManager.gameManagerInstance().getPlayer().getPlayerPoints() >= this.shop.get(pokemons[i])){
                    shopButtons[i].setText("Buy :D");
                    shopButtons[i].setTextFill(Paint.valueOf("Green"));
                }
                else{
                    shopButtons[i].setText("Not enough\npoints!");
                    shopButtons[i].setTextFill(Paint.valueOf("Red"));
                }
            }
            else{
                sprites[i].setImage(new Image(
                                    this.getClass().getResourceAsStream("resources/Blank.png")));
                names[i].setText("");
                abilities[i].setText("");
                for(int j = i*MAXMOVES; j < (i+1)*MAXMOVES; j++){
                    moves[j].setText("");
                }
                prices[i].setText("");
                shopButtons[i].setText("");
            }
        }
    }

    private void refreshScreen(){
        this.setPlayerPoints();
        this.setPokemonOnSell();
    }


    private void createShop() {
        for(Pokemon pokemon : PokemonListManager.POKEMONSHOPLIST1000){
            this.shop.put(pokemon, 1000);
        }
        for(Pokemon pokemon : PokemonListManager.POKEMONSHOPLIST3000){
            this.shop.put(pokemon, 3000);
        }
        for(Pokemon pokemon : PokemonListManager.POKEMONSHOPLIST5000){
            this.shop.put(pokemon, 5000);
        }
    }

    public void goToLeft(){
        if(this.currentPosition > 0){
            this.currentPosition--;
            this.refreshScreen();
        }
    }

    public void goToRight(){
        if(this.currentPosition < MAXPAGES){
            this.currentPosition++;
            this.refreshScreen();
        }
    }

    public static Pokemon getSoldPokemon() {
        return soldPokemon;
    }

    public void chooseUp() throws IOException{
        if(this.saveManager.gameManagerInstance().getPlayer().getPlayerPoints() >= this.shop.get(upPokemon)){
            soldPokemon = upPokemon;
            this.saveManager.gameManagerInstance().getPlayer().decrementPlayerPoints(this.shop.get(upPokemon));
            this.requestGoToAdieu();
        }
    }

    public void chooseMiddle() throws IOException{
        if(this.saveManager.gameManagerInstance().getPlayer().getPlayerPoints() >= this.shop.get(middlePokemon)){
            soldPokemon = middlePokemon;
            this.saveManager.gameManagerInstance().getPlayer().decrementPlayerPoints(this.shop.get(middlePokemon));
            this.requestGoToAdieu();
        }
    }

    public void chooseDown() throws IOException{
        if(this.saveManager.gameManagerInstance().getPlayer().getPlayerPoints() >= this.shop.get(downPokemon)){
            soldPokemon = downPokemon;
            this.saveManager.gameManagerInstance().getPlayer().decrementPlayerPoints(this.shop.get(downPokemon));
            this.requestGoToAdieu();
        }
    }

    public void requestGoToAdieu() throws IOException{
        MainApp.goToAdieu();
    }
    
    public void requestBackToGameMenu() throws IOException{
        MainApp.goToGameMenu();
    }

}
