package controller;


import model.player.Player;
import model.player.PlayersCollection;
import model.player.PlayersCollectionImpl;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.effect.Glow;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import utility.Handler;

import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;

public class LBController implements Initializable{

    private String path = "./";

    @FXML
    public ImageView restartButton;

    @FXML
    public BorderPane leaderPane;

    @FXML
    public Pane MainPageContent;

    int paginaChiamante = 0;

    public LBController(){}
    public LBController(int data){
        this.paginaChiamante = data;
    }

    @FXML
    public void onClick() throws IOException {
        System.out.println("CIAOOOOO");
        if(paginaChiamante == 1) {
            Pane pane = FXMLLoader.load(ClassLoader.getSystemResource("layouts/endGamePage.fxml"));
            MainPageContent.getChildren().setAll(pane);
        } else {
            Pane pane = FXMLLoader.load(ClassLoader.getSystemResource("layouts/main.fxml"));
            MainPageContent.getChildren().setAll(pane);
        }


    }

    public void init(int data){
        this.paginaChiamante = data;
        System.out.println("Data="+this.paginaChiamante);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("Data="+paginaChiamante);
        restartButton.setOnMouseEntered(Handler.imgMouseOver);
        restartButton.setOnMouseExited(Handler.imgMouseExit);

        try {
            caricaDati("");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void caricaDati(String path) throws Exception{
        VBox boxLeft = new VBox();
        VBox boxRight = new VBox();
        PlayersCollection list = PlayersCollectionImpl.getLeaderboard();
        System.out.println(list.toString());
        for(Player p : list.getPlayers()){
            Label label1 = new Label(p.getName());
            label1.getStyleClass().add("scrollpane-text");
            boxLeft.getChildren().add(label1);
            leaderPane.setLeft(boxLeft);

            Label label2 = new Label(""+p.getBestScore());
            label2.getStyleClass().add("scrollpane-text");
            boxRight.getChildren().add(label2);
            leaderPane.setRight(boxRight);
        }
    }
}
