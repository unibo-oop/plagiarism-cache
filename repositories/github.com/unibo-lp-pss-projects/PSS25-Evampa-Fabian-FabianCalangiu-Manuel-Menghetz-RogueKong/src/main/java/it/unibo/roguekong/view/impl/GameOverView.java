package it.unibo.roguekong.view.impl;

import it.unibo.roguekong.view.RogueKongView;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

/**
 * GameOverView renders the Game Over page
 */
public class GameOverView implements RogueKongView {
    private final Scene scene;
    private Runnable onResume, onMenu;

    /**
     * Prepares the Game Over page layout, such as Labels and action buttons
     */
    public GameOverView() {
        VBox root = new VBox(20);
        root.setAlignment(Pos.CENTER);

        Label title = new Label("Game Over");
        title.getStyleClass().add("title");
        Button menu = new Button("Menu");

        menu.setOnAction(e -> runIfNotNull(onMenu));

        root.getChildren().addAll(title, menu);
        this.scene = new Scene(root, 800, 600);
        this.scene.getStylesheets().add(
                getClass().getResource("/css/menu.css").toExternalForm()
        );
    }

    @Override
    public Scene getScene() {
        return scene;
    }

    public void setOnMenu(Runnable r) {
        this.onMenu = r;
    }

    private void runIfNotNull(Runnable r) {
        if (r != null) r.run();
    }
}
