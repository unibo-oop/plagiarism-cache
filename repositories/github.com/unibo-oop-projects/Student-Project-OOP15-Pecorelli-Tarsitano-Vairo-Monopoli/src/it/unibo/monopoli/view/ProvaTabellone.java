package it.unibo.monopoli.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.HashMap;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import it.unibo.monopoli.controller.Controller;
import it.unibo.monopoli.model.table.Box;
import it.unibo.monopoli.model.table.DecksBox;
import it.unibo.monopoli.model.table.Land;
import it.unibo.monopoli.model.table.Ownership;
import it.unibo.monopoli.model.table.TaxImpl;
import it.unibo.monopoli.view.cards.BoxGraphic;
import it.unibo.monopoli.view.cards.DecksGraphic;
import it.unibo.monopoli.view.cards.IBoxGraphic;
import it.unibo.monopoli.view.cards.IBoxGraphic.Position;
import it.unibo.monopoli.view.cards.LandGraphic;
import it.unibo.monopoli.view.cards.OwnershipGraphic;
import it.unibo.monopoli.view.cards.TaxGraphic;

/**
 * 
 * class that creates and initializes the board.
 * 
 *
 */
public class ProvaTabellone {

    private final Controller controller;
    private List<Box> cards;
    private HashMap<Integer, IBoxGraphic> cardsGraphic;
    private int col, row;

    /**
     * builder.
     * 
     * @param col
     * int
     * @param row
     * int
     * @param controller
     * Controller
     */
    public ProvaTabellone(final int col, final int row, final Controller controller) {
        BorderFactory.createLineBorder(Color.BLACK, 2);
        this.col = col;
        this.row = row;
        this.controller = controller;
        this.cards = this.controller.getAllBoxes();
        this.cardsGraphic = new HashMap<Integer, IBoxGraphic>();
    }

    /**
     * Inizialized Table.
     * 
     * @wbp.parser.entryPoint. @return JPanel
     */
    public JPanel initialize() {
        final JPanel panel = new JPanel();
        final JPanel panelM = new JPanel();
        panelM.setLayout(new BorderLayout());
        panelM.add(panel, BorderLayout.CENTER);
        final GridBagLayout gblPanel = new GridBagLayout();

        gblPanel.columnWidths = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
        gblPanel.rowHeights = new int[] { 36, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
        gblPanel.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0 };
        gblPanel.rowWeights = new double[] { 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0,
                Double.MIN_VALUE };

        panel.setLayout(gblPanel);

        if (cards != null) {
            cards.forEach(card -> {
                int c = -1, r = -1, id = 0;
                Position pos = null;
                if (card.getID() < col) {
                    id = card.getID();
                    r = row - 1;
                    c = col - 1 - card.getID();
                    pos = Position.SOUTH;
                } else if (card.getID() < (col + row) - 1) {
                    id = card.getID();
                    c = 0;
                    r = row - 2 - card.getID() - (-row);
                    pos = Position.WEST;
                } else if (card.getID() < (col * 2 + row) - 2) {
                    id = card.getID();
                    r = 0;
                    c = /* col - 1 - card.getID() - (col + row); */ (card.getID() - 20);
                    pos = Position.NORTH;
                } else {
                    id = card.getID();
                    c = col - 1;
                    r = /* row - 1 - card.getID() - (col * 2 + row); */(card.getID() - 30);
                    pos = Position.EAST;

                }

                IBoxGraphic newcard;

                if (card instanceof Land) {
                    newcard = new LandGraphic((Land) card, pos, id);
                } else if (card instanceof Ownership) {
                    newcard = new OwnershipGraphic((Ownership) card, pos, id);
                } else if (card instanceof TaxImpl) {
                    newcard = new TaxGraphic((TaxImpl) card, pos, id);
                } else if (card instanceof DecksBox) {
                    newcard = new DecksGraphic((DecksBox) card, pos, id);
                } else {
                    newcard = new BoxGraphic(card, pos, id);
                    if (id == 0) {
                        controller.getPlayers().forEach(p -> newcard.addPawn(p));

                    }

                }
                GridBagConstraints gbc = new GridBagConstraints();
                gbc.insets = new Insets(0, 0, 0, 0);
                gbc.gridx = c;
                gbc.gridy = r;
                panel.add(newcard.build(), gbc);
                cardsGraphic.put(newcard.getID(), newcard);

            });

        }

        panelM.add(panel, BorderLayout.CENTER);
        panelM.setVisible(true);

        return panel;
    }

    /**
     * return the card map.
     * 
     * @return Map
     */
    public HashMap<Integer, IBoxGraphic> getCardsGraphic() {
        return cardsGraphic;
    }

}
