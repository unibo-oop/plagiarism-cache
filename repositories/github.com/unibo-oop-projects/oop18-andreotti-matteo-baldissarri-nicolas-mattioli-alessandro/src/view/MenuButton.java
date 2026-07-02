package view;

import java.awt.Toolkit;

import javafx.scene.control.Button;

/**
 * 
 * Redefine class Button.
 *
 */
public class MenuButton extends Button {

    private static final double FONT_SIZE = Toolkit.getDefaultToolkit().getScreenSize().getWidth() * 0.025;
    private static final double BUTTON_WIDTH = Toolkit.getDefaultToolkit().getScreenSize().getWidth() / 4;

    /**
     * 
     * @param text text of the button
     */
    public MenuButton(final String text) {
        super();
        this.setText(text);
        this.setMaxWidth(BUTTON_WIDTH);
        this.setStyle(String.format("-fx-font-size: %dpx;", (int) ((FONT_SIZE))));
    }
}
