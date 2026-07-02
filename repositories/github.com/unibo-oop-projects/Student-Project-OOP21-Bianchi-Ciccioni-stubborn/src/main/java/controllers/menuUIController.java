package controllers;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * menuUIController is a presenter class (we called it a controller to make its role more clear)
 * that acts as a game menu.
 */
public final class menuUIController {

    private static final int SCENE_WIDTH = 500;
    private static final int SCENE_HEIGHT = 500;

    @FXML
    private Button exitButton;

    @FXML
    private Button scoreButton;

    @FXML
    private Pane mainPane;
    
    /**
     * This is the constructor of the controller.
     */
    public menuUIController() {
    }

    /**
     * Start a new game.
     */
    public void newGame() {
        Stage boardStage = (Stage) this.mainPane.getScene().getWindow();
        Parent root = null;
        try {
            root = FXMLLoader.load(ClassLoader.getSystemResource("layouts/board.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        final Scene scene = new Scene(root, SCENE_WIDTH + 40, SCENE_HEIGHT + 40);
        boardStage.setScene(scene);
        boardStage.show();
    }

    /**
     * View a table of all saved scores.
     */
    public void viewScores() {
        Stage boardStage = (Stage) this.mainPane.getScene().getWindow();
        Parent root = null;
        try {
            root = FXMLLoader.load(ClassLoader.getSystemResource("layouts/scores.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        final Scene scene = new Scene(root, SCENE_WIDTH, SCENE_HEIGHT);
        boardStage.setScene(scene);
        boardStage.show();
    }

    /**
     * Exit the game.
     */
    public void exit() {
        System.out.println("I hope to see you again");
        System.exit(0);
    }
}
