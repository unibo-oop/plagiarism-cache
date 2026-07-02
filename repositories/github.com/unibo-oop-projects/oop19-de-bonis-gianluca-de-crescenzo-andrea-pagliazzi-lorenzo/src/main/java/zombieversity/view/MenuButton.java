package zombieversity.view;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Font;

/**
 * 
 * Defines standard button.
 *
 */
public class MenuButton extends Button {

    private static final String BUTTON_PRESSED = "-fx-background-color: red;";
    private static final String BUTTON_RELEASED = "-fx-background-color: red;";

    private static final int BUTTON_HEIGHT = 49;
    private static final int BUTTON_WIDTH = 190;
    private static final int BUTTON_FONT = 20;
    private static final int BUTTON_HEIGHT_PRESSED = 45;

    /**
     * 
     * @param text the name of the button.
     */
    public MenuButton(final String text) {
        setText(text);
        setButtonFont();
        setPrefHeight(BUTTON_HEIGHT);
        setPrefWidth(BUTTON_WIDTH);
        setStyle(BUTTON_RELEASED);
        buttonListener();
    }

    private void setButtonFont() {
        setFont(Font.font("Arial Black", BUTTON_FONT));
    }

    private void setButtonPressedStyle() {
        setStyle(BUTTON_PRESSED);
        setPrefHeight(BUTTON_HEIGHT_PRESSED);
        setLayoutY(getLayoutY() + 4);
    }

    private void setButtonReleasedStyle() {
        setStyle(BUTTON_RELEASED);
        setPrefHeight(BUTTON_HEIGHT);
        setLayoutY(getLayoutY() - 4);
    }

    private void buttonListener() {

        setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(final MouseEvent event) {
                if (event.getButton().equals(MouseButton.PRIMARY)) {
                    setButtonPressedStyle();
                }
            }
        });

        setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override
            public void handle(final MouseEvent event) {
                if (event.getButton().equals(MouseButton.PRIMARY)) {
                    setButtonReleasedStyle();
                }
            }
        });
    }
}
