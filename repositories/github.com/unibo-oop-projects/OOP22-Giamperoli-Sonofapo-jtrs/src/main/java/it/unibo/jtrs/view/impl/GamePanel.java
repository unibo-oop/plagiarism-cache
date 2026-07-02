package it.unibo.jtrs.view.impl;

import java.awt.Color;
import java.awt.GridLayout;
import java.util.List;
import java.util.stream.Collectors;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.unibo.jtrs.controller.api.GameController;
import it.unibo.jtrs.model.api.Tetromino;
import it.unibo.jtrs.model.impl.GameModelImpl;
import it.unibo.jtrs.utils.Pair;
import it.unibo.jtrs.view.api.GenericPanel;
import it.unibo.jtrs.view.custom.GridPanel;

/**
 * The class models the game panel. This view must show the Tetronimos placed
 * during the game.
 */
public class GamePanel extends GenericPanel {

    public static final long serialVersionUID = 4328743;

    private final GridPanel game;
    private final transient GameController controller;

    /**
     * Constructor.
     *
     * @param controller the game controller
     */
    @SuppressFBWarnings(justification = "This controller is generated once and never changed")
    public GamePanel(final GameController controller) {
        this.controller = controller;

        this.game = new GridPanel(GameModelImpl.GRID_ROWS, GameModelImpl.GRID_COLS, 0);
        this.setLayout(new GridLayout());
        this.add(this.game);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void redraw() {
        final List<Tetromino> pieces = this.controller.getPieces();
        this.game.blinkLines(this.controller.getDeletedLines());
        final var result = pieces.stream()
            .flatMap(p -> p.getComponents().stream()
                .map(c -> new Pair<>(c, Color.decode(p.getColor()))))
            .collect(Collectors.toMap(Pair::getX, Pair::getY));
        this.game.setCells(result);
    }

}
