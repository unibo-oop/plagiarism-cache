package boardgames.model;

import boardgames.model.board.Box;
import boardgames.model.board.ChessBoard;
import boardgames.model.piece.Piece;
import boardgames.utility.AllChessPieces;
import boardgames.utility.Colour;

public class ChessGame extends Game {

    private final static int WHITE_KING_X = 4;
    private final static int WHITE_KING_Y = 0; 
    private final static int BLACK_KING_X = 4;
    private final static int BLACK_KING_Y = 7;  
    private final CheckMateImpl kingW;
    private final CheckMateImpl kingB; 

    public ChessGame(final boolean isTimed) {
        super();
        this.board.setBoardType(new ChessBoard());
        this.timed = isTimed;
        this.board.reset();
        this.whitePlayer = new PlayerImpl(Colour.White, board);
        this.blackPlayer = new PlayerImpl(Colour.Black, board);
        this.kingW = new CheckMateImpl(this, this.board.getBox(WHITE_KING_X, WHITE_KING_Y).getPiece().get(), this.whitePlayer);
        this.kingB = new CheckMateImpl(this, this.board.getBox(BLACK_KING_X, BLACK_KING_Y).getPiece().get(), this.blackPlayer);
    }

    public void checkPromotion(final Piece pieceToMove, final Box newPosition) {
        // controllo se il pezzo passato è un pedone
        if (pieceToMove.getName().equals(AllChessPieces.Pawn.toString())) {
            // se il pedone è bianco per essere promosso deve arrivare in un qualsiasi box dell'
            // ultima riga, quindi verifico solo la Y
            if(pieceToMove.getColour() == Colour.White && newPosition.getY() == UPPER_BOUND) {
                this.notifyPromotion();
            } else if (pieceToMove.getColour() == Colour.Black && newPosition.getY() == LOWER_BOUND) {
                this.notifyPromotion();
            }
        }
    }

    @Override
    public void checkWinner(final Colour player) {
        if (player == Colour.White) {
            this.kingB.isCheckMate(this.getLastPieceMoved());
        } else {
            this.kingW.isCheckMate(this.getLastPieceMoved());
        }
    }
}

