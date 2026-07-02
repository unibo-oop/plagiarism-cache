package model;

import java.util.List;
import java.util.Map;

import model.entities.Ball;
import model.entities.Bar;
import model.entities.Brick;
import model.entities.IEntity;
import model.entities.Wall;

/**
 * Interfaccia che modella una semplice logica di gioco.
 * 
 * @author Gnoli Mirco
 */
public interface IBasicGame {
    /**
     * Possibili stati in cui si trova il gioco.
     *
     */
    enum GameStatus {
        START,
        WON,
        LOST,
        RUNNING,
        PAUSE;
    }

    /**
     * Inizializza la barra e la pallina a centro schermo ogni volta che si perde una vita.
     */
    void initOnStart();

    /**
     * Aggiorna il gioco e tutti i suoi componenti.
     */
    void updateModel();

    /**
     * Mette il gioco in pausa.
     */
    void setPause();

    /**
     * Lista delle palline attualmente in gioco.
     * @return Lista di {@link Ball}
     */
    List<Ball> getBalls();

    /**
     * Mappa dei mattoncini attualmente in gioco.
     * 
     * @return Mappa di {@link Brick}
     */
    Map<Integer, List<Brick>> getBricks();

    /**
     * Lista delle pareti che delimitano lo spazio di gioco.
     * @return Lista di {@link Wall}
     */
    List<Wall> getWalls();

    /**
     * Ritorna la barra.
     * @return {@link Bar}
     */
    Bar getBar();

    /**
     * Ritorna la stato in cui si trova il gioco.
     * @return {@link GameStatus}
     */
    GameStatus getStatus();

    /**
     * Ritorna il punteggio attuale.
     * @return int - punteggio attuale
     */
    int getScore();

    /**
     * Ritorna il numero delle vite attualmente rimaste.
     * @return int - vite
     */
    int getLives();

    /**
     * Incrementa il numero delle vite.
     */
    void incLives();

    /**
     * Ritorna tutte le {@link IEntity} presenti nel gioco.
     * @return List({@link IENtity})
     */
    List<IEntity> getAllEntities();
}
