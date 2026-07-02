package it.unibo.briscoola.view.impl;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.io.Serial;
import java.net.URL;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 * represents the view of the deck of cards taken by the player.
 * 
 * @author Andrea Reggiani
 */
public final class PileView extends JPanel {

    @Serial
    private static final long serialVersionUID = 1L;

    private static final int PANEL_WIDTH = 150;
    private static final int PANEL_HEIGHT = 100;
    private static final int CARD_BACK_WIDTH = 75;
    private static final int CARD_BACK_HEIGHT = 110;

    private static final int FALLBACK_R = 100;
    private static final int FALLBACK_G = 149;
    private static final int FALLBACK_B = 237;

    private static final int MIN_CARDS_VISIBLE = 0;

    private final JLabel labelForCount = new JLabel("0");

    private final JLabel imageLabel = new JLabel();

    /**
     * builds a specific panel for displaying the cards.
     * 
     * @param ownerName The name of the deck owner : "CPU" or "Player".
     */
    public PileView(final String ownerName) {
        super();
        this.initializeLayout(ownerName);
    }

    /**
     * private to configure components securely.
     * 
     * @param ownerName name of the player
     */
    private void initializeLayout(final String ownerName) {
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createTitledBorder(ownerName + " Pile "));
        setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));

        final URL backUrl = PileView.class.getResource("/cards/backside.png");

        if (backUrl != null) {
            final ImageIcon icon = new ImageIcon(backUrl);
            final Image img = icon.getImage().getScaledInstance(CARD_BACK_WIDTH, CARD_BACK_HEIGHT, Image.SCALE_SMOOTH);
            imageLabel.setIcon(new ImageIcon(img));
        } else {
            imageLabel.setText("BACK");
            imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
            imageLabel.setOpaque(true);
            imageLabel.setBackground(new Color(FALLBACK_R, FALLBACK_G, FALLBACK_B));
            imageLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        }

        labelForCount.setHorizontalAlignment(SwingConstants.CENTER);
        labelForCount.setForeground(Color.BLACK);

        add(imageLabel, BorderLayout.CENTER); 
        add(labelForCount, BorderLayout.SOUTH);

        imageLabel.setVisible(false);
    }

    /**
     * updates the number of cards displayed
     * transform the entire "count" into a string and set it in the label.
     * 
     * @param count the new number of the cards won to show
     */
    public void updateCount(final int count) {
        this.imageLabel.setVisible(count > MIN_CARDS_VISIBLE);
        this.labelForCount.setText(String.valueOf(count));
    }
}
