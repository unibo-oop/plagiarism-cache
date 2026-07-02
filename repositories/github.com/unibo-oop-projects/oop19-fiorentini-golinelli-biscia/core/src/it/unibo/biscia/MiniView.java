package it.unibo.biscia;

import it.unibo.biscia.core.Cell;
import it.unibo.biscia.core.ControllerImpl;
import it.unibo.biscia.core.Direction;
import it.unibo.biscia.core.Entity;
import it.unibo.biscia.core.EntityType;
import it.unibo.biscia.core.Level;
import it.unibo.biscia.core.Player;
import it.unibo.biscia.events.ActionObserver;
import it.unibo.biscia.events.ActionSubject;
import it.unibo.biscia.events.GenericEventSubject;
import it.unibo.biscia.events.GenericEventSubjectImpl;
import it.unibo.biscia.events.StateObserver;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

/**
 * comment.
 *
 */
public final class MiniView extends JFrame implements ActionSubject, StateObserver {
    /**
    * 
    */
    private static final long serialVersionUID = 1L;
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    private final GenericEventSubject<ActionObserver> ges = new GenericEventSubjectImpl<>();

    private final GamePanel gamePanel;

    // private JPanel menuPanel;
    // private JPanel commandsPanel;
    // private JPanel infoPanel;
    private List<Player> players;
    // private Level level;
    private final JLabel lblstato = new JLabel("");
    private final JLabel lblinfo1 = new JLabel("");
    private final JLabel lblinfo2 = new JLabel("");
    private final JLabel lblcommand = new JLabel("");

    /**
     * entry point of application.
     * 
     * @param args command line arguments
     */
    public static void main(final String... args) {
        final int velocity = 50;
        final var names = Arrays.asList("Vipera", "Biscia"); // , "topo", "Arrivo", "Zazzera", "0123", "Bisciante");
        // System.out.println(f.getCanonicalPath());
        final it.unibo.biscia.core.Controller c = new ControllerImpl(names, velocity, false);
        final MiniView miniView = new MiniView();
        c.attachStateObserver(miniView);
        miniView.attachActionObserver(c);
        miniView.players = c.getPlayers();
        System.out.println(names);
        System.out.println(c.getPlayers());
        c.start();

    }

    private static class Elemento {
        private final it.unibo.biscia.core.EntityType tipo;
        private final List<Casella> caselle;

        Elemento(final it.unibo.biscia.core.Entity entity) {
            this.tipo = entity.getType();
            this.caselle = new ArrayList<>(entity.getCells().size());
            for (int i = 0; i < entity.getCells().size(); i++) {
                this.caselle.add(new Casella(entity.getCells().get(i)));
            }
        }

    }

    private static class Casella {
        private final int col;
        private final int row;

        Casella(final int col, final int row) {
            this.col = col;
            this.row = row;
        }

        Casella(final Cell cell) {
            this(cell.getCol(), cell.getRow());
        }
    }

    private static class GamePanel extends JPanel {
        /**
         * 
         */
        private static final long serialVersionUID = 1L;
        private int cols;
        private int rows;
        private final Map<Entity, Elemento> elementi = new HashMap<>();

        GamePanel() {
            super();
        }

        private void setBoard(final Level board) {
            this.elementi.clear();
            this.cols = board.getCols();
            this.rows = board.getRows();
            this.addElements(board.getEntities());

        }

        private void addElements(final List<Entity> entities) {
            for (final var e : entities) {
                // System.out.println("addElements " + e);
                this.elementi.put(e, new Elemento(e));
            }
            this.paintBoard();
        }

        private void removeElements(final List<Entity> entities) {
            for (final var e : entities) {
                // System.out.println("removeElements " + e);
                this.elementi.remove(e);
            }
            this.paintBoard();
        }

        private void updateElements(final List<Entity> entities) {
            for (final var e : entities) {
                // System.out.println("updateElements " + e);
                this.elementi.remove(e);
                this.elementi.put(e, new Elemento(e));
            }
            this.paintBoard();
        }

        private void paintBoard() {
            // System.out.println("paintboard" + board);
            this.repaint();
        }

        @Override
        public void paint(final Graphics g) {
            g.clearRect(0, 0, this.getWidth(), this.getHeight());
            if (this.cols == 0 || this.rows == 0) {
                return;
            }
            final int cellSize = Math.max(1, Math.min(this.getWidth() / this.cols, this.getHeight() / this.rows));

            /*
             * System.out.println("paint cellSize:" + cellSize + " (" + this.getWidth() +
             * "x" + this.getHeight() +")");
             */
            final int left = (this.getWidth() - this.cols * cellSize) / 2;
            final int top = (this.getHeight() - this.rows * cellSize) / 2;
            // g.clearRect(0, 0, cols*cellSize+2*cellSize, rows*cellSize+2*cellSize);
            g.setColor(this.getBackground());
            g.clearRect(0, 0, this.getWidth(), this.getHeight());
            g.fillRect(0, 0, this.getWidth(), this.getHeight());
            g.setColor(Color.lightGray);
            g.fillRect(left, top, this.getWidth() - left * 2, this.getHeight() - top * 2);
            for (final var c : this.elementi.values()) {
                this.paintBoardElement(c, g, left, top, cellSize);
            }

        }

