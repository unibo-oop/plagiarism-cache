package com.geoquiz.view.button;

import javafx.scene.paint.Color;

/**
 * static factory for buttons inside menu.
 */
public final class MyButtonFactory {

    private MyButtonFactory() {
    }

    /**
     * 
     * @param name
     *            the text of button.
     * 
     * @param colorBackground
     *            the color of button background.
     * 
     * @param width
     *            the width of button.
     * 
     * @return the button.
     */
    public static MyButton createMyButton(final String name, final Color colorBackground, final double width) {
        return new MyButtonImpl(name, colorBackground, width);
    }

}
