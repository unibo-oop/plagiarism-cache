package it.unibo.arkanoid.view;

import it.unibo.arkanoid.view.controllers.SubViewController;

/**
 * 
 * Factory for JavaFX {@link SubView}.
 *
 */
public interface SubViewFactory {

    /**
     * 
     * @return new home subview.
     */
    SubViewController createHome();

    /**
     * 
     * @return new GameScreen subview.
     */
    SubViewController createGameScreen();

    /**
     * 
     * @return new ScoresView subview.
     */
    SubViewController createScoresView();

    /**
     * 
     * @return new LevelWin subview.
     */
    SubViewController createLevelWin();

    /**
     * 
     * @return new GameOver subview.
     */
    SubViewController createGameOver();

    /**
     * 
     * @return new GameFinished subview.
     */
    SubViewController createGameFinished();

}
