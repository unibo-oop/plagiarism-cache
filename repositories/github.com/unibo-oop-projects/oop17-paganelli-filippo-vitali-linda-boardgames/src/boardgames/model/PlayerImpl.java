package boardgames.model;

import java.util.List;
import boardgames.model.board.Board;
import boardgames.model.piece.Piece;
import boardgames.utility.Colour;

public class PlayerImpl implements Player {

    private final List<Piece> playerPieces;
    private final Colour player;
    private final Board board;

    public PlayerImpl(final Colour player, final Board board) {
        this.player = player;
        this.board = board;
        if (player == Colour.Black) {
            this.playerPieces = this.board.getBlackStartPieces();
        } else {
            this.playerPieces = this.board.getWhiteStartPieces();
        }
    }
    
    @Override
    public List<Piece> getPlayerPieces() {
        return this.playerPieces;
    }

    @Override
    public void removePiece(final Piece p) {
        if (this.playerPieces.contains(p)) {
            this.playerPieces.remove(p);      
        }
    }

    @Override
    public Colour getColour() {
        return this.player;
    }

    @Override
    public void addPiece(final Piece p) {
        this.playerPieces.add(p);
    }  
}
