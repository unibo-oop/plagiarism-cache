package model;

/**
 * Classe pubblica per tenere traccia di tutte le impostazioni di gioco del modello.
 * @author Gnoli Mirco
 *
 */
public final class ModelCostant {

    private ModelCostant() { }

    /**
     * Larghezza dell'arena.
     */
    public static final int WORLD_WIDTH = 720;

    /**
     * Altezza dell'arena.
     */
    public static final int WORLD_HEIGHT = 620;

    /**
     * Vite ad inizio partita.
     */
    public static final int LIVES_ON_START = 3;

    /**
     * Punteggio ad inizio partita.
     */
    public static final int SCORE_ON_START = 0;

    /**
     * Numero di righe di mattoncini.
     */
    public static final int NUMBER_OF_ROW = 3;

    /**
     * Numero di mattoncini in una riga.
     */
    public static final int NUMBER_OF_COLUMN = 15;

    /**
     * Dimensione del raggio della pallina.
     */
    public static final int DEFAULT_BALL_RADIUS = 7;

    /**
     * Velocità iniziale della pallina. 
     */
    public static final int DEFAULT_SPEED = 10;

    /**
     * Angolo iniziale della pallina. 
     */
    public static final int DEFAULT_ANGLE = 300; //150

    /**
     * Larghezza dei mattoni.
     */
    public static final int DEFAULT_BRICK_WIDTH = (ModelCostant.WORLD_WIDTH - ModelCostant.NUMBER_OF_COLUMN) / ModelCostant.NUMBER_OF_COLUMN;

    /**
     * Altezza dei mattoni.
     */
    public static final int DEFAULT_BRICK_HEIGHT = DEFAULT_BRICK_WIDTH / 2;

    /**
     * Larghezza della barra.
     */
    public static final int DEFAULT_BAR_WIDTH = 440;

    /**
     * Altezza della barra.
     */
    public static final int DEFAULT_BAR_HEIGHT = 15;

    /**
     * Distanza della barra dalla parete.
     */
    public static final int DEFAULT_OFFSET_FROM_WALL = 10;

    /**
     * Arrotondamento della barra alle estremità.
     */
    public static final int DEFAULT_BAR_ARC = 12; //usato per fare il rettangolo arrotondato

    /**
     * Probabilità che un mattone rotto generi un power_up.
     */
    public static final double POWERUP_SPAWN_PROBABILITY = 1;//0.35;

    /**
     * Larghezza dei power-up.
     */
    public static final int POWERUP_WIDTH = DEFAULT_BRICK_WIDTH;

    /**
     * Altezza dei power-up.
     */
    public static final int POWERUP_HEIGHT = POWERUP_WIDTH / 2;

}
