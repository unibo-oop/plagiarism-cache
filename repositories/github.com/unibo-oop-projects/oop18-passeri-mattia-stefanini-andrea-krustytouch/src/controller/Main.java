package controller;

import javafx.application.Application;

import javafx.fxml.FXMLLoader;

import javafx.scene.layout.AnchorPane;


import javafx.stage.Stage;
import view.SpongebobGameView;
import view.SpongebobGameViewImpl;
import view.SpongebobGameViewObserver;

public class Main extends Application {

    private SpongebobGameView view;
    private SpongebobGameViewObserver controller;
    
    @Override
    public void start(Stage PrimaryStage) {

        try {

            this.controller = new SpongebobGameControllerImpl();
            this.view = new SpongebobGameViewImpl(PrimaryStage, this.controller);
            this.view.start();
           /* root = FXMLLoader.load(getClass().getResource("Scena.fxml"));
            Dimension ScreenSize = Toolkit.getDefaultToolkit().getScreenSize();

            root.setMaxHeight((ScreenSize.getHeight() * 90) / 100);
            root.setMaxWidth((root.getMaxHeight() / 2));
            root.setMinHeight((ScreenSize.getHeight() * 40) / 100);
            root.setMinWidth(root.getMinHeight() / 2);
            root.setPrefHeight((ScreenSize.getHeight() * 90) / 100);
            root.setPrefWidth((root.getMaxHeight() / 2));
            Scene scene = new Scene(root);

            //starting the adaptive background image
            ImageView background = new ImageView(new Image("images/sfondo_FINALE.png"));

            background.setPreserveRatio(true);
            background.setSmooth(true);
            background.setVisible(true);
            background.fitWidthProperty().bind(root.widthProperty());
            background.fitHeightProperty().bind(root.heightProperty());

            //starting plankton
            SpawnerManager = new SpawnerPlanktonManager(root);
            SpawnerManager.start();


            //starting spongebob
            spongebob = SpongebobManager.getSpongebobManager(root);
            spongebob.start();

            //starting bonus spawner
            bonus = new BonusSpawner(root);
            bonus.start();


            PrimaryStage.setTitle("Krusty Touch");
            PrimaryStage.setMaxHeight(((ScreenSize.getHeight() * 90) / 100) + 39);
            PrimaryStage.setMaxWidth(PrimaryStage.getMaxHeight() / 2);
            PrimaryStage.setMinHeight(((ScreenSize.getHeight() * 40) / 100) + 16);
            PrimaryStage.setMinWidth(PrimaryStage.getMinHeight() / 2);
            PrimaryStage.centerOnScreen();
            PrimaryStage.setFullScreen(false);
            PrimaryStage.setMaximized(false);
            PrimaryStage.setOnCloseRequest(we -> System.exit(0));
            root.getChildren().add(background);
            PrimaryStage.setScene(scene);
            PrimaryStage.sizeToScene();
            PrimaryStage.show();*/

            /*MediaPlayer music = new MediaPlayer(new Media(new File("src/Images/SpongeBob_Soundtrack.mp3").toURI().toString()));
            music.setVolume(100);
            music.play();*/

        } catch(Exception e) {
            e.printStackTrace();
        }


    }

    public static void main(String[] args) {
        launch(args);
    }
    
}

