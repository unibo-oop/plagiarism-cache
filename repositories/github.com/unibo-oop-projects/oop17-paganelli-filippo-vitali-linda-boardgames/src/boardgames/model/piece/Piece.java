package boardgames.model.piece;

import java.util.*;

import boardgames.model.board.Board;
import boardgames.model.board.Box;
import boardgames.utility.Colour;

public class Piece {

    private Optional<Box> piecePosition = Optional.empty();
    private Optional<Colour> pieceColour = Optional.empty();
    private PieceType type;
    
    public Piece(final Box piecePosition, final Colour pieceColour) {
        this.piecePosition = Optional.of(piecePosition);
        this.pieceColour = Optional.of(pieceColour);
    }
    
    public void setPieceType(final PieceType type) {
        this.type = type;
    }
    
    @Override
    public String toString() {
        return piecePosition.get().toString()+", Colour = "+pieceColour.get();
    }

    public Box getBox() {
        return this.piecePosition.get();
    }

    public void setBox(final Box piecePosition) {
        this.piecePosition = Optional.of(piecePosition);
    }

    public Colour getColour() {
        return this.pieceColour.get();
    }

    public String getName() {
        return this.type.getName();
    }

    public List<Box> possibleMoves(Board b) {
        return this.type.possibleMoves(b, this.getBox(), this.getColour());
    }
}