package it.unibo.plantsfarm.view;

import it.unibo.plantsfarm.view.gamepanel.ImplViewGamePanel;
import it.unibo.plantsfarm.view.menu.MenuPanel;
import it.unibo.plantsfarm.view.music.api.MusicPlayer;
import it.unibo.plantsfarm.view.music.impl.MusicPlayerImpl;
import it.unibo.plantsfarm.view.utility.Texture;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

/**
 * Manages the main screen window.
 */
public final class MainScreen {

    private static final String TITLE = "PlantsFarm";
    private static final String FONT_FAMILY = "SansSerif";

    private static final double FONT_SCALE_RATIO = 0.04;
    private static final double GAP_RATIO = 0.015;
    private static final int COIN_LABEL_RATIO = 5;

    private static final Color GOLD = new Color(255, 252, 115);

    private final MenuPanel menuPanel;
    private ImplViewGamePanel gameViewPanel;
    private JFrame frame;
    private JLabel coinLabel;
    private final MusicPlayer musicPlayer;

    /**
     * Initializes the main screen components.
     */
    public MainScreen() {
        this.menuPanel = new MenuPanel();
        this.menuPanel.setFocusable(false);
        this.musicPlayer = new MusicPlayerImpl();
    }

    /**
     * Creates and displays the main screen window.
     *
     * @param gameView The view component for the game panel to be displayed in the main screen.
     */
    public void createMainScreen(final ImplViewGamePanel gameView) {
        this.frame = new JFrame(TITLE);
        setGameView(gameView);
        this.frame.setLayout(new BorderLayout());
        this.frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.frame.setSize(screenSize.width, screenSize.height);
        this.frame.setLocationRelativeTo(null);
        this.frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.frame.setResizable(false);
        this.frame.setUndecorated(true);

        this.frame.add(this.menuPanel, BorderLayout.EAST);

        final JLayeredPane layeredPane = new JLayeredPane();

        this.gameViewPanel.setBounds(0, 0, screenSize.width, screenSize.height);
        final int hGap = (int) (screenSize.width * GAP_RATIO);
        final int vGap = (int) (screenSize.height * GAP_RATIO);
        final int fontSize = (int) (screenSize.height * FONT_SCALE_RATIO);

        layeredPane.add(this.gameViewPanel, JLayeredPane.DEFAULT_LAYER);

        final JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, hGap, vGap));
        topPanel.setOpaque(false);
        topPanel.setFocusable(false);
        topPanel.setBounds(0, 0, screenSize.width, screenSize.height / COIN_LABEL_RATIO);

        this.coinLabel = new JLabel(" 0 ");
        this.coinLabel.setIcon(Texture.COIN_ICON);
        this.coinLabel.setFont(new Font(FONT_FAMILY, Font.BOLD, fontSize));
        this.coinLabel.setForeground(GOLD);
        topPanel.add(this.coinLabel);

        layeredPane.add(topPanel, JLayeredPane.PALETTE_LAYER);

        this.frame.add(layeredPane, BorderLayout.CENTER);

        this.gameViewPanel.setFocusable(true);
        this.gameViewPanel.requestFocusInWindow();

        this.frame.setVisible(true);

        this.musicPlayer.playLoop("music/GardenMusic.wav");
    }

    /**
     * Updates the coin label text.
     *
     * @param amount The current amount of coins.
     */
    public void updateCoinLabel(final int amount) {
        if (this.coinLabel != null) {
            this.coinLabel.setText(" " + amount);
        }
    }

    /**
     * Allows the controller to attach an action to the Exit button.
     *
     * @param listener The action to perform.
     */
    public void attachExitListener(final ActionListener listener) {
        this.menuPanel.addExitListener(e -> {
            listener.actionPerformed(e);
            this.gameViewPanel.requestFocusInWindow();
        });
    }

    /**
     * Allows the controller to attach an action to the Encyclopedia button.
     *
     * @param listener The action to perform.
     */
    public void attachEncyclopediaListener(final ActionListener listener) {
        this.menuPanel.addEncyclopediaListener(e -> {
            listener.actionPerformed(e);
            this.gameViewPanel.requestFocusInWindow();
        });
    }

    /**
     * Allows the controller to attach an action to the Storage button.
     *
     * @param listener The action to perform.
     */
    public void attachStorageListener(final ActionListener listener) {
        this.menuPanel.addStorageListener(e -> {
            listener.actionPerformed(e);
            this.gameViewPanel.requestFocusInWindow();
        });
    }

    /**
     * Allows the controller to attach an action to the Shop button.
     *
     * @param listener The action to perform.
     */
    public void attachShopListener(final ActionListener listener) {
        this.menuPanel.addShopListener(e -> {
            listener.actionPerformed(e);
            this.gameViewPanel.requestFocusInWindow();
        });
    }

    /**
     * Closes the main window.
     */
    public void close() {
        if (this.frame != null) {
            this.frame.dispose();
        }
        this.musicPlayer.stop();
    }

    private void setGameView(final ImplViewGamePanel givenGameView) {
        this.gameViewPanel = givenGameView;
    }
}
