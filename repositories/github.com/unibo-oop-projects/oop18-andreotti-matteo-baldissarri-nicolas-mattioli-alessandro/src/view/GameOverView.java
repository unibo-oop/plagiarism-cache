package view;

import java.awt.Toolkit;
import java.io.IOException;

import controller.Leaderboard;
import controller.MainController;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;

/**
 * 
 * This Class create the game over view.
 *
 */
public class GameOverView extends StackPane {

    private static final double INPUT_WIDTH = Toolkit.getDefaultToolkit().getScreenSize().getWidth() / 3;
    private static final double FONT_SIZE = Toolkit.getDefaultToolkit().getScreenSize().getWidth() * 0.025;
    private TextField inNickname = new TextField();

    /**
     * 
     * @param view The main view.
     * @param controller The Main Controller.
     * @param score The final score.
     * @param leaderboard The Local Leaderboard.
     */
    public GameOverView(final View view, final MainController controller, final int score, final Leaderboard leaderboard) {
        super();
        inNickname.setTranslateX(Toolkit.getDefaultToolkit().getScreenSize().getWidth() / 100);
        inNickname.setTranslateY(Toolkit.getDefaultToolkit().getScreenSize().getHeight() / 3);
        inNickname.setStyle(String.format("-fx-font-size: %dpx;", (int) ((FONT_SIZE))));
        inNickname.setMaxWidth(INPUT_WIDTH);
        inNickname.setPromptText("Enter Your NickName");
        inNickname.setFocusTraversable(false);
        inNickname.setOnAction(value -> {
            try {
                leaderboard.addRecord(score, inNickname.getText());
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            controller.menu();
        });

        view.getScene().setRoot(this);
        this.setId("gameOver");
        this.getChildren().add(inNickname);
        this.getStylesheets().add("style.css");
    }
}
