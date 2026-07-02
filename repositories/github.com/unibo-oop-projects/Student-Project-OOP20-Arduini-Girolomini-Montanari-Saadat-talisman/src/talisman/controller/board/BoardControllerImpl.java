package talisman.controller.board;

import talisman.model.board.Board;
import talisman.view.board.BoardView;

public class BoardControllerImpl<B extends Board<?, ?>> implements BoardController<B> {

    private final B board;
    private final BoardView view;

    /**
     * Creates a new controller.
     * 
     * @param board the board model to control
     * @param view  the board view
     */
    public BoardControllerImpl(final B board, final BoardView view) {
        this.board = board;
        this.view = view;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public B getBoard() {
        return this.board;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BoardView getView() {
        return this.view;
    }
}
