package test;

import java.io.FileInputStream;

import controllers.commands.PlayerController;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import model.Character;
import model.Floor;

public class TestMain extends Application {
    private static final double W_STAGE = 1280, H_STAGE = 800;
    private Floor floor;

    private static final double W = 1280, H = 800;
    private Character player1, player2;

    @Override
    public final void start(final Stage stage) throws Exception {

        Group root = new Group();
        root.setId("pane");
        
        floor = new Floor("src/main/resources/floor/test.png");
        player1 = new Character("src/main/resources/characters/player1/attack1_1.png", 0, 1280, 0, 800);
        player2 = new Character("src/main/resources/characters/player2/attack1_1.png", 0, 1280, 0, 800);

        
        ImageView backgroundImg = new ImageView(new Image(new FileInputStream("src/main/resources/background/background.png")));

        //Creating a scene object
        root.getChildren().add(backgroundImg);
        root.getChildren().add(floor.getImageView());
        root.getChildren().add(player1.getImageView());
        root.getChildren().add(player2.getImageView());

        player2.setX(800);

        // Set Height and Width FLOOR
        floor.setWidth(W_STAGE);
        floor.setY(H_STAGE - floor.getHeight() / 2);

        Scene scene = new Scene(root, W, H);
        new PlayerController(player1, KeyCode.A, KeyCode.D, KeyCode.W, KeyCode.S).register(scene);
        new PlayerController(player2, KeyCode.LEFT, KeyCode.RIGHT, KeyCode.UP, KeyCode.DOWN).register(scene);

        // Creating an AnimationTimer to manage correct movements of our character
        AnimationTimer gameLoop = new AnimationTimer() {

            private long lastUpdate = 0;

            @Override
            public void handle(final long currentTime) {

                // The first iteration our Animation Timer does
                if (lastUpdate == 0) {
                    lastUpdate = currentTime;
                    return;
                }

                long elapsedNanoSeconds = currentTime - lastUpdate;
                double elapsedSeconds = elapsedNanoSeconds / 1_000_000_000.0;
                player1.move(elapsedSeconds);
                player2.move(elapsedSeconds);
                lastUpdate = currentTime;
            }
        };

        gameLoop.start();

        // Setting title to the Stage
        stage.setTitle("Loading an image");

        // Adding scene to the stage
        stage.setScene(scene);

        // Displaying the contents of the stage
        stage.show();

    }

    public static void main(final String[] args) {
        launch();
    }

}
