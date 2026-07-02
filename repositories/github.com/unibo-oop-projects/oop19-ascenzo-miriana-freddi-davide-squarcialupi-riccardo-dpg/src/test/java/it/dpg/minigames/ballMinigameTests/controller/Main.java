package it.dpg.minigames.ballMinigameTests.controller;

import it.dpg.minigames.ballgame.controller.BallMinigame;
import it.dpg.minigames.base.controller.Minigame;
import javafx.application.Application;
import javafx.stage.Stage;

import java.util.concurrent.TimeUnit;

public class Main extends Application {
    Thread worker = new Thread(() -> {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        int result;
        Minigame m = new BallMinigame();
        result = m.start();
        System.out.println(result);
    });

    @Override
    public void start(Stage primaryStage) {
        worker.setDaemon(true);
        worker.start();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
