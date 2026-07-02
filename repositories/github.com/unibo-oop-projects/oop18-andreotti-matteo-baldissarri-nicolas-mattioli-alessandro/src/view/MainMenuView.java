package view;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import controller.MenuController;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;

/**
 * 
 * This Class create the Main Menu View.
 *
 */
public class MainMenuView extends StackPane {

    private final MenuTitle title = new MenuTitle(3.7, 4);
    private final MenuBox buttonBox = new MenuBox();
    private final List<MenuButton> buttons = new ArrayList<>();

    /**
     * 
     * @param controller Main Controller of the application.
     * @param scene      Main scene.
     */
    public MainMenuView(final MenuController controller, final Scene scene) {
        super();

        this.buttons.addAll(Arrays.asList(new MenuButton("NEW GAME"), new MenuButton("LEADERBOARD"),
                new MenuButton("OPTIONS"), new MenuButton("CREDITS"), new MenuButton("EXIT")));

        this.buttons.get(0).setOnAction(e -> {
            controller.goToNewGame();
        });
        this.buttons.get(1).setOnAction(e -> {
            controller.goToLeaderboards();
        });
        this.buttons.get(2).setOnAction(e -> {
            controller.goToOptions();
        });
        this.buttons.get(3).setOnAction(e -> {
            controller.goToCredits();
        });
        this.buttons.get(4).setOnAction(e -> {
            controller.quit();
        });

        this.buttonBox.getChildren().addAll(buttons);
        this.getChildren().addAll(title, buttonBox);
        this.setId("mainPane");
        this.getStylesheets().add("style.css");
        scene.setRoot(this);
    }
}
