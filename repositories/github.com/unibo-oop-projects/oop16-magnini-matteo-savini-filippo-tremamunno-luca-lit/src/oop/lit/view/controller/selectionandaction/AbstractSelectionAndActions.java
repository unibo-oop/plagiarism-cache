package oop.lit.view.controller.selectionandaction;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import oop.lit.model.SelectableElementGroupModel;
import oop.lit.view.BoardView;
import oop.lit.view.Camera;
import oop.lit.view.GameElementView;
import oop.lit.view.controller.Keyboard;

/**
 * Partial implementation of the logic behind selection and actions.
 */
public abstract class AbstractSelectionAndActions {

    private BoardView boardView;
    private final Keyboard keyboard = new Keyboard();

    /**
     * @return the boardView
     */
    protected BoardView getBoardView() {
        return boardView;
    }

    /**
     * @param gev
     *            the GameElementView that you want to be selectable and perform
     *            actions.
     * @param segm
     *            SelectableElementGroupModel.
     */
    public void editSelectionAndActionOnSource(final GameElementView gev, final SelectableElementGroupModel segm) {
        gev.getImageView().setOnMousePressed(getOnMousePressedSource());
        gev.getImageView().setOnMouseReleased(getOnMouseReleasedSource(gev, segm));
    }

    /**
     * @param boardView
     *            the BoardView that you want to be suitable for selection.
     */
    public void editSelectionOnBoard(final BoardView boardView) {
        this.boardView = boardView;
        boardView.getPane().setOnMousePressed(getOnMousePressedBoard());
        boardView.getPane().setOnMouseReleased(getOnMouseReleasedBoard());
    }

    /**
     * @param s
     *            the Scene that you want to be suitable for multi-selection.
     * @param camera
     *            of the board.
     */
    public void editKeyboard(final Scene s, final Camera camera) {
        this.keyboard.editKeyboard(s, camera);
    }

    /**
     * @return the specific EventHandler for the method setOnOnMousePressed
     *         called on source.
     */
    protected abstract EventHandler<MouseEvent> getOnMousePressedSource();

    /**
     * @param gev
     *            GameElementView.
     * @param segm
     *            SelectableElementGroupModel.
     * @return the specific EventHandler for the method setOnOnMouseReleased
     *         called on source.
     */
    protected abstract EventHandler<MouseEvent> getOnMouseReleasedSource(GameElementView gev,
            SelectableElementGroupModel segm);

    /**
     * @return the specific EventHandler for the method setOnOnMousePressed
     *         called on Board.
     */
    protected abstract EventHandler<MouseEvent> getOnMousePressedBoard();

    /**
     * @return the specific EventHandler for the method setOnOnMouseReleased
     *         called on Board.
     */
    protected abstract EventHandler<MouseEvent> getOnMouseReleasedBoard();

    /**
     * @return the keyboard.
     */
    protected Keyboard getKeyboard() {
        return this.keyboard;
    }
}
