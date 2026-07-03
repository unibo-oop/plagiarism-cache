package view.dialogs;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;

import javafx.application.Platform;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;

/**
 * 
 * Show a message dialog. It asks the player if he wants to exit or remain into the game.
 *
 */
public class CloseGameDiag extends JFXDialog {

    private static final Text TITLE = new Text("Close Game?");
    private static final Text BODY = new Text("Are you sure you want to exit?");
    private static final JFXButton OK_BUTTON = new JFXButton("YES");
    private static final JFXButton NO_BUTTON = new JFXButton("NO");
    private static final JFXDialogLayout DIAG_CONTENT = new JFXDialogLayout();

    /**
     * 
     * Class Constructor.
     * 
     * @param diagContainer
     *                      Dialogbox container. Has to be a StackPane.
     * 
     */
    public CloseGameDiag(final StackPane diagContainer) {
      super(diagContainer, CloseGameDiag.DIAG_CONTENT, JFXDialog.DialogTransition.CENTER);
      DIAG_CONTENT.setHeading(CloseGameDiag.TITLE);
      DIAG_CONTENT.setBody(CloseGameDiag.BODY);
      DIAG_CONTENT.setActions(CloseGameDiag.OK_BUTTON, CloseGameDiag.NO_BUTTON);
      OK_BUTTON.getStyleClass().add("dialog-accept");
      NO_BUTTON.getStyleClass().add("dialog-accept");
      this.registerListeners();
    }

    /**
     * 
     * Register listeners of two buttons (Ok/No).
     * 
     */
    private void registerListeners() {
        CloseGameDiag.OK_BUTTON.setOnMouseClicked(e -> {
            Platform.exit();
            System.exit(0);
        });

        CloseGameDiag.NO_BUTTON.setOnMouseClicked(e -> {
            this.close();
        });
    }
}
