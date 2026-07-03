package view.dialogs;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXSlider;

import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import view.ViewImpl;

/**
 * 
 * Show a message dialog. It asks the player how much tanks move (or not) into state conquered.
 *
 */
public class TanksMovementAfterAttackDiag extends JFXDialog {

    private static final Text TITLE = new Text("How many tanks you would like to move?");
    private static final JFXSlider BODY = new JFXSlider();
    private static final JFXButton OK_BUTTON = new JFXButton("DONE");
    private static final JFXDialogLayout DIAG_CONTENT = new JFXDialogLayout();

    /**
     * 
     * Default class constructor.
     * 
     */
    public TanksMovementAfterAttackDiag() {
        DIAG_CONTENT.setHeading(TITLE);
        DIAG_CONTENT.setBody(BODY);
        DIAG_CONTENT.setActions(OK_BUTTON);
        OK_BUTTON.getStyleClass().add("dialog-accept");
        this.registerListeners();
        this.settingSlider();
        this.setOverlayClose(false);
    }

    /**
     * 
     * Class Constructor.
     * 
     * @param diagContainer
     *                      Dialogbox container. Has to be a StackPane.
     * 
     */
    public TanksMovementAfterAttackDiag(final StackPane diagContainer) {
      super(diagContainer, TanksMovementAfterAttackDiag.DIAG_CONTENT, JFXDialog.DialogTransition.CENTER);
      DIAG_CONTENT.setHeading(TITLE);
      DIAG_CONTENT.setBody(BODY);
      DIAG_CONTENT.setActions(OK_BUTTON);
      OK_BUTTON.getStyleClass().add("dialog-accept");
      this.registerListeners();
      this.settingSlider();
      this.setOverlayClose(false);
    }

    private void registerListeners() {
        OK_BUTTON.setOnMouseClicked(e -> {
            if (((int) Math.ceil(BODY.getValue())) - 3 != 0) {
                ViewImpl.getIstance().getController().tanksToMove(((int) Math.ceil(BODY.getValue())) - ViewImpl.getIstance().getDefState().get().getTanks());
            }
            this.close();
        });
     }

    /**
     * 
     * Setting up slider.
     * 
     */
    private void settingSlider() {
            BODY.setMax(ViewImpl.getIstance().getAtkState().get().getTanks() + ViewImpl.getIstance().getDefState().get().getTanks() - 1);
            BODY.setMin(ViewImpl.getIstance().getDefState().get().getTanks());
    }
}

