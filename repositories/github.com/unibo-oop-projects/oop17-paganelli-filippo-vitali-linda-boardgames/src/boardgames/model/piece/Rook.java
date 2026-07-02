package boardgames.model.piece;

import java.util.LinkedList;
import java.util.List;
import boardgames.model.board.Board;
import boardgames.model.board.Box;
import boardgames.utility.Colour;
import boardgames.utility.PieceUtils;

public class Rook implements PieceType {

    private final PieceUtils pu = new PieceUtils();
    private final static String NAME = "Rook";

    public List<Box> possibleMoves(final Board b, final Box current, final Colour colour) {
        final int x = current.getX();
        final int y = current.getY();
        final List<Box> moves = new LinkedList<>();
        
        /*movimento nord*/
        for (int i = y + 1; i <= PieceUtils.BOARD_SIZE; i++) {
                /*controllo se il box sopra è occupato*/
                if (!b.getBox(x, i).isEmpty()) {
                    /*se il box è occupato da un pezzo avversario posso mangiarlo*/
                    if (b.getBox(x, i).getPiece().get().getColour() != colour) {
                        moves.add(b.getBox(x, i));
                        //non posso scavalcare i pezzi quindi esco dal ciclo for
                        i = PieceUtils.BOARD_SIZE;
                    } else {
                        i = PieceUtils.BOARD_SIZE;
                    }    
                } else {
                    /*il box è libero e quindi posso andarci sopra*/
                    moves.add(b.getBox(x, i));
                }
        }
        /*movimento sud*/
        for (int i = y-1; i >= 0; i--) {
                /*controllo se pezzo sopra è occupato*/
                if (!b.getBox(x, i).isEmpty()) {
                    /*se il box è occupato da un pezzo avversario posso mangiarlo*/
                    if (b.getBox(x, i).getPiece().get().getColour() != colour) {
                        moves.add(b.getBox(x, i));
                    //non posso scavalcare i pezzi quindi esco dal ciclo for
                        i = 0;
                    } else {
                        i = 0;
                    }
                } else {
                    /*il box è libero e quindi posso andarci sopra*/
                    moves.add(b.getBox(x, i));
                }
        }
        /*movimento est*/
        for (int i = x + 1; i <= PieceUtils.BOARD_SIZE; i++) {
                /*controllo se pezzo sopra è occupato*/
                if (!b.getBox(i, y).isEmpty()) {
                    /*se il box è occupato da un pezzo avversario posso mangiarlo*/
                    if (b.getBox(i, y).getPiece().get().getColour() != colour) {
                        moves.add(b.getBox(i, y));
                        //non posso scavalcare i pezzi quindi esco dal ciclo for
                        i = PieceUtils.BOARD_SIZE;
                    } else {
                        i = PieceUtils.BOARD_SIZE;
                    }
                } else {
                    /*il box è libero e quindi posso andarci sopra*/
                    moves.add(b.getBox(i, y));
                }
        }     
        /*movimento ovest*/
        for (int i = x - 1; i >= 0; i--) {
                /*controllo se pezzo sopra è occupato*/
                if (!b.getBox(i, y).isEmpty()) {
                    /*se il box è occupato da un pezzo avversario posso mangiarlo*/
                    if (b.getBox(i, y).getPiece().get().getColour() != colour) {
                        moves.add(b.getBox(i, y));
                    //non posso scavalcare i pezzi quindi esco dal ciclo for
                        i = 0;
                    } else {
                        i = 0;
                    }
                } else {
                    /*il box è libero e quindi posso andarci sopra*/
                    moves.add(b.getBox(i, y));
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
