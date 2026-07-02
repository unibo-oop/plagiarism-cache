package it.unibo.briscoola.view.impl;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.net.URL;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import it.unibo.briscoola.view.api.CardView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Implementation of the {@link CardView} representing a single card in the user interface.
 * It extends {@link JPanel} to be easily integrated into a Swing app.
 * 
 * @author Riem Boukhama
 * @author Maisam Noumi
 */
public final class CardViewImpl extends JPanel implements CardView {

    private static final long serialVersionUID = 1L;

    private static final int CARD_WIDTH = 100;
    private static final int CARD_HEIGHT = 150;

    private static final int BG_R = 100;
    private static final int BG_G = 149;
    private static final int BG_B = 237;

    private final JButton cardButton;
    private final transient Logger logger = LoggerFactory.getLogger(CardViewImpl.class);

    /**
     * Constructs a new CardViewImpl, initializing its layout, dimensions and borders.
     */
    public CardViewImpl() {
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(CARD_WIDTH, CARD_HEIGHT));
        setBackground(Color.WHITE);
        setOpaque(false);
        setBorder(BorderFactory.createLineBorder(Color.BLACK, 1, true));

        cardButton = new JButton();
        cardButton.setHorizontalAlignment(SwingConstants.CENTER);
        cardButton.setVerticalAlignment(SwingConstants.CENTER);

        add(cardButton, BorderLayout.CENTER);
    }

    @Override
    public void renderCard(final String seed, final String value) {

        if (seed == null || value == null) {
            final URL backUrl = getClass().getResource("/cards/backside.png");

            if (backUrl != null) {
                final ImageIcon icon = new ImageIcon(backUrl);
                final Image img = icon.getImage().getScaledInstance(
                        this.getPreferredSize().width, 
                        this.getPreferredSize().height, 
                        Image.SCALE_SMOOTH);
                cardButton.setIcon(new ImageIcon(img));
                cardButton.setText(""); 
                setBackground(Color.WHITE);
            } else {
                cardButton.setIcon(null);
                cardButton.setText("RETRO");
                setBackground(new Color(BG_R, BG_G, BG_B)); 
            }

            revalidate();
            repaint();
            return;
        }

        final String filename = seed + "_" + value + ".png";
        final URL imgUrl = getClass().getResource("/cards/" + filename);

        if (imgUrl != null) {
            final ImageIcon icon = new ImageIcon(imgUrl);
            final Image img = icon.getImage().getScaledInstance(
                    this.getPreferredSize().width, 
                    this.getPreferredSize().height, 
                    Image.SCALE_SMOOTH);
            cardButton.setIcon(new ImageIcon(img));
            cardButton.setText("");
            setBackground(Color.WHITE);
        } else {
            cardButton.setIcon(null);
            cardButton.setText(value + " of " + seed);
            setBackground(Color.LIGHT_GRAY);
            logger.error("(CardView): Immagine non trovata -> /cards/{}", filename);
        }

        revalidate();
        repaint();
    }

    @Override
    public void addCardClickListener(final ActionListener listener) {
        cardButton.addActionListener(listener);
    }
}
