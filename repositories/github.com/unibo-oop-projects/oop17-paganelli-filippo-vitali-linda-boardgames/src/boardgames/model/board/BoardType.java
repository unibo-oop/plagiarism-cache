package boardgames.model.board;

import java.util.List;

import boardgames.model.piece.Piece;

public interface BoardType {

    public List<List<Box>> reset();

    public List<Piece> getWhiteStartPieces();
    
    public List<Piece> getBlackStartPieces();
}
