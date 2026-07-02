package giocoscudetto.model.impl.match;

import java.util.ArrayList;
import java.util.List;

import giocoscudetto.model.api.Board;
import giocoscudetto.model.api.Box;

/**
 * This class represents the board of the game,
 *  it contains a list of boxes that represent the different events that can happen,
 *  during the game.
 */
public class BoardImpl implements Board {

    private static final int BOARD_SIZE = 32;
    private final List<Box> board;

    /**
     * Constructor of the BoardImpl class.
     */
    public BoardImpl() {
        this.board = new ArrayList<>();
        for (int i = 0; i <= BOARD_SIZE; i++) {
            this.board.add(BoxFactory.createBox(i));
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Box getBox(final int index) {
       return board.get(index);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getBoxImage(final int i) {
        return this.board.get(i).getImage();
    }
}
