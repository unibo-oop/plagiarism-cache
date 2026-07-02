package utilities.test.demo;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagLayout;
import java.awt.GridLayout; //imports GridLayout library
import java.awt.Insets;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

import javax.swing.JButton; //imports JButton library
import javax.swing.JFrame; //imports JFrame library
import javax.swing.JPanel;

import controller.Controller;
import controller.ControllerImpl;
import controller.image.BoardImageManager;
import controller.image.BoardImageManagerInterface;
import javafx.util.Pair;
import model.elements.Element;
import utilities.Colors;
import utilities.Status;
import view.board.BoardViewInterface;
import view.board.sideview.GameView;

/**
 * A class implementing the demo view for the game of Minotaurus
 * Andrea Serafini.
 *
 */
public final class DemoBoardView implements BoardViewInterface {

    private static final Dimension PANEL_MINIMUM_DIMENSION = new Dimension(540, 540);

    private static final Dimension FRAME_MINIMUM_DIMENSION = new Dimension(920, 650);

    private final JFrame frame = new JFrame(); // creates frame
    private final JButton[][] grid; // names the grid of buttons
    private final JPanel boardContainer = new JPanel();
    private final JPanel background = new JPanel() {
        /**
         *
         */
        private static final long serialVersionUID = 1L;

        @Override
        public void paintComponent(final Graphics g) {
            g.drawImage(DemoBoardView.this.iconManager.getBackground().getImage(), 0, 0, this.getSize().width,
                    this.getSize().height, this);
        }
    };
    private final JPanel board = new GridPanel();

    private final GameView sidePanel;

    private final BoardImageManagerInterface iconManager = new BoardImageManager();
    private final Controller controller = ControllerImpl.getLog();

    class GridPanel extends JPanel {

        /**
         *
         */
        private static final long serialVersionUID = 1L;

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
    public DemoBoardView(final int width, final int length) {

        this.board.setOpaque(false);

        this.sidePanel = new GameView();

        this.controller.startDemoKeyboardListener();
        this.frame.setLayout(new BorderLayout());

        this.frame.add(this.background);
        this.background.setLayout(new BorderLayout());
        this.boardContainer.setOpaque(false);
        this.background.setOpaque(false);

        this.frame.setMinimumSize(FRAME_MINIMUM_DIMENSION);
        this.board.setMinimumSize(PANEL_MINIMUM_DIMENSION);
        this.boardContainer.setLayout(new GridBagLayout());
        this.boardContainer.add(this.board);
        this.background.add(this.boardContainer, BorderLayout.CENTER);
        this.background.add(this.sidePanel, BorderLayout.EAST);

        // this.frame.add(panel);
        // this.frame.add(sidePanel);

        this.board.setLayout(new GridLayout(width, length, 0, 0)); // set layout
        this.grid = new JButton[width][length]; // allocate the size of grid
        for (int y = 0; y < length; y++) {
            for (int x = 0; x < width; x++) {

                final JButton butt = new JButton();
                butt.setEnabled(false);
                butt.setBackground(Colors.Cyan.getSwingPath());
                // butt.setPreferredSize(BUTTON_PREFERRED_DIMENSION);
                butt.setMargin(new Insets(0, 0, 0, 0));
                butt.addActionListener(l -> {
                    for (int row = 0; row < this.grid.length; row++) {
                        for (int col = 0; col < this.grid[row].length; col++) {
                            if (this.grid[row][col] == (JButton) l.getSource()) {
                                this.controller.select(new Pair<>(row, col));
                                // ControllerImpl.updatePlayerString();
                            }
                        }
                    }
                });
                this.grid[x][y] = butt; // creates new button
                this.board.add(this.grid[x][y]); // adds button to grid
            }
        }

        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // this.frame.addWindowListener(new WindowAdapter() {
        // @Override
        // public void windowClosing(final WindowEvent we) {
        // final int result = JOptionPane.showConfirmDialog(DemoBoardView.this.frame,
        // "Vuoi salvare la partita?",
        // "MINOTAURUS ", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE,
        // DemoBoardView.this.iconManager.getMinotaurus());
        // if (result == JOptionPane.YES_OPTION) {
        // ControllerImpl.getLog().saveGame();
        // DemoBoardView.this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // } else if (result == JOptionPane.NO_OPTION) {
        // DemoBoardView.this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // } else {
        // DemoBoardView.this.frame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        // }
        // }
        // });

        this.frame.pack();
        this.grid[0][0].addComponentListener(new ComponentListener() {
            @Override
            public void componentHidden(final ComponentEvent e) {
            }

            @Override
            public void componentMoved(final ComponentEvent e) {
            }

            @Override
            public void componentResized(final ComponentEvent e) {

                DemoBoardView.this.iconManager.resize(e.getComponent().getWidth(), e.getComponent().getHeight());
                DemoBoardView.this.controller.updateElements();
            }

            @Override
            public void componentShown(final ComponentEvent e) {
            }
        });

        this.iconManager.resize(this.grid[0][0].getWidth(), this.grid[0][0].getHeight());
        this.frame.setLocationRelativeTo(null);
        this.frame.setIconImage(this.iconManager.getIcon().getImage());
        this.frame.setTitle("MINOTAURUS");
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
        this.frame.setVisible(true);
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
