package it.unibo.monopoli.view.cards;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.LinkedList;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

import it.unibo.monopoli.model.mainunits.Player;
import it.unibo.monopoli.model.table.Land;
import it.unibo.monopoli.view.C;
import it.unibo.monopoli.view.JShape;

/**
 * 
 * class that is the graphic implementation of the LandtBox.
 *
 */
public class LandGraphic extends AbstractGraphicCard {

    private Border border = BorderFactory.createLineBorder(Color.BLACK, 2);
    private Land land;
    private JPanel colorP;
    private LinkedList<JShape> houses = new LinkedList<>();

    /**
     * Builder.
     * 
     * @param land
     * 
     *            card representing the land
     * @param pos
     *            position in the table
     * @param id
     *            card id that rappresent the position in the table
     */
    public LandGraphic(final Land land, final Position pos, final int id) {
        super(land, pos);
        this.land = land;
        colorP = new JPanel();
    }

    @Override
    public JPanel build() {
        final JPanel card = new JPanel();
        card.setPreferredSize(new Dimension(C.DIM));
        card.setLayout(new GridLayout(4, 1));

        card.add(colorP);
        colorP.setOpaque(true);
        colorP.setBackground(land.getColor());
        final JLabel nameP = new JLabel("<html>" + land.getName() + "</html>");
        nameP.setFont(new Font("Times New Roman", Font.BOLD, 10));
        card.add(nameP);

        card.add(emptyP);

        JLabel valueP = new JLabel("" + land.getContract().getCost());
        card.add(valueP);

        card.setBorder(border);
        card.setVisible(true);

        card.validate();
        return card;

    }

    /**
     * method that adds the marker of a given in the corresponding paper.
     * 
     * @param p
     *            Player
     */
    public void addHouse(final Player p) {
        final int id = p.getPawn().getID();
        final Color c = C.CL.get(id);
        final JShape house = new JShape(c);

        houses.add(house);
        colorP.add(house);
        colorP.validate();
    }

    /**
     * method that removes the marker of a given in the corresponding paper.
     * 
     * @param p
     *            Player
     */
    public void removeHouse(final Player p) {
        final JShape h = houses.getLast();
        colorP.remove(h);
        h.setVisible(false);
        houses.remove(h);
        colorP.doLayout();
        colorP.repaint();
    }
}
