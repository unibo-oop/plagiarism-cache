package it.unibo.roguekong.view.impl;

import it.unibo.roguekong.view.RogueKongView;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

/**
 * RulesView manages the Rules view rendering, that contains the game keybinds and rules
 */
public class RulesView implements RogueKongView {
    private final Scene scene;
    private Runnable onReturn;

    /**
     * Creates the RulesView view
     */
    public RulesView() {
        VBox root = new VBox(20);
        root.setAlignment(Pos.CENTER);

        Button onReturnButton = new Button("Return");
        onReturnButton.setOnAction(e -> runIfNotNull(onReturn));

        Label rulesLabelTitle = new Label("Rules:");
        rulesLabelTitle.setMinSize(80, 20);
        rulesLabelTitle.setWrapText(true);
        rulesLabelTitle.setStyle(
                """
                    -fx-text-fill: black;
                    -fx-font-size: 20px;
                    -fx-font-family: Arial;
                    -fx-font-weight: bold;
                    -fx-padding: 0 0 0 10;
                """
        );

        Label rulesLabel = new Label("Game Rules: " +
                "You have to reach the purple portal to access to the next level, trying to not get hit. Keep in mind that, " +
                "lives, will not restore after you pass into the next level, so BE AWARE. Once lives are zero, you are DEAD! " +
                "There are 5 levels and you have to pass all of them without dying! Good Luck!"
        );
        rulesLabel.setMinSize(80, 20);
        rulesLabel.setWrapText(true);
        rulesLabel.setStyle(
                        """
                            -fx-text-fill: black;
                            -fx-font-size: 14px;
                            -fx-padding: 10;
                            -fx-background-color: lightgray;
                            -fx-background-radius: 10;
                        """
        );

        Label keyBindsTitle = new Label("Keybinds:");
        keyBindsTitle.setMinSize(80, 20);
        keyBindsTitle.setWrapText(true);
        keyBindsTitle.setStyle(
                """
                    -fx-text-fill: black;
                    -fx-font-size: 20px;
                    -fx-font-family: Arial;
                    -fx-font-weight: bold;
                """
        );

        Label keybindsLabels = new Label("""
                - Press A to move on the left
                - Press D to move on the right
                - Press W to climb the ladder
                - Press SPACE to jump
                - Press ESC to pause the game
                """
        );
        keybindsLabels.setMinSize(80, 20);
        keybindsLabels.setWrapText(true);
        keybindsLabels.setStyle(
                """
                    -fx-text-fill: black;
                    -fx-font-size: 14px;
                    -fx-padding: 10;
                    -fx-background-color: lightgray;
                    -fx-background-radius: 10;
                """
        );

        root.getChildren().addAll(rulesLabelTitle,rulesLabel,keyBindsTitle, keybindsLabels, onReturnButton);

        this.scene = new Scene(root, 800, 600);
        this.scene.getStylesheets().add(
                getClass().getResource("/css/menu.css").toExternalForm()
        );
    }
    @Override
    public Scene getScene() { return this.scene; }

    public void setOnReturn(Runnable r) { this.onReturn = r; }

    private void runIfNotNull(Runnable r) { if (r != null) r.run(); }
}
