package it.unibo.monopoli.view.cards;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

import it.unibo.monopoli.model.table.Box;
import it.unibo.monopoli.view.C;

/**
 * 
 * class that is the graphic implementation of the CornerBox.
 *
 */
public class BoxGraphic extends AbstractGraphicCard {

    private Border border = BorderFactory.createLineBorder(Color.BLACK, 2);
    private Box box;

    /**
     * Builder.
     * 
     * @param box
     *            card
     * @param pos
     *            card pos
     * @param id
     *            card id
     */
    public BoxGraphic(final Box box, final Position pos, final int id) {
        super(box, pos);
        this.box = box;

    }

    @Override
    public JPanel build() {
        JPanel card = new JPanel();
        card.setPreferredSize(C.DIM);
        card.setLayout(new GridLayout(2, 1));

        JLabel nameP = new JLabel("<html>" + box.getName() + "</html>");
        card.add(nameP);

        card.add(emptyP);

        card.setBorder(border);
        card.setVisible(true);

        return card;

    }

}
