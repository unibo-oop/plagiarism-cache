package com.geoquiz.view.label;

import javafx.geometry.Pos;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 * A class used to represent a general label inside the menu.
 */
public class MyLabelImpl extends StackPane implements MyLabel {

    private static final double RECT_WIDHT = 350;
    private static final double RECT_HEIGHT = 40;

    private final Text text;

    MyLabelImpl(final String name, final Color color, final double font) {

        text = new Text(name);
        text.getFont();
        text.setFont(Font.font(font));
        text.setFill(color);

        final Rectangle bg = new Rectangle(RECT_WIDHT, RECT_HEIGHT);
        bg.setFill(Color.TRANSPARENT);

        this.setAlignment(Pos.CENTER_LEFT);

        this.getChildren().addAll(bg, text);

    }

    @Override
    public void setText(final String text) {
        this.text.setText(text);
    }

    @Override
    public String getText() {
        return this.text.getText();
    }
}
