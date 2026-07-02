package view;

import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import controller.MainController;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

/**
 * 
 * In-Game pause menu view.
 *
 */
public class PauseMenu {

    private static final double OPACITY = 0.8;
    private final Canvas pause = new Canvas(Toolkit.getDefaultToolkit().getScreenSize().getWidth(),
            Toolkit.getDefaultToolkit().getScreenSize().getHeight());
    private final MenuBox buttonBox = new MenuBox(2.5, 2);
    private final List<MenuButton> buttons = new ArrayList<>();

    /**
     * 
     * @param root       The game view pane.
     * @param controller The main controller.
     */
    public PauseMenu(final GameView root, final MainController controller) {
        final GraphicsContext gc = pause.getGraphicsContext2D();
        gc.setFill(Color.rgb(0, 0, 0, OPACITY));
        gc.fillRect(0, 0, pause.getWidth(), pause.getHeight());
        gc.drawImage(new Image("pause.png"), pause.getWidth() / 4, pause.getHeight() / 4, pause.getWidth() / 2,
                pause.getHeight() / 10);

        this.buttons.addAll(Arrays.asList(new MenuButton("CONTINUE"), new MenuButton("NEW GAME"),
                new MenuButton("MAIN MENU"), new MenuButton("QUIT")));

        this.buttons.get(0).setOnAction(e -> {
            controller.continueGame();
        });
        this.buttons.get(1).setOnAction(e -> {
            controller.newGame();
        });
        this.buttons.get(2).setOnAction(e -> {
            controller.menu();
        });
        this.buttons.get(3).setOnAction(e -> {
            controller.exit();
        });

        this.buttonBox.getChildren().addAll(buttons);
        root.getChildren().addAll(pause, buttonBox);
    }
}
