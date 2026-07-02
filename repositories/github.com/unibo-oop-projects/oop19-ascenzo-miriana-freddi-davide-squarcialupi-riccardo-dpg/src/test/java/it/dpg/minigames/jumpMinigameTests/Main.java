package it.dpg.minigames.jumpMinigameTests;

import it.dpg.minigames.jumpgame.JumpMinigame;
import it.dpg.minigames.punchygame.PunchyMinigame;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        new Thread(
                () -> System.out.println(new JumpMinigame().start())
        ).start();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
