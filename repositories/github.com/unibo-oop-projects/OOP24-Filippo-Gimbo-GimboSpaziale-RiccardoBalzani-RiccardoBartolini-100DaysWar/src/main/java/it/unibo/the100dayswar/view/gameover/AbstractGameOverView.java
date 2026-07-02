package it.unibo.the100dayswar.view.gameover;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Window;
import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

import it.unibo.the100dayswar.application.The100DaysWar;
import it.unibo.the100dayswar.commons.utilities.impl.LoadPixelFont;

/**
 * Abstract class that represents the game over view.
 */
public abstract class AbstractGameOverView extends JFrame {

    private static final long serialVersionUID = 1L;
    private static final Logger LOGGER = Logger.getLogger(AbstractGameOverView.class.getName());
    private static final int FRAME_WIDTH = 800;
    private static final int FRAME_HEIGHT = 600;
    private static final int MARGIN = 20;
    private final String messageText;
    private final String backgroundImgPath;


    /**
     * Constructor for the AbstractGameOverView class.
     * 
     * @param messageText the message to display
     * @param backgroundImgPath the path to the background image
     */
    public AbstractGameOverView(final String messageText, final String backgroundImgPath) {
        super("Game Over");
        this.messageText = messageText;
        this.backgroundImgPath = backgroundImgPath;
    }

    /**
     * Initializes the game over view.
     */
    public final void initialize() {
        setUI(messageText, backgroundImgPath);
        setPostInitialize();
    }

    /**
     * Sets up the main user interface, including the background and layout.
     *
     * @param messageText        the message to display
     * @param backgroundImgPath  the path to the background image
     */
    private void setUI(final String messageText, final String backgroundImgPath) {
        final JPanel backgroundPanel = new JPanel() {
            private static final long serialVersionUID = 1L;
            private final Image backgroundImage = loadBackgroundImage(backgroundImgPath);

            @Override
            protected void paintComponent(final Graphics g) {
                super.paintComponent(g);
                if (backgroundImage != null) {
                    g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
                }
            }
        };

        backgroundPanel.setLayout(new BorderLayout());
        this.setContentPane(backgroundPanel);

        final JPanel overlayPanel = new JPanel(new BorderLayout());
        overlayPanel.setOpaque(false);
        overlayPanel.setBackground(new Color(0, 0, 0, 100));

        final JLabel messageLabel = createMessageLabel(messageText);
        overlayPanel.add(messageLabel, BorderLayout.CENTER);

        final JButton mainMenuButton = createMainMenuButton();
        overlayPanel.add(mainMenuButton, BorderLayout.SOUTH);

        backgroundPanel.add(overlayPanel, BorderLayout.CENTER);

        backgroundPanel.revalidate();
        backgroundPanel.repaint();
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
     * Creates a JLabel for the game over message.
     *
     * @param messageText the message to display
     * @return the JLabel with the configured message
     */
    private JLabel createMessageLabel(final String messageText) {
        final JLabel message = new JLabel(messageText, JLabel.CENTER);
        final Font customFont = LoadPixelFont.getFont().deriveFont(24f);
        message.setFont(customFont);
        message.setForeground(Color.WHITE);
        return message;
    }

    /**
     * Creates a JButton for returning to the main menu.
     *
     * @return the JButton for the main menu
     */
    private JButton createMainMenuButton() {
        final JButton mainMenuButton = new JButton("Return to Main Menu");
        final Font customFont = LoadPixelFont.getFont().deriveFont(20f);
        mainMenuButton.setFont(customFont);
        mainMenuButton.setMargin(new Insets(10, MARGIN, 10, MARGIN));
        mainMenuButton.addActionListener(e -> {
            closeAllWindows();
            The100DaysWar.CONTROLLER.startGame();
            dispose();
        });
        return mainMenuButton;
    }

    /**
     * Loads the background image from the specified path.
     *
     * @param path the path to the background image
     * @return the loaded image, or null if loading fails
     */
    private Image loadBackgroundImage(final String path) {
        try {
            final URL imageUrl = AbstractGameOverView.class.getResource(path);
            if (imageUrl == null) {
                LOGGER.log(Level.WARNING, "Background image not found at path: {0}", path);
                return null;
            }
            return ImageIO.read(imageUrl);
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Error loading background image", e);
            return null;
        }
    }

    /**
     * Closes all visible windows.
     */
    protected void closeAllWindows() {
        for (final Window window : getWindows()) {
            if (window.isVisible()) {
                window.dispose();
            }
        }
    }
}
