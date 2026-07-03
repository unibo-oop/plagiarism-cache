package view.dialogs;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;

import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

/**
 * 
 * Class that show credits dialog. There are written application developers.
 *
 */
public class CreditDiag extends JFXDialog {

    private static final Text TITLE = new Text("Developers");
    private static final JFXButton EXIT_BUTTON = new JFXButton("CLOSE");
    private static final JFXDialogLayout DIAG_CONTENT = new JFXDialogLayout();

    /**
     * 
     * Class Constructor.
     * 
     * @param diagContainer
     *                      Dialogbox container. Has to be a StackPane.
     * 
     */
    public CreditDiag(final StackPane diagContainer) {
      super(diagContainer, DIAG_CONTENT, JFXDialog.DialogTransition.CENTER);
      DIAG_CONTENT.setHeading(TITLE);
      DIAG_CONTENT.setBody(this.new CreditPane());
      DIAG_CONTENT.setActions(EXIT_BUTTON);
      EXIT_BUTTON.setOnMouseClicked(e -> this.close());
      EXIT_BUTTON.getStyleClass().add("dialog-accept");
    }

    private class CreditPane extends VBox {

        private final Label dev1 = new Label("Andruccioli Matteo (Model)");
        private final Label dev2 = new Label("Borasca Michele (Model)");
        private final Label dev3 = new Label("Orazi Federico (Controller)");
        private final Label dev4 = new Label("Raffaelli Simone (View)");

        /**
         * Class Constructor.
         */
        CreditPane() {
            this.getChildren().addAll(dev1, dev2, dev3, dev4);
        }
    }
}
