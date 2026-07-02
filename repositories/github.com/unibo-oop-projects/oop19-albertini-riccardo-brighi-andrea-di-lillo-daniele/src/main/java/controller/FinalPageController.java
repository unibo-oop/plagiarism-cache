package controller;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class FinalPageController implements Initializable {
    @FXML
    public ImageView restartButtonnn;
    @FXML
    public ImageView stats;
    @FXML
    public ImageView btnHome;

    @FXML
    public Pane MainPageContent;

    @FXML
    public Label lblPunteggio;

    @FXML
    public ImageView imgLogin;

    @FXML
    public void clickHandler() throws IOException {
        System.out.println("CiAO");
        //Pane pane = FXMLLoader.load(ClassLoader.getSystemResource("layouts/leaderboard.fxml"));

        FXMLLoader loader = new FXMLLoader(getClass().getResource("./../layouts/leaderboard.fxml"));
        Pane pane1 = (Pane)loader.load();
        LBController controller = loader.<LBController>getController();

        System.out.println(controller);


        MainPageContent.getChildren().setAll(pane1);//(Pane)loader.load() pane
        controller.init(1);
    }

    @FXML
    public void imgLogin_onClick() throws IOException{

        FXMLLoader loader = new FXMLLoader(getClass().getResource("./../layouts/playerInsertionPage.fxml"));
        Pane pane1 = (Pane)loader.load();
        PlayerInsertionPage controller = loader.<PlayerInsertionPage>getController();

        System.out.println(controller);


        MainPageContent.getChildren().setAll(pane1);
        controller.setGameOverPageCaller();
    }

    @FXML
    public void btnHome_onClick() throws IOException {
        Pane pane = FXMLLoader.load(ClassLoader.getSystemResource("layouts/main.fxml"));
        MainPageContent.getChildren().setAll(pane);
    }

    @FXML
    public void getBack() throws  IOException {

    }

    @FXML
    public void mouseOver() {

        restartButtonnn.setImage(new Image("images/play-again_mouseOver.png"));
    }

    @FXML
    public void mouseExit() {
        System.out.println("CiAO");
        restartButtonnn.setImage(new Image("images/play-again.png"));
    }

    EventHandler<MouseEvent> eventHandlerOver = mouseEvent -> {
        ImageView img = (ImageView)mouseEvent.getSource();
        Glow glow = new Glow(0.2);
        img.setEffect(glow);
    };

    EventHandler<MouseEvent> eventHandlerExit = mouseEvent -> {
        ImageView img = (ImageView)mouseEvent.getSource();
        Glow glow = new Glow(0.0);
        img.setEffect(glow);
    };



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        restartButtonnn.setOnMouseEntered(eventHandlerOver);
        restartButtonnn.setOnMouseExited(eventHandlerExit);
        stats.setOnMouseEntered(eventHandlerOver);
        stats.setOnMouseExited(eventHandlerExit);
        btnHome.setOnMouseEntered(eventHandlerOver);
        btnHome.setOnMouseExited(eventHandlerExit);

    }
}
