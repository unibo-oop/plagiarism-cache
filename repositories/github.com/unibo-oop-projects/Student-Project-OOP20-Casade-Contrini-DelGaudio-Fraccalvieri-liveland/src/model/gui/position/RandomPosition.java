package model.gui.position;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.Map;
import java.util.Random;

import model.person.ticket.PersonTicket;

/**
 * 
 * Class that assigns a different position for each person.
 *
 */
public class RandomPosition {

    private int randX;
    private int randY;
    private static final Dimension Screen = Toolkit.getDefaultToolkit().getScreenSize();
    private static final int WIDTH = (int) Screen.width;
    private static final int HEIGHT = (int) Screen.height;
    private Position<Integer, Integer> newPos;

    public final Position<Integer, Integer> randomPosition(final Map<PersonTicket, Position<Integer, Integer>> map) {
        final Random rand = new Random();
        do {
            randX = rand.nextInt(WIDTH);
            randY = rand.nextInt(HEIGHT);
            this.newPos = new Position<>(this.randX, this.randY);
        } while (map.containsValue(this.newPos));
        return this.newPos;
    }

}
