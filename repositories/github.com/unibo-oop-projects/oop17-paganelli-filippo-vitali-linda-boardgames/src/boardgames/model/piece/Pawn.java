package boardgames.model.piece;

import java.util.LinkedList;
import java.util.List;
import boardgames.model.board.Board;
import boardgames.model.board.Box;
import boardgames.utility.Colour;
import boardgames.utility.PieceUtils;

public class Pawn implements PieceType {

    private boolean isFirstMove = true;
    private Box initial;
    private final PieceUtils pu = new PieceUtils();
    private final static String NAME = "Pawn";
    
    public Pawn(final Box piecePosition) {
        this.initial = piecePosition;
    }

    public List<Box> possibleMoves(final Board b, final Box current, final Colour colour) {
        final int x = current.getX();
        final int y =  current.getY();
        final List<Box> moves = new LinkedList<>();
        
        if (this.initial != current) {
            this.isFirstMove = false;
        }
        
        if (colour == Colour.White) {
            if (isFirstMove) {
                if (b.getBox(x, y + 2).isEmpty() && b.getBox(x, y + 1).isEmpty()) {
                    moves.add(b.getBox(x, y + 2));
                }
            }

            if (this.pu.checkRange(x, y + 1)) {
                if (b.getBox(x, y + 1).isEmpty()) {
                    moves.add(b.getBox(x, y + 1));
                }
            } 
            /*  check if there is a piece of the opponent in the 
             *  box north-east of the piece
             */
            if (this.pu.checkRange(x + 1, y + 1)) {
                if (!b.getBox(x + 1, y + 1).isEmpty()) {
                    if (b.getBox(x + 1, y + 1).getPiece().get().getColour() != colour) {
                        moves.add(b.getBox(x + 1, y + 1));
                    }
                }
            }               
            /*  check if there is a piece of the opponent in the 
             *  box north-west of the piece
             */ 
            if (this.pu.checkRange(x - 1, y + 1)) {
                if (!b.getBox(x - 1, y + 1).isEmpty()) {
                    if (b.getBox(x - 1, y + 1).getPiece().get().getColour() != colour) {
                        moves.add(b.getBox(x - 1, y + 1));
                    }
                }
            }           
        } else {
            if (isFirstMove) {
                if (b.getBox(x, y - 2).isEmpty() && b.getBox(x, y - 1).isEmpty()) {
                    moves.add(b.getBox(x, y - 2));
                }
            }

            if (this.pu.checkRange(x, y - 1)) {
                 if (b.getBox(x, y - 1).isEmpty()) {
                     moves.add(b.getBox(x, y - 1));
                 }
            }
            /*  check if there is a piece of the opponent in the 
             *  box south-east of the piece
             */
            if (this.pu.checkRange(x + 1, y - 1)) {
                if (!b.getBox(x + 1, y - 1).isEmpty()) {
                    if (b.getBox(x + 1, y - 1).getPiece().get().getColour() != colour) {
                        moves.add(b.getBox(x + 1, y - 1));
                    }
                }
            }
            /*  check if there is a piece of the opponent in the 
             *  box south-west of the piece
             */ 
            if (this.pu.checkRange(x - 1, y - 1)) {
                if (!b.getBox(x - 1, y - 1).isEmpty()) {
                    if (b.getBox(x - 1, y - 1).getPiece().get().getColour() != colour) {
                        moves.add(b.getBox(x - 1, y - 1));
                    }
                }
            }
        }
        this.pu.checkBoxMoves(colour, moves, b);
        return moves;  
    }
    
    public String getName() {
        return NAME;
    }
}
