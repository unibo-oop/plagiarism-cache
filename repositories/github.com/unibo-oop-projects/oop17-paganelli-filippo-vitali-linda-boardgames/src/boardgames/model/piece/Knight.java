package boardgames.model.piece;

import java.util.LinkedList;
import java.util.List;
import boardgames.model.board.Board;
import boardgames.model.board.Box;
import boardgames.utility.Colour;
import boardgames.utility.PieceUtils;

public class Knight implements PieceType {

    private final PieceUtils pu = new PieceUtils();
    private final static String NAME = "Knight";

    public List<Box> possibleMoves(final Board b, final Box current, final Colour colour) {
        final int x = current.getX();
        final int y = current.getY();
        final List<Box> moves = new LinkedList<>();
        
        if (this.pu.checkRange(x + 2, y - 1) 
                && this.pu.checkOpponent(x + 2, y - 1, colour, b))  {
            moves.add(b.getBox(x + 2, y - 1));
        }
        if (this.pu.checkRange(x + 2, y + 1)
                && this.pu.checkOpponent(x + 2, y + 1, colour, b)) {
            moves.add(b.getBox(x + 2, y + 1));
        }
        if (this.pu.checkRange(x + 1, y + 2)
                && this.pu.checkOpponent(x + 1, y + 2, colour, b)) {
            moves.add(b.getBox(x + 1, y + 2));
        }
        if (this.pu.checkRange(x - 1, y + 2)
                && this.pu.checkOpponent(x - 1, y + 2, colour, b)) {
            moves.add(b.getBox(x - 1, y + 2));
        }
        if (this.pu.checkRange(x - 2, y + 1)
                && this.pu.checkOpponent(x - 2, y + 1, colour, b)) {
            moves.add(b.getBox(x - 2, y + 1));
        }
        if (this.pu.checkRange(x - 2, y - 1)
                && this.pu.checkOpponent(x - 2, y - 1, colour, b)) {
            moves.add(b.getBox(x - 2, y - 1));
        }
        if (this.pu.checkRange(x + 1, y - 2)
                && this.pu.checkOpponent(x + 1, y - 2, colour, b)) {
            moves.add(b.getBox(x + 1, y - 2));
        }
        if (this.pu.checkRange(x - 1, y - 2)
                && this.pu.checkOpponent(x - 1, y - 2, colour, b)) {
            moves.add(b.getBox(x - 1, y - 2));
        }
        return moves;
    }
    
    public String getName() {
        return NAME;
    }
}
