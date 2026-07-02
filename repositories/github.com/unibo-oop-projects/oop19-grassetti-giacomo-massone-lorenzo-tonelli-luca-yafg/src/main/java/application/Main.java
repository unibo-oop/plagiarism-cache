package application;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * This class represent the Main class of the JavaFX-based application.
 */
public final class Main extends Application {

    private static final double W = 600, H = 400;

    @Override
    public void start(Stage stage) throws Exception {

        Group dungeon = new Group();

        Scene scene = new Scene(dungeon, W, H, Color.FORESTGREEN);

        /**
         *  An switcher for commands the JavaFX window takes as input
         */
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                switch (event.getText()) {
                case "w":
                    System.out.println("Up");
                    break;
                case "s":
                    System.out.println("Down");
                    break;
                case "d":
                    System.out.println("Right");
                    break;
                case "a":
                    System.out.println("Left");
                    break;
                case "c":
                    System.out.println("Light Attack");
                    break;
                case "v":
                    System.out.println("Heavy Attack");
                    break;
                case "b":
                    System.out.println("Special Move");
                    break;
                }
            }
        });


        stage.setScene(scene);
        stage.show();
    }

    /**
     * 
     * @param args
     *                 unused
     */
    public static void main(final String[] args) {
        launch();

    }

}
