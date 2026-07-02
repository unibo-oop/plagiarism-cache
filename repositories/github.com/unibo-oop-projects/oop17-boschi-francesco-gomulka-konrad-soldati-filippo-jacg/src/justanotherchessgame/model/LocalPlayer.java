package justanotherchessgame.model;

import java.util.List;

import justanotherchessgame.controller.Controller;
import justanotherchessgame.util.GameResult;
import justanotherchessgame.util.Point;

/**
 * Class representing a Real Player.
 */
public class LocalPlayer extends Player {

    private Controller controller;

    /**
     * Constructor of the local player.
     * @param color is a boolean representing player pieces color.
     */
    public LocalPlayer(final boolean color) {
        super(color);
    }

    /**
     * Function used to check is the controller is set.
     * @return true if the controller is set, false otherwise.
     */
    private boolean controlsBoard() {
        return controller != null;
    }

    /**
     * Setter of the Controller relative to current player.
     * @param c is the controller which will be applied to the current player
     */
    public void setController(final Controller c) {
        System.out.println("set controller player");
        controller = c;
    }


    @Override
    public final void notifyMove(final MoveInfoImpl m) {
        //TODO: make async
        if (controlsBoard()) {
            //TODO: remove dbg print
            System.out.println("notifying move to bc");
            controller.notifyMove(m);
        }
    }


    @Override
    public final void requestMove(final MoveInfoImpl m) {
        //TODO: make async
        game.requestMove(m);
    }


    @Override
    public final List<MoveInfoImpl> possibleMoves(final Point p) {
        return game.getValidMoves(p);
    }

    @Override
    public final List<MoveInfoImpl> getMoveHistory() {
        return game.getMoveHistory();
    }


    /**
     * Function used to get the list of moves from the beginning until a given index.
     * @param index the limit of the history.
     * @return the list of moves.
     */
    public final List<MoveInfoImpl> getList(final int index) {
        return this.game.getHistoryTill(index);
    }

    /**
     * Function used to get the number of moves made since the beginning of the game.
     * @return the number of moves.
     */
    public final int getMoveCount() {
        return this.game.getMovesCount();
    }

    @Override
    public final void notifyResult(final GameResult result) {
        if (controller != null) {
            controller.notifyGameEnd(result);
        }
    }

}
