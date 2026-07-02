package vg.view.menu.prompt;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import vg.view.ViewController;
import static vg.view.utils.Colors.*;

public class PromptViewController extends ViewController {
    /**
     * Text Title that ask user what to do.
     */
    @FXML
    private Text titleText;

    /**
     * Text description of what key has to been used.
     */
    @FXML
    private Text hintText;

    /**
     * Confirmation button.
     */
    @FXML
    private Label confirmBtn;

    /**
     * Deny button.
     */
    @FXML
    private Label denyBtn;

    /**
     * Apply selection style. Set CONFIRM button with {@link vg.view.utils.Colors#SELECTED_COLOR}
     */
    public void highlightConfirm() {
        this.confirmBtn.setTextFill(SELECTED_COLOR);
        this.denyBtn.setTextFill(UNSELECTED_COLOR);
    }

    /**
     * Apply selection style. Set DENY button with {@link vg.view.utils.Colors#SELECTED_COLOR}
     */
    public void highlightDeny() {
        this.confirmBtn.setTextFill(UNSELECTED_COLOR);
        this.denyBtn.setTextFill(SELECTED_COLOR);
    }

    /**
     * Set title of dialog view.
     * @param title Question to ask to user.
     */
    public void setTitle(final String title) {
        this.titleText.setText(title);
    }

    /**
     * Set hint description of dialog view to explain user keys to be used in selection.
     * @param hintText Suggestion text.
     */
    public void setHint(final String hintText) {
        this.hintText.setText(hintText);
    }
}
