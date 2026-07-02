package it.unibo.monopoli.view.cards;

import java.awt.Color;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JPanel;

import it.unibo.monopoli.model.mainunits.Player;
import it.unibo.monopoli.model.table.Box;
import it.unibo.monopoli.view.C;
import it.unibo.monopoli.view.JShape;

/**
 *
 *
 */
public abstract class AbstractGraphicCard implements IBoxGraphic {
    private final Map<Player, JShape> pawns;
    private final Position pos;
    private final Box card;
    /**
     * panel in which I manage the checkers, the reference to this panel Here
     * because it is in this class that I manage the pieces.
     */
    protected JPanel emptyP;

    /**
     * Builder.
     * 
     * @param pos
     *            id card
     * 
     * @param card
     *            card
     */
    public AbstractGraphicCard(final Box card, final Position pos) {
        this.pos = pos;
        pawns = new HashMap<Player, JShape>();
        emptyP = new JPanel();
        this.card = card;
    }

    /**
     * 
     */
    @Override
    public Position getPosition() {
        return pos;
    }

    @Override
    public void addPawn(final Player p) {
        int id = p.getPawn().getID();
        Color c = C.CL.get(id);
        JShape pawn = new JShape(c);
        pawns.put(p, pawn);
        emptyP.add(pawn);
        emptyP.validate();

    }

    @Override
    public void removePawn(final Player p) {
        JShape pawn = pawns.get(p);
        pawn.setVisible(false);
        emptyP.remove(pawn);
        pawns.remove(p);
        emptyP.validate();
    }

    @Override
    public String getName() {
        return card.getName();
    }

    @Override
    public int getID() {
        return card.getID();
    }
}
