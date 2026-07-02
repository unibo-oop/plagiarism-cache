package justanotherchessgame.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Class representing a log of all performed moves.
 */
public class MovesHistoryImpl implements MovesHistory {

    private final List<MoveInfoImpl> moves;

    /**
     * Constructor of the Move History.
     */
    public MovesHistoryImpl() {
        moves = new ArrayList<MoveInfoImpl>();
    }

    @Override
    public final boolean nextColor() {
        //After an even number of moves, white moves
        return moves.size() % 2 == 0;
    }

    @Override
    public final void addMove(final MoveInfoImpl m) {
        moves.add(m);
    }

    @Override
    public final List<MoveInfoImpl> getMoves() {
        return moves;
    }

    @Override
    public final List<MoveInfoImpl> getMovesTill(final int index) {
        return moves.subList(0, index);
    }
}
