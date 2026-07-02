package menu;

import cubecontroller.CubeDimensions;
import cubecontroller.GameMode;
import visual.Visual;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
/**
 * Class that initialize visually the Game.
 */
public class GameMenu extends Application implements EventHandler<ActionEvent> {

    private static final int INDENTATION = 20;
    private static final int WIDTH = 270;
    private static final int HEIGHT = 150;
    /**
     * Launch GameMenu GUI.
     * @param args -
     */
    public static void main(final String[] args) {
        launch(args);
    }

    @Override
    public final void start(final Stage primaryStage) throws Exception {
        final Button play;
        final Button resolve;
        final Button exit;

        primaryStage.setTitle("OOP18-RubikCube");
        primaryStage.setResizable(false);
        play = new Button();
        resolve = new Button();
        exit = new Button();
        play.setText("PLAY");
        resolve.setText("RESOLVE");
        exit.setText("EXIT");

        play.setOnAction(e -> {
            primaryStage.hide();
            final Stage game = new Stage();
            final Visual visual = new Visual(CubeDimensions.Cube3X3, GameMode.RANDOM);
            game.setScene(visual.getScene());
            game.setTitle("RubikCube - Random Mode");
            game.setResizable(false);
            game.show();
            game.setOnHiding(he -> {
                primaryStage.show();
            });

        });
        resolve.setOnAction(e -> {
            primaryStage.hide();
            final Stage game = new Stage();
            final Visual visual = new Visual(CubeDimensions.Cube3X3, GameMode.SOLVE);
            game.setScene(visual.getScene());
            game.setTitle("RubikCube - Solver Mode");
            game.setResizable(false);
            game.show();
            game.setOnHiding(ah -> {
                primaryStage.show();
            });

        });
        exit.setOnAction(e -> {
            System.exit(0);
        });

        final VBox layout = new VBox(INDENTATION);
        layout.getChildren().addAll(play, resolve, exit);
        layout.setAlignment(Pos.CENTER);

        final Scene scene = new Scene(layout, WIDTH, HEIGHT);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    @Override
    public void handle(final ActionEvent arg0) {
    }
}
