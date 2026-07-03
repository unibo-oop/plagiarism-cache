package maingame.sound;

/**
 * . Interfaccia di sound
 */
public interface Sound {

    /**.
     * fa partire la traccia audio
     * 
     * @param loop
     *            se true la traccia viene ripetuta in loop, se false viene
     *            ripetuta 1 volta
     */
    void play(boolean loop);

    /**.
     * ferma la traccia audio
     */
    void stop();
    /**
     * @return se il sound � stato fermato.
     */
    boolean isStopped();

}
