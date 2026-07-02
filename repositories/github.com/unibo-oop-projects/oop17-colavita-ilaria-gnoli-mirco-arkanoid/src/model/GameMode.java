package model;

/**
 * Enumerazione per gestire le varie modalità di gioco proposte.
 * 
 * @author Mirco Gnoli
 *
 */
public enum GameMode {
    /**
     * Modalità classica.
     */
    CLASSIC,

    /**
     * Modalità survival. Appena una fila di mattoncini viene completamente distrutta, il gioco la rigenera.
     */
    SURVIVAL;
}
