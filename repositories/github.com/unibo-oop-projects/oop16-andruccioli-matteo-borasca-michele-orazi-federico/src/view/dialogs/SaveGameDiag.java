package view.dialogs;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXTextField;

import javafx.application.Platform;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import view.ViewImpl;
import view.mainmenu.MainMenu;

/**
 * 
 * Show a message dialog. It asks the player how to name the save.
 *
 */
public class SaveGameDiag extends JFXDialog {

    private static final Text TITLE = new Text("Save Game?");
    private static final JFXTextField BODY = new JFXTextField("Insert a valid name.");
    private static final JFXButton OK_BUTTON = new JFXButton("DONE");
    private static final JFXDialogLayout DIAG_CONTENT = new JFXDialogLayout();

    /**
     * 
     * Class Constructor.
     * 
     * @param diagContainer
     *                      Dialogbox container. Has to be a StackPane.
     * 
     */
    public SaveGameDiag(final StackPane diagContainer) {
      super(diagContainer, DIAG_CONTENT, JFXDialog.DialogTransition.CENTER);
      DIAG_CONTENT.setHeading(TITLE);
      DIAG_CONTENT.setBody(BODY);
      DIAG_CONTENT.setActions(OK_BUTTON);
      OK_BUTTON.getStyleClass().add("dialog-accept");
      this.registerListeners();
    }

    /**
     * 
     * Register listener of 'Done' button.
     * 
     */
    private void registerListeners() {
       OK_BUTTON.setOnMouseClicked(e -> {
            ViewImpl.getIstance().getController().saveOnFile(BODY.getText());
            ((Stage) ((JFXButton) e.getSource()).getScene().getWindow()).close();
            ViewImpl.getIstance().getController().resetGame();
            ViewImpl.getIstance().restoreViewToInitialValues();
            Platform.runLater(new Runnable() {
                public void run() {
                    new MainMenu().start(new Stage());
                }
            });
        });
    }
}
