package it.dpg.minigames.ballgame.controller;

public interface BallObserver {

    /**
     * signal the up input state
     *
     * @param isGoing true if it's going up, false if it's not
     */
    void signalGoingUp(boolean isGoing);

    /**
     * signal the Down input state
     *
     * @param isGoing true if it's going Down, false if it's not
     */
    void signalGoingDown(boolean isGoing);

    /**
     * signal the Left input state
     *
     * @param isGoing true if it's going Left, false if it's not
     */
    void signalGoingleft(boolean isGoing);

    /**
     * signal the Right input state
     *
     * @param isGoing true if it's going Right, false if it's not
     */
    void signalGoingRight(boolean isGoing);
}
