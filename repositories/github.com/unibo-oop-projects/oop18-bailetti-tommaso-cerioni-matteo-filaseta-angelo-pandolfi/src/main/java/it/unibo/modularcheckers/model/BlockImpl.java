package it.unibo.modularcheckers.model;

import java.util.Objects;

import com.google.common.base.Optional;

import it.unibo.modularcheckers.model.piece.Piece;

/**
 * Block implementation.
 */
public class BlockImpl implements Block {

    private static final long serialVersionUID = 1010812075794706092L;
    private Optional<Piece> piece = Optional.absent();

    /**
     * return the Optional of the piece over the block.
     */
    @Override
    public Optional<Piece> getPiece() {
        return this.piece;
    }

    /**
     * set the piece over the block , overwriting current piece.
     */
    @Override
    public void setPiece(final Piece piece) {
        this.piece = Optional.of(Objects.requireNonNull(piece));
    }

    /**
     * remove the piece over the block.
     */
    @Override
    public Optional<Piece> removePiece() {
        final Optional<Piece> p = this.getPiece();
        this.piece = Optional.absent();
        return p;
    }

    /**
     * check if exists the piece over the block.
     *
     * @return true if piece exists.
     */
    @Override
    public boolean pieceExists() {
        return this.piece.isPresent();
    }
}
