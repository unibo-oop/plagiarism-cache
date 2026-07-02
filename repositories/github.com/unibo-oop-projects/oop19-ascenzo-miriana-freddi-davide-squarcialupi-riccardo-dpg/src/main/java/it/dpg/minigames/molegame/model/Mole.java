package it.dpg.minigames.molegame.model;

public interface Mole {
    /**
     *
     * @return true if Mole is out
     */
    boolean isOut();

    /**
     * set the mole out of the hole
     */
    void setMoleOut();

    /**
     * set the mole inside of the hole
     */
    void setMoleIn();

}
