package it.unibo.monopoli.view.cards;

import javax.swing.JPanel;

import it.unibo.monopoli.model.mainunits.Player;
import it.unibo.monopoli.model.table.Box;

public interface IBoxGraphic extends Box {

    public enum Position {
        SOUTH, WEST, NORTH, EAST
    }

    JPanel build();

    Position getPosition();

    void addPawn(Player p);

    void removePawn(Player p);
}
