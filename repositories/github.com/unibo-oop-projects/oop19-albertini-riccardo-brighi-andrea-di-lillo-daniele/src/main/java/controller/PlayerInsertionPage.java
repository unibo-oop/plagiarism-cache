package controller;

import model.player.Player;
import model.player.PlayersCollection;
import model.player.PlayersCollectionImpl;
import utility.Handler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class PlayerInsertionPage implements Initializable {

    @FXML
    private Button btnGetBack;

    @FXML
    private Button btnSelect;

    @FXML
    private ImageView imgPlay;

    @FXML
    private Label lblControl;

    @FXML
    private TextField txtTextBox;

    @FXML
    private BorderPane mainPageContent;

    @FXML
    private Label lblCurrentPlayer;

    private int paginaChiamante = 0;

    public PlayerInsertionPage(){}
    public PlayerInsertionPage(int data){
        this.paginaChiamante = data;
    }

    @FXML
    public void btnSelect_onClick() throws IOException {
        String name = txtTextBox.getText();
        if(name.isEmpty())
            return;
        int presente = 0;
        //lettura file di dati sui Player

        PlayersCollection leaderboard = PlayersCollectionImpl.getLeaderboard();
        for(Player p:leaderboard.getPlayers()){
            if(p.getName().equals(name)){
                presente = 1;
            }
        }
        if(presente == 1){
            System.out.println("Presente");
            //setto il player corrente
            leaderboard.setCurrentPlayer(name);
            lblControl.setText("Bentornato "+name);
        }
        else{
            System.out.println("Assente");
            leaderboard.addPlayer(name);
            leaderboard.setCurrentPlayer(name);
            lblControl.setText("Hai creato il tuo profilo");
        }
        lblCurrentPlayer.setText(name);
        leaderboard.update();
    }

    @FXML
    public void  btnGetBack_onClick() throws IOException {
        if(paginaChiamante == 0) {
            Pane pane = FXMLLoader.load(ClassLoader.getSystemResource("layouts/main.fxml"));
            mainPageContent.getChildren().setAll(pane);
        } else {
            Pane pane = FXMLLoader.load(ClassLoader.getSystemResource("layouts/endGamePage.fxml"));
            mainPageContent.getChildren().setAll(pane);
        }
    }


    public void imgPlay_onClick(){
        //TODO
    }

    public void setGameOverPageCaller(){
        paginaChiamante = 1;
        System.out.println("Gameover called");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        PlayersCollection leaderboard = null;
        leaderboard = PlayersCollectionImpl.getLeaderboard();
        if(leaderboard.getCurrentPlayer().isPresent())
            lblCurrentPlayer.setText(leaderboard.getCurrentPlayer().get().getName());
        else
            lblCurrentPlayer.setText("");

        lblControl.setText("");

        imgPlay.setOnMouseExited(Handler.imgMouseOver);
        imgPlay.setOnMouseEntered(Handler.imgMouseExit);
    }
}
