package com.geoquiz.view.button;

import javafx.scene.paint.Color;

/**
 * A button inside the menu.
 */
public interface MyButton {
    /**
     * set the text of button.
     * 
     * @param text
     *            the text of button.
     */
    void setText(String text);

    /**
     * get the text of button.
     * 
     * @return the text of button
     */
    String getText();

    /**
     * @param color
     *            sets the background color.
     */
    void setBackground(Color color);

}
