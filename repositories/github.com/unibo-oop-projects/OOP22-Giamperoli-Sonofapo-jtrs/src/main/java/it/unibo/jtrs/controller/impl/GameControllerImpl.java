package it.unibo.jtrs.controller.impl;

import java.util.List;
import java.util.Set;

import it.unibo.jtrs.controller.api.GameController;
import it.unibo.jtrs.model.api.GameModel;
import it.unibo.jtrs.model.api.Tetromino;
import it.unibo.jtrs.model.impl.GameModelImpl;
import it.unibo.jtrs.utils.TetrominoData;
import it.unibo.jtrs.model.api.GameModel.Interaction;

/**
 * GameController implementation.
 */
public class GameControllerImpl implements GameController {

    private final GameModel model;

    /**
     * Constructor.
     */
    public GameControllerImpl() {
        this.model = new GameModelImpl();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Tetromino> getPieces() {
        return this.model.getPieces();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public synchronized boolean advance(final Interaction i) {
        return this.model.advance(i);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean changePiece(final Tetromino next) {
        this.translateToCenter(next);
        return this.model.nextPiece(next);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int deleteRows() {
        return this.model.deleteRows();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Set<Integer> getDeletedLines() {
        return this.model.getDeletedLines();
    }

    private void translateToCenter(final Tetromino tetromino) {
        if (TetrominoData.I_COLOR.equals(tetromino.getColor())) {
            tetromino.translate(-1, 0);
        }
        if (TetrominoData.O_COLOR.equals(tetromino.getColor())) {
            tetromino.translate(0, 1);
        }
        tetromino.translate(0, GameModelImpl.GRID_COLS / 2 - 2);
    }

}
