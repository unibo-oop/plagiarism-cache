package breakout.view;

import java.util.Optional;

import breakout.view.graphics.GraphicStyle;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;
import javafx.stage.StageStyle;

/**
 * A dialog with the final score of the player and a TextFiled to save the score
 * with a name.
 */
public class EndGameDialog {
    private static final double TEXT_SIZE = 35;
    private static final int MAX_CHAR = 10;

    private final TextInputDialog dialog = new TextInputDialog();

    /**
     * @param score
     *            The score to show
     * @param style
     *            the style of the graphic
     */
    public EndGameDialog(final String score, final GraphicStyle style) {
        dialog.setHeaderText(null);
        dialog.setGraphic(null);
        dialog.setContentText("Your Score\n" + score + "\n\nPlease enter\n your name ");
        dialog.initStyle(StageStyle.UNDECORATED);
        dialog.getDialogPane().getStylesheets().add("stylesheet.css");
        dialog.getDialogPane().getStylesheets().add(style.getCSS());
        dialog.getDialogPane().setId("grey_black_radial");
        dialog.getDialogPane().getChildren().forEach(node -> {
            if (node instanceof GridPane) {
                ((GridPane) node).getChildrenUnmodifiable().forEach(child -> {
                    if (child instanceof Label) {
                        ((Label) child).setFont(style.getFont(TEXT_SIZE));
                        ((Label) child).setId(style.getTextStyle());
                        ((Label) child).setTextFill(Color.WHITE);
                        ((Label) child).setTextAlignment(TextAlignment.CENTER);
                    } else if (child instanceof TextField) {
                        ((GridPane) node).getChildren().remove(child);
                        ((GridPane) node).addRow(1, child);
                        ((TextField) child).setFont(style.getFont(TEXT_SIZE / 2));
                        ((TextField) child).setId(style.getTextStyle());
                        ((TextField) child).setOnKeyPressed(e -> {
                            if (((TextField) child).getText().length() > MAX_CHAR) {
                                ((TextField) child).deletePreviousChar();
                            }
                        });
                    }
                });
            } else if (node instanceof ButtonBar) {
                ((ButtonBar) node).getButtons()
                                  .forEach(button -> ((Button) button).setFont(style.getFont(TEXT_SIZE / 2)));
                ((Labeled) ((ButtonBar) node).getButtons().get(0)).setText("Save");
                ((Labeled) ((ButtonBar) node).getButtons().get(1)).setText("Dont Save");
            }
        });
    }

    /**
     * Shows the dialog and waits for the user response.
     * 
     * @return An Optional that contains the string written by the user
     */
    public Optional<String> showAndWait() {
        return this.dialog.showAndWait();
    }

    /**
     * Close the dialog.
     */
    public void close() {
        this.dialog.close();
    }

}
