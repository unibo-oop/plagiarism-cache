package it.unibo.jurassiko.view.panels;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import it.unibo.jurassiko.controller.impl.MenuContollerImpl;
import it.unibo.jurassiko.view.gamescreen.impl.StartMenu;
import it.unibo.jurassiko.view.gamescreen.impl.ViewImpl;

/**
 * JPanel used for the StartMenu frame.
 */
public class MenuPanel extends JPanel {

    private final Logger logger = LoggerFactory.getLogger(MenuPanel.class);

    private static final long serialVersionUID = 1L;

    private static final String START = "Start";
    private static final String QUIT = "Quit";
    private static final String RULE = "Rules";
    private static final String FONT_STYLE = "Serif";
    private static final String NEW_LINE = "\n";
    private static final double WIDTH_PERC = 0.5;
    private static final double HEIGHT_PERC = 0.5;
    private static final double BUTTON_WIDTH_PERC = WIDTH_PERC * 0.2;
    private static final double BUTTON_HEIGHT_PERC = HEIGHT_PERC * 0.1;
    private static final int FONT_SIZE = 24;

    private static final String URL_IMAGE = "images/menu.png";
    private static final String RULES_PATH = "rules/HowToPlay.txt";

    private final Dimension dimension;

    /**
     * The Menu Panel, will show the start, quit and rules buttons.
     * 
     * @param controller controller for the Menu
     * @param frame      frame for the Menu
     */
    public MenuPanel(final MenuContollerImpl controller, final StartMenu frame) {
        final BufferedImage image;
        this.dimension = ViewImpl.getScreenSize();
        this.setPreferredSize(new Dimension(Double.valueOf(dimension.getWidth() * WIDTH_PERC).intValue(),
                Double.valueOf(dimension.getHeight() * HEIGHT_PERC).intValue()));

        try {
            image = ImageIO.read(ClassLoader.getSystemResource(URL_IMAGE));
        } catch (final IOException e) {
            throw new IllegalStateException("Failed to read the menu Background image", e);
        }
        final ImageIcon bgImage = new ImageIcon(fixImageSize(image, dimension.getWidth(), dimension.getHeight()));
        final JLabel bgLabel = new JLabel(bgImage);
        bgLabel.setBounds(0, 0, bgImage.getIconWidth(), bgImage.getIconHeight());
        bgLabel.setOpaque(false);

        final JButton start = createButton(START, getButtonDimension());
        final JButton quit = createButton(QUIT, getButtonDimension());
        final JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridBagLayout());
        final GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 10, 10);

        start.addActionListener(e -> {
            frame.dispose();
            controller.startGame();
        });

        quit.addActionListener(e -> {
            final String[] options = { "Yes", "No" };
            final var result = JOptionPane.showOptionDialog(this, "Do you want to QUIT the game?",
                    QUIT,
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.WARNING_MESSAGE,
                    null, options, 0);
            if (result == 0) {
                frame.dispose();
            }
        });

        final StringBuilder tempBuilder = new StringBuilder();
        try (InputStream in = ClassLoader.getSystemResourceAsStream(RULES_PATH);
                BufferedReader br = new BufferedReader(new InputStreamReader(in, "UTF-8"))) {
            br.lines().forEach(t -> {
                tempBuilder.append(t);
                tempBuilder.append(NEW_LINE);
            });
        } catch (final IOException e) {
            this.logger.error("Cannot read the text file for game rules");
            throw new IllegalStateException("Failed to read the menu rules file", e);
        }
        final JButton rules = createButton(RULE, getButtonDimension());
        rules.addActionListener(e -> JOptionPane.showMessageDialog(this, tempBuilder.toString()));
        addButton(buttonPanel, start, gbc);
        addButton(buttonPanel, quit, gbc);
        addButton(buttonPanel, rules, gbc);

        buttonPanel.setBounds(0, 0, bgImage.getIconWidth(), bgImage.getIconHeight());
        buttonPanel.setOpaque(false);

        final JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.add(bgLabel, Integer.valueOf(0));
        layeredPane.setPreferredSize(new Dimension(bgImage.getIconWidth(), bgImage.getIconHeight()));
        layeredPane.add(buttonPanel, Integer.valueOf(1));

        this.add(layeredPane);
    }

    /**
     * Create a JButton.
     * 
     * @param name name of the button
     * @param dim  dimension of the button
     * @return a JButton
     */
    private JButton createButton(final String name, final Dimension dim) {
        final JButton button = new JButton(name);
        button.setPreferredSize(dim);
        button.setFont(new Font(FONT_STYLE, Font.BOLD, FONT_SIZE));
        return button;
    }

    private Dimension getButtonDimension() {
        return new Dimension(Double.valueOf(dimension.getWidth() * BUTTON_WIDTH_PERC).intValue(),
                Double.valueOf(dimension.getHeight() * BUTTON_HEIGHT_PERC).intValue());
    }

    private void addButton(final JPanel panel, final JButton jb, final GridBagConstraints gbc) {
        panel.add(jb, gbc);
        gbc.gridy++;
    }

    private Image fixImageSize(final BufferedImage image, final double width, final double height) {
        final var temp = new ImageIcon(image);
        return temp.getImage().getScaledInstance(Double.valueOf(width * WIDTH_PERC).intValue(),
                Double.valueOf(height * HEIGHT_PERC).intValue(), Image.SCALE_SMOOTH);
    }
}
