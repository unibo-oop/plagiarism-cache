package justanotherchessgame.model;

import justanotherchessgame.util.GameResult;
import justanotherchessgame.util.Point;
import java.util.ArrayList;
import java.util.List;

/*
 * TODO: this should extend some interface,
 * as we need a skeleton for the different game modes.
 * The Game model represents the status of the game being played.
 * It contains the Chessboard and the moves history. It also must
 * contain reference to the players, so to notify them about the game
 * progress.
 * Core methods identified so far:
 * requestMove(Move m): receives some move request, which must be validated.
 * if performed successfully, must notify players
 * getValidMoves(Square s): in which Locations can I actually move starting from s?
 * returns a list of moves
 */

/**
 * The Game model represents the status of the game being played.
 * It contains the Chessboard and the moves history. It also must
 * contain reference to the players, so to notify them about the game
 * progress.
 */
public class GameModelImpl implements GameModel {
    // Private variables
    private final Player whitePlayer;
    private final Player blackPlayer;
    private final ChessboardModel board;
    private final MovesHistory history;
    private boolean running;

    /**
     * Constructor of the Game Model.
     * @param white is the instance of the White player.
     * @param black is the instance of the Black player.
     */
    public GameModelImpl(final Player white, final Player black) {
        history = new MovesHistoryImpl();
        // Builds chessboard as default one
        board = new ChessboardModelImpl(true);
        whitePlayer = white;
        blackPlayer = black;
        // Must be done last, in order to allow players to ask abour game informations
        whitePlayer.setGame(this);
        blackPlayer.setGame(this);
        // The game is currently running
        running = true;
    }

    /**
     * Method that checks if the game should end now and eventually ends it.
     */
    private void checkGameEnd() {
        // Are there any moves available now?
        boolean hasMoves = false;
        for (final Piece p : board.getPieceOnBoard()) {
            if (p.isWhite() == nextColor() && MovesChecker.possibleMoves(board, p.getPoint()).size() > 0) {
                hasMoves = true;
                break;
            }
        }
        // Either checkmate or stalemate
        if (!hasMoves) {
            if (MovesChecker.kingCheck(board, board.getKing(nextColor()).getPoint(), nextColor())) {
                // it's a checkmate
                whitePlayer.notifyResult(nextColor() ? GameResult.BLACK_WIN : GameResult.WHITE_WIN);
                blackPlayer.notifyResult(nextColor() ? GameResult.BLACK_WIN : GameResult.WHITE_WIN);
            } else {
                // It's a stalemate
                whitePlayer.notifyResult(GameResult.STALEMATE);
                blackPlayer.notifyResult(GameResult.STALEMATE);
            }
            // Updates game status
            running = false;
        }
    }

    public final boolean nextColor() {
        return history.nextColor();
    }

    public final void requestMove(final MoveInfoImpl m) {
        // If game is over, ignore request
        if (!running) {
            return;
        }
        System.out.println("requested " + m);
        // If move is outside board
        if (!m.getTo().onBoard() || !m.getFrom().onBoard()) {
            return;
        }
        //Check turn validity
        if (board.getSquare(m.getFrom()).isWhite() != history.nextColor()) {
            return;
        }
        //TODO: add security checks
        if (board.move(m)) {
            history.addMove(m);
            //notifies white and black separately
            whitePlayer.notifyMove(m);
            blackPlayer.notifyMove(m);
            //TODO: add threefold repetition
            //Check for stalemate/checkmate
            checkGameEnd();
        }
    }

    public final List<MoveInfoImpl> getValidMoves(final Point p) {
        //Only if proper turn
        final Piece pi = board.getSquare(p);
        if (pi == null || pi.color != history.nextColor()) {
            return new ArrayList<MoveInfoImpl>();
        }
        return MovesChecker.possibleMoves(board, p);
    }

    public final ChessboardModel getCurrentBoard() {
        return new ChessboardModelImpl(history.getMoves());
    }

    public final List<MoveInfoImpl> getMoveHistory() {
        return history.getMoves();
    }
 
    public final void loadGame(final List<MoveInfoImpl> moves) {
        for (final MoveInfoImpl m : moves) {
            requestMove(m);
        }
    }

    public final List<MoveInfoImpl> getHistoryTill(final int index) {
        return history.getMovesTill(index);
    }

    public final int getMovesCount() {
        return this.history.getMoves().size();
    }
}
