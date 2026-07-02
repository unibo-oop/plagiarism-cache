package it.unibo.monopoli.view.cards;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

import it.unibo.monopoli.model.table.Tax;
import it.unibo.monopoli.view.C;
import it.unibo.monopoli.view.JShape.Shapes;
import it.unibo.monopoli.view.cards.IBoxGraphic.Position;

/**
 * 
 * class that is the graphic implementation of the TextBox.
 *
 */
public class TaxGraphic extends AbstractGraphicCard {

    private Border border = BorderFactory.createLineBorder(Color.BLACK, 2);
    private Tax tax;

    /**
     * builder.
     * 
     * @param tax
     *            card
     * @param pos
     *            card pos
     * @param id
     *            card id
     */
    public TaxGraphic(final Tax tax, final Position pos, final int id) {
        super(tax, pos);
        this.tax = tax;

    }

    @Override
    public JPanel build() {
        final JPanel card = new JPanel();
        card.setPreferredSize(new Dimension(C.DIM));
        card.setLayout(new GridLayout(4, 1));

        JLabel nameP = new JLabel("<html>" + tax.getName() + "</html>");
        card.add(nameP);

        card.add(emptyP);

        JLabel valueP = new JLabel("" + tax.getCost());
        card.add(valueP);

        card.setBorder(border);
        card.setVisible(true);

        return card;

    }

}
