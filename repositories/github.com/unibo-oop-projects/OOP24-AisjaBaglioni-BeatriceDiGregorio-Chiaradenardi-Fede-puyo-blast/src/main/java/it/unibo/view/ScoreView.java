package it.unibo.view;

import it.unibo.model.ScoreModel;
import it.unibo.view.interfaces.ViewInterface;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import it.unibo.model.Scale;

/**
 * The ScoreView class is responsible for rendering the player's score on the
 * screen.
 * It displays the current score in the upper-right corner of the window.
 */
public class ScoreView implements ViewInterface {
    /**
     * The ScoreModel instance that provides the current score of the player.
     */
    private final ScoreModel score;

    /**
     * The scale object that determines the size and position of the score on the
     * screen.
     */
    private final Scale scale;

    /**
     * Constructor for ScoreView that initializes the score model and scale.
     * 
     * @param score The ScoreModel object representing the player's score.
     * @param scale The Scale object used to adjust the size and position of the
     *              score on the screen.
     */
    public ScoreView(ScoreModel score, Scale scale) {
        this.score = score;
        this.scale = scale;
    }

    /**
     * Draws the score on the screen at a position determined by the scale.
     * 
     * @param g The Graphics object used to draw the score.
     */
    @Override
    final public void draw(Graphics g) {
        /**
         * Calculate the position for the score text
         */
        int x = this.scale.getScale() - this.scale.getScale() / 5;
        int y = this.scale.getScale() - this.scale.getScale() / 7;

        /**
         * Set the font style and size for the score
         */
        Font myFont = new Font("Arial", Font.BOLD, this.scale.getScale() / 35);
        g.setColor(Color.black);
        g.setFont(myFont);

        /**
         * Draw the score string on the screen
         */
        g.drawString("Score: " + this.score.getScore(), x, y);
    }
}
