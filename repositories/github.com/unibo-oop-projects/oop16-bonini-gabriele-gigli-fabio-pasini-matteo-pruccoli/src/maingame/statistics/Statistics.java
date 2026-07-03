package maingame.statistics;

/**
 * Interfaccia di statistiche.
 */
public interface Statistics {
    /**
     * salva le statistiche su file.
     */
    void save();

    /**
     * incrementa il totale di uccisioni.
     * 
     * @param nKill
     *            numero tot di uccisioni da sommare
     */
    void incrementKill(int nKill);

    /**
     * incrementa il totale dei passi.
     * 
     * @param nSteps
     *            numero tot di passi da sommare
     */
    void incrementSteps(int nSteps);

    /**
     * incrementa i secondi totali.
     * 
     * @param second
     *            numero sec tot da sommare
     */
    void incrementTime(int second);

    /**
     * incrementa il numero tot di proiettili.
     * 
     * @param projectile
     *            num proiettili da sommare
     */
    void incrementProjectile(int projectile);

    /**
     * incrementa il punteggio.
     * 
     * @param score
     *            punteggio totale da sommare
     */
    void updateMaxScore(int score);

    /**
     * 
     * @return numero totale di uccizioni
     */
    int getKill();

    /**
     * 
     * @return numero totale di passi
     */
    int getSteps();

    /**
     * 
     * @return tempo giocato
     */
    String getTime();

    /**
     * 
     * @return proiettili sparati
     */
    int getProjectile();

    /**
     * 
     * @return punteggio massimo
     */
    int getMaxScore();

    /**
     * Resetta il file delle statistiche.
     */
    void resetStats();
}