package boardgames.utility;

import java.util.List;

import boardgames.model.board.Board;
import boardgames.model.board.Box;
import boardgames.model.piece.Piece;

public class PieceUtils {

    public final static int BOARD_SIZE = 7;
    
    public boolean checkRange(final int x, final int y) {
        return (((x >= 0) && (x <= BOARD_SIZE)) && ((y >= 0) && (y <= BOARD_SIZE)));
    }
      
    public void checkRangeMoves(final List<Box> moves) {
        for (int i = 0; i < moves.size(); i++) {
            if ((moves.get(i).getX() < 0) || (moves.get(i).getX() > BOARD_SIZE) 
                    || (moves.get(i).getY() < 0) || (moves.get(i).getY() > BOARD_SIZE)) {
                moves.remove(i);
                i--;
            }
        }
    }

    public boolean checkOpponent(final int x, final int y, final Colour c, final Board b) {
       if (!b.getBox(x, y).isEmpty()) {
           return b.getBox(x, y).getPiece().get().getColour() != c;
       }
       return true;
    }
    
    // elimina dalle mosse possibili i box contenenti pezzi dello stesso colore di chi sta muovendo
    public void checkBoxMoves(final Colour colour, final List<Box> moves, final Board b) {        
        for (int i = 0; i < moves.size(); i++) {
            final Box a = b.getBox(moves.get(i).getX(), moves.get(i).getY());
            
           if ((!a.isEmpty()) && (colour == a.getPiece().get().getColour())) {
                   moves.remove(i);
                   i--;       
           }
        }
    }
}
