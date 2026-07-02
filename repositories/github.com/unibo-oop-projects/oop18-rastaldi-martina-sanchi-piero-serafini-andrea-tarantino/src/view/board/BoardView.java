package view.board;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import controller.Controller;
import controller.ControllerImpl;
import controller.image.BoardImageManager;
import controller.image.BoardImageManagerInterface;
import javafx.util.Pair;
import model.elements.Element;
import utilities.Colors;
import utilities.Status;
import view.board.sideview.GameView;

/**
 * A class implementing the view for the game of Minotaurus
 *
 * Andrea Serafini.
 *
 */
public final class BoardView implements BoardViewInterface {

    private static final Dimension PANEL_MINIMUM_DIMENSION = new Dimension(540, 540);

    private static final Dimension FRAME_MINIMUM_DIMENSION = new Dimension(920, 650);

    private final JFrame frame = new JFrame(); // outer frame

    private final JPanel boardContainer = new JPanel(); // panel containing the grid panel

    private final JPanel background = new JPanel() { // panel containing the two main panel providing a wood background

        /**
         *
         */
        private static final long serialVersionUID = -2799287503667927752L;

        @Override
        public void paintComponent(final Graphics g) {
            g.drawImage(BoardView.this.iconManager.getBackground().getImage(), 0, 0, this.getSize().width,
                    this.getSize().height, this);
        }
    };
    private final JPanel board = new GridPanel(); // the grid of the board

    private final JButton[][] grid; // grid of buttons
    private final GameView sidePanel; // the side panel

    private final BoardImageManagerInterface iconManager;
    private final Controller controller;

    /**
     * A class extending a JPanel, implementing an always square one.
     *
     * Andrea Serafini.
     *
     */
    class GridPanel extends JPanel {

        /**
         *
         */
        private static final long serialVersionUID = 5078981737568253127L;

        @Override
        public Dimension getPreferredSize() {
            Dimension d = super.getPreferredSize();
            final Container c = this.getParent();
            if (c != null) {
                d = c.getSize();
            } else {
                return new Dimension(10, 10);
            }
            final int w = (int) d.getWidth();
            final int h = (int) d.getHeight();
            final int s = (w < h ? w : h);
            return new Dimension(s, s);
        }
    }

