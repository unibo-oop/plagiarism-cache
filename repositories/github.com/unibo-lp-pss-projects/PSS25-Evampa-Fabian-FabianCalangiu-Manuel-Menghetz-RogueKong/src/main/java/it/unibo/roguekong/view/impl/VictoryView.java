package it.unibo.roguekong.view.impl;

import it.unibo.roguekong.view.RogueKongView;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.control.Label;

/**
 * This class represents the view when the player wins
 */
public class VictoryView implements RogueKongView {
    private final Scene scene;
    private Runnable onMenu;

    public VictoryView() {
        VBox root = new VBox(20);
        root.setAlignment(Pos.CENTER);

        Label title = new Label("You won!!!");
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
    public Scene getScene() { return scene; }

    public void setOnMenu(Runnable onMenu) { this.onMenu = onMenu; }

    private void runIfNotNull(Runnable r) {
        if (r != null) r.run();
    }
}
