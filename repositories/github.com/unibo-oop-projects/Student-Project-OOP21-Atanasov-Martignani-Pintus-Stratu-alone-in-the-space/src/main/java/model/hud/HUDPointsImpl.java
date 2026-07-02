package model.hud;

import javafx.scene.control.Label;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import utilities.EnumInt;
import utilities.EnumString;

/**
 * This class shows how the HUD for points must look.
 */
public class HUDPointsImpl extends Label implements HUDPoints {

    /*
     * HUD structure
     */
    private static final int X_LAYOUT = 130;
    private static final int Y_LAYOUT = 20;
    private static final String YELLOW = "yellow";
    private static final String MATTER = "Points: ";

    private int points;

    /**
     * Constructor.
     */
    public HUDPointsImpl() {
        this.points = EnumInt.ZERO.getValue();
        this.setLayoutX(EnumInt.WIDTH.getValue() - X_LAYOUT);
        this.setLayoutY(Y_LAYOUT);
        this.setText(MATTER);
        this.setFont(new Font(EnumString.FONT.getValue(), EnumInt.FONT_SIZE.getValue()));
        this.setTextFill(Paint.valueOf(YELLOW));
    }

    @Override
    public int getPoints() {
        return this.points;
    }

    @Override
    public void setPoints(final int value) {
        if (this.getPoints() + value < EnumInt.MAX_POINTS.getValue()) {
            this.points += value;
        } else {
            this.points = EnumInt.MAX_POINTS.getValue();
        }
        this.setText(MATTER + this.getPoints());
    }

    @Override
    public void update(final int points) {
        this.points = points;
        this.setText(MATTER + this.getPoints());
    }
}
