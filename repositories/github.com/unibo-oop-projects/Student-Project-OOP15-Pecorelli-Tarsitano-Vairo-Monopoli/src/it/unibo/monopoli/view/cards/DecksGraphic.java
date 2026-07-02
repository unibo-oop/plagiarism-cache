package it.unibo.monopoli.view.cards;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

import it.unibo.monopoli.model.table.DecksBox;
import it.unibo.monopoli.view.C;

/**
 * 
 * class that is the graphic implementation of the DecksBox.
 *
 */
public class DecksGraphic extends AbstractGraphicCard {

    private Border border = BorderFactory.createLineBorder(Color.BLACK, 2);
    private final DecksBox deck;

    /**
     * Builder.
     * 
     * @param deck
     *            card
     * @param pos
     *            card pos
     * @param id
     *            card id
     */
    public DecksGraphic(final DecksBox deck, final Position pos, final int id) {
        super(deck, pos);
        this.deck = deck;
    }

    @Override
    public JPanel build() {
        final JPanel card = new JPanel();
        card.setPreferredSize(new Dimension(C.DIM));
        card.setLayout(new GridLayout(2, 1));

        final JLabel nameP = new JLabel("<html>" + deck.getName() + "</html>");
        card.add(nameP);

        card.add(emptyP);

        card.setBorder(border);
        card.setVisible(true);
        return card;

    }

}
