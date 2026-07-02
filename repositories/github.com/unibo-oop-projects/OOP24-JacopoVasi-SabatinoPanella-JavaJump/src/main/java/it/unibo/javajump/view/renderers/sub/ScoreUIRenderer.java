package it.unibo.javajump.view.renderers.sub;

import it.unibo.javajump.model.GameModel;

import java.awt.Graphics2D;

/**
 * Interface for rendering the score and UI, to be used in the renderer manager.
 */
public interface ScoreUIRenderer {
    /**
     * Method to draw the score and UI.
     *
     * @param g2                   the Graphics2D context
     * @param model                the GameModel
     * @param isNewHighScore       flag to indicate if the score is a new high score
     * @param showHighScoreMessage flag to indicate if the high score message should be shown
     */
    void drawScoreAndUI(Graphics2D g2, GameModel model, boolean isNewHighScore, boolean showHighScoreMessage);
}
