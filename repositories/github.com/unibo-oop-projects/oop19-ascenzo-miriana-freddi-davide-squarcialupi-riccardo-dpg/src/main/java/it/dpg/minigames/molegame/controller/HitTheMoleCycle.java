package it.dpg.minigames.molegame.controller;

import it.dpg.minigames.base.controller.MinigameCycle;
import it.dpg.minigames.molegame.view.HitTheMoleView;

public interface HitTheMoleCycle extends MinigameCycle {

    /**
     * check if the mole press is out or in
     */
    void pressOnAMole(int whichMole);

    /**
     * push out a new mole
     */
    void moleOutOrIn();

    /**
     * update the view
     */
    void updateView();

    /**
     * start the gamecycle when button start is clicked on the view
     */
    void startGame();

    /**
     * set the view
     */
    void setView(HitTheMoleView view);

}
