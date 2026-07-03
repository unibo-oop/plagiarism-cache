package maingame.entity.item;

import java.awt.Dimension;

import maingame.entity.Entity;
import util.Vector2;

/**
 * Interfaccia di items.
 */
public interface Item extends Entity {

    /**
     * update del item.
     */
    void update();

    /**
     * render del item.
     */
    void render();

    /**
     * 
     * @return se è solido
     */
    boolean isSolid();

    /**
     * 
     * @return il colore per lo spawn
     */
    int getLevelColor();

    /**
     * 
     * @return il nome associato
     */
    String getName();

    /**
     * @return la dimensione
     */
    Dimension getDimension();

    /**
     * @return l'offset
     */
    Vector2<Integer> getOffset();

}