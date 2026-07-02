
package boardgames.controller;

import boardgames.utility.BoardGame;
import boardgames.utility.Colour;
import boardgames.view.game.GameViewImpl;

import java.util.List;
import java.util.Optional;

import boardgames.model.Player;
import boardgames.model.board.Board;
import boardgames.model.board.Box;
import boardgames.model.piece.Piece;
import boardgames.model.piece.PieceType;

/**
 * This interface model the controller responsible of the game that will be
 * launched.
 *
 */
public interface GameController {

    /**
     * Method that initialises which game will be played.
     * 
     * @param game 
     * @param isTimed 
     */
    void setGameConfiguration(BoardGame game, boolean isTimed);
    
    public List<Piece> getGamePieces();
    
    void movePieceTo(Box b);

    Board getGameBoard();
    
    void doPiecePromotion(String upgraded);
    
    void setPieceToMove(Optional<Piece> p);
    void setPieceToEat(Optional<Piece> p);
    
    Optional<Piece> getPieceToMove();
    
    Optional<Piece> getEatedPiece();
    
    boolean isRightTurn();
    
  

    /**Method that sets the start point and the arrival point before moves the piece.
     * @param p 
     * @return list of possible moves of a piece, which the player needs to decide the move
     */
    List<Box> getPossibleMoves(Optional<Piece> p);

    /*
     * ritorna il colore del giocatore a cui tocca giocare white sempre giocatore 1,
     * black sempre giocatore 2
     */

    Colour getTurn();
    


    /*
     * imposta il turno del giocatore tramite il colore, sapendo che white sempre
     * giocatore 1 e black sempre giocatore 2
     */
    public void changeTurn();


}

