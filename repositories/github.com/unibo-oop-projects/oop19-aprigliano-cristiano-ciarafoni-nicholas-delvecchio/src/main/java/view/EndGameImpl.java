package view;

import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import java.io.IOException;


public class EndGameImpl implements EndGame {

    private static final double FX_OBJECT_WIDTH = 500;
    private static final double FX_OBJECT_HEIGHT = 300;
    private static final int HALF = 2;
    private final SuperMarioRunView view;
    private final Button gameOver = new Button("!!! YOU LOST !!!");
    private int playerScore;
    private String name;


    public EndGameImpl(final SuperMarioRunView view, final int userScore) {
        this.view = view;
        this.playerScore = userScore;
    }
    /**
     * This method set the name and the score of the player.
     * @param name user name
     * @param playerScore player score
     */
    @Override
    public void setResult(final String name, final int playerScore) {
        this.playerScore = playerScore;
        this.name = name;
    }

    @Override
    public final Button gameOver() {
        gameOver.setPrefSize(FX_OBJECT_WIDTH, FX_OBJECT_HEIGHT);
        gameOver.setLayoutX(view.getWidth() / HALF  - FX_OBJECT_WIDTH  / HALF);
        gameOver.setLayoutY(view.getHeight() / (HALF * HALF * HALF));
        gameOver.setAlignment(Pos.CENTER);
        gameOver.getStyleClass().add("btnGameOver");

        gameOver.setOnAction(actionEvent -> {
            final Parent menu;
            try {
                menu = FXMLLoader.load(ClassLoader.getSystemResource("layouts/Ranking.fxml"));
                final Scene menuScene = new Scene(menu);
                final Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                window.setScene(menuScene);
                window.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        return null;
    }

    @Override
    public final Button getButton() {
        return gameOver;
    }
}
