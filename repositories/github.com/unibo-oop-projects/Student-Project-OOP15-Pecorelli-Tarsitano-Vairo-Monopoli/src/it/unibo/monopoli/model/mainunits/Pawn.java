package it.unibo.monopoli.model.mainunits;

import it.unibo.monopoli.model.table.Box;

/**
 * This interface represent all the pawns used in the game. Each {@link Player}
 * has one pawn that identify himself in this game.
 *
 */
public interface Pawn {

    /**
     * Return the ID of the {@link Pawn}.
     * 
     * @return the ID of the {@link Pawn}
     */
    int getID();

    /**
     * Return the actual position of the {@link Pawn} in the table of play. This
     * position is represented by the ID of the {@link Box} in witch the
     * {@link Pawn} is located.
     * 
     * @return the actual position of the {@link Pawn}
     */
    int getActualPos();

    /**
     * Returns the previous position of the {@link Pawn} in the table of play.
     * 
     * @return {@link Pawn}'s previous position
     */
    int getPreviousPos();

    /**
     * Set the new position of the {@link Pawn} in the table of play. This
     * position is represented by the ID of the {@link Box} in witch the
     * {@link Pawn} is located.
     * 
     * @param newPosizion
     *            - the new ID of the {@link Box} in witch the {@link Pawn} is
     *            located.
     */
    void setPos(int newPosizion);

}
