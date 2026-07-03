package com.geoquiz.view.button;

import javafx.geometry.Pos;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.effect.Glow;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 * A class used to represent a general button inside the menu.
 */
public class MyButtonImpl extends StackPane implements MyButton {

    private static final double FONT = 35;
    private static final double RECT_HEIGHT = 40;
    private static final double RECT_OPACITY = 0.4;
    private static final double EFFECT = 2;
    private static final double SHADOW = 50;

    private final Text text;
    private final Rectangle bg;

    MyButtonImpl(final String name, final Color colorBackground, final double width) {
        text = new Text(name);
        text.getFont();
        text.setFont(Font.font(FONT));
        text.setFill(Color.WHITE);

        bg = new Rectangle(width, RECT_HEIGHT);
        bg.setOpacity(RECT_OPACITY);
        bg.setFill(colorBackground);
        bg.setEffect(new GaussianBlur(EFFECT));

        setAlignment(Pos.CENTER_LEFT);
        getChildren().addAll(bg, text);

        this.setOnMouseEntered(event -> {
            bg.setTranslateX(10);
            text.setTranslateX(10);
            bg.setFill(Color.WHITE);
            text.setFill(colorBackground);
        });

        this.setOnMouseExited(event -> {
            bg.setTranslateX(0);

            text.setTranslateX(0);
            bg.setFill(colorBackground);
            text.setFill(Color.WHITE);
        });

        final DropShadow drop = new DropShadow(SHADOW, Color.BLUE);
        drop.setInput(new Glow());

        this.setOnMousePressed(event -> setEffect(drop));
        this.setOnMouseReleased(event -> setEffect(null));

    }

    @Override
    public void setText(final String text) {
        this.text.setText(text);
    }

    @Override
    public String getText() {
        return text.getText();
    }

    @Override
    public void setBackground(final Color color) {
        bg.setFill(color);
    }

}
