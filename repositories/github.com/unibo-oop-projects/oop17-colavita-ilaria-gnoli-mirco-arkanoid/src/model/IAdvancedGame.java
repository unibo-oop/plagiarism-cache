/**
 * 
 */
package model;

/**
 * Interfaccia che modella una logica di gioco che interagisce con i power-up.
 * 
 * @see PowerUp
 * @see PowerUpType
 * 
 * @author Gnoli Mirco
 */
public interface IAdvancedGame extends IBasicGame {
    /**
     * Aggiunge palline alla partita.
     */
    void addBalls();

    /**
     * Aggiunge una vita.
     */
    void incLives();

    /*
    /**
     * Conclude il livello corrente e passa a quello successivo.
     *
    void goToNextLevel(); //ancora non implementato il discorso dei livelli!*/
}
