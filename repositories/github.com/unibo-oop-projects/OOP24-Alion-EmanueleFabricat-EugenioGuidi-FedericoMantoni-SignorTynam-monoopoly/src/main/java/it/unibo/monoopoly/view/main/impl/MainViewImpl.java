package it.unibo.monoopoly.view.main.impl;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import org.apache.commons.lang3.tuple.Triple;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.unibo.monoopoly.controller.main.api.MainController;
import it.unibo.monoopoly.controller.state.api.ControllerState;
import it.unibo.monoopoly.view.main.api.MainView;
import it.unibo.monoopoly.view.panel.game.GamePanel;
import it.unibo.monoopoly.view.state.api.ViewState;
import it.unibo.monoopoly.view.state.impl.ViewPrisonState;

/**
 * Represents the main view component of the application when game starts.
 */
public class MainViewImpl extends AbstractView implements MainView {

    private static final int RESIZE_TEXT = 10;
    private static final int RESIZE_PANEL = 2;
    private final GamePanel gamePanel;
    private final MainController controller;
    private ViewState viewState;
    private final List<Color> colors;
    private final Map<String, Color> players;
    private final List<String> nameCells;

    /**
     * Initialize the {@link JFrame} and all the informations needed to show
     * correctly the state of the game to the user.
     * 
     * @param controller  the main controller of the application
     * @param namePlayers the names of the players
     * @param nameCells   the list of names of the cells
     */
    public MainViewImpl(
            final MainController controller,
            final List<String> namePlayers,
            final List<String> nameCells) {
        super();
        this.controller = controller;

        final Dimension screenDimension = Toolkit.getDefaultToolkit().getScreenSize();
        mainFrame().setSize(screenDimension);
        mainFrame().setResizable(false);
        mainFrame().setUndecorated(true);

        this.colors = super.getColors();
        this.players = IntStream.range(0, namePlayers.size()).boxed()
                .collect(Collectors.toMap(namePlayers::get, colors::get));
        this.nameCells = new ArrayList<>(nameCells);
        this.gamePanel = new GamePanel(mainFrame().getHeight(), mainFrame().getWidth(), "1",
                initPlayerView(namePlayers), this.players, this.colors);
        this.viewState = new ViewPrisonState(this);
        this.mainFrame().addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(final KeyEvent k) {
                if (k.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    showConfirmToExit();
                }
            }
        });
    }

    private void showConfirmToExit() {
        if (JOptionPane.showConfirmDialog(this.getMainFrame(), "Siete sicuri di voler chiudere l'applicazione?",
                "Chiudere applicazione", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }

    private List<Triple<String, Integer, Color>> initPlayerView(final List<String> namePlayers) {
        final List<Triple<String, Integer, Color>> l = new LinkedList<>();
        for (final var name : namePlayers) {
            l.add(Triple.of(name, 0, players.get(name)));
        }
        return l;
    }

    private JFrame mainFrame() {
        return this.getMainFrame();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @SuppressFBWarnings(value = "EI_EXPOSE_REP", justification = "Suppressing according to pattern State")
    public JPanel getMainPanel() {
        return this.gamePanel;
    }

    /**
     * Return the actual {@link ViewState}.
     * 
     * @return the actual {@link ViewState}.
     */
    @Override
    @SuppressFBWarnings(value = "EI_EXPOSE_REP", justification = "Suppressing according to pattern State")
    public ViewState getViewState() {
        return this.viewState;
    }

    /**
     *
     * {@inheritDoc}
     */
    @Override
    public ControllerState getControllerState() {
        return this.controller.getControllerState();
    }

    /**
     *
     * {@inheritDoc}
     */
    @Override
    public List<String> getNameCells() {
        return List.copyOf(this.nameCells);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setInteractivePanel(final JPanel panel) {
        this.gamePanel.setInteractivePanel(panel);
    }

    /**
     * {@inheritDoc}
     */
    @SuppressFBWarnings(value = "EI_EXPOSE_REP", justification = "Suppressing according to pattern State")
    @Override
    public void setState(final ViewState state) {
        Objects.requireNonNull(state);
        this.viewState = state;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update() {
        this.gamePanel.update(this.controller.getViewUpdateData());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void endGame(final String player) {
        this.getMainFrame().dispose();
        final JDialog closeWindow = new JDialog();
        closeWindow.setModal(true);
        final JTextArea winnerText = new JTextArea("BRAVO, " + player + " HAI VINTO IL GIOCO");
        winnerText.setEditable(false);
        winnerText.setFont(
                new Font("Arial", Font.PLAIN, Toolkit.getDefaultToolkit().getScreenSize().height / RESIZE_TEXT));
        winnerText.setLineWrap(true);
        winnerText.setWrapStyleWord(true);
        closeWindow.add(winnerText);
        closeWindow.setSize(Toolkit.getDefaultToolkit().getScreenSize().width / RESIZE_PANEL,
                Toolkit.getDefaultToolkit().getScreenSize().height / RESIZE_PANEL);
        closeWindow.setLocationRelativeTo(null);
        closeWindow.setVisible(true);
        closeWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        for (final Window window : Window.getWindows()) {
            window.dispose();
        }
    }
}
