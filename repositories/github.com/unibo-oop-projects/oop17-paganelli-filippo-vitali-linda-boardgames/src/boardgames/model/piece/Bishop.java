package boardgames.model.piece;

import java.util.LinkedList;
import java.util.List;
import boardgames.model.board.Board;
import boardgames.model.board.Box;
import boardgames.utility.Colour;
import boardgames.utility.PieceUtils;

public class Bishop implements PieceType {

    private final PieceUtils pu = new PieceUtils();
    private final static String NAME = "Bishop";

    public List<Box> possibleMoves(final Board b, final Box current, final Colour colour) {
        final int x = current.getX();
        final int y = current.getY();
        final List<Box> moves = new LinkedList<>();
   
        /*movimento nord-ovest*/
        for (int i = y + 1, x1 = x - 1; (i <= PieceUtils.BOARD_SIZE) && (x1 >= 0); i++, x1--) {
               /*controllo se pezzo sopra è occupato*/
               if (!b.getBox(x1, i).isEmpty()) {
                    /*se il box è occupato da un pezzo avversario posso mangiarlo */
                    if (b.getBox(x1, i).getPiece().get().getColour() != colour) {
                        moves.add(b.getBox(x1, i));
                        // non posso scavalcare i pezzi quindi si esce dal ciclo for
                        i = PieceUtils.BOARD_SIZE;
                    } else {
                        i = PieceUtils.BOARD_SIZE;
                    }         
                } else {
                    /*il box è libero e quindi posso andarci sopra*/
                    moves.add(b.getBox(x1, i));
                }
        }  
        /*movimento sud-ovest*/
        for (int i = y - 1, x1 = x - 1; (i >= 0) && (x1 >= 0); i--, x1--) {
                /*controllo se pezzo sopra è occupato*/
                if (!b.getBox(x1, i).isEmpty()) {
                    /*se il box è occupato da un pezzo avversario posso mangiarlo */
                    if (b.getBox(x1, i).getPiece().get().getColour() != colour) {
                        moves.add(b.getBox(x1, i));
                    // non posso scavalcare i pezzi quindi si esce dal ciclo for
                        i = 0;
                    } else {
                        i = 0;
                    }
                } else {
                    /*il box è libero e quindi posso andarci sopra*/
                    moves.add(b.getBox(x1, i));
                }
        }            
        /*movimento nord-est*/
        for (int i = y + 1, x1 = x + 1; (i <= PieceUtils.BOARD_SIZE) && (x1 <= PieceUtils.BOARD_SIZE); i++, x1++) {
                /*controllo se pezzo sopra è occupato*/
                if (!b.getBox(x1, i).isEmpty()) {
                    /*se il box è occupato da un pezzo avversario posso mangiarlo*/
                    if (b.getBox(x1, i).getPiece().get().getColour() != colour) {
                           moves.add(b.getBox(x1, i));
                        // non posso scavalcare i pezzi quindi si esce dal ciclo for
                        i = PieceUtils.BOARD_SIZE;
                    } else {
                        i = PieceUtils.BOARD_SIZE;
                    }              
                } else {
                    /*il box è libero e quindi posso andarci sopra*/
                    moves.add(b.getBox(x1, i));
                }
        }        
        /*movimento sud-est*/
        for (int i = y - 1, x1 = x + 1; (i >= 0) && (x1 <= PieceUtils.BOARD_SIZE); i--, x1++) {
                /*controllo se pezzo sopra è occupato*/
                if (!b.getBox(x1, i).isEmpty()) {
                    /*se il box è occupato da un pezzo avversario posso mangiarlo*/
                    if (b.getBox(x1, i).getPiece().get().getColour() != colour) {
                        moves.add(b.getBox(x1, i));
                        // non posso scavalcare i pezzi quindi si esce dal ciclo for
                        i = 0;
                    } else {
                        i = 0;
                    }
                } else {
                    /*il box è libero e quindi posso andarci sopra*/
                    moves.add(b.getBox(x1, i));
                }
        }
        this.pu.checkRangeMoves(moves);
        this.pu.checkBoxMoves(colour, moves, b);
        return moves;       
    }
    
    public String getName() {
        return NAME;
    }
}
