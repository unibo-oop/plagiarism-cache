package view.dialogs;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;

import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import utils.enumerations.Status;
import view.ViewImpl;

/**
 * 
 * Show a message dialog. It asks the player if he wants to move or not tanks after his attack phase.
 *
 */
public class TanksMovementDiag extends JFXDialog {

    private static final Text TITLE = new Text("Are you going to move tanks?");
    private static final Text BODY = new Text("Would you want to move tanks?");
    private static final JFXButton OK_BUTTON = new JFXButton("YES");
    private static final JFXButton NO_BUTTON = new JFXButton("NO");
    private static final JFXDialogLayout DIAG_CONTENT = new JFXDialogLayout();
    private static final String BUTTON_STYLE = "dialog-accept";


    /**
     * 
     * Default class constructor.
     * 
     */
    public TanksMovementDiag() {
        DIAG_CONTENT.setHeading(TITLE);
        DIAG_CONTENT.setBody(BODY);
        DIAG_CONTENT.setActions(OK_BUTTON, NO_BUTTON);
        OK_BUTTON.getStyleClass().add(BUTTON_STYLE);
        NO_BUTTON.getStyleClass().add(BUTTON_STYLE);
        this.setOverlayClose(false);
        this.registerListeners(); 
    }

    /**
     * 
     * Class Constructor.
     * 
     * @param diagContainer
     *                      Dialogbox container. Has to be a StackPane.
     * 
     */
    public TanksMovementDiag(final StackPane diagContainer) {
      super(diagContainer, TanksMovementDiag.DIAG_CONTENT, JFXDialog.DialogTransition.CENTER);
      DIAG_CONTENT.setHeading(TITLE);
      DIAG_CONTENT.setBody(BODY);
      DIAG_CONTENT.setActions(OK_BUTTON, NO_BUTTON);
      OK_BUTTON.getStyleClass().add(BUTTON_STYLE);
      NO_BUTTON.getStyleClass().add(BUTTON_STYLE);
      this.setOverlayClose(false);
      this.registerListeners();
    }

    /**
     * 
     * Register listeners of two buttons (Ok/No).
     * 
     */
    private void registerListeners() {
        OK_BUTTON.setOnMouseClicked(e -> {
            ViewImpl.getIstance().moveTanks(true);
            this.close();
        });

        NO_BUTTON.setOnMouseClicked(e -> {
            ViewImpl.getIstance().getController().endTurn();
            ViewImpl.getIstance().setGameStateButtonText(Status.DEPLOYMENT);
            this.close();
        });
    }
}

