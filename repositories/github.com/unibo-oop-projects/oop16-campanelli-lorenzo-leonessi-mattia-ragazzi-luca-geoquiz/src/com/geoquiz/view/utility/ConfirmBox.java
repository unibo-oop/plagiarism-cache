package com.geoquiz.view.utility;

import com.geoquiz.view.label.MyLabel;
import com.geoquiz.view.label.MyLabelFactory;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.paint.Color;
import javafx.stage.StageStyle;

/**
 * Public class for closing correctly the program.
 * 
 */
public final class ConfirmBox {

    private static final ConfirmBox CONFIRM = new ConfirmBox();
    private static final double FONT = 35;

    /**
     * Public getter.
     * 
     * @return this component confirm box.
     */
    public static ConfirmBox getBox() {
        return CONFIRM;
    }

    /**
     * Method to return a Alert.
     * 
     * @param message
     *            the message the alert should show.
     * @param color
     *            the color of label inside the alert box.
     * @return the alert.
     */
    public static Alert getAlert(final String message, final Color color) {
        final String header = "ATTENZIONE!";
        final MyLabel label;
        label = MyLabelFactory.createMyLabel(message, color, FONT);
        final Alert dialog = new Alert(AlertType.NONE);
        dialog.initStyle(StageStyle.UNDECORATED);
        dialog.getButtonTypes().add(ButtonType.OK);
        dialog.setHeaderText(header);
        dialog.getDialogPane().setContent((Node) label);

        return dialog;

    }

    /**
     * Method to return a ConfirmBox.
     * 
     * @param message
     *            the message the confirm box should show.
     * @return the Confirm Box.
     */
    public Alert getConfirmBox(final String message) {
        final String title = "ATTENZIONE";
        final Color color = Color.BLACK;
        final Alert dialog = ConfirmBox.getAlert(message, color);

        dialog.getButtonTypes().clear();
        dialog.getButtonTypes().addAll(ButtonType.YES, ButtonType.NO);
        dialog.setTitle(title);

        return dialog;
    }

}