        private void paintBoardElement(final Elemento boardElement, final Graphics g, final int x, final int y,
                final int cellSize) {
            switch (boardElement.tipo) {
            case SNAKE:
                g.setColor(Color.yellow);
                break;
            case FOOD:
                g.setColor(Color.green);
                break;
            case WALL:
                g.setColor(Color.red);
                break;
            default:
                break;
            }
            boardElement.caselle.forEach(c -> {
                if (boardElement.tipo != EntityType.WALL) {
                    g.fillOval(x + c.col * cellSize, y + c.row * cellSize, cellSize, cellSize);
                } else {
                    g.fillRect(x + c.col * cellSize, y + c.row * cellSize, cellSize, cellSize);
                }
            });
        }

    }

    @Override
    public void attachActionObserver(final ActionObserver observer) {
        ges.attach(observer);
    }

    @Override
    public void detachActionObserver(final ActionObserver observer) {
        ges.detach(observer);
    }

    @Override
    public void gameOver() {
        players.forEach(p -> this.updatePlayer(p));
        JOptionPane.showMessageDialog(null, "gameover");
        System.exit(0);
    }

    @Override
    public void gamePause() {
        lblcommand.setText("gamePause");

        // MiniView.this.gamePanel.paintBoard();
    }

    @Override
    public void gameResume() {
        lblcommand.setText("gameResume");
        // JOptionPane.showMessageDialog(null, "resume");

    }

    @Override
    public void newLevel(final Level level) {
        // System.out.println("new level " + level);
        if (gamePanel.cols > 0) {
            JOptionPane.showMessageDialog(null, "End of level");
        }
        gamePanel.setBoard(level);
        lblstato.setText("Livello " + level.getCardinal() + " " + level.getEntities().size() + " elementi");
    }

    @Override
    public void update(final List<Entity> entities) {
        // System.out.println("update");
        gamePanel.updateElements(entities);

    }

    @Override
    public void remove(final List<Entity> entities) {
        // System.out.println("remove");
        gamePanel.removeElements(entities);
    }

    @Override
    public void add(final List<Entity> entities) {
        // System.out.println("add");
        gamePanel.addElements(entities);
    }

    @Override
    public void updatePlayer(final Player player) {
        if (players.contains(player)) {
            JLabel label;
            if (players.indexOf(player) == 0) {
                label = lblinfo1;
            } else {
                label = lblinfo2;
            }
            label.setText(player.toString());
        }

    }

    /**
     * small implementation of view.
     */
    public MiniView() {
        final JPanel statePanel;
        this.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        this.setSize(WIDTH, HEIGHT);
        this.getContentPane().setLayout(new BorderLayout());
        this.gamePanel = new MiniView.GamePanel();
        statePanel = new JPanel();
        // this.menuPanel = new JPanel();
        // this.commandsPanel = new JPanel();
        // this.infoPanel = new JPanel();
        this.getContentPane().add(BorderLayout.CENTER, gamePanel);
        this.getContentPane().add(BorderLayout.NORTH, statePanel);
        // this.getContentPane().add(BorderLayout.WEST, this.menuPanel);
        // this.getContentPane().add(BorderLayout.EAST, this.commandsPanel);
        // this.getContentPane().add(BorderLayout.SOUTH, this.infoPanel);

        statePanel.add(lblstato);
        // this.menuPanel.add(new JLabel("menu"));
        statePanel.add(lblcommand);

        statePanel.add(lblinfo1);
        statePanel.add(lblinfo2);
        // this.setUndecorated(true);
        this.addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(final KeyEvent e) {
                // nothing
            }

            @Override
            public void keyReleased(final KeyEvent e) {
                // nothing
            }

            @Override
            public void keyPressed(final KeyEvent e) {
                // System.out.println(e.getKeyCode() + "=" + String.valueOf(e.getKeyChar()));
                Direction dir;
                int player = 0;
                switch (e.getKeyCode()) {
                case KeyEvent.VK_LEFT /* 37 */:
                    dir = Direction.LEFT;
                    player = 0;
                    break;
                case KeyEvent.VK_RIGHT /* 39 */:
                    dir = Direction.RIGHT;
                    player = 0;
                    break;
                case KeyEvent.VK_DOWN /* 40 */:
                    dir = Direction.DOWN;
                    player = 0;
                    break;
                case KeyEvent.VK_UP /* 38 */:
                    dir = Direction.UP;
                    player = 0;
                    break;
                case KeyEvent.VK_SPACE:
                    SwingUtilities.invokeLater(() -> MiniView.this.ges.notify(o -> o.pauseAndResume()));
                    return;
                case KeyEvent.VK_ESCAPE:
                    SwingUtilities.invokeLater(() -> System.exit(0));
                    return;
                case KeyEvent.VK_W:
                    dir = Direction.UP;
                    player = 1;
                    break;
                case KeyEvent.VK_A:
                    dir = Direction.LEFT;
                    player = 1;
                    break;
                case KeyEvent.VK_D:
                    dir = Direction.RIGHT;
                    player = 1;
                    break;
                case KeyEvent.VK_S:
                    dir = Direction.DOWN;
                    player = 1;
                    break;
                default:
                    return;
                }
                if (MiniView.this.players.size() <= player) {
                    System.out.println("no player presents");
                } else {
                    final int index = player;
                    SwingUtilities.invokeLater(
                            () -> MiniView.this.ges.notify(o -> o.move(MiniView.this.players.get(index), dir)));
                }

            }
        });
        // this.pack();
        this.setVisible(true);
    }
}
