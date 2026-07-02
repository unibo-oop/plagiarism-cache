package it.unibo.the100dayswar.view.gameview;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;


import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

import it.unibo.the100dayswar.view.joystick.JoystickView;
import it.unibo.the100dayswar.view.map.MapView;
import it.unibo.the100dayswar.view.statistics.StatisticsView;

/**
 * Class that represents the main game view, displaying the map,
 * statistics, and joystick.
 */
public class GameView extends JFrame {

    private static final long serialVersionUID = 1L;
    private static final int FRAME_WIDTH = 1200;
    private static final int FRAME_HEIGHT = 900;
    private static final double MAP_WEIGHT_X = 0.6;
    private static final double SIDE_PANEL_WEIGHT_X = 0.4;
    private static final int MAP_HEIGHT = 850;
    private static final double STATISTICS_WEIGHT_Y = 0.3;
    private static final int TOP_BOTTOM_PADDING = 5;
    private static final double JOYSTICK_WEIGHT_Y = 0.7;
    private static final int SIDE_PADDING = 10;
    private static final Logger LOGGER = Logger.getLogger(GameView.class.getName());

    /**
     * Constructor for the GameView class.
     */
    public GameView() {
        super("Game View");
    }

    /**
     * Initializes the frame, setting up the UI and final configuration.
     */
    public final void initialize() {
        setUI();
        setPostInitialize();
    }

    /**
     * Configures the frame settings after the UI is set up.
     */
    private void setPostInitialize() {
        this.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    /**
     * Sets up the main user interface, including the background and layout.
     */
    private void setUI() {
        final JPanel backgroundPanel = new JPanel() {
            private static final long serialVersionUID = 1L;
            private final Image backgroundImage = loadBackgroundImage();

            @Override
            protected void paintComponent(final Graphics g) {
                super.paintComponent(g);
                if (backgroundImage != null) {
                    g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
                }
            }
        };

        backgroundPanel.setLayout(new GridBagLayout());
        this.setContentPane(backgroundPanel);

        final MapView mapView = new MapView();
        final StatisticsView statisticsView = new StatisticsView();
        statisticsView.initialize();
        final JoystickView joystickView = new JoystickView(mapView, statisticsView);

        mapView.setOpaque(false);
        statisticsView.setOpaque(false);
        joystickView.setOpaque(false);

        mapView.setPreferredSize(new Dimension(FRAME_WIDTH, MAP_HEIGHT));

        final GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridheight = 2;
        gbc.weightx = MAP_WEIGHT_X;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.NONE;
        gbc.insets = new Insets(SIDE_PADDING, SIDE_PADDING, SIDE_PADDING, SIDE_PADDING);
        gbc.anchor = GridBagConstraints.CENTER;
        backgroundPanel.add(mapView, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridheight = 1;
        gbc.weightx = SIDE_PANEL_WEIGHT_X;
        gbc.weighty = STATISTICS_WEIGHT_Y;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(SIDE_PADDING, SIDE_PADDING, TOP_BOTTOM_PADDING, SIDE_PADDING);
        gbc.anchor = GridBagConstraints.NORTH;
        backgroundPanel.add(statisticsView, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.weighty = JOYSTICK_WEIGHT_Y;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(TOP_BOTTOM_PADDING, SIDE_PADDING, SIDE_PADDING, SIDE_PADDING);
        gbc.anchor = GridBagConstraints.SOUTH;
        backgroundPanel.add(joystickView, gbc);

        backgroundPanel.revalidate();
        backgroundPanel.repaint();
    }

    /**
     * Loads the background image from the specified path.
     *
     * @return the loaded image, or null if loading fails.
     */
    private Image loadBackgroundImage() {
        try {
            final String path = "/gameview/background.jpg";
            final URL imageUrl = GameView.class.getResource(path);
            if (imageUrl == null) {
                LOGGER.log(Level.WARNING, "Background image not found at path: " + path);
                return null;
            }
            return ImageIO.read(imageUrl);
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Error loading background image", e);
            return null;
        }
    }
}
