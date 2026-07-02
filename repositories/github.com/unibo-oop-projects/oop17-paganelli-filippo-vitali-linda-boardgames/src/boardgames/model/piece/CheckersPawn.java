package boardgames.model.piece;

import java.util.*;
import boardgames.model.board.Board;
import boardgames.model.board.Box;
import boardgames.utility.Colour;
import boardgames.utility.PieceUtils;

public class CheckersPawn implements PieceType {

    private Box start;
    private final PieceUtils cp = new PieceUtils();
    private final String name = "Pawn";
    
    public CheckersPawn(final Box position) {
        this.start = position;
    }

    public List<Box> possibleMoves(final Board b, final Box current, final Colour colour) {
        final int x = current.getX();
        final int y = current.getY();
        final List<Box> moves = new LinkedList<>();

        // white checkerspawn moves N-E
        if (b.getBox(x + 1, y + 1).isEmpty()) {
            moves.add(b.getBox(x + 1, y + 1));
        } else {
            if (!b.getBox(x + 1, y + 1).isEmpty()) {
                if (b.getBox(x + 1, y + 1).getPiece().get().getColour() != colour && b.getBox(x + 2, y + 2).isEmpty())
                    moves.add(b.getBox(x + 2, y + 2));
            }
        }

        // white checkerspawn moves N-O
        if (b.getBox(x - 1, y + 1).isEmpty()) {
            moves.add(b.getBox(x - 1, y + 1));
        } else {
            if (!b.getBox(x - 1, y + 1).isEmpty()) {
                if (b.getBox(x - 2, y + 2).getPiece().get().getColour() != colour && b.getBox(x - 2, y + 2).isEmpty())
                    moves.add(b.getBox(x - 2, y + 2));
            }
        }

        // black checerkspawn moves S-O
        if (b.getBox(x - 1, y - 1).isEmpty()) {
            moves.add(b.getBox(x - 1, y - 1));
        } else {
            if (!b.getBox(x - 1, y - 1).isEmpty()) {
                if (b.getBox(x - 2, y - 2).getPiece().get().getColour() != colour && b.getBox(x - 2, y - 2).isEmpty())
                    moves.add(b.getBox(x - 2, y - 2));
            }
        }

        // black checerkspawn moves S-E
        if (b.getBox(x + 1, y - 1).isEmpty()) {
            moves.add(b.getBox(x + 1, y - 1));
        } else {
            if (!b.getBox(x + 1, y - 1).isEmpty()) {
                if (b.getBox(x + 2, y - 2).getPiece().get().getColour() != colour && b.getBox(x + 2, y - 1).isEmpty())
                    moves.add(b.getBox(x + 2, y - 2));
            }
        }

        this.cp.checkRangeMoves(moves);
        this.cp.checkBoxMoves(colour, moves, b);
        return moves;
    }

    public String getName() {
        return this.name;
    }
}
