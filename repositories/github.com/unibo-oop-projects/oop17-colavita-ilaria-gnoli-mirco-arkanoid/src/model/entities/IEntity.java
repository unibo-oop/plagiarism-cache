package model.entities;

import javafx.util.Pair;

/**
 * Interfaccia di una generica entità di gioco.
 * 
 * @author Gnoli Mirco
 *
 */
public interface IEntity {

    /**
     * Getter della coordinata massima orizzontale.
     * 
     * @return int
     */
    int getMaxX();

    /**
     * Getter della coordinata minima orizzontale.
     * 
     * @return int
     */
    int getMinX();

    /**
     * Getter della coordinata massima verticale.
     * 
     * @return int
     */
    int getMaxY();

    /**
     * Getter della coordinata minima verticale.
     * 
     * @return int
     */
    int getMinY();

    /**
     * Ritorna la posizione attuale.
     * 
     * @return {@link javafx.util.Pair} (x, y)
     */
    Pair<Integer, Integer> getPosition();

    /**
     * Setta la nuova posizione dell'entità.
     * 
     * @param newX - nuova coordinata X
     * @param newY - nuova coordinata Y
     */
    void setPosition(int newX, int newY);
}
