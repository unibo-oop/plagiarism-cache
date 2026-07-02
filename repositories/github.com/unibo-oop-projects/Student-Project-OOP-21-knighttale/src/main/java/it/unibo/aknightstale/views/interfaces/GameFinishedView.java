package it.unibo.aknightstale.views.interfaces;

import it.unibo.aknightstale.controllers.interfaces.GameFinishedController;

public interface GameFinishedView extends View<GameFinishedController> {
    /**
     * Set the score label value.
     *
     * @param score the score obtained in the game.
     */
    void setScoreLabel(int score);
}
