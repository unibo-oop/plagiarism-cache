package model.entities;

/**
 * Interfaccia delle entità di gioco che hanno la possibilità di muoversi.
 *
 */
public interface IEntityThatMoves extends IEntity {

    /**
     * Aggiorna la posizione dell'entità.
     */
    void refreshPosition();
}
