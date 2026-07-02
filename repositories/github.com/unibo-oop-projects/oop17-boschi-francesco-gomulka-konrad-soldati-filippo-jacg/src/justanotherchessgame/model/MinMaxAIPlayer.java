package justanotherchessgame.model;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import justanotherchessgame.util.GameResult;
import justanotherchessgame.util.Point;
import justanotherchessgame.ai.MinMaxAI;

public class MinMaxAIPlayer extends Player {
    private ChessboardModel cb;
    public MinMaxAIPlayer(final boolean color) {
        super(color);
    }
    
    public final void notifyMove(final MoveInfoImpl m) {
        cb.move(m);
        if (game.nextColor() == color) {
        	CompletableFuture.supplyAsync(()->MinMaxAI.getMove(cb, color)).thenAccept(move -> game.requestMove(move));
        }
    }

    @Override
    public final void setGame(final GameModel gm) {
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
    public final List<MoveInfoImpl> getMoveHistory() {
        return null;
        //TODO: same as above, except that I think this should be done completely differently 
    }

    @Override
    public void notifyResult(final GameResult result) {

    }

}
