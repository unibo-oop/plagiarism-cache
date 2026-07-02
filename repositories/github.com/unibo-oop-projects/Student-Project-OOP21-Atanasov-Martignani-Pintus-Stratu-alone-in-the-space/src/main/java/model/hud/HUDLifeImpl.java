package model.hud;

import javafx.scene.control.Label;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import utilities.EnumInt;
import utilities.EnumString;
import utilities.PlayerValues;

/**
 * Class for HUD lifePoints components.
 */
public class HUDLifeImpl extends Label implements HUDLife {

    /*
     * HUD structure
     */
    private static final int X_LAYOUT = 330;
    private static final int Y_LAYOUT = 20;
    private static final int POINTS_UP = 1;
    private static final String YELLOW = "yellow";
    private static final String MATTER = "Life Points: ";

    private static final int INITIAL_LIFE_POINTS = 100;
    private static final int LESS_LIVES_POSSIBLE = 1;

    private int lifePoints;

    private boolean gameStatus;

    /**
     * Constructor.
     */
    public HUDLifeImpl() {
        this.lifePoints = PlayerValues.MAIN_SHIP.getValueFromKey("MAXHEALTH");
        this.setLayoutX(EnumInt.WIDTH.getValue() - X_LAYOUT);
        this.setLayoutY(Y_LAYOUT);
        this.setText(MATTER);
        this.setFont(new Font(EnumString.FONT.getValue(), EnumInt.FONT_SIZE.getValue()));
        this.setTextFill(Paint.valueOf(YELLOW));
        this.gameStatus = true;
    }

    @Override
    public int getLifePoints() {
        return this.lifePoints;
    }

    @Override
    public boolean getGameStatus() {
        return this.gameStatus;
    }

    /**
     * update life points.
     *
     * @param lifePoints
     */
    public void update(final int lifePoints) {
        this.lifePoints = lifePoints;
        this.setText(MATTER + this.lifePoints);
        if (this.lifePoints <= 0) {
            this.gameStatus = false;
        }
    }
}
