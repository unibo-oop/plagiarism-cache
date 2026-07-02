package justanotherchessgame.model;

import java.util.List;

import justanotherchessgame.util.GameResult;
import justanotherchessgame.util.Point;
import justanotherchessgame.ai.DumbAI;

/**
 * Class representing an AI players which uses the simplest AI possible (random legal moves).
 */
public class SimpleAIPlayer extends Player {
    private ChessboardModel cb;

    /**
     * Constructor of the AI player.
     * @param color is the players color.
     */
    public SimpleAIPlayer(final boolean color) {
        super(color);
    }

    /**
     * Method which notifies a given move to the controller.
     * @param m is the move which has to be notified to the controller.
     */
    public void notifyMove(final MoveInfoImpl m) {
        cb = game.getCurrentBoard();
        if (game.nextColor() == color) {
            game.requestMove(DumbAI.getMove(cb, color));
        }
    }

    /**
     * Setter of the players game.
     * @param gm is the current game.
     */
    @Override
    public void setGame(final GameModel gm) {
        super.setGame(gm);
        cb = gm.getCurrentBoard();
    }

    @Override
    public final void requestMove(final MoveInfoImpl m) {
        //TODO: I think that the AI should be the player himself, so I don't like the current interface :(
    }

    @Override
    public final List<MoveInfoImpl> possibleMoves(final Point p) {
        //TODO: refer to requestMove. No AI should ask player about stuff
        return null;
    }

    @Override
    public void notifyResult(final GameResult result) {
        // We don't care about the result, as game is simply over now
    }

    @Override
    public final List<MoveInfoImpl> getMoveHistory() {
        // TODO Auto-generated method stub
        return null;
    }

}
