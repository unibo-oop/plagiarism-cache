package view.menu;

import java.util.ArrayList;
import java.util.List;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import view.AbstractScene;
import view.View;
import view.ViewImpl;

/**
 * layout for leaderboard menu.
 */
public class LeaderboardMenu extends AbstractScene {

    private final List<Label> labels;
    private final VBox scoreBox;

    /**
     * @param width of the scene
     * @param height of the scene
     * @param v view reference
     */
    public LeaderboardMenu(final double width, final double height, final View v) {
        super(new BorderPane(), width, height);
        final View view = v;
        final BorderPane pane = (BorderPane) getRoot();
        labels = new ArrayList<>();
        final Button back = new Button("BACK");
        final Button reset = new Button("RESET");
        scoreBox = new VBox();
        final HBox btnBox = new HBox(back, reset);
        scoreBox.getChildren().addAll(labels);
        scoreBox.setSpacing(40);
        scoreBox.setAlignment(Pos.CENTER);
        pane.setCenter(scoreBox);
        pane.setBottom(btnBox);
        btnBox.setAlignment(Pos.CENTER);
        btnBox.setSpacing(50);
        HBox.setMargin(back, new Insets(50));
        BorderPane.setAlignment(btnBox, Pos.BOTTOM_CENTER);
        back.setOnAction(e -> view.changeScene(ViewImpl.GameScreen.MAINMENU));
    }

    @Override
    public void initialize() {
        
    }
}
