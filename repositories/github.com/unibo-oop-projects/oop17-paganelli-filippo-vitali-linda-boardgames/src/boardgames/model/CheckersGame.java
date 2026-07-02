package boardgames.model;

import java.util.LinkedList;
import java.util.List;

import boardgames.controller.GameController;
import boardgames.controller.GameControllerImpl;
import boardgames.model.board.Board;
import boardgames.model.board.Box;
import boardgames.model.board.CheckersBoard;
import boardgames.model.piece.Piece;
import boardgames.utility.Colour;

/**
 * Class that manages Checkers game.
 *
 */
public class CheckersGame extends Game {
    /**
     * Constructor of the class.
     *
     */
    public CheckersGame() {
        super();
        this.board.setBoardType(new CheckersBoard());
        this.board.reset();
        this.whitePlayer = new PlayerImpl(Colour.White, board);
        this.blackPlayer = new PlayerImpl(Colour.Black, board);
    }
    /*
     * esegue la promozione del pezzo passato, che dovrà essere un pedone, schacchi
     * : tramite view viene indicato il nuovo pezzo che prenderà il suo posto fra
     * regina, torre, alfiere, cavallo >> viene visualizzato menù nella view con le opzioni (checkbox)
     * dama: il pedone diventa automaticamente un damone
     */
    @Override
    public void checkPromotion(Piece pieceToMove, Box newPosition) {
        // TODO Auto-generated method stub
        
    }
    @Override
    public void checkWinner(Colour player) {
        
    }
  
    

   
}
