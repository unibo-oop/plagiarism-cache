package it.unibo.modularcheckers.model;

import java.io.Serializable;

import com.google.common.base.Optional;
import it.unibo.modularcheckers.model.piece.Piece;

/**
 * The block of a chessboard.
 */
public interface Block extends Serializable {
    /**
     * Get the piece over the block.
     * 
     * @return optional of the piece.
     */
    Optional<Piece> getPiece();

    /**
     * Set the piece over the block, if piece already exists, it will removed.
     * 
     * @param piece the piece to be placed in the block.
     */
    void setPiece(Piece piece);

    /**
     * Remove the piece over the block.
     * 
     * @return the piece removed.
     */
    Optional<Piece> removePiece();

    /**
     * Check if the block contains a piece.
     * 
     * @return true if the piece exists.
     */
    boolean pieceExists();
}
