package it.unibo.javajump.view.viewstates;

import it.unibo.javajump.model.GameModel;

import java.awt.Graphics;


/**
 * The interface Game view state.
 */
public interface GameViewState {

    /**
     * Draw.
     *
     * @param g     the g
     * @param model the model
     */
    void draw(Graphics g, GameModel model);

    /**
     * Start fade.
     */
    void startFade();

    /**
     * Update.
     */
    void update();

    /**
     * Stop fade.
     */
    void stopFade();
}
