package justanotherchessgame.view.game;

import justanotherchessgame.model.MoveInfoImpl;
import justanotherchessgame.model.Piece;
import justanotherchessgame.view.ResizableGraphicComponent;

/**
 * Interface used to create the game view.
 */
public interface GameView extends ResizableGraphicComponent {
    /**
     * Function used to start and stop timers.
     */
    void changeTimerState();
    /**
     * Function used to increment the counter of a piece when taken.
     * @param p is the taken piece.
     */
    void addTakenpiece(Piece p);
    /**
     * Function used to add a move to the log on the left part of the game view.
     * @param p is the piece moved.
     * @param m is the move performed.
     */
    void addLog(Piece p, MoveInfoImpl m);
    /**
     * Function used to create the view when a user click on a log.
     * @param cb is the loaded chessboard.
     */
     void createLogView(ChessboardViewImpl cb);
    /**
     * Method to completely stop both timers.
     */
    void stopTimers();
}
