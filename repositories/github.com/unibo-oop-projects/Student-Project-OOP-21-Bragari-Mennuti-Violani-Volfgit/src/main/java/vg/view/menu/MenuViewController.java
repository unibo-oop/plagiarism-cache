package vg.view.menu;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import vg.view.ViewController;
import java.util.List;
import static vg.view.utils.Colors.*;

public class MenuViewController extends ViewController {

    @FXML
    private Label playBtn;

    @FXML
    private Label leaderboardsBtn;

    @FXML
    private Label settingsBtn;

    @FXML
    private Label quitBtn;

    public void highlightSelectedButton(final int idx) {
        List<Label> buttons = List.of(playBtn, leaderboardsBtn, settingsBtn, quitBtn);
        buttons.forEach(button -> button.setTextFill(UNSELECTED_COLOR));
        buttons.get(idx).setTextFill(SELECTED_COLOR);
    }

}
