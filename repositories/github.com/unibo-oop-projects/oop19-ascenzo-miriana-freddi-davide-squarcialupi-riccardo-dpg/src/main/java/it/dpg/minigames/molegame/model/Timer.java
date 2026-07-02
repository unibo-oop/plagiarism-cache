package it.dpg.minigames.molegame.model;

public interface Timer {

    /**
     *function that start the timer for the minigame
     *
     */
    void timeStart();

    /**
     * funciton that check if the time is up
     */
    boolean checkTimeIsUp();

    /**
     * get the remain time
     *
     * @return remain time
     */
    long getRemainTime();




}
