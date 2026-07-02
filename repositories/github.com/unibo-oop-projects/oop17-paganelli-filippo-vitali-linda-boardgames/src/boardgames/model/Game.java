package boardgames.model;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import boardgames.model.board.Board;
import boardgames.model.board.Box;
import boardgames.model.piece.Bishop;
import boardgames.model.piece.Knight;
import boardgames.model.piece.Piece;
import boardgames.model.piece.Queen;
import boardgames.model.piece.Rook;
import boardgames.utility.Colour;
import boardgames.view.game.GameView;

public abstract class Game {

    protected final static int UPPER_BOUND = 7;
    protected final static int LOWER_BOUND = 0;
    protected Board board;
    protected Player whitePlayer;
    protected Player blackPlayer;
    private final List<GameView> observers;
    private Optional<Piece> lastPieceEated;
    private Optional<Piece> lastPieceMoved;
    protected boolean timed = false;
    private boolean eated = false;
    
    public Game() {
        this.observers = new LinkedList<>();
        this.board = new Board();
    }
    
    /**
     * @return initialised board of selected game.
     */
    public Board getBoard() {
        return this.board;
    }
    
    public boolean move(final Colour player, final Piece pieceToMove, final Box newPosition) {
        final Box currentPosition = pieceToMove.getBox();
        //controllo se il giocatore possiede il pezzo passato
        if (this.getPlayer(player).getPlayerPieces().contains(pieceToMove)) {
            //controllo se il pezzo può spostarsi nella nuova posizione indicata
            if (pieceToMove.possibleMoves(board).contains(newPosition)) {
                //se è presente un pezzo avversario lo mangia e si sposta
                if ((!newPosition.isEmpty()) && (newPosition.getPiece().get().getColour() != pieceToMove.getColour())) {
                    this.eat(pieceToMove, newPosition.getPiece().get());
                    this.notifyEat();
                    //aggiorno il box precedente del pezzo spostato
                    currentPosition.setPiece(Optional.empty());
                    this.eated = true;
                    return true;
                } else {
                    currentPosition.setPiece(Optional.empty());
                    this.eated = false;
                    return true;
                }
            } else {
                this.eated = false;
                return false;
            }
        }
        this.eated = false;
        return false;
    }

    public void updateMove(final Piece pieceToMove, final Box newPosition) {
        pieceToMove.setBox(newPosition);
        board.getBox(newPosition.getX(), newPosition.getY()).setPiece(Optional.of(pieceToMove));
        this.lastPieceMoved = Optional.of(pieceToMove);
        this.checkWinner(pieceToMove.getColour());
        this.checkPromotion(pieceToMove, newPosition);
    }

    public abstract void checkPromotion(final Piece pieceToMove, final Box newPosition);

    private void eat(final Piece hungry, final Piece pieceEaten) { 
        this.lastPieceEated = Optional.of(pieceEaten);
        this.getPlayer(pieceEaten.getColour()).getPlayerPieces().remove(pieceEaten);       
    }
    
    public boolean isEated () {
        return this.eated;
    }

    public void notifyEat() {
        this.observers.forEach(a -> a.sendPieceToGraveyard());
    }
    
    public void notifyPromotion() {
        this.observers.forEach(a -> a.updatePromotionStatus());
    }

    public Player getPlayer(final Colour colourOfPlayer) {
        if (colourOfPlayer == Colour.Black) {
            return this.blackPlayer;
        } else {
            return this.whitePlayer;
        }
    }

    public void promotion(final Colour player, final Piece toUpgrade, final String pieceUpgraded) {
        final Piece upgraded= new Piece(toUpgrade.getBox(), player);
        toUpgrade.getBox().setPiece(Optional.of(upgraded));
        switch (pieceUpgraded) {
            case "Queen" : upgraded.setPieceType(new Queen());
            case "Rook"  : upgraded.setPieceType(new Rook());
            case "Knight": upgraded.setPieceType(new Knight());
            case "Bishop": upgraded.setPieceType(new Bishop());      
        }
        this.getPlayer(player).getPlayerPieces().remove(toUpgrade);
        this.getPlayer(player).getPlayerPieces().add(upgraded);
    }

    // ritorna true se il giocatore passato ha vinto, altrimenti false
    public abstract void checkWinner(final Colour player);

    public Piece getLastPieceEated() {
        return this.lastPieceEated.get();
    }
    
    public Piece getLastPieceMoved() {
        return this.lastPieceMoved.get();
    }
    
    public void attach(final GameView gv) {
        this.observers.add(gv);
    }
}
