package justanotherchessgame.model;

/**
 * Interfaces used to manage moves.
 */
public interface MoveInfo {

    /**
     * Setter of the new class of the promoted pawn.
     * @param type is the new class of the pawn.
     * */
    void setPromotion(Class<? extends Piece> type);

    /**
     * Getter of the new class of the promoted pawn.
     * @return is the new class of the pawn
     */
    Class<? extends Piece> getPromotion();

    /**
     * Function which returns the move as a string.
     * @return is the move as a string.
     */
    String toString();
}
