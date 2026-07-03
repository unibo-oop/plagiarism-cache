package com.geoquiz.view.label;

import javafx.scene.paint.Color;

/**
 * static factory for labels inside menu.
 */
public final class MyLabelFactory {

    private MyLabelFactory() {
    }

    /**
     * 
     * @param name
     *            the text of label.
     * @param color
     *            the color of label.
     * @param font
     *            the text font.
     * @return the label.
     */
    public static MyLabel createMyLabel(final String name, final Color color, final double font) {
        return new MyLabelImpl(name, color, font);
    }

}
