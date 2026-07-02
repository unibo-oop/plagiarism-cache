package boardgames.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import boardgames.model.CheckersGame;
import boardgames.model.ChessGame;
import boardgames.model.Game;
import boardgames.model.Player;
import boardgames.model.board.Board;
import boardgames.model.board.Box;
import boardgames.model.piece.Piece;
import boardgames.model.piece.PieceType;
import boardgames.utility.BoardGame;
import boardgames.utility.Colour;
import boardgames.view.board.BoardView;
import boardgames.view.board.CheckersBoardView;
import boardgames.view.board.ChessBoardView;
import boardgames.view.game.GameView;
import boardgames.view.game.GameViewImpl;

/**
 * This class implement the controller responsible of the game that will be
 * launched.
 *
 */
public final class GameControllerImpl implements GameController {

    private static final GameControllerImpl SINGLETON = new GameControllerImpl();
    private BoardGame gameType;
    private boolean isTimed;
    private Game model;
    private GameView gView;
    private Optional<Piece> selectedPiece;
    private Box pieceMovedTo;
    private Board modelBoard;
    private BoardView boardView;
    private Colour turn;

    private GameControllerImpl() {
        this.turn = Colour.White;
        this.selectedPiece = Optional.empty();
        this.pieceMovedTo = null;
    }

    public static GameControllerImpl getGameControllerImpl() {
        return SINGLETON;
    }

    @Override
    public void setGameConfiguration(final BoardGame game, final boolean isTimed) {
        this.gameType = game;
        this.isTimed = isTimed;
        if (this.gameType.equals(BoardGame.CHESS)) {
            this.model = new ChessGame(false);
            this.boardView = new ChessBoardView(this);
        } else {
            this.model = new CheckersGame();
            this.boardView = new CheckersBoardView(this);
        }
        this.modelBoard = model.getBoard();
        this.gView = new GameViewImpl(this, this.boardView);
    
        this.gView.drawGraphics();
        this.model.attach(this.gView);
       
    }

    @Override
    public Board getGameBoard() {
        return this.modelBoard;
    }

    @Override
    public List<Piece> getGamePieces() {
        List<Piece> allPieces = new ArrayList<>();
        allPieces.addAll(this.modelBoard.getBlackStartPieces());
        allPieces.addAll(this.modelBoard.getWhiteStartPieces());
        return allPieces;

    }

    @Override
    public List<Box> getPossibleMoves(final Optional<Piece> p) {
        if (this.isRightTurn()) {
            return p.get().possibleMoves(model.getBoard());
        } else {
            return Collections.emptyList();
        }
    }

    @Override
    public void setPieceToMove(final Optional<Piece> p) {
        this.selectedPiece = p;

    }

    @Override
    public void setPieceToEat(final Optional<Piece> p) {
//        this.pieceMovedTo = p;

    }

    @Override
    public Optional<Piece> getEatedPiece() {
        return Optional.of(model.getLastPieceEated());
        
        
    }

    @Override
    public Optional<Piece> getPieceToMove() {
        return this.selectedPiece;
    }

    @Override
    public boolean isRightTurn() {
        return selectedPiece.get().getColour() == turn;
    }

    @Override
    public void movePieceTo(final Box b) {
        if (this.isRightTurn()) {

//        System.out.println("selectedPiece impostato in precedenza è nel box: " + selectedPiece.get().getBox());
//        System.out.println("Posizione passata da view in cui spostare il pezzo: " + b);

            if (model.move(turn, selectedPiece.get(), b)) {
                gView.drawMove(selectedPiece.get().getBox(), b, model.isEated());

                model.updateMove(selectedPiece.get(), b);
                this.changeTurn();
                this.pieceMovedTo = b;
                setPieceToMove(Optional.empty());
                setPieceToEat(Optional.empty());

            }
        }
    }

    @Override
    public Colour getTurn() {
        return this.turn;
    }

    @Override
    public void changeTurn() {
        this.turn = turn.equals(Colour.Black) ? Colour.White : Colour.Black;

    }

    @Override
    public void doPiecePromotion(String upgraded) {

        model.promotion(pieceMovedTo.getPiece().get().getColour(), pieceMovedTo.getPiece().get(), upgraded);
    }

public Box pieceMovedTo() {
    return this.pieceMovedTo;
}


}
