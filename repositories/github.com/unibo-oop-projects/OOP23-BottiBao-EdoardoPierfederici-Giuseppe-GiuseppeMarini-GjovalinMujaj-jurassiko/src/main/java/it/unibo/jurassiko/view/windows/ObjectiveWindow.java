package it.unibo.jurassiko.view.windows;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.unibo.jurassiko.controller.api.MainController;
import it.unibo.jurassiko.view.gamescreen.impl.ViewImpl;

/**
 * Represents a popup window used to show the objective card to the player.
 */
public class ObjectiveWindow extends JPanel {

    private static final long serialVersionUID = -8078185574894759437L;
    private static final String IMAGE_PATH = "images/objectivecard.png";
    private static final double WIDTH_RATIO = 0.204;
    private static final double HEIGHT_RATIO = 0.456;
    private static final double TEXT_XRATIO = 0.09;
    private static final double TEXT_YRATIO = 0.785;
    private static final double TEXT_WIDTH_RATIO = 0.83;
    private static final double TEXT_HEIGHT_RATIO = 0.165;
    private static final String TEXT_FONT = "Arial";
    private static final int TEXT_SIZE = 15;

    private final JLabel textLabel;
    private final transient MainController controller;

    /**
     * Creates the objective window initializing the card and the text for the
     * description.
     * 
     * @param controller the main controller instance
     */
    @SuppressFBWarnings(value = "EI_EXPOSE_REP2", justification = "MainController instance is needed on this class by design")
    public ObjectiveWindow(final MainController controller) {
        this.controller = controller;
        final int windowWidth = (int) (WIDTH_RATIO * ViewImpl.getScreenSize().getWidth());
        final int windowHeight = (int) (HEIGHT_RATIO * ViewImpl.getScreenSize().getHeight());
        final ImageIcon objectiveCard = loadCardImage(windowWidth, windowHeight);

        final int cardWidth = objectiveCard.getIconWidth();
        final int cardHeight = objectiveCard.getIconHeight();
        final int textX = (int) (cardWidth * TEXT_XRATIO);
        final int textY = (int) (cardHeight * TEXT_YRATIO);
        final int textWidth = (int) (cardWidth * TEXT_WIDTH_RATIO);
        final int textHeight = (int) (cardHeight * TEXT_HEIGHT_RATIO);

        final JLabel objectiveLabel = new JLabel(objectiveCard);
        setObjectiveLabel(objectiveLabel, windowWidth, windowHeight);
        // Make text go to new line when needed
        this.textLabel = new JLabel();
        setTextLabel(this.textLabel, textX, textY, textWidth, textHeight);

        final JLayeredPane layPane = new JLayeredPane();
        layPane.add(objectiveLabel, JLayeredPane.DEFAULT_LAYER);
        layPane.add(textLabel, JLayeredPane.PALETTE_LAYER);
        layPane.setPreferredSize(new Dimension(windowWidth, windowHeight));

        this.setLayout(new BorderLayout());
        this.add(layPane, BorderLayout.CENTER); // Centers the card
    }

    /**
     * Displays the objective card as a JOptionPane.
     */
    public void showObjectiveCard() {
        JOptionPane.showMessageDialog(null, this, "Obiettivo", JOptionPane.PLAIN_MESSAGE);
    }

    /**
     * Update the Objective based on the turn of the Player.
     */
    public void updateObjective() {
        setDescription(this.controller.getCurrentPlayer().getObjective().getDescription());
    }

    /**
     * Sets the description to put over the card.
     * 
     * @param description the description of the objective
     */
    private void setDescription(final String description) {
        this.textLabel.setText("<html>" + description + "</html>");
    }

    /**
     * Loads and scales the card image according to window size.
     * 
     * @param width  the x-axis dimension of the window
     * @param height the y-axis dimension of the window
     * @return the scaled image of the card
     */
    private ImageIcon loadCardImage(final int width, final int height) {
        BufferedImage objectiveCardImage;
        try {
            objectiveCardImage = ImageIO.read(ClassLoader.getSystemResource(IMAGE_PATH));
        } catch (final IOException e) {
            throw new IllegalStateException("Failed to read objective card image", e);
        }

        return ViewImpl.scaleImage(objectiveCardImage, width, height);
    }

    /**
     * Sets layout and dimension of the label containing the card image.
     * 
     * @param objectiveLabel the card label
     * @param width          the x-axis dimension of the window
     * @param height         the y-axis dimension of the window
     */
    private void setObjectiveLabel(final JLabel objectiveLabel, final int width, final int height) {
        objectiveLabel.setOpaque(true);
        objectiveLabel.setBounds(0, 0, width, height);
    }

    /**
     * Sets text style, layout and dimension of the label containing the description
     * of the objective.
     * 
     * @param textLabel the description label
     * @param x         the x-axis position to set in proportion to the window
     * @param y         the y-axis position to set in proportion to the window
     * @param width     the x-axis dimension to set
     * @param height    the y-axis dimension to set
     */
    private void setTextLabel(final JLabel textLabel, final int x, final int y, final int width, final int height) {
        textLabel.setFont(new Font(TEXT_FONT, Font.BOLD, TEXT_SIZE));
        textLabel.setHorizontalAlignment(JLabel.CENTER);
        textLabel.setOpaque(false);
        textLabel.setBounds(x, y, width, height);
    }

}
