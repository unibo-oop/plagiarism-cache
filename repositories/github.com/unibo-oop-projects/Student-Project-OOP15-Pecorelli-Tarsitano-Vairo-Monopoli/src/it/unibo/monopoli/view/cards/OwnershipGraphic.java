package it.unibo.monopoli.view.cards;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

import it.unibo.monopoli.model.table.Ownership;
import it.unibo.monopoli.view.C;

/**
 * 
 * class that is the graphic implementation of the OwnershipBox.
 *
 */
public class OwnershipGraphic extends AbstractGraphicCard {

    private Border border = BorderFactory.createLineBorder(Color.BLACK, 2);
    private Ownership own;

    /**
     * builder.
     * 
     * @param own
     *            card
     * @param pos
     *            card pos
     * @param id
     *            card id
     */
    public OwnershipGraphic(final Ownership own, final Position pos, final int id) {
        super(own, pos);
        this.own = own;

    }

    @Override
    public JPanel build() {
        JPanel card = new JPanel();
        card.setPreferredSize(new Dimension(C.DIM));
        card.setLayout(new GridLayout(4, 1));

        JLabel nameP = new JLabel("<html>" + own.getName() + "</html>");
        card.add(nameP);

        card.add(emptyP);

        int i = this.own.getContract().getCost();
        JLabel valueP = new JLabel("" + i);
        card.add(valueP);

        card.setBorder(border);
        card.setVisible(true);

        return card;

    }

}
