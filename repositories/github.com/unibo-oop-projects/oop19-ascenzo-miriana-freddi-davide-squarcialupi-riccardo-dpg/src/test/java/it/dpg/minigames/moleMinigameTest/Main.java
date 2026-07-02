package it.dpg.minigames.moleMinigameTest;

import it.dpg.minigames.molegame.MoleMiniGame;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        new Thread(
                () -> System.out.println(new MoleMiniGame().start())
        ).start();
    }
}
