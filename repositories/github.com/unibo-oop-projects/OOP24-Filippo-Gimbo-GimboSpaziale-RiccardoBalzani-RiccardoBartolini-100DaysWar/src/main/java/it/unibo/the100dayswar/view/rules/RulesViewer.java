package it.unibo.the100dayswar.view.rules;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JFrame;

import it.unibo.the100dayswar.commons.utilities.impl.LoadPixelFont;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Class that represents the Rules Viewer that shows the rules for the game.
 */
public class RulesViewer extends JFrame {
    private static final long serialVersionUID = 1L;
    private static final int DEFAULT_WIDTH = 500;
    private static final int DEFAULT_HEIGHT = 600;
    private static final String PATH_STRING = "rules/rules.txt";
    private static final String BACKGROUND_PATH = "rules/rulesBackground.jpg";
    private static final int OVERLAY_OPACITY = 150;
    private static final float INITIAL_FONT_SIZE = 14f;
    private static final int FONT_SCALING_FACTOR = 40;

    private static final Logger LOGGER = Logger.getLogger(RulesViewer.class.getName());

    private JTextArea textArea;

    /**
     * Constructor for the RulesViewer.
     */
    public RulesViewer() {
        super("Rules Viewer");
    }

    /**
     * Initializes the class.
     */
    public final void intitialize() {
        setUI();
        setPostInitialize();
    }

    /**
     * Final initialization step for frame configuration.
     */
    private void setPostInitialize() {
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
    }

    /**
     * This method creates the UI for the RulesViewer frame.
     */
    private void setUI() {
        final JPanel panel = new JPanel() {
            private final Image backgroundImage;

            {
                backgroundImage = loadBackgroundImage();
            }

            @Override
            protected void paintComponent(final Graphics g) {
                super.paintComponent(g);
                if (backgroundImage != null) {
                    g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
                }
                final Graphics2D g2d = (Graphics2D) g.create();
                g2d.setColor(new Color(0, 0, 0, OVERLAY_OPACITY));
                g2d.fillRect(0, 0, getWidth(), getHeight());
                g2d.dispose();
            }
        };
        panel.setLayout(new BorderLayout());

        textArea = new JTextArea();
        textArea.setEditable(false);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setMargin(new Insets(10, 10, 10, 10));
        textArea.setFont(LoadPixelFont.getFont().deriveFont(INITIAL_FONT_SIZE));
        textArea.setForeground(Color.WHITE);
        textArea.setOpaque(false);

        final JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);

        panel.add(scrollPane, BorderLayout.CENTER);
        this.add(panel);

        loadRulesText();

        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(final ComponentEvent e) {
                adjustFontSize();
            }
        });
    }

    /**
     * Loads the rules text from the file.
     */
    private void loadRulesText() {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(
            ClassLoader.getSystemResourceAsStream(PATH_STRING), StandardCharsets.UTF_8))) {
            final StringBuilder content = new StringBuilder();
            reader.lines().forEach(line -> content.append(line).append('\n'));
            textArea.setText(content.toString());
        } catch (IOException e) {
            textArea.setText("Error loading the file.");
            LOGGER.log(Level.SEVERE, "Failed to load rules text", e);
        }
    }

    /**
     * Loads the background image from the specified path.
     *
     * @return the loaded image, or null if loading fails
     */
    private Image loadBackgroundImage() {
        final String imagePath = BACKGROUND_PATH;
        final var imageUrl = ClassLoader.getSystemResource(imagePath);

        if (imageUrl == null) {
            LOGGER.log(Level.SEVERE, "Background image not found in the specified path: {0}", imagePath);
            return null;
        }

        return Toolkit.getDefaultToolkit().getImage(imageUrl);
    }

    /**
     * Adjusts the font size dynamically based on the frame size.
     */
    private void adjustFontSize() {
        final int frameWidth = getWidth();
        final int frameHeight = getHeight();
        final int newFontSize = Math.max(Math.min(frameWidth, frameHeight) / FONT_SCALING_FACTOR, 10);
        final Font customFont = LoadPixelFont.getFont();
        if (customFont != null) {
            textArea.setFont(customFont.deriveFont((float) newFontSize));
        }
    }
}
