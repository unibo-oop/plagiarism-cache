package view;

import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.List;

import controller.Leaderboard;
import controller.MenuController;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;

/**
 * 
 * This class create the Options menu view.
 *
 */
public class LeaderboardView extends StackPane {
    private static final double FONT_SIZE = Toolkit.getDefaultToolkit().getScreenSize().getWidth() * 0.025;
    private static final double PADDING = Toolkit.getDefaultToolkit().getScreenSize().getWidth() / 100;
    private static final double LEADERBOARD_WIDTH = Toolkit.getDefaultToolkit().getScreenSize().getWidth() / 2.5;
    private static final double BACK_TRANSLATEX = -(Toolkit.getDefaultToolkit().getScreenSize().getWidth() / 3.2);
    private static final double BACK_TRANSLATEY = Toolkit.getDefaultToolkit().getScreenSize().getHeight() / 3;

    private final MenuTitle title = new MenuTitle(3.7, 4);
    private final MenuBox leaderboardBox = new MenuBox(5.5, 50);
    private final List<Label> leaderboardLabels = new ArrayList<>();
    private final MenuButton back = new MenuButton("BACK");

    /**
     * 
     * @param controller  Main Controller of the application.
     * @param scene       Main scene.
     * @param leaderboard The game leaderboard.
     */
    public LeaderboardView(final MenuController controller, final Scene scene, final Leaderboard leaderboard) {
        super();
        leaderboardBox.setStyle("-fx-background-color: rgba(0,0,0,0.5);");
        leaderboardBox.setMaxWidth(LEADERBOARD_WIDTH);
        leaderboard.getScoreBoard().forEach(score -> {
            this.leaderboardLabels.add(new Label(score.getY() + " " + score.getX()));
        });
        this.leaderboardLabels.forEach(label -> {
            label.setPadding(new Insets(PADDING, PADDING, PADDING, PADDING));
            label.setStyle(String.format("-fx-font-size: %dpx;", (int) ((FONT_SIZE))));
        });

        back.setTranslateX(BACK_TRANSLATEX);
        back.setTranslateY(BACK_TRANSLATEY);
        this.back.setOnAction(e -> {
            controller.goToMainMenu();
        });

        this.leaderboardBox.getChildren().addAll(leaderboardLabels);
        this.getChildren().addAll(title, back, leaderboardBox);
        this.setId("mainPane");
        this.getStylesheets().add("style.css");
        scene.setRoot(this);
    }
}
