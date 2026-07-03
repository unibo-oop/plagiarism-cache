package model.utilities.pawns;

import java.lang.reflect.Method;
import java.util.Set;

import utilities.Pair;
import utilities.Players;

/**
 * @author : Giulio Bacchilega
 */

public abstract class Pawn implements PawnInterface {
    private final PawnTypes name;
    private final Players player;
    private Pair<Integer, Integer> actualPosition;
    private Integer counterMoves;

    /**
     * Contructor of this class.
     * @param type the type of this pawn
     * @param p the player owner of this pawn
     * @param actualPosition the current position of this pawn
     */
    public Pawn(final PawnTypes type, final Players p, final Pair<Integer, Integer> actualPosition) {
        this.name = type;
        this.player = p;
        this.actualPosition = actualPosition;
        this.counterMoves = 0;
    }

    /**
     * @param next the new position of this pawn
     */
    public void setNewPosition(final Pair<Integer, Integer> next) {
        this.actualPosition = next;
        this.counterMoves++;
    }

    /**
     * @return the type of this pawn
     */
    public PawnTypes getType() {
        return this.name;
    }

    /**
     * @return the player of this pawn
     */
    public Players getPlayer() {
        return this.player;
    }

    /**
     * @return the actual position of the pawn
     */
    public Pair<Integer, Integer> getActualPosition() {
        return this.actualPosition;
    }


    /**
     * @return true if the pawn has been moved, false otherwise
     */
    public boolean isMoved() {
        return this.counterMoves > 0;
    }

    /**
     * Decrement the counter of moves.
     */
    public void decrementMoves() {
        this.counterMoves -= 2;
    }

    /**
     * @return the set containing the possible moves of this pawn
     */
    public abstract Set<Pair<Integer, Integer>> getPossibleMoves();

    /**
     * Call the default set functions.
     */
    public abstract void setAfterCreation();

    /**
     * @return String
     */
    public String toString() {
        return "(" + this.getType() + "->" + this.getActualPosition() + ")";
    }

    /**
     * @param <X> the type of the given parameter
     * @param toCompare , the parameter of comparison
     * @return true if the given parameter matches with the invocation of the relative getter method
     */
    public < X > boolean compare(final X toCompare) {
        for (Method m : Pawn.class.getDeclaredMethods()) {
            if (m.getReturnType().equals(toCompare.getClass())) {
                try {
                    return m.invoke(this).equals(toCompare);
                } catch (Exception e) { }
            }
        }
        return false;
    }
}
