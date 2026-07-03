package it.unibo.roguekong.view.impl;

import it.unibo.roguekong.view.RogueKongView;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

/**
 * MenuView manages the Menu page rendering
 */
public class MenuView implements RogueKongView {
    private final Scene scene;

    /**
     * Runnable is an interface defined as follows:
     * interface Runnable{
     *  void run();
     * }
     * It simply executes a series of user written instructions
     */
    private Runnable onStart, onScore, onRules, onExit;

    public MenuView(){
        /*
         * From javafx docs: VBox lays out its children in a single vertical column.
         * If the vbox has a border and/or padding set,
         * then the contents will be laid out within those insets.
         * -------------------------------------------------------------------------
         * setAlignment aligns the referenced object in a certain
         * position relative to the scene.
         */
        VBox root = new VBox(20);
        root.setAlignment(Pos.CENTER);

        Label title = new Label("Rogue Kong");
        title.getStyleClass().add("game-title");
        Button start = new Button("Start");
        Button score = new Button("Score");
        Button rules = new Button("Rules");
        Button exit = new Button("Exit");

        start.setOnAction(e -> runIfNotNull(onStart));
        score.setOnAction(e -> runIfNotNull(onScore));
        rules.setOnAction(e -> runIfNotNull(onRules));
        exit.setOnAction(e -> runIfNotNull(onExit));

        root.getChildren().addAll(title, start, score, rules, exit);
        this.scene = new Scene(root, 800, 600);
        this.scene.getStylesheets().add(
                getClass().getResource("/css/menu.css").toExternalForm()
        );
    }

    @Override
    public Scene getScene() {
        return scene;
    }

    /**
     * Reusable methods to set events
     * They will run in case they are not null, which is the default value
     */
    public void setOnStart(Runnable r){
        this.onStart = r;
    }

    public void setOnScore(Runnable r) { this.onScore = r; }

    public void setOnRules(Runnable r) { this.onRules = r; }

    public void setOnExit(Runnable r){
        this.onExit = r;
    }

    private void runIfNotNull(Runnable r) {
        if (r != null) r.run();
    }
}
