package justanotherchessgame.view;

import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

/**
 * Class used to create an element of a menu.
 */
public abstract class MenuLine extends StackPane implements ResizableGraphicComponent {

    private final Rectangle bg;
    private final Text text;

    /**
     * Class constructor.
     * @param name is the text of the menu element.
     */
    public MenuLine(final String name) {
        final LinearGradient gradient = new LinearGradient(0, 0, 1, 0, true, CycleMethod.NO_CYCLE,
                new Stop[] { new Stop(0, Color.GHOSTWHITE), new Stop(0.1, Color.BLACK), new Stop(0.9, Color.BLACK),
                        new Stop(1, Color.GHOSTWHITE)

        });
        bg = new Rectangle();
        bg.setOpacity(0.2);

        text = new Text(name);
        text.setFill(Color.WHITESMOKE);


        setAlignment(Pos.CENTER);
        getChildren().addAll(bg, text);

        setOnMouseEntered(event -> {
            bg.setFill(gradient);
            text.setFill(Color.ANTIQUEWHITE);
            bg.getScene().setCursor(Cursor.HAND);
        });

        setOnMouseReleased(event -> {
            bg.setFill(gradient);
            if (bg.getScene() != null) {
                bg.getScene().setCursor(Cursor.DEFAULT);
            }
        });

        setOnMouseExited(event -> {
            bg.setFill(Color.BLACK);
            text.setFill(Color.WHITESMOKE);
            // need the check because the method is also called when the mouse is released
            if (bg.getScene() != null) {
                bg.getScene().setCursor(Cursor.DEFAULT);
            }
        });

        setOnMousePressed(event -> {
            bg.setFill(Color.rgb(66, 163, 24));
        });
    }

    /**
     * Getter of the line container.
     * @return the container of the element.
     */
    public Rectangle getBg() {
        return this.bg;
    }

    /**
     * Getter for the line text.
     * @return the text of the line.
     */
    public Text getText() {
        return this.text;
    }
}
