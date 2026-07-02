package boardgames.model.piece;

import java.util.LinkedList;
import java.util.List;

import boardgames.model.board.Board;
import boardgames.model.board.Box;
import boardgames.utility.Colour;
import boardgames.utility.PieceUtils;

public class Dama implements PieceType {

    private Box start;
    private final PieceUtils dm = new PieceUtils();
    private final String name = "Pawn";

    public Dama(final Box piecePosition) {
        this.start = piecePosition;
    }

    public List<Box> possibleMoves(final Board b, final Box current, final Colour colour) {
        final int x = current.getX();
        final int y = current.getY();
        List<Box> moves = new LinkedList<>();

        // moves: white(up-right) black(up-left)
        moves.add(new Box(x + 1, y + 1, null));
        // moves: white(up-left) black(up-right)
        moves.add(new Box(x - 1, y + 1, null));
        // moves: white(down-left) black(down-right)
        moves.add(new Box(x - 1, y - 1, null));
        // moves: white(down-right) black(down-left)
        moves.add(new Box(x + 1, y - 1, null));

        this.dm.checkRangeMoves(moves);
        this.dm.checkBoxMoves(colour, moves, b);
        return moves;

    }

    public String getName() {
        return this.name;
    }
}