    /**
     * Constructor.
     * @param width
     *            the number of columns
     * @param length
     *            the number of rows
     */
    public BoardView(final int width, final int length) {

        this.controller = ControllerImpl.getLog();

        this.sidePanel = new GameView();
        this.iconManager = new BoardImageManager();

        this.frame.setTitle("MINOTAURUS");
        this.frame.setIconImage(this.iconManager.getIcon().getImage());
        this.frame.setLayout(new BorderLayout());
        this.frame.setMinimumSize(FRAME_MINIMUM_DIMENSION);
        this.frame.add(this.background, BorderLayout.CENTER);

        this.background.setLayout(new BorderLayout());
        this.background.add(this.boardContainer, BorderLayout.CENTER);
        this.background.add(this.sidePanel, BorderLayout.EAST);
        this.background.setOpaque(false);


        this.boardContainer.setLayout(new GridBagLayout());
        this.boardContainer.add(this.board);
        this.boardContainer.setOpaque(false);

        this.board.setMinimumSize(PANEL_MINIMUM_DIMENSION);
        this.board.setOpaque(false);
        this.board.setLayout(new GridLayout(width, length));

        this.grid = new JButton[width][length]; // allocate the size of grid
        for (int y = 0; y < length; y++) {
            for (int x = 0; x < width; x++) {

                final JButton butt = new JButton();
                butt.setEnabled(false);
                butt.setBackground(Colors.Cyan.getSwingPath());
                butt.setMargin(new Insets(0, 0, 0, 0));
                butt.addActionListener(l -> {
                    for (int row = 0; row < this.grid.length; row++) {
                        for (int col = 0; col < this.grid[row].length; col++) {
                            if (this.grid[row][col] == (JButton) l.getSource()) {
                                this.controller.select(new Pair<>(row, col));
                            }
                        }
                    }
                });
                this.grid[x][y] = butt; // creates new button
                this.board.add(this.grid[x][y]); // adds button to grid
            }
        }

        this.frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(final WindowEvent we) {
                final int result = JOptionPane.showConfirmDialog(BoardView.this.frame, "Vuoi salvare la partita?",
                        "MINOTAURUS ", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE,
                        BoardView.this.iconManager.getMinotaurus());
                if (result == JOptionPane.YES_OPTION) {
                    ControllerImpl.getLog().saveGame();
                    BoardView.this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                } else if (result == JOptionPane.NO_OPTION) {
                    BoardView.this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                } else {
                    BoardView.this.frame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
                }
            }
        });

        this.frame.setLocationRelativeTo(null);
        this.frame.pack(); // sets appropriate size for frame

        this.iconManager.resize(this.grid[0][0].getWidth(), this.grid[0][0].getHeight()); // resizing of board icons
        this.grid[0][0].addComponentListener(new ComponentListener() {
            @Override
            public void componentHidden(final ComponentEvent e) {
            }

            @Override
            public void componentMoved(final ComponentEvent e) {
            }

            @Override
            public void componentResized(final ComponentEvent e) {
                BoardView.this.iconManager.resize(e.getComponent().getWidth(), e.getComponent().getHeight());
                BoardView.this.controller.updateElements();

            }

            @Override
            public void componentShown(final ComponentEvent e) {
            }
        });

        this.controller.startKeyboardListener();
    }

    @Override
    public void close() {
        this.frame.setVisible(false); // makes frame invisible
        this.frame.dispose();
    }

    private void disableBoard() {
        for (final JButton[] element : this.grid) {
            for (final JButton element2 : element) {
                element2.setEnabled(false);
            }
        }
    }

    @Override
    public void drawBackground(final Pair<Integer, Integer> position, final Pair<Status, Colors> status) {
        if (position != null) {
            final int x = position.getKey();
            final int y = position.getValue();
            this.grid[x][y].setEnabled(false);
            this.grid[x][y].setText(null);
            this.grid[x][y].setIcon(null);
            this.grid[x][y].setBackground(status.getValue().getSwingPath());
        }
    }

    @Override
    public void drawElement(final Pair<Integer, Integer> position, final Element element) {
        final int x = position.getKey();
        final int y = position.getValue();
        // this.grid[x][y].setOpaque(true);
        this.grid[x][y].setBackground(element.getColor().getSwingPath());
        switch (element.getType()) {
        case EROE:
            this.grid[x][y].setIcon(this.iconManager.getHero());
            this.grid[x][y].setDisabledIcon(this.iconManager.getHero());
            break;

        case MINOTAURO:
            this.grid[x][y].setIcon(this.iconManager.getMinotaurus());
            this.grid[x][y].setDisabledIcon(this.iconManager.getMinotaurus());
            break;
        default:
            break;
        }
    }

    private void enableSelection(final Pair<Integer, Integer> coord) {
        final int x = coord.getKey();
        final int y = coord.getValue();
        this.grid[x][y].setEnabled(true);
    }

    @Override
    public JButton[][] getGrid() {
        return this.grid.clone();
    }

    @Override
    public GameView getSidePanel() {
        return this.sidePanel;
    }

    @Override
    public void selectedIcon(final Pair<Integer, Integer> position) {
        final int x = position.getKey();
        final int y = position.getValue();

        this.grid[x][y].setIcon(this.iconManager.getSelectedHero());
        this.grid[x][y].setDisabledIcon(this.iconManager.getSelectedHero());
    }

    @Override
    public void show() {
        this.frame.setVisible(true); // makes frame visible
    }

    @Override
    public void waitDice() {
        this.disableBoard();
        this.sidePanel.waitDice();
    }

    @Override
    public void waitMove() {
        this.disableBoard();
        this.sidePanel.waitMove();
    }

    @Override
    public void waitSelect(final Pair<Integer, Integer> coord) {
        this.enableSelection(coord);
        this.sidePanel.waitSelect();
    }
}
