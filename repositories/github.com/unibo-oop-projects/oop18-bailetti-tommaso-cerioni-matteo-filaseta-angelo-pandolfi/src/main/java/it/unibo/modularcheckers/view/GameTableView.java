package it.unibo.modularcheckers.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagLayout;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JPanel;

import it.unibo.modularcheckers.model.Chessboard;
import it.unibo.modularcheckers.model.Coordinate;
import it.unibo.modularcheckers.model.Pair;
import it.unibo.modularcheckers.util.GameDataDeserializerImpl;
import it.unibo.modularcheckers.util.SpriteSelector;
import it.unibo.modularcheckers.view.components.BlockComponent;
import it.unibo.modularcheckers.view.components.ChessBoardComponent;

/**
 * View defining the base of a chess board.
 */
public class GameTableView extends View {

    private static final long serialVersionUID = -1930603709528904000L;

    /**
     * Initialize the GameTableView with the previously serialized Class.
     * TODO use the chess board given by the Engine
     */
    public GameTableView() {
        super();
        final Chessboard chessboard = new GameDataDeserializerImpl().deserializeCheckersBoard();

        final int height = chessboard.getSize().getX();
        final int width = chessboard.getSize().getY();

        final Map<JButton, Coordinate> map = new HashMap<>();
        final JPanel panel = new JPanel(new BorderLayout(3, 3));
        final JPanel container = new JPanel(new GridBagLayout());
        final JPanel grid = new ChessBoardComponent(height, width);
        final SpriteSelector spriteSelector = new SpriteSelector();

        for (int i = width - 1; i >= 0; i--) {
            for (int j = 0; j < height; j++) {
                final JButton button = new BlockComponent();

                if ((i % 2 == 0 && j % 2 == 1) || (i % 2 == 1 && j % 2 == 0)) {
                    button.setBackground(Color.WHITE);
                } else {
                    button.setBackground(Color.BLACK);
                }
                button.addActionListener(l -> {
                    System.out.println(map.get(button).getX() + "-" + map.get(button).getY());
                });
                if (chessboard.getBlock(new Coordinate(i, j)).getPiece().isPresent()) {
                    button.setIcon(spriteSelector.getSprite(new Pair<>(
                            chessboard.getBlock(new Coordinate(i, j)).getPiece().get().getType(),
                            chessboard.getBlock(new Coordinate(i, j)).getPiece().get().getColor())));
                }
                map.put(button, new Coordinate(i, j));
                grid.add(button);
            }
        }

        container.add(grid);
        panel.add(container);
        this.add(panel);
        this.setFullScreen();
        this.setVisible(true);
    }
}
