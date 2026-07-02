package boardgames.model.board;

import java.util.List;
import boardgames.model.piece.Piece;
import boardgames.utility.PieceUtils;

public class Board {

    private List<List<Box>> allPiecesOnBoard;
    private List<Piece> whiteStartPieces;
    private List<Piece> blackStartPieces;
    private BoardType type;

    public void setBoardType(final BoardType type) {
        this.type = type;
    }
    
    public void reset() {
        this.allPiecesOnBoard = this.type.reset();
        this.whiteStartPieces = this.type.getWhiteStartPieces();
        this.blackStartPieces = this.type.getBlackStartPieces();
    }
    
    public Box getBox(final int x, final int y) {
        if (this.checkBox(x, y)) {
            return this.allPiecesOnBoard.get(x).get(y);
        } else {
            return null;
        }
    }

    public List<List<Box>> getAllBoxes() {
        return this.allPiecesOnBoard;
    }

    public List<Piece> getWhiteStartPieces() {
        return this.whiteStartPieces;
   }
    
    public void addWhitePiece(final Piece pw) {
        this.whiteStartPieces.add(pw);
    }

    public List<Piece> getBlackStartPieces() {
        return this.blackStartPieces;
    }
    
    public void addBlackPiece(final Piece pb) {
        this.blackStartPieces.add(pb);
    }

    public boolean checkBox(final int x, final int y) {
        return ((x >= 0) && (x <= PieceUtils.BOARD_SIZE)) && ((y >= 0) && (y <= PieceUtils.BOARD_SIZE));      
    }
}
