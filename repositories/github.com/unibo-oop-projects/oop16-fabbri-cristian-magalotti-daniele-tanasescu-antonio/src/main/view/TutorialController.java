package main.view;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import main.MainApp;

public class TutorialController implements Initializable{
    
    private static final String WELCOME = "Welcome to Javamon!";
    private static final String GAME = "In this game you're going to\nbattle a lot of people!";
    private static final String PEOPLE = "Some are easy to defeat,\nsome others are very strong!";
    private static final String OPTIONS = "In Javamon's world you'll see\n there are a lot of options aviliable!";
    private static final String SHOP = "On the top of the Game Menu\nyou'll see there's the Shop.";
    private static final String ENTERINGSHOP = "Winning battles you'll earn points\nyou can spend here\n to improve your team!";
    private static final String ATTENTION = "Pay attention: if you buy\na new Pokemon, you'll also have\n to free one of your Team.";
    private static final String ARROWS = "Use the arrows to navigate\nthrough all the enemies.";
    private static final String ENEMIES = "If you're strong and brave\nenough to battle that enemy\nyou'll see the button\n showing \"Battle\"";
    private static final String WINLOSE = "If you win, you'll\nearn some points; if you lose,\nprepare for some troubles!";
    private static final String TRADE = "Once you've defeated an enemy,\n if you think one of his/her\nPokemon is too cool or strong,\n"
                                         + "you can ask a Trade!";
    private static final String TRADE2 = "You can ask only one Trade\nper Enemy, and also\nprepare to say goodbye to one of\nyour actual teammate.";
    private static final String MANAGEMENT = "On the bottom of the Game Menu\nthere's the Management Menu.";
    private static final String TEAM = "In the Team Preview,\nyou can see your actual Team\nwith all Pokemons\nand their moves!";
    private static final String SAVE = "Also, in the Mnagement Menu,\nyou can Save all your progress,\n if you like.";
    private static final String PLEASE = "(Please, don't touch\nthe save file, if you\nare cool enough to find it!\n"
                                           + "Or you'll destroy this game...)";
    private static final String READY = "So, are you ready?";
    private static final String AWAITS = "15 strong Enemies awaits for you,\ncan you battle'em all?";
    private static final String GIFT = "(As a special gift,\nwhen you'll win your first\nbattle, you'll earn more points.)";
    private static final String USING = "(Are you going to collect\n more of them, maybe\nto buy a Legendary Pokemon,\nor you'll improve immediately\n"
                                          + "your team?)";
    private static final String JOURNEY = "Your journey has just begun:\ngood luck!\n\nSincerly, the PointerForward Team.";
    
    private static final int MAXMESSAGES = 20;
    
    @FXML
    Label message;
    
    private int messageCounter;
    private final String[] messages = new String[]{WELCOME, GAME, PEOPLE, OPTIONS, SHOP, ENTERINGSHOP, ATTENTION, ARROWS,
                                                   ENEMIES, WINLOSE, TRADE, TRADE2, MANAGEMENT, TRADE, SAVE, PLEASE, READY,
                                                   AWAITS, GIFT, USING, JOURNEY};

    public TutorialController() {
        // TODO Auto-generated constructor stub
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        messageCounter = 0;
        this.showMessage();
    }

    private void showMessage() {
        this.message.setText(this.messages[messageCounter]); 
    }
    
    public void goToPrevious(){
        if(this.messageCounter > 0){
            this.messageCounter--;
            this.showMessage();
        }
    }
    
    public void goToNext() throws IOException{
        if(this.messageCounter < MAXMESSAGES){
            this.messageCounter++;
            this.showMessage();
        }
        else{
            this.requestGoToGameMenu();
        }
    }
    
    public void skipTutorial() throws IOException{
        MainApp.disableTutorial();
        this.requestGoToGameMenu();
    }
    
    private void requestGoToGameMenu() throws IOException{
        MainApp.disableTutorial();
        MainApp.goToGameMenu();
    }

}
