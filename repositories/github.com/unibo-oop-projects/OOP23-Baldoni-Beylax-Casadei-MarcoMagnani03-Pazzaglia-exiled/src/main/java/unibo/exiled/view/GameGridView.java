package unibo.exiled.view;

import unibo.exiled.utilities.ConstantsAndResourceLoader;
import unibo.exiled.controller.GameController;
import unibo.exiled.model.map.CellType;
import unibo.exiled.utilities.Position;
import unibo.exiled.view.character.CharacterView;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.util.List;

/**
 * The Grid class, is responsible for initializing and drawing the grid on the game panel.
 * It places cells in the grid based on the cell type and content.
 */
public class GameGridView {
    private static final int PLAYER_GRID_VISIBILITY = 5;
    private final GameController gameController;
    private final CharacterView playerView;
    private JPanel gridPanel;
    private final JPanel gamePanel; 

    /**
     * Constructs a Grid instance with the specified game controller, game panel, and player view.
     *
     * @param gameController The game controller that manages interaction between the model and the view.
     * @param playerView     The view representing the player.
     */
    public GameGridView(final GameController gameController,
                        final CharacterView playerView) {
        this.gameController = gameController;
        this.playerView = playerView;
        this.gridPanel = new JPanel(new GridLayout(gameController.getMapController().getMapSize(),
                gameController.getMapController().getMapSize()));
        this.gamePanel = new JPanel(new BorderLayout());
    }

    /**
     * Initializes and draws the grid on the game panel.
     * 
     * @return the game panel.
     */
    public JPanel initializeGrid() {
        drawGrid();
        this.gamePanel.removeAll();
        this.gamePanel.add(gridPanel, BorderLayout.CENTER);
        this.gamePanel.revalidate();
        this.gamePanel.repaint();
        final JPanel clonedGamePanel = new JPanel(new BorderLayout());
        clonedGamePanel.add(gamePanel, BorderLayout.CENTER);
        return clonedGamePanel;
    }

    /**
     * Draws the grid game panel.
     */
    private void drawGrid() {
        final Position playerPosition = gameController.getCharacterController().getPlayerPosition();
        final int mapSize = this.gameController.getMapController().getMapSize();
        final int range = PLAYER_GRID_VISIBILITY;

        final int startingY = Math.max(0, playerPosition.y() - range);
        final int endingY = Math.min(mapSize, playerPosition.y() + range + 1);
        final int startingX = Math.max(0, playerPosition.x() - range);
        final int endingX = Math.min(mapSize, playerPosition.x() + range + 1);

        this.gridPanel.removeAll();

        this.gridPanel = new JPanel(new GridLayout(endingY - startingY, endingX - startingX));

        this.gamePanel.add(this.gridPanel, BorderLayout.CENTER);


        for (int i = startingY; i < endingY; i++) {
            for (int j = startingX; j < endingX; j++) {
                placeCell(new Position(j, i));
            }
        }

        this.gridPanel.revalidate();
        this.gridPanel.repaint();
    }

    /**
     * Places a cell in the grid based on the cell type and content.
     *
     * @param position The position of the cell.
     */
    private void placeCell(final Position position) {
        final JLabel label;

        if (position.equals(gameController.getCharacterController().getPlayerPosition())) {
            label = playerView;
        } else if (gameController.getMapController().isEnemyInCell(position)) {
            label = createEnemyLabel(position);
        } else {
            label = new JLabel();
        }

        configureLabel(label, position);
        this.gridPanel.add(label);
    }

    private JLabel createEnemyLabel(final Position position) {
        final List<String> characterImagePath = gameController.getCharacterController()
                .getImagePathOfCharacter(ConstantsAndResourceLoader.ENEMY_PATH,
                        gameController.getMapController().getNameOfCharacterInPosition(position)
                                + "/"
                                + gameController.getMapController().getNameOfCharacterInPosition(position));
        final JLabel enemyLabel = new CharacterView(characterImagePath);
        ((CharacterView) enemyLabel).changeImage(gameController.getMapController()
                        .getLastDirectionOfCharacterInPosition(position),
                gameController.getCharacterController().getIfCharacterInPositionIsMoving(position));

        return enemyLabel;
    }

    private void configureLabel(final JLabel label, final Position position) {
        label.setOpaque(true);
        final CellType cellType = gameController.getMapController().getCellType(position);
        final Color backgroundColor = getBackgroundColor(cellType);
        label.setBackground(backgroundColor);
        label.setBorder(new LineBorder(Color.BLACK));
    }

    private Color getBackgroundColor(final CellType cellType) {
        switch (cellType) {
            case VOLCANO -> {
                return Color.ORANGE;
            }
            case PLAINS -> {
                return Color.YELLOW;
            }
            case FOREST -> {
                return Color.GREEN;
            }
            case STORM -> {
                return Color.DARK_GRAY;
            }
            case SWAMP -> {
                return Color.BLUE;
            }
            default -> {
                return Color.WHITE;
            }
        }
    }
}
