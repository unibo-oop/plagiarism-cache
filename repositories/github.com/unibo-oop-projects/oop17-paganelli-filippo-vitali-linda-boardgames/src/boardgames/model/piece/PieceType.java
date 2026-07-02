package boardgames.model.piece;

import java.util.List;

import boardgames.model.board.Board;
import boardgames.model.board.Box;
import boardgames.utility.Colour;

public interface PieceType {

    public List<Box> possibleMoves(final Board b, final Box current, final Colour colour);
    
    public String getName();
}
